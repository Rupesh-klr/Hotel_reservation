package servlets.booking;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
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
import entites.dataObject;
import entites.user;

@WebServlet("/bookingBYDates")
public class bookingDatesBYRoomID extends HttpServlet {
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
		ArrayList<String> get=new ArrayList();
		System.out.println("-->>");
		try {
			if( request.getParameter("RoomId")==null) {
				resObj= new dataObject(-1,"nothing","failed","log track");
				return;
			}
			get=dao.getAllBookedDateOfRoom( Integer.parseInt( request.getParameter("RoomId")) ,0 );
			if(get.size()>0) {
				resObj= new dataObject(1,"Successfull","nothing","log track"); 
			}else {
				resObj= new dataObject(0,"nothing","failed","log track"); 				
			}
				resObj.setDatapack(get);
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




/*


extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out =response.getWriter(); 
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		dataObject resObj=null;
		HttpSession session = request.getSession();
		userdao dao = new userdao(Linker.getConn());
		System.out.println("-->>");
		
		try {
			
		} catch (Exception e) {	e.printStackTrace(); System.out.println("catch at servlet");
		}finally {			
			response.setContentType("application/json");
			out.append(		gson.toJson(resObj)	   );
			out.flush();
			out.close();
		}
	}
}




	
	try {
			if(rst != null) {rst.close();}
			if(psmt != null) {psmt.close();}
			String sql = "select id,userName,userPhoneNo,userPassword,photoRef,Requested,apporvalmsg from klr.Userlist where isDelete=0	and  TypesMembership=? and userEmail=?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, u.getRole());
			
			
		} catch (Exception e) {e.printStackTrace();return "Try again later server is busy!\nconnection";
		}finally {
			try {if(rst != null) {rst.close();}
				if(psmt != null) {psmt.close();}
			} catch (Exception e2) {e2.printStackTrace();}
		}








*/