package gui;

import java.util.ArrayList;
import java.util.Collections;

import JDBC.Msg;
import JDBC.MsgType;
import client.ChatClient;
import controllers.CemsFileController;
import controllers.CountDown;
import controllers.StudentTestController;
import controllers.TestController;
import controllers.TestToExecuteController;
import controllers.TimeController;
import enteties.StudentTest;
import enteties.Test;
import enteties.TestToExecute;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import notifications.NotificationAlertsController;

public class ManualTestController extends AbstractController implements CountDown,Testing {


	@FXML
	private Button donebtn,downbtn,upbtn;


	 /**
     * Labels for displaying test information.
     */
    @FXML
    private Label info1, info2, info3, info4, info5, timeLabel,warnningLabel;

    /**
     * Container for displaying instructions for students.
     */
    @FXML
    private TextFlow instructionsForStudentTextFlow;

	
	private boolean lastMin = false;
	private Integer code;
	private String lock;
	private TestToExecute numbersOfStudent; 
	private int timeOfStudent;
	private Msg msg;
	private Integer grade=75;
	private String flagEndOrMiddle;
	
	
	TestToExecute testToExecute;
	TimeController timeController;
	private CemsFileController cemsFileController = new CemsFileController();
	private StudentTestController studentTestController =new StudentTestController();
	private NotificationAlertsController alert= new NotificationAlertsController();
	private TestToExecuteController testToExecuteController = new TestToExecuteController();
	
    public ManualTestController() {
    	
    	if (ChatClient.lastCurrentScreen instanceof StartTestController) {
            testToExecute = ((StartTestController) ChatClient.lastCurrentScreen).getTestToExecuteToShow();
            code = testToExecute.getTestCode();
        }
    	 //timeController = new TimeController(testToExecute.getTest().getDuration(), this);
    	 timeController = new TimeController(1, this);
    }
    @FXML
    protected void initialize() { 
    	setInfo();
    	timeController.startTimer();
    }
    /**
     * Sets the information of the test and updates the display.
     * This method populates various labels and text flows with the relevant information
     * about the test and its instructions for students and lecturers, based on the current screen state.
     * It also adjusts the formatting and styling of the displayed elements.
     */
    public void setInfo() {
        // Set the info of the test
        Text textForTextFlow = null;
        info1.setText("Test Code: " + testToExecute.getTestCode());
        info2.setText("Course: " + testToExecute.getTest().getCourse().getName());
        info3.setText("Duration:" + testToExecute.getTest().getDuration() + " minutes");
        info4.setText("Date: " + testToExecute.getDate());
        info5.setText("Student Name:" + ChatClient.user.getName());
        // Set instructions for students
        if (testToExecute.getTest().getInstructionsForStudent() != null) {
            textForTextFlow = new Text("Instructions For Student: " + testToExecute.getTest().getInstructionsForStudent());
            textForTextFlow.wrappingWidthProperty().bind(instructionsForStudentTextFlow.widthProperty());
            textForTextFlow.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 14));
            instructionsForStudentTextFlow.getChildren().addAll(textForTextFlow);
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
		 // Stop the timer
        timeController.stopTimer();
        
        // Calculate the time taken by the student
        timeOfStudent = testToExecute.getTest().getDuration() - timeController.timeLeft();
        
