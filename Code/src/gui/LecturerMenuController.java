package gui;

import client.ChatClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class LecturerMenuController extends AbstractController {
	
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
    void logOut(ActionEvent event) {

    }

    @FXML
    void reports(ActionEvent event) throws Exception {
    	ChatClient.screens.putIfAbsent("lecturerStaticsReport", new LecturerStaticsReportController());
		ChatClient.getScreen("lecturerStaticsReport").start("lecturerStaticsReport");
    }

    @FXML
    void runningTest(ActionEvent event) throws Exception {
    	ChatClient.screens.putIfAbsent("lecturerTestView", new LecturerTestViewController());
		ChatClient.getScreen("lecturerTestView").start("lecturerTestView");
    }


}
