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
    	System.out.println("123456789");
    	String code= codeTextField.getText();
    	if (testToExecuteController.checkValidCode(code)) {
    		Msg msg = testToExecuteController.selectTestToExecuteByCode(code);
    		sendMsg(msg);
    		ArrayList<TestToExecute>arr=msgReceived.convertData(TestToExecute.class);
    		testToExecute=arr.get(0);
    		idTextField.setDisable(false);
    		//idTextField.editableProperty();
    		idTextField.setEditable(true);
    	}

    }
    @FXML
    void startBtn(ActionEvent event) throws Exception {
    	User user = ChatClient.user;
    	String id= idTextField.getText();
    	if(testToExecuteController.checkValidId(id,user)) {
    		if (testToExecute!=null) {
    			Msg msg = studentTestController.insertStudentTest(testToExecute, user);
    			sendMsg(msg);
    			studentTest.setTestCode(Integer.valueOf(codeTextField.getText()));
    			studentTest.setStudentId(ChatClient.user.getId());
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
