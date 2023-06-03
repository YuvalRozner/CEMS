package gui;

import client.ChatClient;
import client.ClientUI;
import controllers.JDBC.Msg;
import controllers.JDBC.MsgType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;

public class LecturerMenuController extends AbstractController {
	
    @FXML
    private Label welcomeLbl;
	
	@FXML
    void approveGrades(ActionEvent event) {
		
    }

    @FXML
    void createQuestion(ActionEvent event) throws Exception {
		ChatClient.screens.putIfAbsent("createQuestion", new CreateQuestionController());
		ChatClient.getScreen("createQuestion").start("createQuestion");
    }

    @FXML
    void createTest(ActionEvent event) throws Exception {
    	ChatClient.screens.putIfAbsent("createTest", new CreateTestController());
		ChatClient.getScreen("createTest").start("createTest");
    }

    @FXML
    void executeTest(ActionEvent event) {

    }


    @FXML
    void reports(ActionEvent event) throws Exception {
    	ChatClient.screens.putIfAbsent("lecturerTestView", new LecturerTestViewController());
		ChatClient.getScreen("lecturerTestView").start("lecturerTestView");
    }

    @FXML
    void runningTest(ActionEvent event) throws Exception {
    	
    }
    
	public void logout(ActionEvent event) throws Exception {
    	Msg msg = new Msg(MsgType.update);
		msg.setTableToUpdate("cems.user");
		msg.setSet("loggedin", "no");
    	msg.setWhere("username", ChatClient.user.getUsername());
    	msg.setWhere("password", ChatClient.user.getPassword());
    	ClientUI.send(msg);
		((Node)event.getSource()).getScene().getWindow().hide();
		ChatClient.screens.putIfAbsent("login", new LoginController());
		ChatClient.getScreen("login").display();
	}

}
