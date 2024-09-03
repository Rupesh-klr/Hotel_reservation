package servlets.room;

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
import dao.roomdao;
import entites.dataObject;
import entites.hotel;

/**
 * Servlet implementation class selectRoomByHotelHTid
 */
@WebServlet("/selectRoomByHotelHTid")
public class selectRoomByHotelHTid extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 response.setHeader("Access-Control-Allow-Origin", "*");
	        response.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
	        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
	        response.setContentType("text/html");
		PrintWriter out =response.getWriter(); 
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        dataObject resObj =null;
		hotel h = new hotel();
		Object okObject=null;
		roomdao objHoteldao = new roomdao(Linker.getConn());
		h.setHotelid( Integer.parseInt( request.getParameter("HTId")) );
		System.out.println(h.getHotelid());
		okObject=objHoteldao.getRoomByHotelHTid(h);
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
