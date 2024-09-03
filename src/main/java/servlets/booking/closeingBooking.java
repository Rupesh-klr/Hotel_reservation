package servlets.booking;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import Jdbc.Linker;
import dao.bookingdao;
import dao.userdao;
import entites.dataObject;

/**
 * Servlet implementation class closeingBooking
 */
@WebServlet("/closeingBooking")
public class closeingBooking extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out =response.getWriter(); 
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		dataObject resObj=null;
		String rsp = null;
		HttpSession session = request.getSession();
		bookingdao dao = new bookingdao(Linker.getConn());
		System.out.println("-->>");		
		try {
			resObj=dao.updateBooking(Integer.parseInt(request.getParameter("BookingId")),0);
			System.out.println(gson.toJson(resObj));
		} catch (Exception e) {	e.printStackTrace(); System.out.println("catch at servlet");
		}finally {		
			Linker.colseConn();
			response.setContentType("application/json");
			out.append(		gson.toJson(resObj)	   );
			out.flush();
			out.close();
		}
	}
}
