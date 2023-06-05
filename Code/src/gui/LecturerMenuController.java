package gui;

import client.ChatClient;
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
		new CreateQuestionController().start("createQuestion", "lecturerMenu");
    }

    @FXML
    void createTest(ActionEvent event) throws Exception {
		new CreateTestController().start("createTest", "lecturerMenu");
    }

    @FXML
    void executeTest(ActionEvent event) throws Exception {
    	new MenuController().start("menu", "lecturerMenu"); //temporary........................
    }


    @FXML
    void reports(ActionEvent event) throws Exception {
		new LecturerTestViewController().start("lecturerTestView", "lecturerMenu");
    }

    @FXML
    void runningTest(ActionEvent event) throws Exception {
    	
    }
    
	public void logout(ActionEvent event) throws Exception {
    	super.logout();
    	((Node)event.getSource()).getScene().getWindow().hide();
    	ChatClient.getScreen("login").display();
	}

	public void setWelcome(String name) {
		welcomeLbl.setText(name);
	}
}
