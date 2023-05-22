package client;
import gui.ClientGetQuestionController;
import javafx.application.Application;
import javafx.stage.Stage;

public class ClientUI extends Application {
	public static ClientController chat; //only one instance

	public static void main( String args[] ) throws Exception { 
		    launch(args);  
	   }
	 
	@Override
	public void start(Stage primaryStage) throws Exception {		  		
		ClientGetQuestionController clientGetQuestionController = new ClientGetQuestionController(); // create first client window.
		primaryStage.setOnCloseRequest(event -> {
			event.consume(); // Prevent the default close action
			try {
				clientGetQuestionController.getExitBtn(null);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Exit CEMS Server app.");
			System.exit(0);
		});
		clientGetQuestionController.start(primaryStage);
	}
}