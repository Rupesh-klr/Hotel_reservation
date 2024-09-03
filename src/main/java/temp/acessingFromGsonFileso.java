package temp;

import java.util.ArrayList;
import org.apache.log4j.Logger;  
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;

import Jdbc.Linker;
import dao.hoteldao;
import dao.roomdao;
import dao.userdao;
import entites.*;

public class acessingFromGsonFileso {
		static Logger errorLog = Logger.getLogger("errorLogger"); 
	   static Logger debugLog  = Logger.getLogger("debugLogger"); 
	   static Logger statusLog = Logger.getLogger("statusLogger");  
	public acessingFromGsonFileso(){
		
	}
	public static void main(String[] args) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		dataObject resObj =null;
		ArrayList< Object> list=new ArrayList<>();
		
		System.out.println("hello------\n\n\n\n\n");
		userdao dao=new userdao(Linker.getConn());
		user user=new user();
		user.setId(1002 );
		user.setUserName("mainchange");
		user.setUserNumber("12394567" );
		user.setUserEmail( "rk.gmail.com");
		user.setUserEmail( "rup0@datazoic.com");
		user.setRole("owner");
		user.setPhotoRef("NTH");
		resObj=dao.editmember(user, 1001);
	
		System.out.println(	gson.toJson(resObj)	  );

	}
	 
}
