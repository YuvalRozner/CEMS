package gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

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
    
    public ShowQuestionController() {
    	// need to mark the correct answer
    }

}
