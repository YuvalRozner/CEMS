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

    @FXML
    void connectBtn(ActionEvent event) {
    	String code= codeTextField.getText();
    	Msg msg=studentTestController.studentAlreadyAccessed(ChatClient.user,code);
    	sendMsg(msg);
    	//ArrayList<StudentTest> idOfStudent=msgReceived.convertData(StudentTest.class);////לשאול את רוזנר אם אפשר לגשת למידע ישירות של המאסג רסיב
    	//System.out.println("1");
    	if (msgReceived==null) {
    		if (testToExecuteController.checkValidCode(code)) {
        		Msg msgGetTesttoexeute = testToExecuteController.selectTestToExecuteByCode(code);
        		sendMsg(msgGetTesttoexeute);
        		ArrayList<TestToExecute>arr=msgReceived.convertData(TestToExecute.class);
        		testToExecute=arr.get(0);
        		idTextField.setDisable(false);
        		//idTextField.editableProperty();
        		idTextField.setEditable(true);
        	}
    	}
    	else {
    		NotificationAlertsController alert = new NotificationAlertsController();
    		alert.showErrorAlert("Sorry, you have already accessed this test and submitted it");
    	}
    		
    }
    @FXML
    void startBtn(ActionEvent event) throws Exception {
    	String code= codeTextField.getText();
    	User user = ChatClient.user;
    	String id= idTextField.getText();
    	if(testToExecuteController.checkValidId(id,user)) {
    		if (testToExecute!=null) {
    			Msg msgInsert = studentTestController.insertStudentTest(testToExecute, user);
    			sendMsg(msgInsert);
    			studentTest.setTestCode(Integer.valueOf(codeTextField.getText()));
    			studentTest.setStudentId(ChatClient.user.getId());
    			
    			Msg msgUpdate = testToExecuteController.updateNumberOfStudenByOne(code);
    			sendMsg(msgUpdate);
    			
    			if(testToExecute.getTestingType().equals("manual")) {
    				start("manualTest", "startTest");
    			}
    			else {start("onlineTest", "startTest");}
    		}
    	}
    }
    public StartTestController() {
    	idTextField=new TextField();
    	idTextField.setDisable(true);
    }

}
