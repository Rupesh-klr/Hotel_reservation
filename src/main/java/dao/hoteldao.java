package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Jdbc.Linker;
import entites.*;
import servlets.approval.newApprovalRequestAdmin;

public class hoteldao {
	private Connection conn;
	private PreparedStatement psmt;
	private ResultSet rst;
	public hoteldao(Connection conn) {
		super();
		this.conn = conn;
		psmt=null;
		rst=null;
	}
	public Object seracherWordInTable(String word,int flage) {
		hotel h2 =null; String qry =null;
		ArrayList<hotel> htList =new ArrayList();
		switch (flage) {
		case 0:
			qry = "SELECT * FROM klr.Hotels   with (NOLOCK)  WHERE Htisdeleted = 0   and HTstatus=1 AND (HostelName LIKE ? OR HotelAddress LIKE ? OR HotelLocation LIKE ?) ";
			break;
		case 1:
			qry = "SELECT * FROM klr.Hotels   with (NOLOCK) WHERE Htisdeleted = 0  and HTstatus=1 ORDER by EndPr DESC";
			break;
		case 2:
			qry = "SELECT * FROM klr.Hotels  with (NOLOCK)  WHERE Htisdeleted = 0  and HTstatus=1 and EndPr <> 0  ORDER by EndPr ASC";
			break;
		case 3:
			qry = "SELECT * FROM klr.Hotels  with (NOLOCK)  WHERE Htisdeleted = 0  and HTstatus=1 AND (HotelLocation LIKE ?) ";
			break;
		case 4:
			qry = "SELECT * FROM klr.Hotels  with (NOLOCK)  WHERE Htisdeleted = 0  and HTstatus=1 AND (HostelName LIKE ? ";
			break;
		case 5:
			qry = "SELECT * FROM klr.Hotels  with (NOLOCK)  WHERE Htisdeleted = 0  and HTstatus=1 AND (HotelAddress LIKE ? ) ";
			break;
		case 6:
			qry = "SELECT * FROM klr.Hotels  with (NOLOCK)  WHERE Htisdeleted = 0  and HTstatus=1 AND  "+word;
			System.out.println(qry);
			break;
		default :
			qry = "select top(20)* from klr.Hotels  with (NOLOCK)  WHERE Htisdeleted=0   and HTstatus=1 ORDER by rating ";
			break;
		}
		try {
			psmt=Linker.getConn().prepareStatement(qry);
			if(flage==1) {				
				psmt.setString(1, word);
				psmt.setString(2, word);
				psmt.setString(3, word);
			}else if(flage==3|| flage==4||flage==5) {				
				psmt.setString(1, word);
			}
			rst=psmt.executeQuery();
			while(rst.next()) {
				h2= new hotel();
				h2.setHotelid(rst.getInt(1) );
				h2.setId(rst.getInt(2) );
				h2.setHostelName(rst.getString(3) );
				h2.setHotelAddress(rst.getString(4) );
				h2.setHotelNumber(rst.getString(5) );
				h2.setHotelDesc(rst.getString(6) );
				h2.setHtimage(rst.getString(7) );
				h2.setRating(rst.getInt(8) );
				h2.setHotelLocation(rst.getString(9) );
				h2.setStaringPr(rst.getDouble(10) );
				h2.setEndPr(rst.getDouble(11));
				htList.add(h2);
				System.out.println(h2.getHotelid());
			}
			Linker.statusLog.info("Hotel search has been completed!");
			return htList;
		} catch (Exception e) {
			Linker.errorLog.error("From\t type:: \t file::  \tmethod::\t "+Thread.currentThread().getStackTrace()[1]+"\n coonection closing for psmt(or) rstm\n\n=="+ e.toString());
			e.printStackTrace();System.out.println("Hotel request"); return "User not a owner are no registered hotel available to you.";
		}
	}
	
