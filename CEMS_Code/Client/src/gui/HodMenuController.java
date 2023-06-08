package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class HodMenuController extends AbstractController{

    @FXML
    void ShowQuestions(ActionEvent event) throws Exception {
    	start("questionTable", "hodMenu");
    }

    @FXML
    void approveChange(ActionEvent event) throws Exception {
		start("approveChanges", "hodMenu");
    }

    @FXML
    void logOut(ActionEvent event) throws Exception{
    	super.logout();
    	super.backBtn(event);
    }

    @FXML
    void reports(ActionEvent event) throws Exception {
		start("chooseReportType", "hodMenu");
    }

    @FXML
    void showTest(ActionEvent event) throws Exception {
		start("showTestsData", "hodMenu");
    }

}
