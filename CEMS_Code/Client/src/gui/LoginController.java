package gui;

import JDBC.Msg;
import client.ChatClient;
import controllers.UserController;
import enteties.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import notifications.NotificationAlertsController;

/**
 * The LoginController class handles the logic and user interface interactions for the login screen.
 * It is responsible for authenticating users and displaying appropriate error messages.
 *
 */
public class LoginController extends AbstractController{	
	
	private UserController userController=new UserController();
    private NotificationAlertsController notificationAlertsController=new NotificationAlertsController();
    private ChatClientInterface chatClientIF;

    public LoginController() {
    	this.chatClientIF=new ChatClientController ();
    }
    
    public LoginController(ChatClientInterface chatClientIf) {
    	this.chatClientIF=chatClientIf;
    }
	
	
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
    	start(user.getPremission()+"Menu", "Login");
    	((Menu)ChatClient.getScreen(user.getPremission()+"Menu")).setWelcome("Welcome " + user.getName());
    }

    /**
     * Authenticates the user with the provided username and password.
     *
     * @param username The username entered by the user.
     * @param password The password entered by the user.
     * @return {@code true} if the login is successful, {@code false} otherwise.
     */
	public boolean login(String username, String password) {
		if(username==null || password==null) { notificationAlertsController.showErrorAlert("you must enter username and password."); return false;}
		chatClientIF.sendMsgIF(userController.selectUser(username));
    	User user = chatClientIF.getUser();
    	if(user==null) { notificationAlertsController.showErrorAlert("cant find this usename."); return false;}
    	if(!user.getPassword().equals(password)) { notificationAlertsController.showErrorAlert("username or password are wrong."); return false;}
    	if(user.getLoggedin().equals("yes")) { notificationAlertsController.showErrorAlert("this user is already loggedin in another device."); return false;}
    	Msg msg  = userController.getLoggedinMsg(user, "yes");
    	System.out.println("login msg: "+ msg);
    	chatClientIF.sendMsgIF(msg);
    	return true;
	}
	
	

	public class ChatClientController implements ChatClientInterface {
		User user;
	    @Override
	    public void sendMsgIF(Msg msg) {
	    	sendMsg(msg);
	    }

		@Override
		public User getUser() {
			return ChatClient.user;
		}

		@Override
		public void setUser(User user) {
			user=ChatClient.user;		
		}
	}
	
	
	public void setNotificationAlertsController(NotificationAlertsController notification) {
		
		this.notificationAlertsController=notification;
	}
	
	public void setuserController(UserController userController) {
		
		this.userController=userController;
	}
}