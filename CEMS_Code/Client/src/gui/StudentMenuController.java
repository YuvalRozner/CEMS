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
	/**
	 * lable for headline
	 */

	@FXML
	private Label welcomeLbl;
	/**
	 * save the test to shown.
	 */
	private ArrayList<StudentTest> allTest=new ArrayList<StudentTest>() ;	
	
	/**
	 * object to use the StudentTestController class method.
	 */
	StudentTestController studentTestController =new StudentTestController();
	
	/**
	 * logout from the system return to login screen and change in the db to lodged out.
	 * @param event press on logout button.
	 * @throws Exception
	 */
	@FXML
	void logOut(ActionEvent event) throws Exception {
		super.logout();
		super.backBtn(event);
	}
	/**
	 * show list of approved grades and option to see the test .
	 * @param event press on show grade in menu.
	 * @throws Exception
	 */

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
	/**
	 * go to start test window to connect for a test.
	 * @param event press on start test in menu.
	 * @throws Exception
	 */
	@FXML
	void startTest(ActionEvent event) throws Exception {
		start("startTest", "studentMenu");
	}
	/**
	 * put for any clien his own name in the welcome lable.
	 * @param name name of client that entered.
	 */
	public void setWelcome(String name) {
		welcomeLbl.setText(name);
	}
}