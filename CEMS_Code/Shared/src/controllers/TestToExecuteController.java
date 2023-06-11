package controllers;

import JDBC.Msg;
import JDBC.MsgType;
import client.ChatClient;
import notifications.NotificationAlertsController;

public class TestToExecuteController {
	
	public boolean checkValidCode(String code) {
		NotificationAlertsController alert = new NotificationAlertsController();
		if (code.isEmpty() == true){alert.showWarningAlert("You must enter code for a test, 4 digits!");return false;}
		if (code.length()!=4) {alert.showWarningAlert("The code must be 4 in length!");return false;}
		if (!code.matches("\\d+")) {alert.showWarningAlert("The code must consist of only digits!");return false;}
		return true;
	}
	
	public Msg selectTestToExecuteByCode(String code) {
    	Msg msg = new Msg(MsgType.select);
    	msg.setSelect("*");
    	msg.setFrom("cems.testtoexecute");
    	msg.setWhere("testCode", code); 
    	return msg;
    }
	
	public boolean checkValidId(String id) {
		NotificationAlertsController alert = new NotificationAlertsController();
		if (id.isEmpty() == true){alert.showWarningAlert("ID is empty!");return false;}
		if (!ChatClient.user.getId().equals(id)) {alert.showWarningAlert("ID is worng!");return false;}
		return true;
	}
}