	public Object getMyHotelList(hotel h,int flag) {//request related table and deos
		hotel h2 =null;
		ArrayList<hotel> htList =new ArrayList();
		String qry = null;
		if(flag==0) {//admin to get list hotelstatus //getMyHotelList(null,0)
		 qry = "select * from klr.Hotels  with (NOLOCK)  WHERE Htisdeleted=0  and HTstatus=0 "; 
		}else if(flag==1) {/// user to get approvaliof //getMyHotelList(null,id)
			 qry = "select * from klr.Hotels  with (NOLOCK)  WHERE Htisdeleted=0 and HTstatus=0 and id=? "; 
		}else if(flag==2) {/// user to get //getMyHotelList(null,id)
			 qry = "select * from klr.Hotels  with (NOLOCK)  WHERE Htisdeleted=0  and id=? "; 
		}else if(flag==3){//admin to get list hotelstatus
			 qry = "select * from klr.Hotels  with (NOLOCK)  WHERE Htisdeleted=0 and HTstatus=1 and id=? "; 
		}else if(flag==4){//admin to get list hotelstatus
			 qry = "select * from klr.Hotels  with (NOLOCK)  WHERE Htisdeleted=0 and HTstatus=1 "; 
		}else if(flag==5) {
			qry = "select * from klr.Hotels  with (NOLOCK)  WHERE id=?"; 			
		}
		System.out.println(qry+"\n\n"+h);
		try {
			//if(conn==null) {conn=Linker.getConn();}
			psmt=Linker.getConn().prepareStatement(qry);
			if(flag==1 || flag==2||flag==3||flag==5) {
				System.out.println(h.getId());
				psmt.setInt(1, h.getId());
			}
			rst=psmt.executeQuery();
			while(rst.next()) {
				h2= new hotel();
				h2.setHotelid(rst.getInt(1) );
				h2.setId(rst.getInt(2) );
				h2.setHostelName(rst.getString(3) );
				h2.setHotelAddress(rst.getString(4) );
				h2.setHotelNumber(rst.getString(5) );
				h2.setHotelDesc(rst.getString(6) );
				h2.setHtimage(rst.getString(7) );
				h2.setRating(rst.getInt(8) );
				h2.setHotelLocation(rst.getString(9) );
				h2.setStaringPr(rst.getDouble(10) );
				h2.setEndPr(rst.getDouble(11));
				h2.setHTstatus(rst.getInt(12));
				h2.setStatumsg(rst.getString(13));
				h2.setHtisdeleted(rst.getInt(14));
				htList.add(h2);
				System.out.println(h2.getHotelid());
			}
			Linker.statusLog.info("Hotel List has been genarated!");
			return htList;
		} catch (Exception e) {
			Linker.errorLog.error("From\t type:: \t file::  \tmethod::\t "+Thread.currentThread().getStackTrace()[1]+"\n coonection closing for psmt(or) rstm\n\n=="+ e.toString());
			e.printStackTrace();System.out.println("Hotel request"); return "User not a owner are no registered hotel available to you.";
		}

	}
 	 public hotel getHotelByHTId(hotel h) {
		 hotel h2= new hotel();
		 try {
		 String qry = "select * from klr.Hotels   with (NOLOCK)  WHERE Htisdeleted=0   and HotelId =? "; 
		 if(conn==null) {conn=Linker.getConn();}
			psmt=Linker.getConn().prepareStatement(qry);
			psmt.setInt(1, h.getHotelid());
			rst=psmt.executeQuery();
			while(rst.next()) {
				h2.setHotelid(rst.getInt(1) );
				h2.setId(rst.getInt(2) );
				h2.setHostelName(rst.getString(3) );
				h2.setHotelAddress(rst.getString(4) );
				h2.setHotelNumber(rst.getString(5) );
				h2.setHotelDesc(rst.getString(6) );
				h2.setHtimage(rst.getString(7) );
				h2.setRating(rst.getInt(8) );
				h2.setHotelLocation(rst.getString(9) );
				h2.setStaringPr(rst.getDouble(10) );
				h2.setEndPr(rst.getDouble(11));
			}
		 }catch (Exception e) {Linker.errorLog.error("From\t type:: \t file::  \tmethod::\t "+Thread.currentThread().getStackTrace()[1]+"\n coonection closing for psmt(or) rstm\n\n=="+ e.toString());e.printStackTrace();System.out.println("Catch Block gethotel diaplay hotellist catch");
			return new hotel();
		}finally {
			try {if(rst != null) {rst.close();}
				if(psmt != null) {psmt.close();}
				if(conn != null) {conn.close();}
			} catch (Exception e2) {Linker.errorLog.error("From\t type:: \t file::  \tmethod::\t "+Thread.currentThread().getStackTrace()[1]+"\n coonection closing for psmt(or) rstm\n\n=="+ e2.toString());e2.printStackTrace();System.out.println("finally catch hoteldao diaplay hotellist catch");}
		}
		 Linker.statusLog.info("Hotel sent with hotel id: "+h.getHotelid());
		 return h2;
	 }
	
