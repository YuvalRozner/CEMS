package client;
import gui.AcademicFrameController;
import javafx.application.Application;
import javafx.stage.Stage;

public class ClientUI extends Application {
	public static ClientController chat; //only one instance

	public static void main( String args[] ) throws Exception	   { 
		    launch(args);  
	   }
	 
	@Override
	public void start(Stage primaryStage) throws Exception {
		chat= new ClientController("localhost", 5555);		  		
		AcademicFrameController aFrame = new AcademicFrameController(); // create first client window.
		aFrame.start(primaryStage);
	}
}