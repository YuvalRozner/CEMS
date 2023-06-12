package gui;

import java.util.ArrayList;
import JDBC.Msg;
import client.ChatClient;
import controllers.TestToExecuteController;
import enteties.TestToExecute;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Controller class for the RunningTest screen.
 *  
 * @author Yuval Rozner 
 */
public class RunningTestController extends AbstractController{
		
	/**
	 * the table of the running tests.
	 */
    @FXML
    private TableView<TestToExecute> table = new TableView<TestToExecute>();
    /**
     * columns for the table.
     */
    @FXML
    private TableColumn<TestToExecute, String> selectCol, testCodeCol, courseCol, startedCol ,finishedCol ,durationCol, explanationCol;

    /**
     * the observable list of running test for the table.
     */
    private ObservableList<TestToExecute> RuningTestTable;
    /**
     * the toggleGrope for selecting a test to change or lock from table.
     */
    ToggleGroup toggleGroupOfTestToExecute = new ToggleGroup();
    /**
     * the list of running test (type TestToExecute) got from DB.
     */
    private ArrayList<TestToExecute> runningTestLst;
    /**
	 * object to use the TestToExecuteController class method.
	 */
    private static TestToExecuteController testToExecuteController = new TestToExecuteController();
    
    
    
    
    public RunningTestController() {
    	Msg msg = testToExecuteController.selectRunningTestToExecuteByUser(ChatClient.user);
    	sendMsg(msg);
    	runningTestLst = msgReceived.convertData(TestToExecute.class);
    	RuningTestTable = FXCollections.observableArrayList(runningTestLst);
    	for (TestToExecute runningTest : runningTestLst) {
    		runningTest.setNewTextField(); // duration
    		runningTest.setNewTextField1(); // explanation for changing the duration.
    		runningTest.setNewRadioButton(); //select
    		toggleGroupOfTestToExecute.getToggles().add(runningTest.getRadioButton()); // duration
    		runningTest.getTextField().setPromptText(runningTest.getTest().getDuration().toString()); // explanation
    		runningTest.getTextField1().setPromptText("Explanation for change");
    		runningTest.getTextField().setDisable(true); // duration
    		runningTest.getTextField1().setDisable(true); // explanation
    		runningTest.getRadioButton().selectedProperty().addListener((observable, oldValue, newValue) -> {
        		if (newValue == true) {
        			runningTest.getTextField().setDisable(false);
        			runningTest.getTextField1().setDisable(false);
        		}
        		else {
        			runningTest.getTextField().setDisable(true);
        			runningTest.getTextField1().setDisable(true);
        		}
             });
    	}	
    }
    
    
    
    @FXML
	protected void initialize() {
    	selectCol.setCellValueFactory(new PropertyValueFactory<TestToExecute, String>("radioButton"));
    	testCodeCol.setCellValueFactory(new PropertyValueFactory<TestToExecute, String>("testCode"));
    	courseCol.setCellValueFactory(new PropertyValueFactory<TestToExecute, String>("courseName"));
    	startedCol.setCellValueFactory(new PropertyValueFactory<TestToExecute, String>("numberOfStudentsStarted"));
    	finishedCol.setCellValueFactory(new PropertyValueFactory<TestToExecute, String>("numberOfStudentsFinished"));
    	durationCol.setCellValueFactory(new PropertyValueFactory<TestToExecute, String>("textField"));
    	explanationCol.setCellValueFactory(new PropertyValueFactory<TestToExecute, String>("textField1"));
		table.setItems(RuningTestTable);
		table.refresh();
	}


    @FXML
    void changeBtn(ActionEvent event) {
    	int oldvalue = 0;
    	String newvalue = null;
    	for (TestToExecute test : RuningTestTable) {
    		if (test.getRadioButton().isSelected()) {
    			oldvalue = 	test.getTest().getDuration();
    			newvalue = test.getTextField().getText();
    			break;
    		}
    	}
    	System.out.println("the old value was: " + oldvalue + " ,the new value is " + newvalue);
    } 

    @FXML
    void lockBtn(ActionEvent event) {
    	System.out.println("i locked the test sucsesfully");
    	TestToExecute testtodelete = null;
    	for (TestToExecute test : RuningTestTable) {
    		if (test.getRadioButton().isSelected()) {
    			testtodelete = test;
    			break;
    		}
    	}
    	RuningTestTable.remove(testtodelete);
    }

}