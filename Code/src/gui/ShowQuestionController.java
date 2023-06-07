package gui;

import client.ChatClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ShowQuestionController extends AbstractController{

	@FXML
    private Label A1;

    @FXML
    private Label A2;

    @FXML
    private Label A3;

    @FXML
    private Label A4;

    @FXML
    private Label answer;

    @FXML
    private Button back;

    @FXML
    private Label question;

    @FXML
    void backBtn(ActionEvent event) {
    	((Node)event.getSource()).getScene().getWindow().hide();
		ChatClient.getScreen("HodMenu").display();
    }
    
    public ShowQuestionController() {
    	// need to mark the correct answer
    }

}
