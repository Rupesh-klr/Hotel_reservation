package dao;
import java.awt.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

import Jdbc.Linker;
import entites.*;
public class bookingdao {
	private Connection conn;
	private PreparedStatement psmt;
	private ResultSet rst;
	public bookingdao(Connection conn) {
		super();
		this.conn = conn;
		psmt=null;
		rst=null;
	}


	public String addBooking(booking b) {
		int sta=0,i=0;
//		System.out.println("booking");
		System.out.println(b);
		try {
			if(psmt != null) {psmt.close();}
			String sql = "insert into klr.BookingLists (id,Hostelid,Roomid,NoOfGuests,BookedDate,BookingFrom,BookingTo,TotalStay) VALUES(?,?,  ?, ? ,getdate(),DATEADD(day,?,?), DATEADD(day,?,?),1)";
//			System.out.println(b.getDays());
			if(b.getDays()>=i ) {
				while(i<b.getDays()) {
					psmt = conn.prepareStatement(sql);
					psmt.setInt(1, b.getId());
					psmt.setInt(2, b.getHostelId() );
					psmt.setInt(3, b.getRoomid() );
					psmt.setInt(4, b.getNoOfGuests() );
					psmt.setInt(5, i );
					psmt.setString(6, b.getBookingFrom() );
					psmt.setInt(7, i++ );
					psmt.setString(8, b.getBookingFrom() );
					sta  = psmt.executeUpdate();
//					System.out.println(sta);
				}
			}
			Linker.statusLog.info("Booking add for user "+b.getId()+" has been booked succesfully.");
//			getAllBookedDateOfRoom(b.getRoomid(),b.getId());
			if(psmt != null) {psmt.close();}			
		} catch (Exception e) {e.printStackTrace();Linker.errorLog.error("From\t type:: \t file::  \tmethod::\t "+Thread.currentThread().getStackTrace()[1]+"\n coonection closing for psmt(or) rstm\n\n=="+ e.toString());
		if(i==1) {return "Booking completed!\nAt disconnection";}return "recored Not inserted!\nconnection";
		}
		return "Booking completed!";
	}


