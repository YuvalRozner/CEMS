package gui;

import client.ChatClient;
import client.ClientUI;
import controllers.JDBC.Msg;
import controllers.JDBC.MsgType;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public abstract class AbstractController implements SceneSetter {
	private static Stage primaryStage;
	private Scene scene;
	private String fxmlName;

	public void start(String fxmlName) throws Exception {
		this.fxmlName = fxmlName;

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/" + fxmlName + ".fxml"));
		Parent root = loader.load();
		ChatClient.screens.put(fxmlName, loader.getController());

		scene = new Scene(root);

		((SceneSetter) loader.getController()).setScene(scene);

		primaryStage.setTitle(fxmlName);
		primaryStage.setScene(scene);
		primaryStage.centerOnScreen();
		primaryStage.show();
	}

	public void setScene(Scene scene) {
		this.scene = scene;
	}

	public static void sendMsg(Msg msg) {
		ClientUI.client.handleMessageFromClientUI(msg);
	}

	public static void setPrimaryStage(Stage primaryStage) {
		AbstractController.primaryStage = primaryStage;
	}

	public void display() {
		primaryStage.setTitle(fxmlName);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public void exitBtn(ActionEvent event) throws Exception {
		try {
			sendMsg(new Msg(MsgType.disconnect));
		} catch (Throwable t) {
			System.out.println("error getExitBtn");
			System.exit(0);
		}
		System.exit(0); // exit
	}

	public String getFxmlName() {
		return fxmlName;
	}
}
