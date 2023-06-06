package server;

import gui.ServerController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CEMSserverUI extends Application {

	public static void main(String args[]) throws Exception {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ServerPort.fxml"));
		Parent root = loader.load();
		ServerController serverController = loader.getController();
		Scene scene = new Scene(root);
		primaryStage.setTitle("Server");
		primaryStage.setScene(scene);

		primaryStage.setOnCloseRequest(event -> {
			event.consume(); // Prevent the default close action
			serverController.disconnect(null);
			System.out.println("Exit CEMS Server app.");
			System.exit(0);
		});
		primaryStage.show();
	}
}
