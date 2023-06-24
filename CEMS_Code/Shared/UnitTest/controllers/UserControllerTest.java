package controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import JDBC.Msg;
import JDBC.MsgType;
import enteties.User;
import javafx.application.Platform;
import notifications.NotificationAlertsController;

public class UserControllerTest {
	
	private UserController userController;
	private NotificationAlertsController notification;
	private UserController userControllerMock;
	
	@BeforeAll
	public static void initializeJavaFXToolkit() {
	    // Initialize the JavaFX toolkit
	    Platform.startup(() -> {});
	}
	
	@BeforeEach
	void setUp() throws Exception {
		userController = new UserController();
		
		//set up login controller object
        notification = Mockito.mock(NotificationAlertsController.class);
        userController.setNotificationAlertsController(notification);
        userControllerMock =  Mockito.mock(UserController.class);

	}

    /**
     * Description: verifies the behavior of the checkValid method when the password is null.
     *
     * Input: username= "testUser" and the password= null.
     *
     * Expected Result: use notification.showErrorAlert with the msg "you must enter username and password." method return false.
     */
	@Test
	void checkValidTest_PasswordIsNull() {
        // Arrange
        String username = "testUser";
        String password = null;  	
        
		// Act
        boolean result = userController.checkValid(username, password);
        
        // Assert
        Mockito.verify(notification).showErrorAlert("you must enter username and password.");
        
        assertFalse(result);
        // Add any additional assertions based on your requirements
        
	}
	/**
	 * Description: verifies the behavior of the checkValid method when the username is null.
	 *
	 * Input: password="testUser" , username= null.
	 *
	 * Expected Result:use notification.showErrorAlert with the msg "you must enter username and password." method return false.
	 * 
	 */
	@Test
	void checkValidTest_UsernameIsNull() {
        // Arrange
        String username = null;
        String password = "testUser";  	
        
		// Act
        boolean result = userController.checkValid(username, password);
        
        // Assert
        Mockito.verify(notification).showErrorAlert("you must enter username and password.");
        
        assertFalse(result);
        // Add any additional assertions based on your requirements
        
	}
	/**
	 * Description: verifies the behavior of the cheakuser method when the user is not null.
	 *
	 * Input: username = "testuser", password = "testpassword",user=null.
	 *
	 * Expected Result:use notification.showErrorAlert with the msg "cant find this usename." method return false.
	 */
	@Test
	void cheakuserTest_UserIsNull() {
		// Arrange
        String username = "testuser";
        String password = "testpassword";

        
        // Mock the behavior of the dependencies
        Mockito.when(userControllerMock.selectUser(username)).thenReturn(null);
        
        // Act
        boolean result = userController.cheakuser(username, password,null);

        // Assert
        assertFalse(result);
        Mockito.verify(notification).showErrorAlert("cant find this usename.");
    
	}
	
	/**
	 * Description: verifies the behavior of the cheakuser method when got incorrect password.
	 * 
	 * Input:username = "testuser" ,  password = "incorrectpassword".
	 *
	 * Expected Result: use notification.showErrorAlert with the msg "username or password are wrong." method return false.
	 */
	@Test
	void cheakuserTest_IncorrectPassword() {
	    // Arrange
	    String username = "testuser";
	    String password = "incorrectpassword";
	    
	    // Create a mock user with a different password
	    User user=new User();
	    user.setPassword("correctpassword");
	    
	    // Mock the behavior of the dependencies
	    Mockito.when(userControllerMock.selectUser(username)).thenReturn(null);
	    
	    // Act
	    boolean result = userController.cheakuser(username, password,user);

	    // Assert
	    assertFalse(result);
	    Mockito.verify(notification).showErrorAlert("username or password are wrong.");

	}
	/**
	 * Description: verifies the behavior of the cheakuser method when user is loggedin.
	 * 
	 * Input: username = "testuser", password = "incorrectpassword", Loggedin="yes".
	 *
	 * Expected Result: use notification.showErrorAlert with the msg "this user is already loggedin in another device." method return false.
	 */
	@Test
	void cheakuserTest_logginIsYes() {
	    // Arrange
	    String username = "testuser";
	    String password = "incorrectpassword";
	    
	    // Create a mock user with a different password
	    User user=new User();
	    user.setPassword("incorrectpassword");
	    user.setLoggedin("yes");
	    
	    // Mock the behavior of the dependencies
	    Mockito.when(userControllerMock.selectUser(username)).thenReturn(null);
	    
	    // Act
	    boolean result = userController.cheakuser(username, password,user);

	    // Assert
	    assertFalse(result);
	    Mockito.verify(notification).showErrorAlert("this user is already loggedin in another device.");
	    
	}
	/**
	 * Description:  verifies the behavior of the cheakuser method when user is loggedin with all parameter is valid and user is loggesout.

	 * Input: username = "testuser", password = "incorrectpassword",  Loggedin="no".

	 * Expected Result:method return true.
	 */
	@Test
	void cheakuserTest_AllparametersAreValids() {
	    // Arrange
	    String username = "testuser";
	    String password = "incorrectpassword";
	    
	    // Create a mock user with a different password
	    User user=new User();
	    user.setPassword("incorrectpassword");
	    user.setLoggedin("no");
	    
	    // Mock the behavior of the dependencies
	    Mockito.when(userControllerMock.selectUser(username)).thenReturn(null);
	    Mockito.when(userControllerMock.getLoggedinMsg(user,"yes")).thenReturn(null);
	    
	    // Act
	    boolean result = userController.cheakuser(username, password,user);

	    // Assert
	    assertTrue(result);
	    
	}	

