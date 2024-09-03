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
 * Servlet implementation class loginUserCheck
 */
@WebServlet("/loginUserCheck")
public class loginUserCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out =response.getWriter(); 
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		dataObject resObj =null;
		userdao dao = new userdao(Linker.getConn());
		user u = new user();
		HttpSession session = request.getSession();
//		System.out.println(session.getAttribute("userObj").equals(request.getAttribute("userentity"))+"\n"+session.getAttribute("userid").equals(request.getAttribute("userid")));
//		System.out.println(session.getAttribute("userObj")+"--..--"+session.getAttribute("userid")+"--..--"+session.getAttribute("hello")+"--..--");
//		if(!(session.getAttribute("userObj")==null && session.getAttribute("userObj").equals(request.getParameter("userentity"))) ) {
//			resObj=new dataObject(-1,"not valide user");
//			return;
//		}
		try {
			u.setUserEmail(request.getParameter( "userMain"));
			u.setUserPass(request.getParameter( "userPass"));
			u.setRole(request.getParameter( "role"));
			System.out.println(u+"-->>");
			String responseMessage = dao.canLoginValide(u);
			System.out.println(responseMessage+"--->>");
			if ( responseMessage.equalsIgnoreCase("validated user recored!")) {
				resObj =new dataObject(1,responseMessage,"nothing","nothing track data");u.setUserPass("");	resObj.setDatapack(u);
				session.setAttribute("userObj", u);
				session.setAttribute( "userid", u.getId());
				session.setAttribute(u.getUserEmail(), "1");
			} else if( responseMessage.equalsIgnoreCase("Not valid user credantionals")){
				resObj =new dataObject(0,"nothing",responseMessage,"nothing track data");
				session.setAttribute("errorMsg", "user credationals are incorrect!");
			}else {
				resObj =new dataObject(-1,"nothing","nothing",responseMessage);
				session.setAttribute("errorMsg", "Something wrong on server");
			}
		} catch (Exception e) {			e.printStackTrace();
		}finally {			
			out.append(		gson.toJson(resObj)	   );
			Linker.colseConn();
			System.out.println("closing adduser");
			response.setContentType("application/json");
			out.close();
		}
	}

}
