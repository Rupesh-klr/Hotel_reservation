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
import entites.user;

/**
 * Servlet implementation class addUser
 */
@WebServlet("/addUser")
public class addUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    	doPost(req, resp);
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out =response.getWriter(); 
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		dataObject resObj =null;
		userdao dao = new userdao(Linker.getConn());
		user u = new user();
		HttpSession session = request.getSession();
		System.out.println(request.getParameter("userName"));
		if(request.getParameter("delete")!=null && request.getParameter("delete").equalsIgnoreCase("yes") ) {
			System.out.println(request.getParameter("AdmId"));
			int ad=Integer.parseInt(request.getParameter("AdmId"))
					,id=Integer.parseInt(request.getParameter("id"));
			resObj=dao.deletemember(ad,id );
			return;
		}
		if(	request.getParameter("userName") == null || 
				request.getParameter( "userEmail") == null  ||
				request.getParameter( "userPass") == null  ||
				request.getParameter( "userNumber") == null  ||
				request.getParameter( "userKey") == null  ) {	
			resObj =new dataObject(-1,"nothing","Data missing required all execpted data fields.","NTH");
				session.setAttribute("errorMsg", "Something wrong on server");
				return;
		}
		try {
			u.setUserName(request.getParameter("userName"));
			u.setUserEmail(request.getParameter( "userEmail"));
			u.setUserPass(request.getParameter( "userPass"));
			u.setUserNumber(request.getParameter( "userNumber"));
			u.setUserKey(request.getParameter( "userKey"));
			System.out.println(request.getParameter("userName"));
			String responseMessage = dao.register(u);
			if ( responseMessage.equalsIgnoreCase("inserted recored!")) {
				resObj =new dataObject(1,"suussufully inserted ","nothing","nothing track data");
				resObj.setDatapack(u);
				session.setAttribute("sucMsg", "Register Sucessfully");
			} else if( responseMessage.equalsIgnoreCase("Existed")){
				resObj =new dataObject(0,"User already exist.","nothing","nothing track data");
				session.setAttribute("errorMsg", "Something wrong on server");
			}else {
				resObj =new dataObject(-1,"NTH","Server busy!.","NTH");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {	
			out.append(		gson.toJson(resObj)	   );
			response.setContentType("application/json");
			System.out.println("closing adduser");
			out.flush();
			out.close();
		}
	
	}

}
