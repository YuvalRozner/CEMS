package gui;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import JDBC.Msg;
import client.ChatClient;
import controllers.UserController;
import enteties.User;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import notifications.NotificationAlertsController;


class LoginControllerTest{
	
	private LoginController loginController;
	private NotificationAlertsController notification;
	private TestChatClient testChatClient;
	private UserController userController;
	
	
	/**
	 * The TestChatClient class implements the ChatClientInterface interface and represents a test chat client.
	 * It keeps track of the user and the sent messages.
	 */
	public class TestChatClient implements ChatClientInterface {
	    User user;
	    private List<Msg> sentMessages = new ArrayList<>();
	    /**
	     * Sends a message to the chat.
	     *
	     * @param msg The message to be sent.
	     */
	    @Override
	    public void sendMsgIF(Msg msg) {
	    	sentMessages.add(msg);
	    }
	    /**
	     * Retrieves the user associated with the chat client.
	     *
	     * @return The user associated with the chat client.
	     */
	    @Override
	    public User getUser() {
	    	return user;
	    }
	    /**
	     * Sets the user associated with the chat client.
	     *
	     * @param user The user to be set.
	     */
		@Override
		public void setUser(User user) {
			this.user=user;
			
		}
	}

    @BeforeEach
    void setUp() throws Exception {
        //set up login controller object
        testChatClient = new TestChatClient();
        loginController  = new LoginController(testChatClient);
        notification = Mockito.mock(NotificationAlertsController.class);
        loginController.setNotificationAlertsController(notification);
        userController =  Mockito.mock(UserController.class);
        loginController.setNotificationAlertsController(notification);
        loginController.setuserController(userController);
        
    }
    /**
     * Description: verifies the behavior of the login method when the password is null.
     *
     * Input: username= "testUser" and the password= null.
     *
     * Expected Result: use notification.showErrorAlert with the msg "you must enter username and password." method return false.
     */
	@Test
	void loginTestPasswordIsNull() {
        // Arrange
        String username = "testUser";
        String password = null;  	
        
		// Act
        boolean result = loginController.login(username, password);
        
        // Assert
        Mockito.verify(notification).showErrorAlert("you must enter username and password.");
        
        assertFalse(result);
        // Add any additional assertions based on your requirements
        
	}
	/**
	 * Description: verifies the behavior of the login method when the username is null.
	 *
	 * Input: password="testUser" , username= null.
	 *
	 * Expected Result:use notification.showErrorAlert with the msg "you must enter username and password." method return false.
	 * 
	 */
	@Test
	void loginTestUsernameIsNull() {
        // Arrange
        String username = null;
        String password = "testUser";  	
        
		// Act
        boolean result = loginController.login(username, password);
        
        // Assert
        Mockito.verify(notification).showErrorAlert("you must enter username and password.");
        
        assertFalse(result);
        // Add any additional assertions based on your requirements
        
	}
	/**
	 * Description: verifies the behavior of the login method when the user is not null.
	 *
	 * Input: username = "testuser", password = "testpassword",user=null.
	 *
	 * Expected Result:use notification.showErrorAlert with the msg "cant find this usename." method return false.
	 */
	@Test
	void loginTestUserIsNull() {
		// Arrange
        String username = "testuser";
        String password = "testpassword";

        
        // Mock the behavior of the dependencies
        Mockito.when(userController.selectUser(username)).thenReturn(null);
        
        // Act
        boolean result = loginController.login(username, password);

        // Assert
        assertFalse(result);
        Mockito.verify(notification).showErrorAlert("cant find this usename.");
    
	}
	
	/**
	 * Description: verifies the behavior of the login method when got incorrect password.
	 * 
	 * Input:username = "testuser" ,  password = "incorrectpassword".
	 *
	 * Expected Result: use notification.showErrorAlert with the msg "username or password are wrong." method return false.
	 */
	@Test
	void loginTestIncorrectPassword() {
	    // Arrange
	    String username = "testuser";
	    String password = "incorrectpassword";
	    
	    // Create a mock user with a different password
	    User user=new User();
	    user.setPassword("correctpassword");
	    testChatClient.setUser(user);
	    
	    // Mock the behavior of the dependencies
	    Mockito.when(userController.selectUser(username)).thenReturn(null);
	    
	    // Act
	    boolean result = loginController.login(username, password);

	    // Assert
	    assertFalse(result);
	    Mockito.verify(notification).showErrorAlert("username or password are wrong.");

	}
	/**
	 * Description: verifies the behavior of the login method when user is loggedin.
	 * 
	 * Input: username = "testuser", password = "incorrectpassword", Loggedin="yes".
	 *
	 * Expected Result: use notification.showErrorAlert with the msg "this user is already loggedin in another device." method return false.
	 */
	@Test
	void loginTestlogginIsYes() {
	    // Arrange
	    String username = "testuser";
	    String password = "incorrectpassword";
	    
	    // Create a mock user with a different password
	    User user=new User();
	    user.setPassword("incorrectpassword");
	    user.setLoggedin("yes");
	    testChatClient.setUser(user);
	    
	    // Mock the behavior of the dependencies
	    Mockito.when(userController.selectUser(username)).thenReturn(null);
	    
	    // Act
	    boolean result = loginController.login(username, password);

	    // Assert
	    assertFalse(result);
	    Mockito.verify(notification).showErrorAlert("this user is already loggedin in another device.");
	    
	}
	/**
	 * Description:  verifies the behavior of the login method when user is loggedin with all parameter is valid and user is loggesout.

	 * Input: username = "testuser", password = "incorrectpassword",  Loggedin="no".

	 * Expected Result:method return true.
	 */
	@Test
	void loginTestAllparametersAreValids() {
	    // Arrange
	    String username = "testuser";
	    String password = "incorrectpassword";
	    
	    // Create a mock user with a different password
	    User user=new User();
	    user.setPassword("incorrectpassword");
	    user.setLoggedin("no");
	    testChatClient.setUser(user);
	    
	    // Mock the behavior of the dependencies
	    Mockito.when(userController.selectUser(username)).thenReturn(null);
	    Mockito.when(userController.getLoggedinMsg(user,"yes")).thenReturn(null);
	    
	    // Act
	    boolean result = loginController.login(username, password);

	    // Assert
	    assertTrue(result);
	    
	}
	
	
	/**
	 * Starts the JavaFX application by setting up and displaying the login screen.
	*/
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/" + "Login" + ".fxml"));
		Parent root = loader.load();
		ChatClient.screens.put("Login", loader.getController());
		Scene tmpScene = new Scene(root);
		((SceneSetter)loader.getController()).setScene(tmpScene);
		//((SceneSetter)loader.getController()).setPrevScreen(prevScreen);
		//ChatClient.lastCurrentScreen = loader.getController();
		//primaryStage.setTitle(convertToSentence(fxmlName));
		primaryStage.setScene(tmpScene);
		primaryStage.centerOnScreen();
		primaryStage.show();
		
	}

}
