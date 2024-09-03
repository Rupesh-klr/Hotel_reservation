package temp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Jdbc.Linker;

//import Jdbc.*;

public class JdbcconnectionCheck {
	public static PreparedStatement psmt =null;
	public static ResultSet  rst =null;
	public static void main(String[] args) {
		Linker Link= new Linker();
		Connection conn;
		System.out.println("started ..");
		try {
			conn = Linker.getConn();
			if(conn==null) {System.out.println("coonnection is null.Terminated");}
			else {
				String qry = "select 1";
				psmt = conn.prepareStatement(qry);
				rst = psmt.executeQuery();
				if( rst.next() ) {
					System.out.print(rst.getString(1)+ " from table");
				}
			}
			Linker.statusLog.info("FROM LIKKER 0");
			Linker.debugLog.info("FROM LINKKER 0");
			Linker.errorLog.error("FROM LINKKER 0");
		}catch (Exception e) {System.out.println("Execution error occured.Terminated");Linker.errorLog.error(e.toString()+"From\n type:: \tfile::  \tmethod::");}
		finally {
			try {
				if(rst != null) {rst.close();}
				if(psmt != null) {psmt.close();}
				if(Linker.getConn() != null) { Linker.getConn().close();}
			} catch (Exception e2) {System.out.println("failed to disconnect are close connection.");}
			System.out.println("Ended ..");
		}	
		//ending try block & END of method
	}

}


