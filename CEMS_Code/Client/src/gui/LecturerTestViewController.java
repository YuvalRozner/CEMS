package gui;

import java.util.ArrayList;

import JDBC.Msg;
import JDBC.MsgType;
import client.ChatClient;
import controllers.TestToExecuteController;
import enteties.Test;
import enteties.TestToExecute;
import javafx.collections.FXCollections;
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
    
    
    
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    
    
    
    /**
     * the table of the tests.
     */
	@FXML
    private TableColumn<Test, String> courseCol, dateCol, idCol, selectCol;
    /**
     * 
     */
    @FXML
    private TableView<Test> table;
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
    	
    	
    	
    	
    	/*
        //arrdup = new ArrayList<Test>(Main.tests);
    	Msg msg = new Msg(MsgType.select);
    	msg.setSelect("*");
    	msg.setFrom("test");
    	sendMsg(msg);
    	arrdup = new ArrayList<Test>(msgReceived.convertData(Test.class));
        testsTable = FXCollections.observableArrayList(arrdup);
        toggleGroup = new ToggleGroup(); // Initialize the ToggleGroup
        for (Test test : testsTable) {
            test.getRadioButton().setToggleGroup(toggleGroup);
        }
        */
    }
    
    @FXML
    protected void initialize() {/*
        idCol.setCellValueFactory(new PropertyValueFactory<Test, String>("id"));
        courseCol.setCellValueFactory(new PropertyValueFactory<Test, String>("Course"));
        dateCol.setCellValueFactory(new PropertyValueFactory<Test, String>("Date"));
        selectCol.setCellValueFactory(new PropertyValueFactory<Test, String>("radioButton"));
        
        table.setItems(testsTable);
        table.refresh();
        */
    }
	
	public void showStatistics(ActionEvent event) throws Exception {
		start("lecturerStaticsReport", "lecturerTestView");
	}
}
