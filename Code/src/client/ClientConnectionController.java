package client;

import java.io.IOException;

import gui.AbstractController;
import gui.LoginController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ClientConnectionController extends AbstractController  {
	
    @FXML
    private Button btnConnect;
    
    @FXML
    private TextField inputIp;

    @FXML
    private TextField inputPort;
    
	@FXML
	private Button btnExit = null;
	
    @FXML
    void connect(ActionEvent event) {
    	try {
    		try{ ClientUI.client = new ChatClient(inputIp.getText(), Integer.valueOf(inputPort.getText()));
    		}catch (IOException exception) { System.out.println("Error: Can't setup connection!"+ " Terminating client.");
    		      System.exit(1);  }
    		//ChatClient.screens.putIfAbsent("Menu", new MenuController());
    		//ChatClient.getScreen("Menu").start("Menu"); 
    		
    		ChatClient.screens.putIfAbsent("login", new LoginController());
    		ChatClient.getScreen("login").start("login");

    	} catch(Throwable t) {System.out.println("input ip and port - error connecting.");}    	
    }
    
}
