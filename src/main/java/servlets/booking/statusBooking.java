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
 * Servlet implementation class statusBooking
 */
@WebServlet("/statusBooking")
public class statusBooking extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out =response.getWriter(); 
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		dataObject resObj=null;
		HttpSession session = request.getSession();
		bookingdao dao = new bookingdao(Linker.getConn());
		Object tempObject=null;
		System.out.println("-->>");
		if(request.getParameter("ALL")!=null && request.getParameter("ALL").equalsIgnoreCase("YES")) {
			tempObject=dao.getdetaildBooking(Integer.parseInt(request.getParameter("id")),0);
//			System.out.println("all");
		}else {//{"Bookingid":,"ALL":"NO"}
			System.out.println("Bookingid");
			tempObject=dao.getdetaildBooking(Integer.parseInt(request.getParameter("Bookingid")),1);			
		}
		resObj=new dataObject(1,"succuss","NTH","log");
		try {
			resObj.setDatapack(tempObject);
			
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