 	public Object displayHotel(int i,int id) {
		Map<String, ArrayList<hotel> > rtdataMap=new HashMap();
		hotel tempHtHotel=null;
		String[] sql= {"select * from klr.Hotels   with (NOLOCK)  WHERE Htisdeleted=0 and HTstatus=0  and id=?",
				"select * from klr.Hotels   with (NOLOCK)  WHERE Htisdeleted=0",//for admin
				"select * from klr.Hotels   with (NOLOCK)  WHERE  Htisdeleted=0 and id=?",//for admin to now all deleted hotels
				"select top(3)* from klr.Hotels   with (NOLOCK)  WHERE Htisdeleted=0 and HTstatus=1 ORDER by Rating DESC"
				,"select top(3)* from klr.Hotels   with (NOLOCK) WHERE Htisdeleted=0   and HTstatus=1  and rating=5  ORDER by EndPr DESC ",
				"select top(3)* from klr.Hotels  with (NOLOCK)  WHERE Htisdeleted=0   and HTstatus=1  and rating=5  ORDER by StaringPr ASC ",
				"select top(20)* from klr.Hotels  with (NOLOCK)  WHERE Htisdeleted=0  and HTstatus=1"};
		String[] name= {"","","","topRated","Topclass","topDeal","topL"};
//		System.out.println(sql[i]);
		try {
//			System.out.println(sql[i]);
			for(;i<sql.length;i++) {
				ArrayList<hotel> tempar =new ArrayList();
				psmt=conn.prepareStatement(sql[i] );
				switch (i) {
				case 0:
					psmt.setInt(1, id);
					break;
				case 2:
					psmt.setInt(1, id);
					break;
				default:
					break;
				}
				rst=psmt.executeQuery();
				while(rst.next()) {
					tempHtHotel=new hotel();
					tempHtHotel.setHotelid(rst.getInt(1) );
					tempHtHotel.setId(rst.getInt(2) );
					tempHtHotel.setHostelName(rst.getString(3) );
					tempHtHotel.setHotelAddress(rst.getString(4) );
					tempHtHotel.setHotelNumber(rst.getString(5) );
					tempHtHotel.setHotelDesc(rst.getString(6) );
					tempHtHotel.setHtimage(rst.getString(7) );
					tempHtHotel.setRating(rst.getInt(8) );
					tempHtHotel.setHotelLocation(rst.getString(9) );
					tempHtHotel.setStaringPr(rst.getDouble(10) );
					tempHtHotel.setEndPr(rst.getDouble(11));
					tempar.add(tempHtHotel);
				}
				if(i<3) {
					return tempar;
				}
				rtdataMap.put(name[i],tempar );
			}
			Linker.statusLog.info("Main page hotel has been genarated!");
			return rtdataMap;
		}catch (Exception e) {Linker.errorLog.error("From\t type:: \t file::  \tmethod::\t "+Thread.currentThread().getStackTrace()[1]+"\n coonection closing for psmt(or) rstm\n\n=="+ e.toString());e.printStackTrace();System.out.println("Catch Block hoteldao diaplay hotellist catch");
			return "Nothing in hotel list.";
		}finally {
			try {if(rst != null) {rst.close();}
				if(psmt != null) {psmt.close();}
				if(conn != null) {conn.close();}
			} catch (Exception e2) {Linker.errorLog.error("From\t type:: \t file::  \tmethod::\t "+Thread.currentThread().getStackTrace()[1]+"\n coonection closing for psmt(or) rstm\n\n=="+ e2.toString());e2.printStackTrace();System.out.println("finally catch hoteldao diaplay hotellist catch");}
		}
	}

	
	public boolean isExistmemberHT(hotel h) {
		boolean f = true;		
		try {
			String sql = "select * from klr.Hotels  with (NOLOCK)  WHERE Htisdeleted=0 and HTstatus in (1,0) and id=? and HostelName=? and HotelAddress =? and Htimage=? and HotelLocation =?";
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setInt( 1, h.getId() );
			psmt.setString( 2, h.getHostelName() );
			psmt.setString( 3, h.getHotelAddress() );
			psmt.setString( 4, h.getHtimage() );
			psmt.setString( 5, h.getHotelLocation() );
			ResultSet rst = psmt.executeQuery();
			while(rst.next()) {
				System.out.println(rst.getString(1));
				Linker.statusLog.info("Hotel already existed!");
				f= false;
			}
			if(rst != null) {rst.close();}
			if(psmt != null) {psmt.close();}
		} catch (Exception e) {Linker.errorLog.error("From\t type:: \t file::  \tmethod::\t "+Thread.currentThread().getStackTrace()[1]+"\n coonection closing for psmt(or) rstm\n\n=="+ e.toString());System.out.println("////////");e.printStackTrace();}
		Linker.statusLog.info("Hotel don't existed!");
		return f;
	}
	public String addHotel(hotel h) {
		String rspString=null;
		int i=0;
		if( !isExistmemberHT(h)) {return "Existed";}
		try {
			if(psmt != null) {psmt.close();}
			String sql = "insert into klr.Hotels  (id,HostelName,HotelAddress,HotelNumber  ,HotelDesc,Rating,HotelLocation,Htimage  ,statusmsg) VALUES(?,?,?,?, ?,0,?,?,?)";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, h.getId());            
			psmt.setString(2, h.getHostelName());
			psmt.setString(3, h.getHotelAddress());
			psmt.setString(4, h.getHotelNumber());
			
			psmt.setString(5, h.getHotelDesc());
			psmt.setString(6, h.getHotelLocation());
			psmt.setString(7, h.getHtimage());
			psmt.setString(8, h.getStatumsg());
//			psmt.setInt(1, h.getHTstatus());
//			psmt.setDouble(1, h.getStaringPr());
//			psmt.setDouble(1, h.getEndPr());
//			psmt.setInt(1, h.getRating());
			i = psmt.executeUpdate();
			if(psmt != null) {psmt.close();}			
		} catch (Exception e) {e.printStackTrace();Linker.errorLog.error("From\t type:: \t file::  \tmethod::\t "+Thread.currentThread().getStackTrace()[1]+"\n coonection closing for psmt(or) rstm\n\n=="+ e.toString());
		if(i==1) {Linker.statusLog.info("Hotel added into DB");return "inserted recored!\n at disconnection";} rspString= "recored Not inserted!\nconnection";
		}
		Linker.statusLog.info("Hotel added into DB");
		rspString= "inserted recored!";
		return rspString;
		
	}
	
	public String editHotel(hotel h, String editor) {
		String rspString=null;
		int i=0;
		try {
			if(psmt != null) {psmt.close();}
			String sql = "UPDATE klr.Hotels set HostelName=?,HotelAddress=?,HotelNumber=?,Rating=? ,HotelLocation=?,Htimage=?,LastmodifiedByHT=?,LastmodifiedHT=GETDATE() where Hotelid=?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, h.getHostelName());
			psmt.setString(2, h.getHotelAddress());
			psmt.setString(3, h.getHotelNumber());
			psmt.setInt(4, h.getRating());
			psmt.setString(5, h.getHotelLocation());
			psmt.setString(6, h.getHtimage());
			psmt.setString(7, "ID"+editor );  
			psmt.setInt(8, h.getHotelid());            
//			psmt.setString(8, h.getStatumsg());
//			psmt.setString(5, h.getHotelDesc());
//			psmt.setInt(1, h.getHTstatus());
//			psmt.setDouble(1, h.getStaringPr());
//			psmt.setDouble(1, h.getEndPr());
			i = psmt.executeUpdate();
			if(psmt != null) {psmt.close();}			
		} catch (Exception e) {e.printStackTrace();Linker.errorLog.error("From\t type:: \t file::  \tmethod::\t "+Thread.currentThread().getStackTrace()[1]+"\n coonection closing for psmt(or) rstm\n\n=="+ e.toString());
		if(i==1) {return "Updated recored!\n at disconnection";} rspString= "recored Not updated!\nconnection";
		}
		Linker.statusLog.info("Hotel recored has been update.With new changes.");
		rspString= "Updated recored!";
		return rspString;
	}
	public String deletHotel(int hTId, String editor) {
		String rspString=null;
		int i=0;
		try {
			if(psmt != null) {psmt.close();}
			System.out.println(hTId+"\t\t"+editor);
			String sql = "UPDATE klr.Hotels set Htisdeleted=1,HTstatus=2,LastmodifiedByHT=?,LastmodifiedHT=GETDATE() where Hotelid=?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, "ID"+editor );  
			psmt.setInt(2, hTId);  
			i = psmt.executeUpdate();
			sql = "update  klr.Roomlists set RmisDeleted=1,LastmodifiedByRM=?,LastmodifiedRM=GETDATE()  WHERE HostelId=?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, "ID"+editor );  
			psmt.setInt(2, hTId);  
			i = psmt.executeUpdate();
			if(psmt != null) {psmt.close();}			
		} catch (Exception e) {e.printStackTrace();Linker.errorLog.error("From\t type:: \t file::  \tmethod::\t "+Thread.currentThread().getStackTrace()[1]+"\n coonection closing for psmt(or) rstm\n\n=="+ e.toString());
		if(i==1) {return "Updated recored!\n at disconnection";} rspString= "recored Not updated!\nconnection";
		}
		Linker.statusLog.info("Hotel is deleted Successfully into DB");
		rspString= "Updated recored!";
		return rspString;
	}
	public Object hotelaporoval(int hotelId, int adid, int changeSt) {
		String rspString=null;
		int i=0;
		try {
			if(psmt != null) {psmt.close();}
			String sql = "UPDATE klr.Hotels set HTstatus=?,LastmodifiedByHT=?,LastmodifiedHT=GETDATE() where Hotelid=?";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, changeSt);  
			psmt.setString(2, "ID"+adid );  
			psmt.setInt(3, hotelId);  
			i = psmt.executeUpdate();
			if(psmt != null) {psmt.close();}			
		} catch (Exception e) {e.printStackTrace();Linker.errorLog.error("From\t type:: \t file::  \tmethod::\t "+Thread.currentThread().getStackTrace()[1]+"\n coonection closing for psmt(or) rstm\n\n=="+ e.toString());
		if(i==1) {return "Updated recored!\n at disconnection";} rspString= "recored Not updated!\nconnection";
		}
		Linker.statusLog.info("Hotel Approval has been updated by abmin.");
		rspString= "Updated recored!";
		return rspString;
	}


}

