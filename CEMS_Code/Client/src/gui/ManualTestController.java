package gui;

import JDBC.Msg;
import JDBC.MsgType;
import client.ChatClient;
import controllers.CemsFileController;
import controllers.StudentTestController;
import enteties.Test;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import notifications.NotificationAlertsController;

public class ManualTestController extends AbstractController {

	@FXML
	private Label Course;

	@FXML
	private Label NameCourse;

	@FXML
	private Button donebtn;

	@FXML
	private Button downbtn;

	@FXML
	private Label timer;

	@FXML
	private Button upbtn;

	private CemsFileController cemsFileController = new CemsFileController();
	private StudentTestController studentTestController =new StudentTestController();
	private static Integer duration;
	private NotificationAlertsController alert= new NotificationAlertsController();

	@FXML
	public void submmitBtn(ActionEvent event) throws Exception {
		Integer timeOfStudent = 250; //time fiktivy //dor
		alert.setOnCancelAction(new Runnable() {	@Override public void run() {
			((Node)event.getSource()).getScene().getWindow().hide();
			ChatClient.getScreen("manualTest").display();
			getPrimaryStage().setTitle("Manual Test");
		}});
		alert.setOnOkAction(new Runnable() {	@Override public void run() {
			Msg msg=studentTestController.checkTimeLeft(StartTestController.getTestToExecute().getTestCode());
			sendMsg(msg);
			duration=msgReceived.convertData(Test.class).get(0).getDuration();
			System.out.println(duration);
			if(duration<timeOfStudent) {
				alert.showErrorAlert("You have exceeded the allowed time!");
				try {start("studentMenu", "login");} catch (Exception e) {e.printStackTrace();}
			}
			else {
				alert.showInformationAlert("The test was successfully submitted!");
				try {start("studentMenu", "login");} catch (Exception e) {e.printStackTrace();}
				///lockkk
			}
			
			
			try {
				start("studentMenu", "login");
			} catch (Exception e) {}}});
		alert.showConfirmationAlert(ChatClient.user.getName()+" Are you sure ?","After clicking the OK button, the submission is final and there is no option to change it");
		//start("startTest", "studentMenu");
	}

	
	
	/**
	 * down load test from server to client.
	 * 
	 * @param event
	 */
	@FXML
	void download(ActionEvent event) {
		Msg msg = new Msg(MsgType.fileToSend);
		sendMsg(msg);
		NotificationAlertsController alert = new NotificationAlertsController();
		alert.showInformationAlert("The test was download successfully!");

	}

	/**
	 * upload test to submit it , go to server folder.
	 * 
	 * @param event
	 */
	@FXML
	void upload(ActionEvent event) {
		String path ="@../../file/logicTest.docx";
		//String path = "C:\\Users\\liorz\\OneDrive\\Documents\\GitHub\\CEMS\\CEMS_Code\\Client\\file\\logicTest.docx";
		Msg msg = cemsFileController.createMsgWithFile(path);
		sendMsg(msg);
		NotificationAlertsController alert = new NotificationAlertsController();
		alert.showInformationAlert("The test was uploaded successfully!");

	}
}