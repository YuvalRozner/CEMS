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
    	sendMsg(msg);
    	User user = ChatClient.user;
    	if(user!=null) {
    		System.out.println("user:  "+user);
    		//if(user.getLoggedin().equals("no")) {
	    		msg = new Msg(MsgType.update);
	    		msg.setTableToUpdate("cems.user");
	    		msg.setSet("loggedin", "yes");
	        	msg.setWhere("username", username);
	        	msg.setWhere("password", password);
	        	sendMsg(msg);
	        	switch(user.getPremission()) {
	    		case "lecturer":
	        		new LecturerMenuController().start("lecturerMenu");
	        		((LecturerMenuController)ChatClient.getScreen("lecturerMenu")).setWelcome("Welcome " + user.getName());
	    			break;
	    		case "student":
	    			//new StudentMenuController().start("studentMenu");
	    			break;
	    		case "hod":
	    			//new HODMenuController().start("hodMenu");
	    			break;
				default:
					break;
	    	//}
    		}
    	}

    	
    	
    	//temporary, it will take us to the lecturer menu: ......
    	//ChatClient.screens.putIfAbsent("lecturerMenu", new LecturerMenuController());
		//ChatClient.getScreen("lecturerMenu").start("lecturerMenu");
    }
}
