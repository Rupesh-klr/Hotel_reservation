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

/**
 * Servlet implementation class selectHotelRequest
 */
@WebServlet("/selectHotelRequest")
public class selectHotelRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out =response.getWriter(); 
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        dataObject resObj =null;
        Connection conn1=Linker.getConn();
		hotel h = new hotel();
		Object okObject=null;
		hoteldao objHoteldao = new hoteldao(conn1);
		h.setId( Integer.parseInt( request.getParameter("id")) );
		System.out.println(h.getId());
		if(request.getParameter("opration")!=null &&request.getParameter("opration").equalsIgnoreCase("statustrue")) {
			okObject=objHoteldao.getMyHotelList(h,3);
		}else {
		okObject=objHoteldao.getMyHotelList(h,1);}
		if( okObject instanceof String) {
			resObj =new dataObject(1,"successfully task completed.","nothing","data retrived to display for user");
			resObj.setDatapack( okObject );
		}else {
			resObj =new dataObject(1,"successfully task completed.","nothing","data retrived to display for user");
			resObj.setDatapack( okObject );
		}
		if(conn1!=null) {try {conn1.close();} catch (SQLException e) {e.printStackTrace();	}}
		response.setContentType("application/json");
        out.append(		gson.toJson(resObj)	   );
        out.flush();
        out.close();
	}

}



