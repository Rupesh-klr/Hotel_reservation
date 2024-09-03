package servlets.booking;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

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
import entites.booking;
import entites.dataObject;

/**
 * Servlet implementation class bookingByuser
 */
@WebServlet("/bookingByuser")
public class bookingByuser extends HttpServlet {
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
		ArrayList<booking> bList= new ArrayList<booking>();
		System.out.println("-->>");
		try {
			bList=dao.getMyBooking( Integer.parseInt(request.getParameter("id")) );
			if(bList.size()>0)  {
				resObj=new dataObject(0,"NTH","Failed","log");
			}else {
				resObj=new dataObject(1,"Scuufully","NTH","log");
			}
			resObj.setDatapack(bList);
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
