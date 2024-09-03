package servlets.hotel;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import Jdbc.Linker;
import dao.hoteldao;
import entites.dataObject;
import entites.hotel;


@WebServlet("/selectHotelSerach")
public class selectHotelSerach extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out =response.getWriter(); 
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        dataObject resObj =null;
		Object okObject=null;
		hoteldao objHoteldao = new hoteldao(Linker.getConn());
		//typessearch:"query"
		String typesSearchString=request.getParameter("typesSearch");
		if(typesSearchString==null) {
			okObject=objHoteldao.seracherWordInTable("%"+request.getParameter("SerachElement")+"%",0);
		}else {
			switch (typesSearchString) {
			case "priceasce":
				okObject=objHoteldao.seracherWordInTable("",1);
				break;
			case "pricedsce":
				okObject=objHoteldao.seracherWordInTable("",2);
				break;
			case "location":
				okObject=objHoteldao.seracherWordInTable("%"+request.getParameter("SerachElement")+"%",3);
				break;
			case "HTName":
				okObject=objHoteldao.seracherWordInTable("%"+request.getParameter("SerachElement")+"%",4);
				break;
			case "HTaddress":
				okObject=objHoteldao.seracherWordInTable("%"+request.getParameter("SerachElement")+"%",5);
				break;
			case "query":
//				System.out.println(request.getParameter("SerachElement")+" %"+request.getParameter("value")+"% "+"-->>>");
				okObject=objHoteldao.seracherWordInTable(request.getParameter("SerachElement")+" '%"+request.getParameter("value")+"%' ",6);
				break;
			case "query1":
				okObject=objHoteldao.seracherWordInTable(request.getParameter("SerachElement"),6);
				break;
			default://selectes by rating of hotels 
				okObject=objHoteldao.seracherWordInTable("",7);
				break;
			}
		}
		if( okObject instanceof String) {
			resObj =new dataObject(1,"successfully task completed.","nothing","data retrived to display for user");
			resObj.setDatapack( okObject );
		}else {
			resObj =new dataObject(1,"successfully task completed.","nothing","data retrived to display for user");
			resObj.setDatapack( okObject );
		}
		Linker.colseConn();
		response.setContentType("application/json");
        out.append(		gson.toJson(resObj)	   );
        out.flush();
        out.close();
	}

}