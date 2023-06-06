package server;

import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnector { 
	@SuppressWarnings("deprecation")
	public boolean connectionToDataBase(CEMSserver Cserver)
	  {
		    try 
			{
	          Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
	          CEMSserver.serverController.addConsole("Driver definition succeed.\n");
	          System.out.println("Driver definition succeed");
	      } catch (Exception ex) {
	    	 CEMSserver.serverController.addConsole("Driver definition failed.\n");
	      	 System.out.println("Driver definition failed"); 	 }
	      try 
	      {
	    	  Cserver.setConn(DriverManager.getConnection("jdbc:mysql://"+CEMSserver.serverController.getServerIdTxt()+"/"+CEMSserver.serverController.getDBNameTxt()+"?serverTimezone=IST",
	    			  CEMSserver.serverController.getDBUsernameTxt(),
	    			  CEMSserver.serverController.getPasswordTxt()));
	      } catch (SQLException ex) 
		    {/* handle any errors*/
	    	  CEMSserver.serverController.addConsole("SQLException: " + ex.getMessage()+".\n");
	    	  CEMSserver.serverController.addConsole("SQLState: " + ex.getSQLState()+".\n");
	    	  CEMSserver.serverController.addConsole("VendorError: " + ex.getErrorCode()+".\n");
	      System.out.println("SQLException: " + ex.getMessage());
	      System.out.println("SQLState: " + ex.getSQLState());
	      System.out.println("VendorError: " + ex.getErrorCode());
	      return false;
	      }     
		return true;
	  }
}