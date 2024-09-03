package servlets.hotel;

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
import dao.hoteldao;
import entites.dataObject;
import entites.hotel;

/**
 * Servlet implementation class editHotel
 */
@WebServlet("/editHotel")
public class editHotel extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out =response.getWriter(); 
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		dataObject resObj=null;
		HttpSession session = request.getSession();
		String rspstr = null;
		hoteldao dao = new hoteldao(Linker.getConn());
		hotel h= new hotel();
		System.out.println("-->>");
		try {
			h.setHostelName( request.getParameter("HotelName"));
			h.setHotelNumber( request.getParameter("HotelNumber"));
			h.setHotelLocation( request.getParameter("HotelLocation"));
			h.setHotelAddress( request.getParameter("HotelAddress"));
			h.setHtimage( request.getParameter("HTimage"));
			h.setRating( Integer.parseInt( request.getParameter("Rating")));
			h.setHotelid( Integer.parseInt( request.getParameter("HTId")));
			String editor=  request.getParameter("ADId");
			rspstr=dao.editHotel(h,editor);
			System.out.println(rspstr);
			if ( rspstr.equalsIgnoreCase("Updated recored!")) {
				resObj =new dataObject(1,"suussufully inserted ","nothing","nothing track data");
			} else {
				resObj =new dataObject(0,"nothing","Error raised while handling with DB.",rspstr);
			}
			resObj.setDatapack(rspstr);
		} catch (Exception e) {	e.printStackTrace(); System.out.println("catch at servlet");
		}finally {			
			out.flush();
			Linker.colseConn();
			response.setContentType("application/json");
			out.append(		gson.toJson(resObj)	   );
			out.close();
		}
	}
}
