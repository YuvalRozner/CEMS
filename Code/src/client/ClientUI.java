package client;
import gui.AbstractController;
import gui.ClientConnectionController;
import javafx.application.Application;
import javafx.stage.Stage;

public class ClientUI extends Application {
	public static ClientController chat; //only one instance

	public static void main( String args[] ) throws Exception { 
		    launch(args);  
	   }
	 
	@Override
	public void start(Stage primaryStage) throws Exception {
		AbstractController.setPrimaryStage(primaryStage);
		ClientConnectionController clientConnectionController = new ClientConnectionController(); // create first client window.
		ChatClient.screens.putIfAbsent("ClientConnection", clientConnectionController);
		primaryStage.setOnCloseRequest(event -> {
			event.consume(); // Prevent the default close action
			try {
				clientConnectionController.exitBtn(null);
			} catch (Exception e) {	e.printStackTrace();}
			System.out.println("Exit Client app.");
			System.exit(0);
		});
		clientConnectionController.start("ClientConnection");
	}
}