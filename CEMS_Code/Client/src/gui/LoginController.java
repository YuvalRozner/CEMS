package gui;

import client.ChatClient;
import controllers.UserController;
import enteties.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import notifications.NotificationAlertsController;

public class LoginController extends AbstractController{
	
    @FXML
    private TextField userNameTxt;
    
    @FXML
    private PasswordField passwordTxt;
    
    /**
	 * object to use the UserController class method.
	 */
    private static UserController userController = new UserController();
    /**
	 * object to use the notifications class.
	 */
    private static NotificationAlertsController notification = new NotificationAlertsController();

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
    		case "Hod":
    			start("hodMenu" , "login");
    			((HodMenuController)ChatClient.getScreen("hodMenu")).setWelcome("Welcome " + user.getName());
    			break;
    	}
    }

	private boolean login(String username, String password) {
		if(username==null || password==null) { notification.showErrorAlert("you must enter username and password."); return false;}
    	sendMsg(userController.selectUser(username));
    	User user = ChatClient.user;
    	if(user==null) { notification.showErrorAlert("cant find this usename."); return false;}
    	if(!user.getPassword().equals(password)) { notification.showErrorAlert("username or password are wrong."); return false;}
    	//if(user.getLoggedin().equals("yes")) { notification.showErrorAlert("this user is already loggedin in another device."); return false;}
    	sendMsg(userController.getLoggedinMsg(user, "yes"));
    	return true;
	}
}
