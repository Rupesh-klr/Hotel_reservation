package servlets.user;

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
import dao.userdao;
import entites.dataObject;

/**
 * Servlet implementation class updateUserRequest
 */
@WebServlet("/updateUserRequest")
public class updateUserRequest extends HttpServlet {
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
		try {//0 if key
			if(request.getParameter("opertion")!=null&&request.getParameter("opertion").equalsIgnoreCase("forgotPass") ) {
				resObj=dao.restpassword(request.getParameter("newpass"), request.getParameter("email"),request.getParameter("value1"), Integer.parseInt(request.getParameter("type")));
			}
		} catch (Exception e) {	e.printStackTrace(); System.out.println("catch at servlet");
		}finally {			
			response.setContentType("application/json");
			out.append(		gson.toJson(resObj)	   );
			out.flush();
			out.close();
		}
	}
}
