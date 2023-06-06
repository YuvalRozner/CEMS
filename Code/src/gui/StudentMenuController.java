package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class StudentMenuController extends AbstractController{

    @FXML
    void logOut(ActionEvent event) throws Exception {
    	super.logout();
    	super.backBtn(event);
    }

    @FXML
    void showGrade(ActionEvent event) throws Exception {
    	start("showGrade", "studentMenu");
    }

    @FXML
    void startTest(ActionEvent event) throws Exception{
		start("startTest", "studentMenu");
    }

}