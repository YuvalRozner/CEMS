package gui;

import java.io.IOException;


import client.ChatClient;
import client.ClientController;
import client.ClientUI;
import client.ChatIF;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import ocsf.client.AbstractClient;
import enteties.Question;

public  class AcademicFrameController   {
	
	private static int itemIndex = 3;
	
	@FXML
	private Button btnExit = null;
	
	@FXML
	private Button btnShow = null;
	
	public void show(ActionEvent event) throws Exception {
		
		try {
			ClientUI.chat.accept("SELECT * FROM cems.question;");
		}catch(Throwable t) {System.out.println("accept dont work");};
		
		TableViewSample tableViewSample = new TableViewSample(ChatClient.questionList);
		FXMLLoader loader = new FXMLLoader();
		((Node)event.getSource()).getScene().getWindow().hide(); //hiding primary window
		Stage primaryStage = new Stage();
		tableViewSample.start(primaryStage);
	}

	public void start(Stage primaryStage) throws Exception {	
		Parent root = FXMLLoader.load(getClass().getResource("/gui/AcademicFrame.fxml"));	
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/gui/AcademicFrame.css").toExternalForm());
		primaryStage.setTitle("Academic Managment Tool");
		primaryStage.setScene(scene);
		primaryStage.show();	 	   
	}
	
	public void getExitBtn(ActionEvent event) throws Exception {
		System.out.println("exit Academic Tool");
		try {ClientUI.chat.client.quit();}
		catch(Throwable t) {
			System.out.println("error getExitBtn");
		}
		System.exit(0); //exit 
	}
	
	public  void display(String message) {
		System.out.println("message");	
	}
}
