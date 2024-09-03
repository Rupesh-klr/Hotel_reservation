package Jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import org.apache.log4j.Logger;  

public class Linker{	
	private final static  String conURlString="jdbc:sqlserver://192.168.3.125;database=SQLTraining",
			userName ="RupeshKrish",userPassword="ruRk!sql56";
	private static Connection conn;
	public static Logger errorLog = Logger.getLogger("errorLogger"),debugLog  = Logger.getLogger("debugLogger"), statusLog = Logger.getLogger("statusLogger");
  
// method used for connection to Database common method to ( handling with Database )
	public static Connection getConn() {
		Linker.statusLog.info("connection establised");
		Linker.debugLog.info("connection establised");
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn= DriverManager.getConnection(conURlString,userName,userPassword);
//			 conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=master;user=chaithra;password=12345678;");

		} catch (Exception e) {
			Linker.errorLog.error("From\t type:: \t file::  \tmethod::\t "+Thread.currentThread().getStackTrace()[1]+"\ncoonection establishing failed\n\n=="+e.toString());
			e.printStackTrace();
		}
		return conn;
	}
// method used for closing connection to Database connection.( after handling with Database )
	public static void colseConn() {
		try {
			if(conn!=null) { 				conn.close(); 			}
		} catch (Exception e) {
			e.printStackTrace();System.out.println("can't able to close connection");
			Linker.errorLog.error("From\t type:: \t file::  \tmethod::\t "+Thread.currentThread().getStackTrace()[1]+"\n coonection closing failed\n\n=="+e.toString());	
		}
	}
	
}