	public ArrayList<String> getAllBookedDateOfRoom(int roomid,int flag) {
		ArrayList<String> get=new ArrayList();
		String sql="";
		if(flag==0) {
			sql = "select BookingFrom from  [klr].[BookingLists]  with (NOLOCK)  where  BookingisClosed=0 and BookingFrom >GETDATE() and Roomid=?";
		}else{
			sql = "select BookingFrom from  [klr].[BookingLists]  with (NOLOCK)  where  BookingisClosed=0 and BookingFrom >GETDATE() and Roomid=? and id="+flag;
		}
		try {
			if(rst != null) {rst.close();}
			if(psmt != null) {psmt.close();}
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, roomid);
			rst=psmt.executeQuery();
			while(rst.next()) {
				get.add(rst.getString(1));
			}		
		} catch (Exception e) {e.printStackTrace();System.out.println("Try again later server is busy!\nconnection");Linker.errorLog.error("From\t type:: \t file::  \tmethod::\t "+Thread.currentThread().getStackTrace()[1]+"\n coonection closing for psmt(or) rstm\n\n=="+ e.toString());
		}finally {
			try {if(rst != null) {rst.close();}
				if(psmt != null) {psmt.close();}
			} catch (Exception e2) {e2.printStackTrace();Linker.errorLog.error("From\t type:: \t file::  \tmethod::\t "+Thread.currentThread().getStackTrace()[1]+"\n coonection closing for psmt(or) rstm\n\n=="+ e2.toString());}
		}
		Linker.statusLog.info("Requested room booking are genrated as requested!");
		return get;
	}

		public ArrayList<booking> getBookingHTId(int HTId) {
		ArrayList<booking> bList=new ArrayList<>();
		booking b=null;
		try {
			if(rst != null) {rst.close();}
			if(psmt != null) {psmt.close();}
			String sql = "select * from klr.BookingLists  with (NOLOCK)  where BookingisClosed=0 and Hostelid=?";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, HTId);
			rst=psmt.executeQuery();
			while (rst.next()) {
				b=new booking();
				b.setBookingId(rst.getInt(1));
				b.setId(rst.getInt(2));
				b.setHostelId(rst.getInt(3));
				b.setRoomid(rst.getInt(4));
				b.setNoOfGuests(rst.getInt(5));;
				b.setBookedDate(rst.getString(6));
				b.setBookingFrom(rst.getNString(7));
				b.setBookingTo(rst.getString(8));
				b.setTotalStay(rst.getInt(9));
				b.setPrice(rst.getDouble(10));
				b.setStayDetails(rst.getInt(11));
				b.setBookingisClosed(rst.getInt(12));
//				b.setStayDetails (rst.getInt(11));// lastModified date
//				b.setStayDetails (rst.getInt(11));// lastemodiedby member name as hotel account are roomservices
				bList.add(b);
			}
		} catch (Exception e) {Linker.errorLog.error("From\t type:: \t file::  \tmethod::\t "+Thread.currentThread().getStackTrace()[1]+"\n coonection closing for psmt(or) rstm\n\n=="+ e.toString());e.printStackTrace();System.out.println( "Try again later server is busy!\nconnection");
		}finally {
			try {if(rst != null) {rst.close();}
				if(psmt != null) {psmt.close();}
			} catch (Exception e2) {e2.printStackTrace();Linker.errorLog.error("From\t type:: \t file::  \tmethod::\t "+Thread.currentThread().getStackTrace()[1]+"\n coonection closing for psmt(or) rstm\n\n=="+ e2.toString());}
		}
		Linker.statusLog.info("Booking is genrated as request by owner HTid."+HTId);
		return bList;
	}
		public ArrayList<booking> getBookingRMId(int RMId) {
			ArrayList<booking> bList=new ArrayList<>();
			booking b=null;
			try {
				if(rst != null) {rst.close();}
				if(psmt != null) {psmt.close();}
				String sql = "select * from klr.BookingLists  with (NOLOCK)  where BookingisClosed=0 and Roomid=?";
				psmt = conn.prepareStatement(sql);
				psmt.setInt(1, RMId);
				rst=psmt.executeQuery();
				while (rst.next()) {
					b=new booking();
					b.setBookingId(rst.getInt(1));
					b.setId(rst.getInt(2));
					b.setHostelId(rst.getInt(3));
					b.setRoomid(rst.getInt(4));
					b.setNoOfGuests(rst.getInt(5));;
					b.setBookedDate(rst.getString(6));
					b.setBookingFrom(rst.getNString(7));
					b.setBookingTo(rst.getString(8));
					b.setTotalStay(rst.getInt(9));
					b.setPrice(rst.getDouble(10));
					b.setStayDetails(rst.getInt(11));
					b.setBookingisClosed(rst.getInt(12));
					bList.add(b);
				}
			} catch (Exception e) {Linker.errorLog.error("From\t type:: \t file::  \tmethod::\t "+Thread.currentThread().getStackTrace()[1]+"\n coonection closing for psmt(or) rstm\n\n=="+ e.toString());e.printStackTrace();System.out.println( "Try again later server is busy!\nconnection");
			}finally {
				try {if(rst != null) {rst.close();}
				if(psmt != null) {psmt.close();}
				} catch (Exception e2) {Linker.errorLog.error("From\t type:: \t file::  \tmethod::\t "+Thread.currentThread().getStackTrace()[1]+"\n coonection closing for psmt(or) rstm\n\n=="+ e2.toString());e2.printStackTrace();}
			}
			Linker.statusLog.info("Users booking has been genarated and sent back.");
			return bList;
		}
		public ArrayList<booking> getMyBooking(int id) {
			ArrayList<booking> bList=new ArrayList<>();
			booking b=null;
			try {
				if(rst != null) {rst.close();}
				if(psmt != null) {psmt.close();}
				String sql = "select BookingId,id,Hostelid,Roomid,NoOfGuests,CONVERT(varchar(100),BookedDate,105)as bookedon,CONVERT(varchar(100),BookingFrom,105) as BookingFrom,CONVERT(varchar(100),BookingTo,105) as BookingTo,TotalStay,Totalprice from klr.BookingLists  with (NOLOCK)  where BookingisClosed=0 and id=?";
				psmt = conn.prepareStatement(sql);
				psmt.setInt(1, id);
				rst=psmt.executeQuery();
				while (rst.next()) {
					b=new booking();
					b.setBookingId(rst.getInt(1));
					b.setId(rst.getInt(2));
					b.setHostelId(rst.getInt(3));
					b.setRoomid(rst.getInt(4));
					b.setNoOfGuests(rst.getInt(5));;
					b.setBookedDate( rst.getString(6));
					b.setBookingFrom(rst.getString(7));
					b.setBookingTo(rst.getString(8));
					b.setTotalStay(rst.getInt(9));
					b.setPrice(rst.getDouble(10));
//					b.setStayDetails(rst.getInt(11));
//					b.setBookingisClosed(rst.getInt(12));
//				b.setStayDetails (rst.getInt(11));// lastModified date
//				b.setStayDetails (rst.getInt(11));// lastemodiedby member name as hotel account are roomservices
					bList.add(b);
				}
			} catch (Exception e) {Linker.errorLog.error("From\t type:: \t file::  \tmethod::\t "+Thread.currentThread().getStackTrace()[1]+"\n coonection closing for psmt(or) rstm\n\n=="+ e.toString());e.printStackTrace();System.out.println( "Try again later server is busy!\nconnection");
			}finally {
				try {if(rst != null) {rst.close();}
				if(psmt != null) {psmt.close();}
				} catch (Exception e2) {Linker.errorLog.error("From\t type:: \t file::  \tmethod::\t "+Thread.currentThread().getStackTrace()[1]+"\n coonection closing for psmt(or) rstm\n\n=="+ e2.toString());e2.printStackTrace();}
			}
			Linker.statusLog.info("Booking already make by user has been genarated!");
			return bList;
		}
		public booking getBookingDetail(int bookingId) {
			booking b=null;
			try {
				if(rst != null) {rst.close();}
				if(psmt != null) {psmt.close();}
				String sql = "select * from klr.BookingLists  with (NOLOCK)  where BookingID=?";
				psmt = conn.prepareStatement(sql);
				psmt.setInt(1, bookingId);
				rst=psmt.executeQuery();
				if (rst.next()) {
					b=new booking();
					b.setBookingId(rst.getInt(1));
					b.setId(rst.getInt(2));
					b.setHostelId(rst.getInt(3));
					b.setRoomid(rst.getInt(4));
					b.setNoOfGuests(rst.getInt(5));;
					b.setBookedDate(rst.getString(6));
					b.setBookingFrom(rst.getNString(7));
					b.setBookingTo(rst.getString(8));
					b.setTotalStay(rst.getInt(9));
					b.setPrice(rst.getDouble(10));
					b.setStayDetails(rst.getInt(11));
					b.setBookingisClosed(rst.getInt(12));
				b.setStayDetails (rst.getInt(11));// lastModified date
				b.setStayDetails (rst.getInt(11));// lastemodiedby member name as hotel account are roomservices
					return b;
				}
			} catch (Exception e) {e.printStackTrace();System.out.println( "Try again later server is busy!\nconnection");Linker.errorLog.error("From\t type:: \t file::  \tmethod::\t "+Thread.currentThread().getStackTrace()[1]+"\n coonection closing for psmt(or) rstm\n\n=="+ e.toString());
			}finally {
				try {if(rst != null) {rst.close();}
				if(psmt != null) {psmt.close();}
				} catch (Exception e2) {e2.printStackTrace();Linker.errorLog.error("From\t type:: \t file::  \tmethod::\t "+Thread.currentThread().getStackTrace()[1]+"\n coonection closing for psmt(or) rstm\n\n=="+ e2.toString());}
			}
			Linker.statusLog.info("Details of boooking by booking id :"+bookingId);
			return b;
		}


		public Object getdetaildBooking(int UserId,int flage) {			
			try {
				if(rst != null) {rst.close();}
				if(psmt != null) {psmt.close();}
				
				String sqlLaString="",sql1 = "select bl.BookingID,bl.NoOfGuests,CONVERT(varchar(100),bl.BookedDate,105)as bookedon,CONVERT(varchar(100),bl.BookingFrom,105) as BookingFrom, ",
				sql2 =" CONVERT(varchar(100),bl.BookingTo,105) as BookingTo,bl.TotalStay,bl.Totalprice,bl.Roomid,rl.RoomType,rl.price,bl.Hostelid,hl.HostelName,hl.HotelLocation,hl.HotelAddress,hl.HotelNumber,bl.id,ul.userEmail,ul.userName,ul.userPhoneNo ",
				sql3=" from klr.BookingLists as bl inner JOIN  klr.Roomlists as rl on bl.Roomid=rl.Roomid inner JOIN  klr.Hotels as hl on hl.Hotelid=rl.HostelId inner JOIN  klr.Userlist as ul on ul.id=bl.id where ";
				if(flage==0) {
					sqlLaString=" bl.id=? ";
				}else {
					sqlLaString=" bl.BookingID=? ";					
				}
				psmt = conn.prepareStatement(sql1+sql2+sql3+sqlLaString);
				psmt.setInt(1, UserId);
				rst=psmt.executeQuery();
//				if (rst.next()) {
					return convertResultsetToObject(rst);
//				}
			} catch (Exception e) {e.printStackTrace();System.out.println( "Try again later server is busy!\nconnection");Linker.errorLog.error("From\t type:: \t file::  \tmethod::\t "+Thread.currentThread().getStackTrace()[1]+"\n coonection closing for psmt(or) rstm\n\n=="+ e.toString());
			}finally {
				try {if(rst != null) {rst.close();}
				if(psmt != null) {psmt.close();}
				} catch (Exception e2) {e2.printStackTrace();Linker.errorLog.error("From\t type:: \t file::  \tmethod::\t "+Thread.currentThread().getStackTrace()[1]+"\n coonection closing for psmt(or) rstm\n\n=="+ e2.toString());}
			}
			return null;
		}
		private ArrayList<Object[]> convertResultsetToObject(ResultSet rs) throws SQLException {
			ArrayList<Object[]> results = new ArrayList<Object[]>();
		    int count = 0;
		    if(rs != null) {
		        ResultSetMetaData rsm = rs.getMetaData();
		        count = rsm.getColumnCount();
		    }
		    while(rs != null && rs.next() ) {
		        Object [] obj = new Object[count];
		        int temp = 1;
		        while(temp <= count) {
		            obj[temp - 1] = rs.getObject(temp);
		            temp++;
		        }
		        results.add(obj);
		    }
		    if(results!=null) {
		    	Linker.statusLog.info("Get more required details of boooking.");
		    }
		    return results;
		}

		public dataObject updateBooking(int bookingId, int flage) {
			String sqlString="";
			if(flage==0) {
				sqlString="update klr.BookingLists set BookingisClosed=1 , LastmodifiedBy='user' , Lastmodified=getdate() where BookingId=?";
			}
			try {
				psmt=conn.prepareStatement(sqlString);
				psmt.setInt(1, bookingId);
				if(psmt.executeUpdate()==1) {
					Linker.statusLog.info("Booking has been closed by user "+bookingId);
					return new dataObject(1,"Booking closed");
				}
				return new dataObject(0,"Unable to close becase booking (not available)");
			} catch (Exception e) {
				Linker.errorLog.error("From\t type:: \t file::  \tmethod::\t "+Thread.currentThread().getStackTrace()[1]+"\n coonection closing for psmt(or) rstm\n\n=="+ e.toString());
				Linker.errorLog.error( "From\n type::dao \tfile::Booking  \tmethod::updateBooking"+e.toString() );
				return new dataObject(-1,"exection failure!.");
			}			
		}

}
