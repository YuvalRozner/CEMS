package server;

import java.net.InetAddress;

import gui.ServerPortFrameController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CEMSserverUI extends Application {
	
	final public static int DEFAULT_PORT = 5555;	

	public static void main( String args[] ) throws Exception
	   {   
		 launch(args);
	  } // end main
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/gui/ServerPort.fxml"));
		
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/gui/ServerPort.css").toExternalForm());
		primaryStage.setTitle("Client");
		primaryStage.setScene(scene);
		
		primaryStage.show();	
	}
	/*
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
	    	
	        //CEMSserver sv = new CEMSserver(port);
	        sv = new CEMSserver(port);
	        	
	        try 
	        {
	          sv.listen(); //Start listening for connections
	        } 
	        catch (Exception ex) 
	        {
	          System.out.println("ERROR - Could not listen for clients!");
	        }
	}
	*/

}

