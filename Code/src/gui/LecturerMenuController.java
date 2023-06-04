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
 
    
    public LecturerMenuController() {
    	
    }
	@FXML
    void approveGrades(ActionEvent event) {
		
    }

    @FXML
    void createQuestion(ActionEvent event) throws Exception {
		new CreateQuestionController().start("createQuestion");
    }

    @FXML
    void createTest(ActionEvent event) throws Exception {
		new CreateTestController().start("createTest");
    }

    @FXML
    void executeTest(ActionEvent event) {

    }


    @FXML
    void reports(ActionEvent event) throws Exception {
		new LecturerTestViewController().start("lecturerTestView");
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
    	sendMsg(msg);
    	
		((Node)event.getSource()).getScene().getWindow().hide();
		ChatClient.getScreen("login").display();
	}

	public void setWelcome(String name) {
		welcomeLbl.setText(name);
	}
}
