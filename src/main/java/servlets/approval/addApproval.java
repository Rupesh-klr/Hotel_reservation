package servlets.approval;

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
import dao.approvaldao;
import entites.approval;
import entites.dataObject;

/**
 * Servlet implementation class addApproval
 */
@WebServlet("/addApproval")
public class addApproval extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out =response.getWriter(); 
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		dataObject resObj=null;
		HttpSession session = request.getSession();
		approval objApproval=new approval();
		objApproval.setProof( request.getParameter("ProofDocumentNumber"));
		objApproval.setProofType( Integer.parseInt( request.getParameter("proofType")));
		objApproval.setId(  Integer.parseInt( request.getParameter("id")));
		objApproval.setAttachmentMessage( request.getParameter("AttachmentTA"));
		approvaldao dao = new approvaldao(Linker.getConn());
		System.out.println("-->>");
		try {
			String serStr = dao.addRequest(objApproval);
			if(serStr.equalsIgnoreCase("Requested ! Wait till responds comes.")) {
				resObj=new dataObject(1,"Successfull","nothing","log track is nothing");
			}else {
				resObj=new dataObject(0,"NTH","failed","log track is nothing");
			}
			resObj.setDatapack(serStr);
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

