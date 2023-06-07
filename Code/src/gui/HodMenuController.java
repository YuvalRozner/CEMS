package gui;

import client.ChatClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;

public class HodMenuController extends AbstractController{

    @FXML
    void ShowQuestions(ActionEvent event) throws Exception {
    	ChatClient.screens.putIfAbsent("questionTable", new QuestionTableController());
		ChatClient.getScreen("questionTable").start("questionTable");
    }

    @FXML
    void approveChange(ActionEvent event) throws Exception {
    	ChatClient.screens.putIfAbsent("ApproveChanges", new ApproveChangesController());
		ChatClient.getScreen("ApproveChanges").start("ApproveChanges");
    }

    @FXML
    void logOut(ActionEvent event) throws Exception{
    	((Node)event.getSource()).getScene().getWindow().hide();
    	ChatClient.screens.putIfAbsent("login", new LoginController());
    	ChatClient.getScreen("login").display();
    }

    @FXML
    void reports(ActionEvent event) throws Exception {
    	ChatClient.screens.putIfAbsent("ChooseReportType", new ChooseReportTypeController());
    	AbstractController screen=ChatClient.getScreen("ChooseReportType");
		screen.start("ChooseReportType");
    }

    @FXML
    void showTest(ActionEvent event) throws Exception {
    	ChatClient.screens.putIfAbsent("showTestsData", new ShowTestDataController());
    	AbstractController screen=ChatClient.getScreen("showTestsData");
		screen.start("showTestsData");
    }

}
