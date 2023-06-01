package gui;

import client.ChatClient;
import client.ClientController;
import client.ClientUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ClientConnectionController extends AbstractController  {
	
    @FXML
    private Button btnConnect;
    
    @FXML
    private TextField inputIp;

    @FXML
    private TextField inputPort;

    @FXML
    private Label lblIP;

    @FXML
    private Label lblPort;
    
	@FXML
	private Button btnExit = null;
	
	
    @FXML
    void connect(ActionEvent event) {
    	try {
    		ClientUI.chat = new ClientController(inputIp.getText(), Integer.valueOf(inputPort.getText()));
    		ChatClient.screens.putIfAbsent("Menu", new MenuController());
    		ChatClient.getScreen("Menu").start("Menu"); 

    	} catch(Throwable t) {System.out.println("input ip and port - error connecting.");}    	
    }
    
}
