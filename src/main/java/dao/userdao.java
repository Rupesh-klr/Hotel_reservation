package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import Jdbc.Linker;
import entites.*;

public class userdao {
	private Connection conn;
	private PreparedStatement psmt;
	private ResultSet rst;
	public userdao(Connection conn) {
		super();
		this.conn = conn;
		psmt=null;
		rst=null;
	}
	
	public ArrayList<user> getAllcoOrdinators(String isRole){
		ArrayList<user> objUsers= new ArrayList();
		if(!isRole.equalsIgnoreCase("admin")) {
			return objUsers;
		}
		try {
			if(rst != null) {rst.close();}
			if(psmt != null) {psmt.close();}
			String sql = "select id,userName,userEmail,userPhoneNo,photoRef,TypesMembership from klr.Userlist   with (NOLOCK)  WHERE isDelete=0";
//			String sql = "select id,userName,userEmail,userPhoneNo,photoRef,TypesMembership from klr.Userlist WHERE isDelete=0 and TypesMembership='owner'";
			psmt = conn.prepareStatement(sql);
			rst=psmt.executeQuery();
			user u=null;
			while (rst.next()) {
				u=new user();
				u.setRole(rst.getString(6));
				u.setId(rst.getInt(1));
				u.setUserName(rst.getString(2));
				u.setUserEmail(rst.getString(3));
				u.setUserNumber(rst.getString(4));
				u.setPhotoRef(rst.getString(5));
				objUsers.add(u);
			}Linker.statusLog.info("User who are co-ordinator/owners are requested only by admin ");
			return objUsers;
		} catch (Exception e) {e.printStackTrace();
		Linker.errorLog.error("From\t type:: \t file::  \tmethod::\t "+Thread.currentThread().getStackTrace()[1]+"\n for getiing owner to admin datatable\n\n=="+e.toString());
		}finally {
			try {if(rst != null) {rst.close();}
				if(psmt != null) {psmt.close();}
			} catch (Exception e2) {e2.printStackTrace();Linker.errorLog.error("From\t type:: \t file::  \tmethod::\t "+Thread.currentThread().getStackTrace()[1]+"\n coonection closing for psmt(or) rstm\n\n=="+ e2.toString());}
		}
		return objUsers;
	}
 	public String canLoginValide(user u) {		
		try {
			if(rst != null) {rst.close();}
			if(psmt != null) {psmt.close();}
			String sql = "select id,userName,userPhoneNo,userPassword,photoRef,Requested,apporvalmsg from klr.Userlist   with (NOLOCK)  where isDelete=0	and  TypesMembership=? and userEmail=?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, u.getRole());
			psmt.setString(2, u.getUserEmail());
			ResultSet rst = psmt.executeQuery();
			if (rst.next() && u.getUserPass().equals(rst.getString(4)) ) {
				u.setId(rst.getInt(1));
				u.setUserName(rst.getString(2));
				u.setUserNumber(rst.getString(3));
				u.setPhotoRef(rst.getString(5));
				u.setRequested(rst.getInt(6));
				u.setApporavalmsg(rst.getString(7));
				System.out.print("..........usrid id is ");
				System.out.println(u.getId()+" <<-.->> ");	
				Linker.statusLog.info("User is loged in with coorect credintionals.");
				return "validated user recored!";
			}else {
				Linker.statusLog.info("User attempted without correct credintionals.");
				return "Not valid user credantionals";
			}
		} catch (Exception e) {e.printStackTrace();
		Linker.errorLog.error("From\t type:: \t file::  \tmethod::\t "+Thread.currentThread().getStackTrace()[1]+"\n While checking user is available in Our Records!\n\n=="+e.toString());return "Try again later server is busy!\nconnection";
		}finally {
			try {if(rst != null) {rst.close();}
				if(psmt != null) {psmt.close();}
			} catch (Exception e2) {e2.printStackTrace();
			Linker.errorLog.error("From\t type:: \t file::  \tmethod::\t "+Thread.currentThread().getStackTrace()[1]+"\n coonection closing for psmt(or) rstm\n\n=="+ e2.toString());
			}
		}
	}
	
