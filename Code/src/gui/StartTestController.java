package gui;

import client.ChatClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
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
    	((Node)event.getSource()).getScene().getWindow().hide();
		ChatClient.getScreen("studentMenu").display();
    }

    @FXML
    void connectBtn(ActionEvent event) {
    	idTextField.setDisable(false);
    }

    @FXML
    void startBtn(ActionEvent event) throws Exception {
    	((Node)event.getSource()).getScene().getWindow().hide();
    	ChatClient.screens.putIfAbsent("OnlineTest", new StudentTestRunController());
		ChatClient.getScreen("OnlineTest").start("OnlineTest");
    }
    public StartTestController() {
    	idTextField=new TextField();
    	idTextField.setDisable(true);
    }

}
