package temp.point;

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

import entites.*;

/**
 * Servlet implementation class gsonOutput
 */
@WebServlet("/temp/gsonOutput")
public class gsonOutput extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
     protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	 HttpSession session=request.getSession();
    	 String kString=(String) session.getAttribute("userObj");
    	 if(kString!=null) {
    		 System.out.println( session.getAttribute("userObj"));
    		 System.out.println(session.getAttribute("userObj").equals(request.getAttribute("userentity")));
    	 }
    	 kString=(String) session.getAttribute("userid");
    	 if(kString!=null) {
    		 System.out.println(session.getAttribute("userid").equals(request.getAttribute("userid")));
    	 }
//    	 System.out.println(session.getAttribute("userObj").equals(request.getAttribute("userentity"))+"\n"+session.getAttribute("userid").equals(request.getAttribute("userid")));
// 		System.out.println(session.getAttribute("userObj")+"--..--"+session.getAttribute("userid")+"--..--"+session.getAttribute("hello")+"--..--");
// 		if(!(session.getAttribute("userObj")==null && session.getAttribute("userObj").equals(request.getParameter("userentity"))) ) {
// 			resObj=new dataObject(-1,"not valide user");
// 			return;
//// 		}
		session.setAttribute("userObj", request.getAttribute("userentity"));
		session.setAttribute( "userid", request.getAttribute("userid"));
    	PrintWriter out =response.getWriter(); 
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
		dataObject resObj =new dataObject();
        response.setContentType("application/json");
        out.append(		gson.toJson(resObj)	   );
        out.flush();
        out.close();
	}
     protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}