package gui;

import JDBC.Msg;
import client.ChatClient;
import enteties.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * The LoginController class handles the logic and user interface interactions for the login screen.
 * It is responsible for authenticating users and displaying appropriate error messages.
 *
 */
public class LoginController extends AbstractController{
	
	/**
	 * input
	 */
    @FXML
    private TextField userNameTxt;
	/**
	 * input
	 */
    @FXML
    private PasswordField passwordTxt;

    /**
     * Handles the connect button action event.
     *
     * @param event The ActionEvent object representing the button click event.
     * @throws Exception if an error occurs during the connection process.
     */
    @FXML
    void connect(ActionEvent event) throws Exception {
    	ChatClient.resetUser();
    	if(!login(userNameTxt.getText(), passwordTxt.getText())) return; 
    	User user = ChatClient.user;
    	start(user.getPremission()+"Menu", "login");
    	((Menu)ChatClient.getScreen(user.getPremission()+"Menu")).setWelcome("Welcome " + user.getName());
    }

    /**
     * Authenticates the user with the provided username and password.
     *
     * @param username The username entered by the user.
     * @param password The password entered by the user.
     * @return {@code true} if the login is successful, {@code false} otherwise.
     */
	private boolean login(String username, String password) {
		if(username==null || password==null) { notification.showErrorAlert("you must enter username and password."); return false;}
    	sendMsg(userController.selectUser(username));
    	User user = ChatClient.user;
    	if(user==null) { notification.showErrorAlert("cant find this usename."); return false;}
    	if(!user.getPassword().equals(password)) { notification.showErrorAlert("username or password are wrong."); return false;}
    	if(user.getLoggedin().equals("yes")) { notification.showErrorAlert("this user is already loggedin in another device."); return false;}
    	Msg msg  = userController.getLoggedinMsg(user, "yes");
    	System.out.println("login msg: "+ msg);
    	sendMsg(msg);
    	return true;
	}
}