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
        if (Boolean.getBoolean("headless")) {
            System.setProperty("java.awt.headless", "true");
        } else {
            // Initialize JavaFX toolkit if not already initialized
            if (System.getProperty("javafx.embed.singleThread", "false").equals("false")) {
                new javafx.embed.swing.JFXPanel();
            }
        }
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
     * Description: This test checks if the login method correctly handles the scenario where the password is null.
     * It verifies that an error alert is shown and the login result is false.
     *
     * Input: The test case sets the username to "testUser" and the password to null.
     *
     * Output: The test case verifies that the error alert is shown using the notification object,
     * and the login result is false.
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
	 * Description: This test checks if the login method correctly handles the scenario where the username is null.
     * It verifies that an error alert is shown and the login result is false.
	 *
	 * Input: The test case sets the password to "testUser" and the username to null.
	 *
	 * Output:the expected output is a boolean value indicating whether the login was successful or not. 
	 * In this case, the result is expected to be false since the username is null.
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
	 * Description: This test case verifies the behavior of the loginController's login method when the user is not found.
	 *
	 * Input: The input for this test is a username and password combination where the user is not found in the system.
	 *
	 * Output:The expected output is a boolean value indicating whether the login was successful or not. In this case, the result is expected to be false since the user is not found.
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
	 * Tests the login functionality when the password is incorrect.
	 *
	 * Description: Verifies the behavior of the loginController's login method when the provided password is incorrect.
	 * Sets up necessary mock behavior for the 'userController' dependency and creates a mock user with a different password.
	 * The login method is called with the username and incorrect password.
	 * 
	 * Input:The input for this test is a username and an incorrect password combination.
	 *
	 * Output: The expected output is a boolean value indicating whether the login was successful or not.
	 *  In this case, the result is expected to be false since the password is incorrect.
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
	 * Description:Verifies the behavior of the loginController's login method when the user is already logged in.
	 * Sets up necessary mock behavior for the 'userController' dependency and creates a mock user with a matching password and 'loggedin' status set to "yes".
	 * The login method is called with the username and password, expecting a false result and verifying an error alert message.
	 * 
	 * Input: The input for this test is a username and a password combination where the user is already logged in.
	 *
	 * Output:The expected output is a boolean value indicating whether the login was successful or not.
	 *  In this case, the result is expected to be false since the user is already logged in.
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
	 * Tests the login functionality when all parameters are valid.
	 *
	 * Description: Verifies the behavior of the loginController's login method when all parameters are valid, including valid username,
	 *  password, and user status.

	 * Input: The input for this test is a username and a password combination where all parameters are valid.

	 * Output: The expected output is a boolean value indicating whether the login was successful or not. 
	 * In this case, the result is expected to be true since all parameters are valid.
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