	public String register(user u) {
		int i=0;
		if( !isExistmember(u,0)) {Linker.statusLog.info("User is already exist in our DB.Ask user for password reset are password change.");return "Existed";}
		try {
			if(psmt != null) {psmt.close();}
			String sql = "insert into klr.Userlist(userName,userEmail,userPhoneNo,userPassword,SecertKey,TypesMembership) values(?,?, ?,?, ?,'owner')";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, u.getUserName());
			psmt.setString(2, u.getUserEmail());
			psmt.setString(3, u.getUserNumber());
			psmt.setString(4, u.getUserPass());
			psmt.setString(5, u.getUserKey());
			i = psmt.executeUpdate();
			if(psmt != null) {psmt.close();}			
		} catch (Exception e) {e.printStackTrace();
		Linker.errorLog.error("From\t type:: \t file::  \tmethod::\t "+Thread.currentThread().getStackTrace()[1]+"\n coonection closing for psmt(or) rstm\n\n=="+ e.toString());
		if(i==1) {return "inserted recored!\n at disconnection";}return "recored Not inserted!\nconnection";
		}Linker.statusLog.info("User has been added.New member is add to our DB.");
		return "inserted recored!";
	}
	public boolean isExistmember(user u,int flage) {
		boolean f = true;	String sql = null;	
		if(flage==1) {			
			sql = "select * from  klr.Userlist   with (NOLOCK)  where userEmail= ? and id=?";
		}else {
			sql = "select * from  klr.Userlist   with (NOLOCK)  where userEmail= ?";			
		}
		try {
			PreparedStatement psmt = conn.prepareStatement(sql);
			if(flage==0) {
				psmt.setString( 1, u.getUserEmail() );
				ResultSet rst = psmt.executeQuery();
				if(rst.next()) {
					System.out.println(rst.getString(1));
					return false;
				}
			}else {
				psmt.setString( 1, u.getUserEmail() );
				psmt.setInt( 2, u.getId() );
				ResultSet rst = psmt.executeQuery();
				if(rst.next()) {
					System.out.println(rst.getString(1));
					return false;
				}else{
					return true;
				}
			}
			if(rst != null) {rst.close();}
			if(psmt != null) {psmt.close();}
		} catch (Exception e) {System.out.println("////////");
		Linker.errorLog.error("From\t type:: \t file::  \tmethod::\t "+Thread.currentThread().getStackTrace()[1]+"\n coonection closing for psmt(or) rstm\n\n=="+ e.toString());
			e.printStackTrace();Linker.errorLog.error("From\t type:: \t file::  \tmethod::\t "+Thread.currentThread().getStackTrace()[1]+"\n coonection closing for psmt(or) rstm\n\n=="+e.toString());
		}
		return f;
	}
	public dataObject deletemember(int Adid,int userid) {
		try {
			String sql = "update klr.Userlist  set isDelete=1,Lastmodified=GETDATE(),LastmodifiedBy=? where id=?";
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setString( 1, String.valueOf(Adid) );
			psmt.setInt( 2, userid );
			int ex=psmt.executeUpdate();
			if(psmt != null) {psmt.close();}
			if(ex==1) {
				return new dataObject(1,"deleted user");
			}else {
				return new dataObject(0,"user not deleted");
			}
		} catch (Exception e) {System.out.println("////////");
		Linker.errorLog.error("From\t type:: \t file::  \tmethod::\t "+Thread.currentThread().getStackTrace()[1]+"\n coonection closing for psmt(or) rstm\n\n=="+ e.toString());
			e.printStackTrace();Linker.errorLog.error("From\t type:: \t file::  \tmethod::\t "+Thread.currentThread().getStackTrace()[1]+"\n coonection closing for psmt(or) rstm\n\n=="+e.toString());
		}
		return new dataObject(-1,"Someting problem");
	}
	public dataObject updatemember(int id,int Adid,String role) {
		try {
			String sql = "update  klr.Userlist set TypesMembership=?,LastmodifiedBy=?,Lastmodified=GETDATE()  where id=?";
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setString( 1, role );
			psmt.setInt( 2, Adid );
			psmt.setInt( 3, id );
			int k2=psmt.executeUpdate();
			if(psmt != null) {psmt.close();}
			if(k2==1) {
				return new dataObject(1,"updated user");
			}else {
				return new dataObject(0,"Not updated user");				
			}
		} catch (Exception e) {System.out.println("////////");
		Linker.errorLog.error("From\t type:: \t file::  \tmethod::\t "+Thread.currentThread().getStackTrace()[1]+"\n coonection closing for psmt(or) rstm\n\n=="+ e.toString());
			e.printStackTrace();Linker.errorLog.error("From\t type:: \t file::  \tmethod::\t "+Thread.currentThread().getStackTrace()[1]+"\n coonection closing for psmt(or) rstm\n\n=="+e.toString());
		}
		return new dataObject(-1,"Something problem");
	}

	public dataObject editmember(user user, int adid) {
		if(!isExistmember(user,0)  ) {
			if(isExistmember(user,1) ) {
				return new dataObject(0,"This email has been using by other person.");
			}
		}
		try {
			String sql = "update  klr.Userlist set userName=?,userPhoneNo=?,userEmail=?,TypesMembership=?,photoRef=?,LastmodifiedBy=?,Lastmodified=GETDATE()  where id=?";
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setString( 1, user.getUserName() );
			psmt.setString( 2, user.getUserNumber() );
			psmt.setString( 3, user.getUserEmail() );
			psmt.setString( 4, user.getRole() );
			psmt.setString( 5, user.getPhotoRef() );
			psmt.setInt( 6, adid );
			psmt.setInt( 7, user.getId() );
			int k2=psmt.executeUpdate();
			if(psmt != null) {psmt.close();}
			if(k2==1) {
				return new dataObject(1,"updated user");
			}else {
				return new dataObject(0,"Not updated user");				
			}
		} catch (Exception e) {System.out.println("////////");
		Linker.errorLog.error("From\t type:: \t file::  \tmethod::\t "+Thread.currentThread().getStackTrace()[1]+"\n coonection closing for psmt(or) rstm\n\n=="+ e.toString());
			e.printStackTrace();Linker.errorLog.error("From\t type:: \t file::  \tmethod::\t "+Thread.currentThread().getStackTrace()[1]+"\n coonection closing for psmt(or) rstm\n\n=="+e.toString());
		}
		return new dataObject(-1,"Something problem");
	}
	public dataObject restpassword(String newPass,String mail,String value,int flage) {	
		String sql=null;
		try {
			if(flage==0) {
				sql = "update klr.Userlist set userPassword=? where userEmail=? and SecertKey=?";
			}else {
				sql="update klr.Userlist set userPassword=? where userEmail=? and userPassword=?";
			}
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setString( 1, newPass );
			psmt.setString( 2, mail );
			psmt.setString( 3, value );
			int k2=psmt.executeUpdate();
			if(psmt != null) {psmt.close();}
			if(k2==1) {
				return new dataObject(1,"updated user");
			}else {
				return new dataObject(0,"Not updated user");				
			}
		} catch (Exception e) {System.out.println("////////");
		Linker.errorLog.error("From\t type:: \t file::  \tmethod::\t "+Thread.currentThread().getStackTrace()[1]+"\n coonection closing for psmt(or) rstm\n\n=="+ e.toString());
			e.printStackTrace();Linker.errorLog.error("From\t type:: \t file::  \tmethod::\t "+Thread.currentThread().getStackTrace()[1]+"\n coonection closing for psmt(or) rstm\n\n=="+e.toString());
		}
		return new dataObject(-1,"Something problem");
	}
	public dataObject updaterequest(int id) {	
		System.out.println(id);
		String sql="select apporvalmsg,Requested,TypesMembership from klr.Userlist WITH (NOLOCK) where id=?";
		try {
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setInt( 1, id );
			rst=psmt.executeQuery();			
			if(rst.next()) {
				System.out.println("data sql");
				HashMap< String, String>hashMap =new HashMap< String, String>();
				hashMap.put("apporavalmsg", rst.getString(1));
				hashMap.put("Requested", rst.getString(2));
				hashMap.put("role", rst.getString(3));
				dataObject rtDataObject=new dataObject(1,"Successfully");
				rtDataObject.setDatapack(hashMap);
				return rtDataObject;
			}else {
				return new dataObject(0,"user not found!");				
			}
			
		} catch (Exception e) {System.out.println("////////");
		Linker.errorLog.error("From\t type:: \t file::  \tmethod::\t "+Thread.currentThread().getStackTrace()[1]+"\n coonection closing for psmt(or) rstm\n\n=="+ e.toString());
			e.printStackTrace();Linker.errorLog.error("From\t type:: \t file::  \tmethod::\t "+Thread.currentThread().getStackTrace()[1]+"\n coonection closing for psmt(or) rstm\n\n=="+e.toString());
		}finally {
			try {if(psmt != null) {psmt.close();}			
			} catch (Exception e2) {	e2.printStackTrace();Linker.errorLog.error("From\t type:: \t file::  \tmethod::\t "+Thread.currentThread().getStackTrace()[1]+"\n coonection closing for psmt(or) rstm\n\n=="+e2.toString());		}
			
		}
		return new dataObject(-1,"Something problem");
	}


}
