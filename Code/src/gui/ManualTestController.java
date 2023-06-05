package gui;

import client.ChatClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ManualTestController extends AbstractController{

    @FXML
    private Label Course;

    @FXML
    private Label NameCourse;

    @FXML
    private Button donebtn;

    @FXML
    private Button downbtn;

    @FXML
    private Label timer;

    @FXML
    private Button upbtn;

    @FXML
    void backBtn(ActionEvent event) {
    	((Node)event.getSource()).getScene().getWindow().hide();
		ChatClient.getScreen("StartTest").display();
    }

}