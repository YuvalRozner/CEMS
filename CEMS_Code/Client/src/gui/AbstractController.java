package gui;

import JDBC.Msg;
import JDBC.MsgType;
import client.ChatClient;
import client.ClientUI;
import controllers.CourseController;
import controllers.QuestionController;
import controllers.RequestController;
import controllers.StudentTestController;
import controllers.SubjectController;
import controllers.TestController;
import controllers.TestToExecuteController;
import controllers.UserController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import notifications.NotificationAlertsController;

public abstract class AbstractController implements SceneSetter {
	private static Stage primaryStage;
	public static Msg msgReceived;
	private Scene scene;
	private String fxmlName;
	public String prevScreen;
	
    /**
	 * object to use the UserController class method.
	 */
    public static UserController userController = new UserController();
    /**
	 * object to use the CourseController class method.
	 */
    public static CourseController courseController = new CourseController();
    /**
	 * object to use the SubjectController class method.
	 */
    public static SubjectController subjectController = new SubjectController();
    /**
	 * object to use the RequestController class method.
	 */
    public static RequestController requestController = new RequestController();
    /**
	 * object to use the TestController class method.
	 */
    public static TestController testController = new TestController();
    /**
	 * object to use the StudentTestController class method.
	 */
    public static StudentTestController studentTestController = new StudentTestController();
    /**
	 * object to use the TestToExecuteController class method.
	 */
    public static TestToExecuteController testToExecuteController = new TestToExecuteController();
    /**
	 * object to use the QuestionController class method.
	 */
    public static QuestionController questionController = new QuestionController();
    /**
	 * object to use the notifications class.
	 */
    public static NotificationAlertsController notification = new NotificationAlertsController();

	public void start(String fxmlName, String prevScreen) throws Exception {
		
		this.fxmlName = convertToSentence(fxmlName);

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/" + fxmlName + ".fxml"));
		Parent root = loader.load();
		ChatClient.screens.put(fxmlName, loader.getController());
		Scene tmpScene = new Scene(root);
		
		((SceneSetter)loader.getController()).setScene(tmpScene);
		((SceneSetter)loader.getController()).setPrevScreen(prevScreen);
		ChatClient.lastCurrentScreen = loader.getController();
		primaryStage.setTitle(convertToSentence(fxmlName));
		primaryStage.setScene(tmpScene);
		primaryStage.centerOnScreen();
		primaryStage.show();
	}

	public void setPrevScreen(String prevScreen) {
		this.prevScreen = prevScreen;
	}

	public void setScene(Scene scene) {
		this.scene = scene;
	}

	public static void sendMsg(Msg msg) {
		ClientUI.client.handleMessageFromClientUI(msg);
	}

	public static void setPrimaryStage(Stage primaryStage) {
		AbstractController.primaryStage = primaryStage;
	}

	public void display() {
		primaryStage.setTitle(fxmlName);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public void exitBtn(ActionEvent event) throws Exception {
		try {
			logout();
			sendMsg(new Msg(MsgType.disconnect));
		} catch (Throwable t) {
			System.out.println("error getExitBtn");
			System.exit(0);
		}
		System.exit(0); // exit
	}

	public String getFxmlName() {
		return fxmlName;
	}
	public static Stage getPrimaryStage() {
		return primaryStage;
	}
	public static Msg getMsgReceived() {
		return msgReceived;
	}

	public static void setDataReceived(Msg msgReceived) {
		AbstractController.msgReceived = msgReceived;
	}
	
	public void backBtn(ActionEvent event) throws Exception {
		((Node)event.getSource()).getScene().getWindow().hide();
		ChatClient.getScreen(prevScreen).display();
		ChatClient.lastCurrentScreen = ChatClient.getScreen(prevScreen);
		primaryStage.setTitle(convertToSentence(prevScreen));
	}
	
	public void logout() throws Exception {
		if(ChatClient.user==null) return;
    	sendMsg(userController.getLoggedinMsg(ChatClient.user, "no"));
	}
	
	/**
	 * Converts a string in the format "thisIsTheStringNeedToBeConvertedToSentence" to a sentence format.
	 *
	 * @param input the input string to be converted
	 * @return the converted string in sentence format
	 */
	private static String convertToSentence(String input) {
        StringBuilder output = new StringBuilder();
        // Convert the first character to uppercase
        output.append(Character.toUpperCase(input.charAt(0)));
        // Iterate through the remaining characters
        for (int i = 1; i < input.length(); i++) {
            char currentChar = input.charAt(i);
            // Check if the character is an uppercase letter
            if (Character.isUpperCase(currentChar)) {
                // Add a space before the uppercase letter
                output.append(' ');
            }
            // Add the current character to the output
            output.append(currentChar);
        }
        // Return the converted string
        return output.toString();
    }
	
	public void popMessage(String msg) {
		notification.showInformationAlert(msg);
	}
	
}