package gui;

import client.ChatClient;
import client.ClientUI;
import controllers.JDBC.Msg;
import controllers.JDBC.MsgType;
import enteties.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController extends AbstractController{
	
    @FXML
    private TextField userNameTxt;
    
    @FXML
    private PasswordField passwordTxt;

    @FXML
    void connect(ActionEvent event) throws Exception {
    	
    	String username = userNameTxt.getText();
    	String password = passwordTxt.getText();
    	Msg msg = new Msg(MsgType.select);
    	msg.setSelect("*");
    	msg.setFrom("cems.user");
    	msg.setWhere("username", username);
    	msg.setWhere("password", password);
    	ClientUI.send(msg);
    	User user = ChatClient.user;
    	if(user!=null) {
    		System.out.println("user:  "+user);
    		if(user.getLoggedin().equals("no")) {
	    		msg = new Msg(MsgType.update);
	    		msg.setTableToUpdate("cems.user");
	    		msg.setSet("loggedin", "yes");
	        	msg.setWhere("username", username);
	        	msg.setWhere("password", password);
	        	ClientUI.send(msg);
	        	switch(user.getPremission()) {
	    		case "lecturer":
	    	    	ChatClient.screens.putIfAbsent("lecturerMenu", new LecturerMenuController());
	    			ChatClient.getScreen("lecturerMenu").start("lecturerMenu");
	    			break;
	    		case "student":
	    	    	//ChatClient.screens.putIfAbsent("studentMenu", new StudentMenuController());
	    			//ChatClient.getScreen("studentMenu").start("studentMenu");
	    			break;
	    		case "hod":
	    	    	//ChatClient.screens.putIfAbsent("hodMenu", new HODMenuController());
	    			//ChatClient.getScreen("hodMenu").start("hodMenu");
	    			break;
				default:
					break;
	    	}
    		}
    	}

    	
    	
    	//temporary, it will take us to the lecturer menu: ......
    	//ChatClient.screens.putIfAbsent("lecturerMenu", new LecturerMenuController());
		//ChatClient.getScreen("lecturerMenu").start("lecturerMenu");
    }
}
