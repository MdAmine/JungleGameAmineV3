package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection{

	  private static String url = "jdbc:mysql://localhost/junglegamev4";
	  private static String user = "root";
	  private static String passwd = "";
	  private static Connection connect;
	   
	  public static Connection getInstance(){
	    if(connect == null){
	    	try {
			    Class.forName( "com.mysql.jdbc.Driver" );
			} catch ( ClassNotFoundException e ) {
				System.out.println("not found") ;
			}
	    	try {
	        connect = DriverManager.getConnection(url, user, passwd);
	      } catch (SQLException e) {
	        e.printStackTrace();
	      }
	    }      
	    return connect;
	  }   
	}