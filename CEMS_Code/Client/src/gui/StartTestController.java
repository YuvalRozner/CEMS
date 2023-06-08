package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class StartTestController extends AbstractController{

    @FXML
    private Button backbtn;

    @FXML
    private TextField codeTextField;

    @FXML
    private Button connectbtn;

    @FXML
    private TextField idTextField;

    @FXML
    private Button startbtn;

    @FXML
    void connectBtn(ActionEvent event) {
    	idTextField.setDisable(false);
    }

    @FXML
    void startBtn(ActionEvent event) throws Exception {
    	start("onlineTest", "startTest");
    	//start("manualTest", "startTest");
    }
    public StartTestController() {
    	idTextField=new TextField();
    	idTextField.setDisable(true);
    }

}
