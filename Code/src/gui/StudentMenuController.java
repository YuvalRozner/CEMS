package gui;

import client.ChatClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;

public class StudentMenuController extends AbstractController{

    @FXML
    void logOut(ActionEvent event) {
    	((Node)event.getSource()).getScene().getWindow().hide();
		ChatClient.screens.putIfAbsent("login", new LoginController());
		ChatClient.getScreen("login").display();
    }

    @FXML
    void showGrade(ActionEvent event) throws Exception {
    	ChatClient.screens.putIfAbsent("ShowGrades", new ShowGradeController());
		ChatClient.getScreen("ShowGrades").start("ShowGrades");
    }

    @FXML
    void startTest(ActionEvent event) {

    }

}