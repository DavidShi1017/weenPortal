package com.jingtong.platform.ireport.util;

import java.sql.Connection;
import java.sql.DriverManager;


public class JDBCConnection {
	public static Connection getConnection(){
		try {
	          String drivername = IreportUtil.getProperty ("jdbc.driverClassName");
	          String url = IreportUtil.getProperty ("jdbc.url");
	          String username=IreportUtil.getProperty("jdbc.username");
	          String password=IreportUtil.getProperty("jdbc.password");
	          Class.forName(drivername);
	          Connection con = DriverManager.getConnection(url, username, password);
	          return con;
		   }
		  catch(Exception e){
		    e. printStackTrace();
		  }
		  return null;
	}
	public static void closeConntion(Connection conn){
		try {
			if(conn!=null&&!conn.isClosed()){
				conn.close();
			}
		} catch (Exception e) {
			
		}
		
	}
}
