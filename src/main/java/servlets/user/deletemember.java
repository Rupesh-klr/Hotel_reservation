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
import dao.hoteldao;
import dao.userdao;
import entites.dataObject;
import entites.hotel;
import entites.user;

@WebServlet("/deletemember")
public class deletemember extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out =response.getWriter(); 
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		dataObject resObj =null;
		userdao dao = new userdao(Linker.getConn());
		HttpSession session = request.getSession();
		try {
			if(request.getParameter("delete")!=null && request.getParameter("delete").equalsIgnoreCase("yes") ) {
				System.out.println(request.getParameter("AdmId")+"--->>");
				int ad=Integer.parseInt(request.getParameter("AdmId"))
						,id=Integer.parseInt(request.getParameter("id"));
				resObj=dao.deletemember(ad,id );
				return;
			}else if(request.getParameter("viewHotel")!=null && request.getParameter("viewHotel").equalsIgnoreCase("yes") ) {
				hoteldao hdaoHoteldao=new hoteldao(Linker.getConn());
				resObj=new dataObject(1);
				hotel htemp=new hotel();
				System.out.println("fklkfkfkfhkh");
				htemp.setId(Integer.parseInt(request.getParameter("id")));
				System.out.println(htemp);
				resObj.setDatapack(  hdaoHoteldao.getMyHotelList(htemp,2 ));
			}else if(request.getParameter("update")!=null && request.getParameter("update").equalsIgnoreCase("admin") ) {
				int ad=Integer.parseInt(request.getParameter("AdmId"))
						,id=Integer.parseInt(request.getParameter("id"));
				resObj=dao.updatemember(id, ad, request.getParameter("role"));
			}else if(request.getParameter("opertion")!=null && request.getParameter("opertion").equalsIgnoreCase("modifyUser") )  {
				user user=new user();
				user.setId(request.getParameter("id") );
				user.setUserName(request.getParameter("userName") );
				user.setUserNumber(request.getParameter("userNumber") );
				user.setUserEmail( request.getParameter("useremail"));
				user.setRole(request.getParameter("role") );
				user.setPhotoRef(request.getParameter("photoref"));
				resObj=dao.editmember(user,Integer.parseInt(request.getParameter("AdmId")) );
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {	
			out.append(		gson.toJson(resObj)	   );
			response.setContentType("application/json");
			System.out.println("closing delete");
			out.flush();
			out.close();
		}
	}

}
