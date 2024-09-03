package servlets.hotel;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.xml.sax.Parser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import Jdbc.Linker;
import dao.hoteldao;
import entites.dataObject;
import entites.hotel;

/**
 * Servlet implementation class selecthotelByHTId
 */
@WebServlet("/selecthotelByHTId")
public class selecthotelByHTId extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out =response.getWriter(); 
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        dataObject resObj =null;
		hotel h = new hotel();
		h.setHotelid( Integer.parseInt( request.getParameter("HTId")) );
		System.out.println(h.getHotelid());
		hoteldao objHoteldao = new hoteldao(Linker.getConn());
		h=objHoteldao.getHotelByHTId(h);
		if(h.getHotelid()==0) {
			resObj =new dataObject(1,"successfully task completed.","nothing","data retrived to display for user");
			resObj.setDatapack( h );
		}else {
			resObj =new dataObject(1,"successfully task completed.","nothing","data retrived to display for user");
			resObj.setDatapack( h );
		}
		Linker.colseConn();
		response.setContentType("application/json");
        out.append(		gson.toJson(resObj)	   );
        out.flush();
        out.close();
	}

}
