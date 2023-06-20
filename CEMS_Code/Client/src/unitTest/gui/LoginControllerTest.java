package gui;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import client.ChatClient;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import notifications.NotificationAlertsController;


class LoginControllerTest{
	private static LoginController loginController;

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        if (Boolean.getBoolean("headless")) {
            System.setProperty("java.awt.headless", "true");
        } else {
            // Initialize JavaFX toolkit if not already initialized
            if (System.getProperty("javafx.embed.singleThread", "false").equals("false")) {
                new javafx.embed.swing.JFXPanel();
            }
        }
        //set up login controller object
        loginController  = new LoginController();
    }

	@Test
	void loginTestPasswordIsNull() {
        // Arrange
        String username = "testUser";
        String password = null;  	
      		
      	// Create a mock of the Notification object
        NotificationAlertsController notification = Mockito.mock(NotificationAlertsController.class);
        loginController.setNotificationAlertsController(notification);
        
		// Act
        boolean result = loginController.login(username, password);
        
        // Assert
        Mockito.verify(notification).showErrorAlert("you must enter username and password.");
        
        assertFalse(result);
        // Add any additional assertions based on your requirements
        
	}
	
	@Test
	void loginTestUsernameIsNull() {
        // Arrange
        String username = null;
        String password = "testpassword";
      	
      		
      	// Create a mock of the Notification object
        NotificationAlertsController notification = Mockito.mock(NotificationAlertsController.class);
        loginController.setNotificationAlertsController(notification);
        
		// Act
        boolean result = loginController.login(username, password);
        
        // Assert
        Mockito.verify(notification).showErrorAlert("you must enter username and password.");
        
        assertFalse(result);
        // Add any additional assertions based on your requirements
        
	}


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
