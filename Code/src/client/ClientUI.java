package client;
import controllers.JDBC.Msg;
import gui.AbstractController;
import gui.CountDownTimerController;
import javafx.application.Application;
import javafx.stage.Stage;

public class ClientUI extends Application {
	public static ChatClient client;

	public static void main( String args[] ) throws Exception { 
		    launch(args);  
	}
	 
	@Override
	public void start(Stage primaryStage) throws Exception {
		AbstractController.setPrimaryStage(primaryStage);
		ClientConnectionController clientConnectionController = new ClientConnectionController(); // create first client window.
		ChatClient.screens.putIfAbsent("ClientConnection", clientConnectionController);
		primaryStage.setOnCloseRequest(event -> { // Prevent the default close action
			event.consume(); 
			try {
				clientConnectionController.exitBtn(null);
			} catch (Exception e) {	e.printStackTrace();}
			System.out.println("Exit Client app.");
			System.exit(0);
		});
		// original line: 
		clientConnectionController.start("ClientConnection");
		
		//to open another screen: 
		
	}
	
	  public static void send(Msg msg) {
		  client.handleMessageFromClientUI(msg);
	  }
}