package gui;

import java.util.ArrayList;

import client.ChatClient;
import client.ClientUI;
import controllers.JDBC.DB_controller;
import controllers.JDBC.Msg;
import controllers.JDBC.MsgType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;

public class MenuController extends AbstractController {
	
	@FXML
	private Button btnShow = null;
    
	public void show(ActionEvent event) throws Exception {
		Msg tmpMsg = new Msg();
		tmpMsg.setType(MsgType.select);
		tmpMsg.getInfo().add(new ArrayList<String>());
		tmpMsg.getInfo().add(new ArrayList<String>());
		tmpMsg.getInfo().add(null);
		tmpMsg.getInfo().add(null);
		tmpMsg.getSelectInfo().add("*");
		tmpMsg.getFromInfo().add("question");
		try {
			ClientUI.chat.accept(tmpMsg);
			//ClientUI.chat.accept(DB_controller.getAllQuestion());
		}catch(Throwable t) {System.out.println("accept dont work");};
		
		//the start of the new table
		QuestionTableController q = new QuestionTableController();
		ChatClient.screens.putIfAbsent("questionTable",q );
		ChatClient.getScreen("questionTable").start("questionTable");		
	}
	
	
	public void backBtn(ActionEvent event) throws Exception {
		((Node)event.getSource()).getScene().getWindow().hide();
		ChatClient.screens.putIfAbsent("ClientConnection", new ClientConnectionController());
		ChatClient.getScreen("ClientConnection").display();
	}

}
