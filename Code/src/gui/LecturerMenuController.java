package gui;

import client.ChatClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;

public class LecturerMenuController extends AbstractController {
	
	
	@FXML
    void approveGrades(ActionEvent event) throws Exception {
		ChatClient.screens.putIfAbsent("aprroveGrade", new AprroveGradeController());
		ChatClient.getScreen("aprroveGrade").start("aprroveGrade");
    }

    @FXML
    void createQuestion(ActionEvent event) throws Exception {
		ChatClient.screens.putIfAbsent("createQuestion", new ShowGradeController());
		ChatClient.getScreen("createQuestion").start("createQuestion");
    }

    @FXML
    void createTest(ActionEvent event) throws Exception {
    	ChatClient.screens.putIfAbsent("createTest", new CreateTestController());
		ChatClient.getScreen("createTest").start("createTest");
    }

    @FXML
    void executeTest(ActionEvent event) throws Exception{
    	ChatClient.screens.putIfAbsent("executeTest", new executeTestController());
		ChatClient.getScreen("executeTest").start("executeTest");
    }

    @FXML
    void logOut(ActionEvent event) {

    }

    @FXML
    void reports(ActionEvent event) throws Exception {
    	ChatClient.screens.putIfAbsent("lecturerTestView", new LecturerTestViewController());
		ChatClient.getScreen("lecturerTestView").start("lecturerTestView");
    }

    @FXML
    void runningTest(ActionEvent event) throws Exception {
    	ChatClient.screens.putIfAbsent("runningTest", new runningTestController());
		ChatClient.getScreen("runningTest").start("runningTest");
    	
    }
    
	public void logout(ActionEvent event) throws Exception {
		((Node)event.getSource()).getScene().getWindow().hide();
		ChatClient.screens.putIfAbsent("login", new LoginController());
		ChatClient.getScreen("login").display();
	}


}
