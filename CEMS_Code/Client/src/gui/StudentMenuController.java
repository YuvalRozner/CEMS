package gui;

import java.util.ArrayList;

import JDBC.Msg;
import client.ChatClient;
import controllers.StudentTestController;
import enteties.StudentTest;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import notifications.NotificationAlertsController;

public class StudentMenuController extends AbstractController {

	@FXML
	private Label welcomeLbl;
	
	private ArrayList<StudentTest> allTest=new ArrayList<StudentTest>() ;	
	StudentTestController studentTestController =new StudentTestController();

	@FXML
	void logOut(ActionEvent event) throws Exception {
		super.logout();
		super.backBtn(event);
	}

	@FXML
	void showGrade(ActionEvent event) throws Exception {
		Msg msg =studentTestController.getMsgForStudentTestsByID(ChatClient.user);
    	sendMsg(msg);
    	try {
    		allTest = msgReceived.convertData(StudentTest.class);
        } catch (Exception e) {
        	NotificationAlertsController noGradesAlert=new NotificationAlertsController();
        	noGradesAlert.showErrorAlert("No grades to show !");
        	return;
        	}
		start("showGrade", "studentMenu");
	}

	@FXML
	void startTest(ActionEvent event) throws Exception {
		start("startTest", "studentMenu");
	}

	public void setWelcome(String name) {
		welcomeLbl.setText(name);
	}
}