package servlets.booking;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import Jdbc.Linker;
import dao.bookingdao;
import dao.hoteldao;
import dao.roomdao;
import entites.booking;
import entites.dataObject;
import entites.hotel;


@WebServlet("/appBooking")
public class appBooking extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	PrintWriter out =response.getWriter(); 
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    dataObject resObj =null;
	booking b = new booking();
	Object okObject=null;
	bookingdao objHoteldao = new bookingdao(Linker.getConn());
	b.setNoOfGuests( Integer.parseInt( request.getParameter("canStayCount")));
	b.setDays( Integer.parseInt( request.getParameter("NoOfDays")));
	b.setBookingFrom(  request.getParameter("CheckInTime"));
	b.setPrice( Double.parseDouble(( request.getParameter("advanceMount")) ) );
	b.setRoomid( Integer.parseInt( request.getParameter("RoomId")) );
//	b.setRoomid( Integer.parseInt( request.getParameter("RoomId")) );
	b.setHostelId( Integer.parseInt( request.getParameter("HostelId")) );
	b.setId( Integer.parseInt( request.getParameter("id")) );
	okObject =objHoteldao.addBooking(b);
	if( okObject instanceof String) {
		resObj =new dataObject(1,"successfully task completed.","nothing","data retrived to display for user");
		resObj.setDatapack( okObject );
	}else {
		resObj =new dataObject(1,"successfully task completed.","nothing","data retrived to display for user");
		resObj.setDatapack( okObject );
	}
//	if(connection!=null) {try {connection.close();} catch (SQLException e) {e.printStackTrace();	}}
	Linker.colseConn();
	response.setContentType("application/json");
    out.append(		gson.toJson(resObj)	   );
    out.flush();
    out.close();
}

}
