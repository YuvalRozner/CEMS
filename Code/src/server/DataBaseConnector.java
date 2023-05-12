package server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnector {

	  public boolean connectionToDataBase(CEMSserver Cserver)
	  {
		    try 
			{
	          Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
	          System.out.println("Driver definition succeed");
	      } catch (Exception ex) {
	      	/* handle the error*/
	      	 System.out.println("Driver definition failed");
	      	 }
	      try 
	      {
	    	  //conn  = DriverManager.getConnection("jdbc:mysql://localhost/cems?serverTimezone=IST","root","DorShabat123");
	    	  Cserver.setConn(DriverManager.getConnection("jdbc:mysql://localhost/cems?serverTimezone=IST","root","DorShabat123"));
	      } catch (SQLException ex) 
		    {/* handle any errors*/
	      System.out.println("SQLException: " + ex.getMessage());
	      System.out.println("SQLState: " + ex.getSQLState());
	      System.out.println("VendorError: " + ex.getErrorCode());
	      return false;
	      }     
		return true;
	  }

}