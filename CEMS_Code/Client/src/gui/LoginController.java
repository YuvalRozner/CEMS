package gui;

import JDBC.Msg;
import JDBC.MsgType;
import client.ChatClient;
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
    	ChatClient.resetUser();
    	if(!login(userNameTxt.getText(), passwordTxt.getText())) return; 
    	User user = ChatClient.user;
	    switch(user.getPremission()) {
    		case "lecturer":
        		start("lecturerMenu", "login");
        		((LecturerMenuController)ChatClient.getScreen("lecturerMenu")).setWelcome("Welcome " + user.getName());
    			break;
    		case "student":
    			start("studentMenu" , "login");
    			((StudentMenuController)ChatClient.getScreen("studentMenu")).setWelcome("Welcome " + user.getName());
    			break;
    		case "hod":
    			start("hodMenu" , "login");
    			//((HODMenuController)ChatClient.getScreen("HODMenu")).setWelcome("Welcome " + user.getName());
    			break;
    	}
    }

	private boolean login(String username, String password) {
		if(username==null || password==null) { System.out.println("you must enter username and password."); return false;}
    	Msg msg = new Msg(MsgType.select);
    	msg.setSelect("*");
    	msg.setFrom("cems.user");
    	msg.setWhere("username", username);
    	//msg.setWhere("password", password);
    	
    	sendMsg(msg);
    	User user = ChatClient.user;
    	if(user==null) { System.out.println("cant find this usename."); return false;}
    	if(!user.getPassword().equals(password)) { System.out.println("username or password are wrong."); return false;}
    	//if(user.getLoggedin().equals("yes")) { System.out.println("this user is already loggedin in another device."); return false;}
    	msg = new Msg(MsgType.update);
		msg.setTableToUpdate("cems.user");
		msg.setSet("loggedin", "yes");
    	msg.setWhere("username", username);
    	//msg.setWhere("password", password);
    	sendMsg(msg);
    	
    	
    	return true;
	}
}
