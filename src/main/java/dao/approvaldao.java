package dao;
import java.lang.invoke.StringConcatFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import Jdbc.Linker;
import entites.*;

public class approvaldao {
	private Connection conn;
	private PreparedStatement psmt;
	private ResultSet rst;
	public  approvaldao(Connection conn) {
		super();
		this.conn = conn;
		psmt=null;
		rst=null;
	}
	public String addRequest(approval a) {
		int sta=0;
		try {
			if(rst != null) {rst.close();}
			if(psmt != null) {psmt.close();}
			String sql = "insert into klr.ApprovalRequests (ProofType,Proof,AttachmentMessage,id) values (?,?,?,?)";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, a.getProofType());
			psmt.setString(2, a.getProof());
			psmt.setString(3, a.getAttachmentMessage());
			psmt.setInt(4, a.getId());
			sta=psmt.executeUpdate();
			sql="update klr.Userlist set Requested=2 ,apporvalmsg=? where id=?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, a.getAttachmentMessage());
			psmt.setInt(2, a.getId());
			sta+=psmt.executeUpdate();
			if(sta==2) {
				Linker.statusLog.info("Approval request is add for .User id "+a.getId());
				return "Requested ! Wait till responds comes.";
			}
			return "Something went wrong! request again!.";
		} catch (Exception e) {Linker.errorLog.error("From\t type:: \t file::  \tmethod::\t "+Thread.currentThread().getStackTrace()[1]+"\n coonection closing for psmt(or) rstm\n\n=="+ e.toString());e.printStackTrace();return "Try again later server is busy!\nConnection failed";
		}finally {
			try {if(rst != null) {rst.close();}
				if(psmt != null) {psmt.close();}
			} catch (Exception e2) {e2.printStackTrace();Linker.errorLog.error("From\t type:: \t file::  \tmethod::\t "+Thread.currentThread().getStackTrace()[1]+"\n coonection closing for psmt(or) rstm\n\n=="+ e2.toString());}
		}

	}
	public approval getapprovalPack(int approvalId) {
		approval a =null;
		int sta=0;
		try {
			if(rst != null) {rst.close();}
			if(psmt != null) {psmt.close();}
			String sql = "select * from klr.ApprovalRequests   with (NOLOCK) where isClosed=0 and ApprovalId=?";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, approvalId);
			rst=psmt.executeQuery();
			if(rst.next()) {
				a=new approval();
				a.setApprovalId(rst.getInt(1));
				a.setProofType(rst.getInt(2));
				a.setProof(rst.getString(3));
				a.setAttachmentMessage(rst.getString(4));
				a.setId(rst.getInt(5));
				a.setClosebyId(rst.getInt(6));
				a.setIsClosed(rst.getInt(7));
				a.setClosedReplay(rst.getString(8));
				a.setClosedOn(rst.getString(9));
				Linker.statusLog.info("Approval request for Approval Id "+approvalId+" is available.");
				return a;
			}
			
		} catch (Exception e) {Linker.errorLog.error("From\t type:: \t file::  \tmethod::\t "+Thread.currentThread().getStackTrace()[1]+"\n coonection closing for psmt(or) rstm\n\n=="+ e.toString());e.printStackTrace(); return a;
		}finally {
			try {if(rst != null) {rst.close();}
				if(psmt != null) {psmt.close();}
			} catch (Exception e2) {Linker.errorLog.error("From\t type:: \t file::  \tmethod::\t "+Thread.currentThread().getStackTrace()[1]+"\n coonection closing for psmt(or) rstm\n\n=="+ e2.toString());e2.printStackTrace();}
		}
		return null;
	}
	public ArrayList<approval> getMyApprovalReq(int id) {
		ArrayList<approval> aList=new ArrayList<approval>();
		approval a=null;
		try {
			if(rst != null) {rst.close();}
			if(psmt != null) {psmt.close();}
			String sql = "select * from klr.ApprovalRequests  with (NOLOCK)  where id=?";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, id);
			rst=psmt.executeQuery();
			while (rst.next()) {
				a=new approval();
				a.setApprovalId(rst.getInt(1));
				a.setProofType(rst.getInt(2));
				a.setProof(rst.getString(3));
				a.setAttachmentMessage(rst.getString(4));
				a.setId(rst.getInt(5));
				a.setClosebyId(rst.getInt(6));
				a.setIsClosed(rst.getInt(7));
				a.setClosedReplay(rst.getString(8));
				a.setClosedOn(rst.getString(9));
				aList.add(a);
			}
		} catch (Exception e) {e.printStackTrace();System.out.println( "Try again later server is busy!\nconnection");Linker.errorLog.error("From\t type:: \t file::  \tmethod::\t "+Thread.currentThread().getStackTrace()[1]+"\n coonection closing for psmt(or) rstm\n\n=="+ e.toString());
		}finally {
			try {if(rst != null) {rst.close();}
				if(psmt != null) {psmt.close();}
			} catch (Exception e2) {Linker.errorLog.error("From\t type:: \t file::  \tmethod::\t "+Thread.currentThread().getStackTrace()[1]+"\n coonection closing for psmt(or) rstm\n\n=="+ e2.toString());e2.printStackTrace();}
		}
		Linker.statusLog.info("Approval request status is checked for userId "+id+"With approval id : "+aList.get(0).getApprovalId());
		return aList;
	}
	public String updateApprovalByAdmin(int updateApId, int adid, String closeRepaly,int requestChageg,int userId) {
		String sql="update klr.ApprovalRequests set closebyId=? , isClosed=1 , closedReplay=? ,closedOn=GETDATE() where ApprovalId=?";		
		int sta=0;
		try {
			if(rst != null) {rst.close();}
			if(psmt != null) {psmt.close();}
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, adid);
			psmt.setString(2, closeRepaly);
			psmt.setInt(3, updateApId);
			sta = psmt.executeUpdate();
			psmt.close();
			sql= "update klr.Userlist set Requested=? , apporvalmsg=? , TypesMembership=? , Lastmodified=GETDATE() , LastmodifiedBy='admin' where id=?";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1,requestChageg );
			psmt.setString(2, "\nRepaly :\n"+closeRepaly);
			switch (requestChageg) {
			case 2:
				psmt.setString(3, "owner" );
				break;
			case 0:
				psmt.setString(3, "user" );
				break;
			case -1: 
				psmt.setString(3, "user" );
				break;
			case 3:
				psmt.setString(3, "admin" );
				break;
			}
			psmt.setInt(4, userId);
			sta += psmt.executeUpdate();
			if(sta==2) {
				Linker.statusLog.info("Admin changing approval with id has been update wih approval id "+updateApId);
				System.out.println(userId);
				return "Update approval ::"+updateApId;
			}
		} catch (Exception e) {e.printStackTrace();System.out.println( "Try again later server is busy!\nconnection");Linker.errorLog.error("From\t type:: \t file::  \tmethod::\t "+Thread.currentThread().getStackTrace()[1]+"\n coonection closing for psmt(or) rstm\n\n=="+ e.toString());
		}finally {
			try {if(rst != null) {rst.close();}
				if(psmt != null) {psmt.close();}
			} catch (Exception e2) {Linker.errorLog.error("From\t type:: \t file::  \tmethod::\t "+Thread.currentThread().getStackTrace()[1]+"\n coonection closing for psmt(or) rstm\n\n=="+ e2.toString());e2.printStackTrace();}
		}
		
		return "Not updated";
	}
	public ArrayList<approval> getNewApprovalS() {
		ArrayList<approval> aList=new ArrayList<approval>();
		approval a=null;
		try {
			if(rst != null) {rst.close();}
			if(psmt != null) {psmt.close();}
			String sql = "select * from klr.ApprovalRequests  with (NOLOCK)  where isClosed=0";
			psmt = conn.prepareStatement(sql);
			rst=psmt.executeQuery();
			while (rst.next()) {
				a=new approval();
				a.setApprovalId(rst.getInt(1));
				a.setProofType(rst.getInt(2));
				a.setProof(rst.getString(3));
				a.setAttachmentMessage(rst.getString(4));
				a.setId(rst.getInt(5));
				a.setClosebyId(rst.getInt(6));
				a.setIsClosed(rst.getInt(7));
				a.setClosedReplay(rst.getString(8));
				a.setClosedOn(rst.getString(9));
				aList.add(a);
				
			}
		} catch (Exception e) {Linker.errorLog.error("From\t type:: \t file::  \tmethod::\t "+Thread.currentThread().getStackTrace()[1]+"\n coonection closing for psmt(or) rstm\n\n=="+ e.toString());e.printStackTrace();System.out.println( "Try again later server is busy!\nconnection");
		}finally {
			try {if(rst != null) {rst.close();}
				if(psmt != null) {psmt.close();}
			} catch (Exception e2) {Linker.errorLog.error("From\t type:: \t file::  \tmethod::\t "+Thread.currentThread().getStackTrace()[1]+"\n coonection closing for psmt(or) rstm\n\n=="+ e2.toString());e2.printStackTrace();}
		}
		Linker.statusLog.info("all available new approval records are given.");
		return aList;
	}
	
}
