package servlets.room;

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
import dao.roomdao;
import dao.userdao;
import entites.dataObject;
import entites.room;

/**
 * Servlet implementation class addRoomBYHTid
 */
@WebServlet("/addRoomBYHTid")
public class addRoomBYHTid extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out =response.getWriter(); 
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		dataObject resObj=null;
		HttpSession session = request.getSession();
		roomdao dao = new roomdao(Linker.getConn());
		System.out.println("-->>");
		try {
			room r = new room();
			r.setBedCount( Integer.parseInt( request.getParameter( "rmbed")));
			r.setPrice(Integer.parseInt(request.getParameter( "price")));
			r.setRmcode(request.getParameter( "rmcode"));
			r.setRoomType(request.getParameter( "rmtype"));
			r.setCanStaycount(Integer.parseInt(request.getParameter( "canstay")));
			r.setHostelId(Integer.parseInt(request.getParameter( "HTId")));
			r.setId(Integer.parseInt(request.getParameter( "id")));
			resObj=dao.addRoom(r);
			System.out.println(r);
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
