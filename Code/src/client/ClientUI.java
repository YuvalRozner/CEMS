package client;

import controllers.JDBC.Msg;
import gui.AbstractController;
import gui.CountDownTimerController;
import javafx.application.Application;
import javafx.stage.Stage;

public class ClientUI extends Application {
	public static ChatClient client;

	public static void main(String args[]) throws Exception {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		AbstractController.setPrimaryStage(primaryStage);

		// original line:
		new ClientConnectionController().start("ClientConnection");

		primaryStage.setOnCloseRequest(event -> { // Prevent the default close action
			event.consume();
			try {
				ChatClient.getScreen("ClientConnection").exitBtn(null);
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("Exit Client app.");
			System.exit(0);
		});

	}
}