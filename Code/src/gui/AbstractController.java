package gui;

import client.ClientUI;
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
		Parent root = FXMLLoader.load(getClass().getResource("/gui/"+fxmlName+".fxml"));	
		scene = new Scene(root);
		primaryStage.setTitle(fxmlName);
		primaryStage.setScene(scene);
		primaryStage.show();	 	   
	}

	public static void setPrimaryStage(Stage primaryStage) {
		AbstractController.primaryStage = primaryStage;
	}
	
	public void display() {
		//((Stage)scene.getWindow()).show();
		primaryStage.setTitle(fxmlName);
		primaryStage.setScene(scene);
		primaryStage.show();	
	}
	
	public void exitBtn(ActionEvent event) throws Exception {
		//System.out.println("exit " + getFxmlName()); // the name is not working..
		try {
			ClientUI.chat.accept("disconnected");
			System.exit(0);}
		catch(Throwable t) {
			System.out.println("error getExitBtn");
		}
		System.exit(0); //exit 
	}

	public String getFxmlName() {
		return fxmlName;
	}
}
