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
import dao.userdao;
import entites.dataObject;
import entites.hotel;

/**
 * Servlet implementation class updateHotelRequestAdmin
 */
@WebServlet("/updateHotelRequestAdmin")
public class updateHotelRequestAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out =response.getWriter(); 
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		dataObject resObj=null;
		HttpSession session = request.getSession();
		hoteldao dao = new hoteldao(Linker.getConn());
		Object rsp = new Object();
		System.out.println("-->>");
		String type= request.getParameter("type");
		try {
			switch (type) {
			case "get":
					rsp=dao.getMyHotelList(null, 0);
				break;
			case "update":
				int hotelId= Integer.parseInt(request.getParameter("HTId"));
				int Adid= Integer.parseInt( request.getParameter("id"));
				int changeSt= Integer.parseInt(request.getParameter("ModifiedChange"));
				rsp=dao.hotelaporoval(hotelId,Adid,changeSt);
				break;
			default:
				break;
			}
			if(rsp!=null) {
				resObj=new dataObject(1,"SuccessFull","NTH","log");
			}else {
			resObj=new dataObject(1,"SuccessFull","NTH","log");
			}
			resObj.setDatapack(rsp);
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
