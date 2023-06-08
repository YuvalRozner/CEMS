package gui;

import JDBC.Msg;
import JDBC.MsgType;
import client.ChatClient;
import client.ClientUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public abstract class AbstractController implements SceneSetter {
	private static Stage primaryStage;
	public static Msg msgReceived;
	private Scene scene;
	private String fxmlName;
	public String prevScreen;

	public void start(String fxmlName, String prevScreen) throws Exception {
		this.fxmlName = fxmlName;

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/" + fxmlName + ".fxml"));
		Parent root = loader.load();
		ChatClient.screens.put(fxmlName, loader.getController());
		Scene tmpScene = new Scene(root);

		((SceneSetter)loader.getController()).setScene(tmpScene);
		((SceneSetter)loader.getController()).setPrevScreen(prevScreen);

		primaryStage.setTitle(fxmlName);
		primaryStage.setScene(tmpScene);
		primaryStage.centerOnScreen();
		primaryStage.show();
	}

	public void setPrevScreen(String prevScreen) {
		this.prevScreen = prevScreen;
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
			logout();
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
	
	public static Msg getMsgReceived() {
		return msgReceived;
	}

	public static void setDataReceived(Msg msgReceived) {
		AbstractController.msgReceived = msgReceived;
	}
	
	public void backBtn(ActionEvent event) throws Exception {
		((Node)event.getSource()).getScene().getWindow().hide();
		ChatClient.getScreen(prevScreen).display();
	}
	
	public void logout() throws Exception {
		if(ChatClient.user==null) return;
    	Msg msg = new Msg(MsgType.update);
		msg.setTableToUpdate("cems.user");
		msg.setSet("loggedin", "no");
    	msg.setWhere("username", ChatClient.user.getUsername());
    	sendMsg(msg);
	}
}
