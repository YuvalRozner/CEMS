package server;

import java.net.InetAddress;

import gui.ServerPortFrameController;
import javafx.application.Application;
import javafx.stage.Stage;

public class CEMSserverUI extends Application {
	final public static int DEFAULT_PORT = 5555;
	
	public static InetAddress clientAddress;
	public static String clientHostname;
	
	public static ServerPortFrameController aFrame;
	
	//public static Vector<Student> students=new Vector<Student>();

	public static void main( String args[] ) throws Exception
	   {   
		 launch(args);
	  } // end main
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub				  		
		aFrame = new ServerPortFrameController(); // create StudentFrame
		 
		aFrame.start(primaryStage);
	}
	
	public static void runServer(String p)
	{
		 int port = 0; //Port to listen on

	        try
	        {
	        	port = Integer.parseInt(p); //Set port to 5555
	          
	        }
	        catch(Throwable t)
	        {
	        	System.out.println("ERROR - Could not connect!");
	        }
	    	
	        CEMSserver sv = new CEMSserver(port);
	        sv.getClientAddress();
	        sv.getClientHostname();
	        try {
	        	aFrame.setClientAddress(clientAddress);
	        	aFrame.setClientHostName(clientHostname);
	        }
	        catch(Throwable t) {
	        	System.out.println("error printing the id and host name of client..");}
	        	
	        try 
	        {
	          sv.listen(); //Start listening for connections
	        } 
	        catch (Exception ex) 
	        {
	          System.out.println("ERROR - Could not listen for clients!");
	        }
	}
	

}

