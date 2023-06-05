package gui;

import client.ChatClient;
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
    void backBtn(ActionEvent event) {

    }

    @FXML
    void connectBtn(ActionEvent event) {
    	idTextField.setDisable(false);
    }

    @FXML
    void startBtn(ActionEvent event) throws Exception{
    	////start manual test
    	ChatClient.screens.putIfAbsent("ManualTest", new ManualTestController());
		ChatClient.getScreen("ManualTest").start("ManualTest");
		
		//start online test
    }
    public StartTestController() {
    	idTextField=new TextField();
    	idTextField.setDisable(true);
    }

}
