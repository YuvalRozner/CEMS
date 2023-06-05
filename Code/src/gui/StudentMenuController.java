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
		ChatClient.getScreen("ShowGrades").start("ShowGrade");
    }

    @FXML
    void startTest(ActionEvent event) throws Exception{
    	ChatClient.screens.putIfAbsent("StartTest", new ShowGradeController());
		ChatClient.getScreen("StartTest").start("StartTest");
    }

}