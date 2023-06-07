package gui;

import client.ChatClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ShowQuestionController extends AbstractController{

    @FXML
    private TextField A1;

    @FXML
    private TextField A2;

    @FXML
    private TextField A3;

    @FXML
    private TextField A4;

    @FXML
    private Button back;

    @FXML
    private TextField question;

    @FXML
    void backBtn(ActionEvent event) {
    	((Node)event.getSource()).getScene().getWindow().hide();
		ChatClient.getScreen("HodMenu").display();
    }
    
    public ShowQuestionController() {
    	// need to mark the correct answer
    }

}