		alert.setOnCancelAction(new Runnable() {	
			@Override public void run() {
			 timeController.startTimer();
		}});
		alert.setOnOkAction(new Runnable() {	
			@Override public void run() {

			if(testToExecute.getLock().equals("true")){
				flagEndOrMiddle="End";
				testIsLockCantSubmmit();
				updateAverageAndMedian();	
			}

			else {
				testIsSubmit(timeOfStudent,grade);
				checkIfStudentIsTheLastOne();
				System.out.println("befor average and median");
				updateAverageAndMedian();	
				System.out.println("after average and median");
			}				
			try {start("studentMenu", "login");} catch (Exception e) {}}});
		alert.showConfirmationAlert(ChatClient.user.getName()+" Are you sure ?","After clicking the OK button, the submission is final and there is no option to change it");

	}
	
	
	public void checkIfStudentIsTheLastOne() {
		msg=testToExecuteController.checkIfTheStudentIsLast(StartTestController.getTestToExecute().getTestCode());
		sendMsg(msg);
		numbersOfStudent=msgReceived.convertData(TestToExecute.class).get(0);
		Integer needToLock=numbersOfStudent.getNumberOfStudentsStarted()-numbersOfStudent.getNumberOfStudentsFinished()-numbersOfStudent.getNumberOfStudents();
		if (needToLock==0) {
			msg=testToExecuteController.getMsgToLockTest(StartTestController.getTestToExecute());
			sendMsg(msg);
		}
    	
    }
   
	/**
	 * Updates the average and median grades for a specific test throw the server.
	 */
	public void updateAverageAndMedian(){
		
		double average=0 , median=0;
		msg=studentTestController.selectAllstudentBySpecificCodeTest(code.toString());
		sendMsg(msg);
		ArrayList<StudentTest> listOfStudent =msgReceived.convertData(StudentTest.class);
		System.out.println("the grade"+listOfStudent.get(0).getGrade());///////
		ArrayList<Integer> listOfGrades=new ArrayList<Integer>();
		for(StudentTest student : listOfStudent) {
			average+=student.getGrade();
			listOfGrades.add(student.getGrade());
		}
		average=average/listOfGrades.size();
		Collections.sort(listOfGrades);
		median=listOfGrades.get(listOfGrades.size()/2);
		msg=testToExecuteController.updateMedianAndAverage(code.toString(),average,median);
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
		Msg msgUpdate = testToExecuteController.updateNumberOfStudenByOne(1,code.toString(),"finish");
		sendMsg(msgUpdate);
		msg=studentTestController.InsertAnswersAndGradeManual("false",timeOfStudent,"23244",grade,ChatClient.user.getId() ,code.toString());
		sendMsg(msg);
		msg=testToExecuteController.insertDistributionByCode(code.toString(),grade,1);
		sendMsg(msg);
	}

	/**
	 * Handles the case when the student has exceeded the allowed time for the test.
	 *
	 * @param timeOfStudent The time taken by the student to complete the test (in minutes).
	 */
	public void timeOfStudentIsOverLoad() {
		Msg msg;
		alert.showErrorAlert("You have exceeded the allowed time!");
		Msg msgUpdate = testToExecuteController.updateNumberOfStudenByOne(1,Integer.toString(StartTestController.getTestToExecute().getTestCode()),"cantSubmit");
		sendMsg(msgUpdate);
		msg=studentTestController.InsertAnswersAndGradeManual("false",timeOfStudent,"00000",0,ChatClient.user.getId() ,code.toString());
		sendMsg(msg);
		msg=testToExecuteController.insertDistributionByCode(code.toString(),0,1);
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

	/**
	 * Handles the case when the test is locked in the middle and the student cannot submit the test.
	 *
	 * @param timeOfStudent The time taken by the student to complete the test (in minutes).
	 */
	public void testIsLockCantSubmmit() {
		msg = testToExecuteController.updateNumberOfStudenByOne(1,Integer.toString(StartTestController.getTestToExecute().getTestCode()),"cantSubmit");
		sendMsg(msg);
		alert.showErrorAlert("The test is locked!\n You will not be able to submit the test!");
		if(flagEndOrMiddle.equals("End")) {
			msg=studentTestController.InsertAnswersAndGradeManual("false",timeOfStudent,"00000",0,ChatClient.user.getId() ,code.toString());
		}
		else {
			msg=studentTestController.InsertAnswersAndGradeManual("false",timeOfStudent,"12000",50,ChatClient.user.getId() ,code.toString());
		}
		sendMsg(msg);
		msg=testToExecuteController.insertDistributionByCode(code.toString(),0,1);
		sendMsg(msg);
		try {start("studentMenu", "login");} catch (Exception e) {e.printStackTrace();}
	}
	
	@Override
	public void testGotManualyLockedByLecturer(String testCode) {
		if(!testCode.equals(code)) return;
		alert.showErrorAlert("Sorry, but the test got locked by your lecturer..");
		flagEndOrMiddle="Middle";
		testIsLockCantSubmmit();
	}

	@Override
	public void setTextCountdown(String s) {
		timeLabel.setText(s);
	}

	@Override
	public void endOfTime() {
		if (lastMin == false) {
			lastMin = true;
			timeController.updateClock(1);
			timeController.startTimer();
			warnningLabel.setText("This is you last chance to submit!");
			warnningLabel.setStyle("-fx-font-family: \"Comic Sans MS\"; -fx-font-size: 14px; -fx-text-fill: red;");
			timeLabel.setStyle("-fx-font-family: \"Comic Sans MS\"; -fx-font-size: 18px; -fx-text-fill: red;");
		}
		else {
			timeOfStudentIsOverLoad();
			checkIfStudentIsTheLastOne();
			updateAverageAndMedian();	
		}
	
	}
}