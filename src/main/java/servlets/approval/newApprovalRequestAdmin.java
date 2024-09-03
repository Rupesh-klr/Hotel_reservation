package servlets.approval;

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
import dao.approvaldao;
import dao.userdao;
import entites.approval;
import entites.dataObject;

/**
 * Servlet implementation class newApprovalRequestAdmin
 */
@WebServlet("/newApprovalRequestAdmin")
public class newApprovalRequestAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out =response.getWriter(); 
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		dataObject resObj=null;
		HttpSession session = request.getSession();
		approvaldao dao = new approvaldao(Linker.getConn());
		ArrayList<approval> aList = new ArrayList<approval>();
		System.out.println("-->>");
		try {
			aList=dao.getNewApprovalS();
			if(aList.size()>0)  {
				resObj=new dataObject(0,"NTH","Failed","log");
			}else {
				resObj=new dataObject(1,"Scuufully","NTH","log");
			}
			resObj.setDatapack(aList);
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
