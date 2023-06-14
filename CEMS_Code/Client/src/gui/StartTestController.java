package gui;

import java.util.ArrayList;

import JDBC.Msg;
import client.ChatClient;
import controllers.StudentTestController;
import controllers.TestToExecuteController;
import enteties.StudentTest;
import enteties.TestToExecute;
import enteties.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import notifications.NotificationAlertsController;

public class StartTestController extends AbstractController{

    @FXML
    private Button backbtn;

    @FXML
    private TextField codeTextField;

    @FXML
    private Button connectbtn;

    @FXML
    private TextField idTextField;

    @FXML
    private Button startbtn;
    private static TestToExecuteController testToExecuteController = new TestToExecuteController();
    private static StudentTestController studentTestController = new StudentTestController();
    private static TestToExecute testToExecute = new TestToExecute();
    private static StudentTest studentTest = new StudentTest();
    NotificationAlertsController alert = new NotificationAlertsController();
    private String lock=null;

    /**
     * after enter code of test user push connect , this fun check if code is valid and get the appropriate  testtoexeucte.
     * If everything was successful, permission is opened for the student to insert an ID.
     * @param event press on button connect after enter code.
     */
    @FXML
    void connectBtn(ActionEvent event) {
    	String code= codeTextField.getText();
    	Msg msg = testToExecuteController.checkIfTheTestIsLock(code);
    	sendMsg(msg);
    	lock=msgReceived.convertData(TestToExecute.class).get(0).getLock();
    	if(lock.equals("true")) {alert.showErrorAlert("Sorry, this test is lock!");return;}
    	msg=studentTestController.studentAlreadyAccessed(ChatClient.user,code);
    	sendMsg(msg);
    	//ArrayList<StudentTest> idOfStudent=msgReceived.convertData(StudentTest.class);////לשאול את רוזנר אם אפשר לגשת למידע ישירות של המאסג רסיב
    	//System.out.println("1");
    	if (msgReceived==null) {
    		if (testToExecuteController.checkValidCode(code)) {
        		Msg msgGetTesttoexeute = testToExecuteController.selectTestToExecuteByCode(code);
        		sendMsg(msgGetTesttoexeute);
        		ArrayList<TestToExecute>arr=msgReceived.convertData(TestToExecute.class);
        		setTestToExecute(arr.get(0));
        		idTextField.setDisable(false);
        		//idTextField.editableProperty();
        		idTextField.setEditable(true);
        	}
    	}
    	else {
    		alert.showErrorAlert("Sorry, you have already accessed this test and submitted it");
    	}
    		
    }
    /**
     * If the id inserted matches the user, 
     * it is checked whether the test is manual or online and a screen is displayed accordingly.
     * @param event press on button connect start after enter id.
     * @throws Exception
     */
    @FXML
    void startBtn(ActionEvent event) throws Exception {
    	String code= codeTextField.getText();
    	User user = ChatClient.user;
    	String id= idTextField.getText();
    	if(testToExecuteController.checkValidId(id,user)) {
    		if (getTestToExecute()!=null) {
    			Msg msgInsert = studentTestController.insertStudentTest(getTestToExecute(), user);
    			sendMsg(msgInsert);
    			studentTest.setTestCode(Integer.valueOf(codeTextField.getText()));
    			studentTest.setStudentId(ChatClient.user.getId());
    			
    			Msg msgUpdate = testToExecuteController.updateNumberOfStudenByOne(1,code,"start");
    			sendMsg(msgUpdate);
    			
    			if(getTestToExecute().getTestingType().equals("manual")) {
    				start("manualTest", "startTest");
    			}
    			else {start("onlineTest", "startTest");}
    		}
    	}
    }
    /**
     * Constructor Makes the id uneditable.
     */
    public StartTestController() {
    	idTextField=new TextField();
    	idTextField.setDisable(true);
    }
    /**
     * return TestToExecute to be shown.
     * @return TestToExecute to be shown.
     */
	public static TestToExecute getTestToExecute() {
		return testToExecute;
	}
	/**
	 * set TestToExecute to be shown.
	 * @param testToExecute to be shown.
	 */
	public static void setTestToExecute(TestToExecute testToExecute) {
		StartTestController.testToExecute = testToExecute;
	}

}
