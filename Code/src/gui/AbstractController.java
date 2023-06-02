package gui;

import client.ClientUI;
import controllers.JDBC.Msg;
import controllers.JDBC.MsgType;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public abstract class AbstractController {
	private static Stage primaryStage;
	private Scene scene; 
	private String fxmlName;
	
	public void start(String fxmlName) throws Exception {
		this.fxmlName = fxmlName;
		Parent root = FXMLLoader.load(getClass().getResource("/fxml/"+fxmlName+".fxml"));	
		scene = new Scene(root);
		primaryStage.setTitle(fxmlName);
		primaryStage.setScene(scene);
		primaryStage.centerOnScreen();
		primaryStage.show();	 	   
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
			ClientUI.chat.accept(new Msg(MsgType.disconnect));
		}catch(Throwable t) {
			System.out.println("error getExitBtn");
			System.exit(0);
		}
		System.exit(0); //exit 
	}

	public String getFxmlName() {
		return fxmlName;
	}
}
