package server;

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
		Parent root = FXMLLoader.load(getClass().getResource("/server/ServerPort.fxml"));
		
		Scene scene = new Scene(root);
		primaryStage.setTitle("Server");
		primaryStage.setScene(scene);
		
		primaryStage.show();	
	}
	
}