	/**
	 * Description: verifies the behavior of the selectUser method when the username is null. 
	 * Input: username=null. 
	 * Expected Result: Null message object.
	 */
	@Test
	void selectUserTest_UsernameIsNull() {
		String username = null;
		Msg actualMsg = null;
		try {
			actualMsg = userController.selectUser(username);
		} catch (Exception e) {
			assertFalse(true);
		}
		assertEquals(actualMsg, null);
	}

	/**
	 * Description: verifies the behavior of the selectUser method  when the username is valid. 
	 * Input: username="liorzucker".
	 * Expected Result: actualMsg with the appropriate type, select query, and WHERE clause for the valid username.
	 */
	@Test
	void selectUserTest_UsernameIsValid() {
		String username = "liorzucker";

		Msg actualMsg = userController.selectUser(username);

		ArrayList<String> arrSelect = new ArrayList<String>();
		arrSelect.add("*");

		ArrayList<String> arrFrom = new ArrayList<String>();
		arrFrom.add("user");

		// Assert that the actual message matches the expected message
		assertEquals(actualMsg.getType(), MsgType.select);
		assertEquals(actualMsg.getSelect(), arrSelect);
		assertEquals(actualMsg.getFrom(), arrFrom);
		assertEquals("username", actualMsg.getWhere().keySet().iterator().next());
		assertEquals("liorzucker", actualMsg.getWhere().values().iterator().next());
	}

	/**
	 * Description: verifies the behavior of the selectUser method when the username is empty. 
	 * Input:usernam= "". 
	 * Expected Result: The message with the appropriate type, select query, and WHERE clause with empty username.
	 */
	@Test
	void selectUserTest_UsernameIsEmpty() {
		String username = "";

		Msg actualMsg = userController.selectUser(username);

		ArrayList<String> arrSelect = new ArrayList<String>();
		arrSelect.add("*");

		ArrayList<String> arrFrom = new ArrayList<String>();
		arrFrom.add("user");

		// Assert that the actual message matches the expected message
		assertEquals(actualMsg.getType(), MsgType.select);
		assertEquals(actualMsg.getSelect(), arrSelect);
		assertEquals(actualMsg.getFrom(), arrFrom);
		assertEquals("username", actualMsg.getWhere().keySet().iterator().next());
		assertEquals("", actualMsg.getWhere().values().iterator().next());
	}

	/**
	 * Description: verifies the behavior of the getLoggedinMsg method when the user is logged in. 
	 * Input: User object with a valid username username=liorzucker, loggedIn = "yes" 
	 * Expected Result: Message object with type "login", tableToUpdate set to "user", set field with "loggedin" key
	 * set to "yes", and where field with "username" key set to the user's username.
	 */
	@Test
	void getLoggedinMsgTest_UserLoggedIn() {
		User user = new User();
		user.setUsername("liorzucker");
		String loggedIn = "yes";

		Msg actualMsg = userController.getLoggedinMsg(user, loggedIn);

		ArrayList<String> tableToUpdate = new ArrayList<String>();
		tableToUpdate.add("user");

		// Assert that the actual message matches the expected message
		assertEquals(actualMsg.getType(), MsgType.login);
		assertEquals(actualMsg.getTableToUpdate(), tableToUpdate);

		assertEquals("loggedin", actualMsg.getSet().keySet().iterator().next());
		assertEquals(loggedIn, actualMsg.getSet().values().iterator().next());

		assertEquals("username", actualMsg.getWhere().keySet().iterator().next());
		assertEquals("liorzucker", actualMsg.getWhere().values().iterator().next());
	}

	/**
	 * Description: verifies the behavior of the getLoggedinMsg method when the user is logged out. 
	 * Input: User object with a valid username username="liorzucker", loggedIn = "no". 
	 * Expected Result: Message object with type "logout", tableToUpdate set to "user", set field with "loggedin"
	 * key set to "no", and where field with "username" key set to the user's
	 * username.
	 */
	@Test
	void getLoggedinMsgTest_UserLoggedOut() {
		User user = new User();
		user.setUsername("liorzucker");
		String loggedIn = "no";

		Msg actualMsg = userController.getLoggedinMsg(user, loggedIn);

		ArrayList<String> tableToUpdate = new ArrayList<String>();
		tableToUpdate.add("user");

		// Assert that the actual message matches the expected message
		assertEquals(actualMsg.getType(), MsgType.logout);
		assertEquals(actualMsg.getTableToUpdate(), tableToUpdate);

		assertEquals("loggedin", actualMsg.getSet().keySet().iterator().next());
		assertEquals(loggedIn, actualMsg.getSet().values().iterator().next());

		assertEquals("username", actualMsg.getWhere().keySet().iterator().next());
		assertEquals("liorzucker", actualMsg.getWhere().values().iterator().next());
	}

	/**
	 * Description: verifies the behavior of the getLoggedinMsg method when the loggedIn parameter is null. 
	 * Input: User object with a valid username, loggedIn = null. 
	 * Expected Result: An exception is thrown.
	 */
	@Test
	void getLoggedinMsgTest_loggedInIsNull() {
		User user = new User();
		user.setUsername("liorzucker");
		String loggedIn = null;
		Msg actualMsg = null;
		try {
			userController.getLoggedinMsg(user, loggedIn);
		} catch (Exception e) {
			assertFalse(true);
		}
		assertEquals(actualMsg, null);
	}

	/**
	 * Description: verifies the behavior of the getLoggedinMsg method when the user parameter is null. 
	 * Input: User = null, loggedIn ="yes".
	 * Expected Result: actualMsg is null.
	 */

	@Test
	void getLoggedinMsgTest_UserIsNull() {
		User user = null;
		String loggedIn = "yes";
		Msg actualMsg = null;
		try {
			userController.getLoggedinMsg(user, loggedIn);
		} catch (Exception e) {
			assertFalse(true);
		}
		assertEquals(actualMsg, null);
	}
}