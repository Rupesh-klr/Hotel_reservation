package Jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import org.apache.log4j.Logger;  

public class Linker{	
	private final static  String Dbtype= getDmysqltype();
	public static int isenabled = 0; 
	private static  String conURlString="jdbc:sqlserver://192.168.3.125;database=SQLTraining",
			userName ="RupeshKrish",
			userPassword="ruRk!sql56", 
			className = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	public class mssqlDbdetails{	////mssql	
		public final static  String conURlString="jdbc:sqlserver://192.168.3.125;database=SQLTraining",
				userName ="RupeshKrish",userPassword="ruRk!sql56", 
				className = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	}
public class mysqlDbdetails{	//mysql	
		public final static  String conURlString="jdbc:mysql://localhost:3306/klr",
				userName ="root",
				userPassword="Admin@123", 
				className = "com.mysql.cj.jdbc.Driver";
	}	
	private static Connection conn;
	public static Logger errorLog = Logger.getLogger("errorLogger"),debugLog  = Logger.getLogger("debugLogger"), statusLog = Logger.getLogger("statusLogger");
	public static String getDBtype() {
		return Dbtype;
	}
	public static String getDmysqltype() {
		return "mysql";
	}
	public static String getDmssqltype() {
		return "mssql";
	}
// method used for connection to Database common method to ( handling with Database )
	public static Connection getConn() {
		if( isenabled ==0) {
			setmyvalues();
		}
		System.out.print("conntetion establisted");
		Linker.statusLog.info("connection establised");
		Linker.debugLog.info("connection establised");
		try {
			Class.forName(className);
			conn= DriverManager.getConnection(conURlString,userName,userPassword);
		} catch (Exception e) {
			Linker.errorLog.error("From\t type:: \t file::  \tmethod::\t "+Thread.currentThread().getStackTrace()[1]+"\ncoonection establishing failed\n\n=="+e.toString());
			e.printStackTrace();
		}
		return conn;
	}
private static void setmyvalues() {
		// TODO Auto-generated method stub
		if(Dbtype == getDmysqltype()) {
			conURlString = mysqlDbdetails.conURlString;
			userName = mysqlDbdetails.userName;
			userPassword = mysqlDbdetails.userPassword;
			className = mysqlDbdetails.className;
		}else if( Dbtype == getDmssqltype()) {
			conURlString = mssqlDbdetails.conURlString;
			userName = mssqlDbdetails.userName;
			userPassword = mssqlDbdetails.userPassword;
			className = mssqlDbdetails.className;
		}else {
			conURlString = mysqlDbdetails.conURlString;
			userName = mysqlDbdetails.userName;
			userPassword = mysqlDbdetails.userPassword;
			className = mysqlDbdetails.className;			
		}
		isenabled +=1;
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

/*// using for normal jabva class
 
package Jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import org.apache.log4j.Logger;  

public class Linker{	
	private final static  String Dbtype= "mysql";
	public static int isenabled = 0; 
	private static  String conURlString="jdbc:sqlserver://192.168.3.125;database=SQLTraining",
			userName ="RupeshKrish",
			userPassword="ruRk!sql56", 
			className = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	public class mssqlDbdetails{	////mssql	
		public final static  String conURlString="jdbc:sqlserver://192.168.3.125;database=SQLTraining",
				userName ="RupeshKrish",userPassword="ruRk!sql56", 
				className = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	}
public class mysqlDbdetails{	//mysql	
		public final static  String conURlString="jdbc:mysql://localhost:3306/klr",
				userName ="root",
				userPassword="Admin@123", 
				className = "com.mysql.cj.jdbc.Driver";
	}	
	private static Connection conn;
//	public static Logger errorLog = Logger.getLogger("errorLogger"),debugLog  = Logger.getLogger("debugLogger"), statusLog = Logger.getLogger("statusLogger");
  
// method used for connection to Database common method to ( handling with Database )
	public static Connection getConn() {
		if( isenabled ==0) {
			setmyvalues();
		}
//		Linker.statusLog.info("connection establised");
//		Linker.debugLog.info("connection establised");
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn= DriverManager.getConnection(conURlString,userName,userPassword);
//			 conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=master;user=chaithra;password=12345678;");

		} catch (Exception e) {
//			Linker.errorLog.error("From\t type:: \t file::  \tmethod::\t "+Thread.currentThread().getStackTrace()[1]+"\ncoonection establishing failed\n\n=="+e.toString());
			e.printStackTrace();
		}
		return conn;
	}
private static void setmyvalues() {
		// TODO Auto-generated method stub
		if(Dbtype == "mysql") {
			conURlString = mysqlDbdetails.conURlString;
			userName = mysqlDbdetails.userName;
			userPassword = mysqlDbdetails.userPassword;
			className = mysqlDbdetails.className;
		}else if( Dbtype == "mssql") {
			conURlString = mssqlDbdetails.conURlString;
			userName = mssqlDbdetails.userName;
			userPassword = mssqlDbdetails.userPassword;
			className = mssqlDbdetails.className;
		}else {
			conURlString = mysqlDbdetails.conURlString;
			userName = mysqlDbdetails.userName;
			userPassword = mysqlDbdetails.userPassword;
			className = mysqlDbdetails.className;			
		}
		isenabled +=1;
	}
	// method used for closing connection to Database connection.( after handling with Database )
	public static void colseConn() {
		try {
			if(conn!=null) { 				conn.close(); 			}
		} catch (Exception e) {
			e.printStackTrace();System.out.println("can't able to close connection");
//			Linker.errorLog.error("From\t type:: \t file::  \tmethod::\t "+Thread.currentThread().getStackTrace()[1]+"\n coonection closing failed\n\n=="+e.toString());	
		}
	}
	
}
*/