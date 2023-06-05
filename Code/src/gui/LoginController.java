package gui;

import client.ChatClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController extends AbstractController{
    @FXML
    private PasswordField passwordTxt;

    @FXML
    private TextField userNameTxt;
    
     // conect with lecturer user.
    
    @FXML
    void connect(ActionEvent event) throws Exception {
    	//temporary, it will take us to the lecturer munu: ......
    	ChatClient.screens.putIfAbsent("lecturerMenu", new LecturerMenuController());
		ChatClient.getScreen("lecturerMenu").start("lecturerMenu");
    }
    
    
     // conect with student user.
     /*
    @FXML
    void connect(ActionEvent event) throws Exception {
    	//temporary, it will take us to the lecturer munu: ......
    	ChatClient.screens.putIfAbsent("studentMenu", new StudentMenuController());
		ChatClient.getScreen("studentMenu").start("studentMenu");
		
    }*/
}
