package gui;

import java.util.ArrayList;
import JDBC.Msg;
import client.ChatClient;
import controllers.RequestController;
import controllers.TestToExecuteController;
import enteties.Request;
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
    private ObservableList<TestToExecute> runningTestTable;
    /**
     * the toggleGrope for selecting a test to change or lock from table.
     */
    ToggleGroup toggleGroupOfTestToExecute = new ToggleGroup();
    /**
     * the list of running test (type TestToExecute) got from DB.
     */
    private ArrayList<TestToExecute> runningTestLst;
    /**
     * the selected (by radio) testToExecute from the list in the table.
     */
    private TestToExecute selectedTest = null;
    /**
	 * object to use the TestToExecuteController class method.
	 */
    private static TestToExecuteController testToExecuteController = new TestToExecuteController();
    /**
	 * object to use the RequestController class method.
	 */
    private static RequestController requestController = new RequestController();
    /**
	 * object to use the notifications class.
	 */
    private static NotificationAlertsController notification = new NotificationAlertsController();
    
    
    /**
     * Constructs a new RunningTestController object.
     * Retrieves the running tests to execute for the user and initializes the necessary data structures.
     */
    public RunningTestController() {
    	Msg msg = testToExecuteController.selectRunningTestToExecuteByUser(ChatClient.user);
    	sendMsg(msg);
    	runningTestLst = msgReceived.convertData(TestToExecute.class); //ArrayList
    	runningTestTable = testToExecuteController.getObservLstWithFXValues(runningTestLst); //ObservableList
    	for(TestToExecute runningTest : runningTestTable) //toggle the radio in the table.
    		toggleGroupOfTestToExecute.getToggles().add(runningTest.getRadioButton()); // duration
    }
    
    /**
     * Initializes the controller and sets up the necessary components.
     */
    @FXML
	protected void initialize() {
    	selectCol.setCellValueFactory(new PropertyValueFactory<TestToExecute, String>("radioButton"));
    	testCodeCol.setCellValueFactory(new PropertyValueFactory<TestToExecute, String>("testCode"));
    	courseCol.setCellValueFactory(new PropertyValueFactory<TestToExecute, String>("courseName"));
    	startedCol.setCellValueFactory(new PropertyValueFactory<TestToExecute, String>("numberOfStudentsStarted"));
    	finishedCol.setCellValueFactory(new PropertyValueFactory<TestToExecute, String>("numberOfStudentsFinished"));
    	durationCol.setCellValueFactory(new PropertyValueFactory<TestToExecute, String>("textField"));
    	explanationCol.setCellValueFactory(new PropertyValueFactory<TestToExecute, String>("textField1"));
		table.setItems(runningTestTable);
		table.refresh();
	}

    /**
     * Handles the change button action event.
     *
     * @param event The action event triggered by clicking the change button.
     */
    @FXML
    void changeBtn(ActionEvent event) {
    	selectedTest = testToExecuteController.getSelectedTest(runningTestTable);
    	if(selectedTest==null) { notification.showErrorAlert("You didn't choose any test to change duration for."); return; } 
    	Object request = requestController.createRequestByTest(selectedTest, ChatClient.user, ChatClient.user); ///// need to be changed to find the proper hod.
    	if(request instanceof String) { notification.showErrorAlert((String)request); return; }
    	notification.setOnCancelAction(new Runnable() {	@Override public void run() {return;}});
    	notification.setOnOkAction(new Runnable() {
			@Override
			public void run() {
				Msg msg;
				try{ msg = requestController.insertRequest((Request)request); //insert to request table.
				}catch(Exception e) {System.out.println("there was a problem getting a msg for inserting the request"); return;}
		    	sendMsg(msg);
		    	notification.showInformationAlert("A request for changing diration sent to your HOD for consideration.");
		    	resetFields();
			}});
    	String str1 = "Are you sure you want to change the duration?";
    	String str2 = "test code "+selectedTest.getTestCode()+",  from "+ selectedTest.getTest().getDuration()+
    			"min to "+Integer.valueOf(selectedTest.getTextField().getText())+"min ?";
    	notification.showConfirmationAlert(str2, str1);
    } 

    /**
     * Handles the lock button action event.
     *
     * @param event The action event triggered by clicking the lock button.
     */
    @FXML
    void lockBtn(ActionEvent event) {
    	selectedTest = testToExecuteController.getSelectedTest(runningTestTable);
    	if(selectedTest==null) { notification.showErrorAlert("You didn't choose any test to lock."); return; }
    	notification.setOnCancelAction(new Runnable() {	@Override public void run() {return;}});
    	notification.setOnOkAction(new Runnable() {
			@Override
			public void run() {
				Msg msg = testToExecuteController.getMsgToLockTest(selectedTest); //manyMsg -> update & lockTest.
		    	sendMsg(msg);
		    	notification.showInformationAlert("The test (code "+selectedTest.getTestCode()+") got locked.");
		    	resetFields();
			}});
    	notification.showConfirmationAlert("you can't unlock after that.", "Are you sure you want to lock test "+selectedTest.getTestCode()+" ?");
    }
    
    /**
     * Resets all input fields to their default values.
     */
    private void resetFields() {
    	try { start("runningTest", "lecturerMenu");
		} catch (Exception e) {	e.printStackTrace();}
    }
}