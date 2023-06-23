package controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import JDBC.Msg;
import JDBC.MsgType;
import enteties.User;

class UserControllerTest {
	private UserController userController;

	@BeforeEach
	void setUp() throws Exception {
		userController = new UserController();
	}

	/**
	 * Description: verifies the behavior of the selectUser method when the username is null. 
	 * Input: username=null. 
	 * Expected Result: Null message object.
	 */
	@Test
	void selectUserTestUsernameIsNull() {
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
	void selectUserTestUsernameIsValid() {
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
	void selectUserTestUsernameIsEmpty() {
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
	void getLoggedinMsgTestUserLoggedIn() {
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
	void getLoggedinMsgTestUserLoggedOut() {
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
	void getLoggedinMsgTestloggedInIsNull() {
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
	void getLoggedinMsgTestUserIsNull() {
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
