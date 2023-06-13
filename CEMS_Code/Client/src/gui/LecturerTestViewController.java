package gui;

import java.util.ArrayList;
import JDBC.Msg;
import client.ChatClient;
import controllers.TestToExecuteController;
import enteties.TestToExecute;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import notifications.NotificationAlertsController;

/**
 * Controller class for the LecturerTestView screen.
 *  
 * @author Yuval Rozner 
 */
public class LecturerTestViewController extends AbstractController {
    
    /**
     * the table of the tests.
     */
	@FXML
    private TableColumn<TestToExecute, String> selectCol, testCodeCol, courseCol, dateCol, finishedCol, studentsStartedCol;
    /**
     * columns for the table.
     */
    @FXML
    private TableView<TestToExecute> table;
    /**
     * the toggleGrope for selecting a test to change or lock from table.
     */
    ToggleGroup toggleGroupOfTestToExecute = new ToggleGroup();
    /**
     * the observable list of running test for the table.
     */
    private ObservableList<TestToExecute> testTable;
    /**
     * the list of running test (type TestToExecute) got from DB.
     */
    private ArrayList<TestToExecute> testLst;
    /**
	 * object to use the TestToExecuteController class method.
	 */
    private static TestToExecuteController testToExecuteController = new TestToExecuteController();
    /**
	 * object to use the notifications class.
	 */
    private static NotificationAlertsController notification = new NotificationAlertsController();
    
    
    
    public LecturerTestViewController() {
    	// get the relevant TestToExecute from DB:
    	Msg msg = testToExecuteController.selectTestToExecuteByUser(ChatClient.user);
    	sendMsg(msg);
    	testLst = msgReceived.convertData(TestToExecute.class); //ArrayList
    	// put some FX fields but only the RadioButton is relevant :
    	testTable = testToExecuteController.getObservLstWithFXValues(testLst); //ObservableList 
    	// toggle the radio in the table:
    	for(TestToExecute test : testTable) //toggle the radio in the table.
    		toggleGroupOfTestToExecute.getToggles().add(test.getRadioButton()); // duration
    }
    
    @FXML
    protected void initialize() {
    	selectCol.setCellValueFactory(new PropertyValueFactory<TestToExecute, String>("radioButton"));
    	testCodeCol.setCellValueFactory(new PropertyValueFactory<TestToExecute, String>("testCode"));
    	courseCol.setCellValueFactory(new PropertyValueFactory<TestToExecute, String>("courseName"));
    	dateCol.setCellValueFactory(new PropertyValueFactory<TestToExecute, String>("date"));
    	finishedCol.setCellValueFactory(new PropertyValueFactory<TestToExecute, String>("finished"));
    	studentsStartedCol.setCellValueFactory(new PropertyValueFactory<TestToExecute, String>("numberOfStudentsStarted"));
        table.setItems(testTable);
        table.refresh();
        
    }
	
	public void showStatistics(ActionEvent event) throws Exception {
		start("lecturerStaticsReport", "lecturerTestView");
	}
}
