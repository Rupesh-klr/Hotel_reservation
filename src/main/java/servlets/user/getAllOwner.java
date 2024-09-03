package servlets.user;

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
import entites.dataObject;
import entites.user;
/**
 * Servlet implementation class getAllOwner
 */
@WebServlet("/getAllOwner")
public class getAllOwner extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out =response.getWriter(); 
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		dataObject resObj=null;
		HttpSession session = request.getSession();
		ArrayList< user> owners=null;
		userdao dao = new userdao(Linker.getConn());
		System.out.println("-->>");
		try {
			owners=dao.getAllcoOrdinators("admin");
			if(owners.size()>0) {
				resObj=new dataObject(1,"succufully","NTH","log track");
			}else {
				resObj=new dataObject(0,"NTH","Failed","log track");				
			}
			resObj.setDatapack(owners);
		} catch (Exception e) {	e.printStackTrace(); System.out.println("catch at servlet");
		}finally {			
			response.setContentType("application/json");
			out.append(		gson.toJson(resObj)	   );
			out.flush();
			out.close();
		}
	}
}
