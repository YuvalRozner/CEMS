package client;
import gui.ClientGetQuestionController;
import javafx.application.Application;
import javafx.stage.Stage;

public class ClientUI extends Application {
	public static ClientController chat; //only one instance

	public static void main( String args[] ) throws Exception  { 
		    launch(args);  
	   }
	 
	@Override
	public void start(Stage primaryStage) throws Exception {
		//chat= new ClientController("localhost", 5555);		  		
		ClientGetQuestionController clientGetQuestionController = new ClientGetQuestionController(); // create first client window.
		clientGetQuestionController.start(primaryStage);
	}
}