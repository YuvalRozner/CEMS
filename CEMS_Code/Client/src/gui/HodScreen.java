package gui;

import client.ChatClient;
import notifications.NotificationAlertsController;

public class HodScreen extends AbstractController {
	
	public void popRequest() {
		if(!ChatClient.user.getPremission().equals("hod")) return;
		//continue only if Im a hod:
		new NotificationAlertsController().showInformationAlert("You got a new Request wainting for you.");
	}
}