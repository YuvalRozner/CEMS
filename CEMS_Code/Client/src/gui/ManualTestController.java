package gui;

import JDBC.Msg;
import JDBC.MsgType;
import controllers.CemsFileController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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

	@FXML
	public void submmitBtn(ActionEvent event) throws Exception {
		
		start("startTest", "studentMenu");
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
		String path = "C:\\Users\\liorz\\OneDrive\\Documents\\GitHub\\CEMS\\CEMS_Code\\Client\\file\\logicTest.docx";
		Msg msg = cemsFileController.createMsgWithFile(path);
		sendMsg(msg);
		NotificationAlertsController alert = new NotificationAlertsController();
		alert.showInformationAlert("The test was uploaded successfully!");

	}
}