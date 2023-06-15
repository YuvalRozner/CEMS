package gui;

import java.util.ArrayList;
import java.util.Collections;

import JDBC.Msg;
import JDBC.MsgType;
import client.ChatClient;
import controllers.CemsFileController;
import controllers.StudentTestController;
import controllers.TestController;
import controllers.TestToExecuteController;
import enteties.StudentTest;
import enteties.Test;
import enteties.TestToExecute;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import notifications.NotificationAlertsController;

public class ManualTestController extends AbstractController implements Testing {

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
	
	private String code;
	private Integer duration;
	private String lock;
	private TestToExecute numbersOfStudent; 
	Integer timeOfStudent = 100; //time fiktivy //dor
	
	private CemsFileController cemsFileController = new CemsFileController();
	private StudentTestController studentTestController =new StudentTestController();
	private NotificationAlertsController alert= new NotificationAlertsController();
	private TestController testController =new TestController();
	private TestToExecuteController testToExecuteController = new TestToExecuteController();
	
    public ManualTestController() {
    	
    	if (ChatClient.lastCurrentScreen instanceof StartTestController) {
    		code = ((StartTestController)ChatClient.lastCurrentScreen).getCode();
    	}
 	 
    }
    
	/**
	 * Handles the action event for the submit button in a JavaFX application.
	 *
	 * @param event The ActionEvent object representing the button click event.
	 * @throws Exception If an exception occurs during the execution of the method.
	 */
	@FXML
	public void submmitBtn(ActionEvent event) throws Exception {
		Msg msg;
		Integer grade=75;
		alert.setOnCancelAction(new Runnable() {	@Override public void run() {
			((Node)event.getSource()).getScene().getWindow().hide();
			ChatClient.getScreen("manualTest").display();
			getPrimaryStage().setTitle("Manual Test");
		}});
		alert.setOnOkAction(new Runnable() {	@Override public void run() {
			Msg msg=testController.getDurationByCode(code);
			sendMsg(msg);
			duration=msgReceived.convertData(Test.class).get(0).getDuration();
			if(duration<timeOfStudent) {
				timeOfStudentIsOverLoad(timeOfStudent);/////////dor
			}
			else {
				msg=testToExecuteController.checkIfTheTestIsLock(Integer.toString(StartTestController.getTestToExecute().getTestCode()));
				sendMsg(msg);
				lock=msgReceived.convertData(TestToExecute.class).get(0).getLock();
				if(lock.equals("true")) {
					testIsLockInMiddleOfTest(timeOfStudent);/////////dor
				}
				else {
					testIsSubmit(timeOfStudent,grade);
				}				
			}
			try {
				start("studentMenu", "login");
			} catch (Exception e) {}}});
		alert.showConfirmationAlert(ChatClient.user.getName()+" Are you sure ?","After clicking the OK button, the submission is final and there is no option to change it");
		updateAverageAndMedian();
		msg=testToExecuteController.checkIfTheStudentIsLast(StartTestController.getTestToExecute().getTestCode());
		sendMsg(msg);
		numbersOfStudent=msgReceived.convertData(TestToExecute.class).get(0);
		Integer needToLock=numbersOfStudent.getNumberOfStudentsStarted()-numbersOfStudent.getNumberOfStudentsFinished()-numbersOfStudent.getNumberOfStudents();
		System.out.println(needToLock);
		if (needToLock==0) {
			msg=testToExecuteController.getMsgToLockTest(StartTestController.getTestToExecute());
			sendMsg(msg);
		}
	
	}
	/**
	 * Updates the average and median grades for a specific test throw the server.
	 */
	public void updateAverageAndMedian(){
		Msg msg;
		double average=0 , median=0;
		msg=studentTestController.selectAllstudentBySpecificCodeTest(StartTestController.getTestToExecute().getTestCode());
		sendMsg(msg);
		ArrayList<StudentTest> listOfStudent =msgReceived.convertData(StudentTest.class);
		ArrayList<Integer> listOfGrades=new ArrayList<Integer>();
		for(StudentTest student : listOfStudent) {
			average+=student.getGrade();
			listOfGrades.add(student.getGrade());
		}
		average=average/listOfGrades.size();
		Collections.sort(listOfGrades);
		median=listOfGrades.get(listOfGrades.size()/2);
		msg=testToExecuteController.updateMedianAndAverage(StartTestController.getTestToExecute().getTestCode(),average,median);
		sendMsg(msg);
		
	}
	
	
	/**
	 * Handles the submission of a test by a student.
	 *
	 * @param timeOfStudent The time taken by the student to complete the test (in minutes).
	 * @param grade The grade obtained by the student.
	 */
	public void testIsSubmit(Integer timeOfStudent,Integer grade) {
		Msg msg;
		alert.showInformationAlert("The test was successfully submitted!");
		try {start("studentMenu", "login");} catch (Exception e) {e.printStackTrace();}
		////////update data
		Msg msgUpdate = testToExecuteController.updateNumberOfStudenByOne(1,code,"finish");
		sendMsg(msgUpdate);
		msg=studentTestController.InsertAnswersAndGradeManual("false",timeOfStudent,"23244",grade,ChatClient.user.getId() ,code);
		sendMsg(msg);
		msg=testToExecuteController.insertDistributionByCode(code,grade,1);
		sendMsg(msg);
	}
	/**
	 * Handles the case when the test is locked in the middle and the student cannot submit the test.
	 *
	 * @param timeOfStudent The time taken by the student to complete the test (in minutes).
	 */
	public void testIsLockInMiddleOfTest(Integer timeOfStudent) {
		Msg msg;
		Msg msgUpdate = testToExecuteController.updateNumberOfStudenByOne(1,Integer.toString(StartTestController.getTestToExecute().getTestCode()),"cantSubmit");
		sendMsg(msgUpdate);
		alert.showErrorAlert("The test is locked!\n You will not be able to submit the test!");
		msg=studentTestController.InsertAnswersAndGradeManual("false",timeOfStudent,"00000",0,ChatClient.user.getId() ,code);
		sendMsg(msg);
		msg=testToExecuteController.insertDistributionByCode(code,0,1);
		sendMsg(msg);
		try {start("studentMenu", "login");} catch (Exception e) {e.printStackTrace();}
	}
	/**
	 * Handles the case when the student has exceeded the allowed time for the test.
	 *
	 * @param timeOfStudent The time taken by the student to complete the test (in minutes).
	 */
	public void timeOfStudentIsOverLoad(Integer timeOfStudent) {
		Msg msg;
		alert.showErrorAlert("You have exceeded the allowed time!");
		Msg msgUpdate = testToExecuteController.updateNumberOfStudenByOne(1,Integer.toString(StartTestController.getTestToExecute().getTestCode()),"cantSubmit");
		sendMsg(msgUpdate);
		msg=studentTestController.InsertAnswersAndGradeManual("false",timeOfStudent,"00000",0,ChatClient.user.getId() ,code);
		sendMsg(msg);
		msg=testToExecuteController.insertDistributionByCode(code,0,1);
		sendMsg(msg);
		try {start("studentMenu", "login");} catch (Exception e) {e.printStackTrace();}
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
		String path ="@../../file/algebraTest.docx";
		Msg msg = cemsFileController.createMsgWithFile(path);
		sendMsg(msg);
		NotificationAlertsController alert = new NotificationAlertsController();
		alert.showInformationAlert("The test was uploaded successfully!");

	}
	public void testIsLockInMiddleOfTest() {
		Msg msg;
		msg = testToExecuteController.updateNumberOfStudenByOne(1,code,"cantSubmit");
		sendMsg(msg);
		//alert.showErrorAlert("The test is locked!\n You will not be able to submit the test!");
		msg=studentTestController.InsertAnswersAndGradeManual("false",timeOfStudent,"12000",20,ChatClient.user.getId() ,code);
		sendMsg(msg);
		msg=testToExecuteController.insertDistributionByCode(code,20,1);
		sendMsg(msg);
		try {start("studentMenu", "login");} catch (Exception e) {e.printStackTrace();}
	}
	
	@Override
	public void testGotManualyLockedByLecturer(String testCode) {
		if(!testCode.equals(code)) return;
		alert.showErrorAlert("Sorry, but the test got locked by your lecturer..");
		testIsLockInMiddleOfTest();
	}
}