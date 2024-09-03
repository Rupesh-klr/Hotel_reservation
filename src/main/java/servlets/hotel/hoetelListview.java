package servlets.hotel;

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
import dao.hoteldao;
import entites.dataObject;
import entites.hotel;

/**
 * Servlet implementation class hoetelListview
 */
@WebServlet("/hoetelListview")
public class hoetelListview extends HttpServlet {
	private static final long serialVersionUID = 1L;
      //getMyHotelList(h.setid 

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out =response.getWriter(); 
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		dataObject resObj=null;
		Object htList =null;
		HttpSession session = request.getSession();
		hoteldao dao = new hoteldao(Linker.getConn());
		try {
			
			if(request.getParameter("role")!=null && request.getParameter("role").equalsIgnoreCase("admin")) {
				hotel h=new hotel();h.setId( request.getParameter("id"));			
				htList=dao.getMyHotelList(h,4);
				resObj=new dataObject(1,"successfuly");
				resObj.setDatapack(htList);
				return;
			}else if( request.getParameter("opertion")!=null && request.getParameter("opertion").equalsIgnoreCase("allmyhotellist")) {
				hotel h=new hotel();h.setId( request.getParameter("id"));			
				htList=dao.getMyHotelList(h,5);
				resObj=new dataObject(1,"successfuly");
				resObj.setDatapack(htList);
				return;
			}else if( request.getParameter("id")!=null) {
				hotel h=new hotel();h.setId( request.getParameter("id"));			
				htList=dao.getMyHotelList(h,2);
				resObj=new dataObject(1,"successfuly");
				resObj.setDatapack(htList);
				return;
			}
		
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
