package servlets.hotel;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import Jdbc.Linker;
import dao.hoteldao;
import entites.approval;
import entites.dataObject;
import entites.hotel;

/**
 * Servlet implementation class selectTopHotels
 */
@WebServlet("/selectTopHotels")
public class selectTopHotels extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request,response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out =response.getWriter(); 
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        dataObject resObj =new dataObject(1,"successfully task completed.","nothing","data retrived to display for user");
		hoteldao objHoteldao = new hoteldao(Linker.getConn());
		resObj.setDatapack( objHoteldao.displayHotel(3 ,9999) );
		Linker.colseConn();
        response.setContentType("application/json");
        out.append(		gson.toJson(resObj)	   );
        out.flush();
        out.close();
	}

}
