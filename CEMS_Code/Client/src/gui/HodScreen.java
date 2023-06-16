package gui;

import client.ChatClient;
import notifications.NotificationAlertsController;

/**
 * Abstract controller class for Hod screens.
 * This class provides common functionality and methods for Hod screens.
 * It extends the AbstractController class.
 * 
 * <p>Subclasses should implement their specific logic and UI components.</p>
 * <p>Subclasses should call the `popRequest()` method to display a notification when a new request is received.</p>
 * 
 * <p>Note: Only HoD users can receive and display requests.</p>
 * 
 * @see AbstractController
 * @author Yuval Rozner
 */
public class HodScreen extends AbstractController {
	
	/**
     * Displays a notification for a new request.
     * This method is called when a new request is received.
     * It checks if the logged-in user has the "hod" permission, and if so, displays a notification alert.
     * The notification alert informs the user that a new request is waiting for their attention.
     */
	public void popRequest() {
		if(!ChatClient.user.getPremission().equals("hod")) return;
		//continue only if Im a hod:
		new NotificationAlertsController().showInformationAlert("You got a new Request waiting for you.");
	}
}