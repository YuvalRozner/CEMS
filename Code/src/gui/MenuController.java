package gui;

import client.ChatClient;
import client.ClientUI;
import controllers.JDBC.DB_controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import juliaScreens.CreateTestController;

public class MenuController extends AbstractController  {
	
	@FXML
	private Button btnShow = null;
	
    
	public void show(ActionEvent event) throws Exception {
		
		try {
			ClientUI.chat.accept(DB_controller.getAllQuestion());
		}catch(Throwable t) {System.out.println("accept dont work");};
		
		
		
		//the start of the new table
		
		QuestionTableController q = new QuestionTableController();
		ChatClient.screens.putIfAbsent("questionTable",q );
		ChatClient.getScreen("questionTable").start("questionTable");
		
		//the start of the old table
		/*
		TableViewSample tableViewSample = new TableViewSample(ChatClient.questionList);
		//FXMLLoader loader = new FXMLLoader();
		((Node)event.getSource()).getScene().getWindow().hide(); //hiding primary window
		Stage primaryStage = new Stage();
		tableViewSample.start(primaryStage);
*/
	}
	
	
	
	public void backBtn(ActionEvent event) throws Exception {
		((Node)event.getSource()).getScene().getWindow().hide();
		ChatClient.getScreen("ClientConnection").display();
	}

}