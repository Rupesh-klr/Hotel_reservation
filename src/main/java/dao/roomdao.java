package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import Jdbc.Linker;
import entites.*;

public class roomdao {
	private Connection conn;
	private PreparedStatement psmt;
	private ResultSet rst;
	public roomdao(Connection conn) {
		super();
		if(conn!=null) {
			this.conn = conn;
		}else {
			this.conn=Linker.getConn();
		}
		psmt=null;
		rst=null;
	}
	public Object getRoomByHotelHTid(hotel h) {
		room r2 =null;
		ArrayList<room> rmList =new ArrayList();
		try {
			System.out.println(h.getHotelid());
			System.out.println("lnkmbkjb");
			 String qry = "SELECT * from klr.Roomlists WITH (NOLOCK) WHERE RmisDeleted=0   and HostelId =? "; 
			if(conn==null) {conn=Linker.getConn();}
			psmt=conn.prepareStatement(qry);
			psmt.setInt(1, h.getHotelid());
			rst=psmt.executeQuery();
			while(rst.next()) {
				r2= new room();
				r2.setRoomid(rst.getInt(1) );
				r2.setHostelId(rst.getInt(2));
				r2.setId(rst.getInt(3));
				r2.setRoomType(rst.getString(4));
				r2.setBedCount(rst.getInt(5) );
				r2.setRmimage(rst.getString(6));
				r2.setPrice(rst.getDouble(7) );
				r2.setRmcode(rst.getString(8) );
				r2.setCanStaycount(rst.getInt(9) );
				rmList.add(r2);
				System.out.println(r2.getId());
				System.out.println(r2.getRoomid());
			}
			return rmList;
		} catch (Exception e) {
			e.printStackTrace();System.out.println("Hotel request"); 
			Linker.errorLog.error("From\t type:: \t file::  \tmethod::\t "+Thread.currentThread().getStackTrace()[1]+"\n Geting room by using hotelId\n\n=="+e.toString());
			return "User not a owner are no registered hotel available to you.";
		}

	}
	public dataObject addRoom(room r) {
		dataObject rspString=null;
		int i=0;
		try {
			if(psmt != null) {psmt.close();}
			String sql = "insert into klr.Roomlists (HostelId, id, RoomType,      BedCount, price , CanStaycount     ,Rmcode)VALUES(?,? ,?,        ?, ?,?   ,       ?)";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, r.getHostelId());
			psmt.setInt(2, r.getId());
			psmt.setString(3, r.getRoomType());
			psmt.setInt(4, r.getBedCount());
			psmt.setDouble(5, r.getPrice());
			psmt.setInt(6, r.getCanStaycount());
			psmt.setString(7, r.getRmcode());
			i = psmt.executeUpdate();
			sql="DECLARE @hotelid2 as int=?;update klr.Hotels set StaringPr=(Select MIN(price)from klr.Roomlists where HostelId=@hotelid2 ) where  Hotelid=@hotelid2;update klr.Hotels set EndPr=(Select MAx(price)from klr.Roomlists where HostelId=@hotelid2 ) where  Hotelid=@hotelid2";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, r.getHostelId());
			i+=psmt.executeUpdate();
			if(psmt != null) {psmt.close();}			
		} catch (Exception e) {e.printStackTrace();Linker.errorLog.error("From\t type:: \t file::  \tmethod::\t "+Thread.currentThread().getStackTrace()[1]+"\n coonection closing for psmt(or) rstm\n\n=="+ e.toString());
		}
		if(i==2) {Linker.statusLog.info("ROOM added into DB");rspString=new dataObject(1,"ROOM ADDED"+i);} else {rspString=new dataObject(0,"Room not added"+i);}
		Linker.statusLog.info("Room added into DB");
		return rspString;
	}

	public dataObject roomList(int usid,int flage) {
		dataObject rspString=null;
		room r2 =null;
		ArrayList<room> rmList =new ArrayList();
		 String qry =null;
		try {
			if(flage==0) {
				qry = "SELECT * from klr.Roomlists WITH (NOLOCK) WHERE RmisDeleted=0   and id =? ORDER BY HostelId "; 
			}else {
				qry = "SELECT * from klr.Roomlists WITH (NOLOCK) WHERE RmisDeleted=0   ORDER BY HostelId "; 
			}
			psmt=conn.prepareStatement(qry);
			if(flage==0) {
				psmt.setInt(1, usid);
			}
			rst=psmt.executeQuery();
			while(rst.next()) {
				r2= new room();
				r2.setRoomid(rst.getInt(1) );
				r2.setHostelId(rst.getInt(2));
				r2.setId(rst.getInt(3));
				r2.setRoomType(rst.getString(4));
				r2.setBedCount(rst.getInt(5) );
				r2.setRmimage(rst.getString(6));
				r2.setPrice(rst.getDouble(7) );
				r2.setRmcode(rst.getString(8) );
				r2.setCanStaycount(rst.getInt(9) );
				rmList.add(r2);
			}
			rspString=new dataObject(1);
			rspString.setDatapack(rmList);
			return rspString;
		} catch (Exception e) {
			e.printStackTrace();System.out.println("Hotel request"); 
			Linker.errorLog.error("From\t type:: \t file::  \tmethod::\t "+Thread.currentThread().getStackTrace()[1]+"\n Geting room by using hotelId\n\n=="+e.toString());
			return new dataObject(0,"notable retrive data");
		}
	}
	public dataObject deleteroom(int usid,int rmid) {
		dataObject rspString=null;
		int i=0;
		try {
			if(psmt != null) {psmt.close();}
			String sql = "update klr.Roomlists set RmisDeleted=1 ,LastmodifiedByRM=?,LastmodifiedRM=GETDATE() where Roomid=?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, String.valueOf(usid));
			psmt.setInt(2, rmid);		
			i = psmt.executeUpdate();
			if(psmt != null) {psmt.close();}			
		} catch (Exception e) {e.printStackTrace();Linker.errorLog.error("From\t type:: \t file::  \tmethod::\t "+Thread.currentThread().getStackTrace()[1]+"\n coonection closing for psmt(or) rstm\n\n=="+ e.toString());
		}
		if(i==1) {Linker.statusLog.info("ROOM deleted into DB");rspString=new dataObject(1,"ROOM ADDED");} else {rspString=new dataObject(0,"Room not deleted");}
		Linker.statusLog.info("Room deleted into DB");
		return rspString;
	}
	public dataObject roomUpdate(room r,int flage) {
		int i=0;dataObject rspString=null;
		try {
			if(psmt != null) {psmt.close();}
			String sql = "update  klr.Roomlists set RoomType=?, BedCount=?, price=? , CanStaycount=? ,Rmcode=? where Roomid=?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, r.getRoomType());
			psmt.setInt(2, r.getBedCount());
			psmt.setDouble(3, r.getPrice());
			psmt.setInt(4, r.getCanStaycount());
			psmt.setString(5, r.getRmcode());
			psmt.setInt(6, r.getRoomid());
			i = psmt.executeUpdate();
			i+=psmt.executeUpdate();
			if(psmt != null) {psmt.close();}			
		} catch (Exception e) {e.printStackTrace();Linker.errorLog.error("From\t type:: \t file::  \tmethod::\t "+Thread.currentThread().getStackTrace()[1]+"\n coonection closing for psmt(or) rstm\n\n=="+ e.toString());
		}
		if(i==2) {Linker.statusLog.info("ROOM added into DB");rspString=new dataObject(1,"ROOM ADDED"+i);} else {rspString=new dataObject(0,"Room not added"+i);}
		Linker.statusLog.info("Room added into DB");
		return rspString;
	}
}
