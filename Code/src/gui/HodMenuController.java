package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HodMenuController extends AbstractController{

    @FXML
    private Label welcomLbl;

    @FXML
    void questionsBtn(ActionEvent event) throws Exception {
    	new QuestionTableController().start("QuestionTable");
    }

    @FXML
    void testBtn(ActionEvent event) {
    	
    }
    
    @FXML
    void reportsBtn(ActionEvent event) {
    	
    }

   

}
