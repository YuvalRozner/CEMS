package gui;

import java.util.ArrayList;

import JDBC.Msg;
import JDBC.MsgType;
import client.ChatClient;
import controllers.TestToExecuteController;
import enteties.Question;
import enteties.Test;
import enteties.TestToExecute;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * Controller class for the Show Test Data screen.
 * Handles the interaction between the UI and the underlying logic for displaying tests.
 * 
 * @author Mor Shmuel
 */
public class ShowTestDataController  extends AbstractController{
	
	private ArrayList<TestToExecute> arrShowTest = new ArrayList<>();

	private ObservableList<TestToExecute> showTestTable;
	
	/**
	 * object to use the TestToExecuteController class method.
	 */
    private static TestToExecuteController testToExecuteController = new TestToExecuteController();
    
    @FXML
    private Button backbtn;

    /**
     * the columns for the table.
     */
    @FXML
    private TableColumn<TestToExecute, String> codecol,coursnamecol,datecol,showcol,typecol;
    
    /**
     * the columns for the table.
     */
    @FXML
    private TableColumn<TestToExecute, Integer> numTestcol;
    
    @FXML
    private TableView<TestToExecute> table = new TableView<TestToExecute>();
    
    /**
	 * the TestToExecute for the show button.
	 */
	TestToExecute testToExecuteToShow;
    
    public ShowTestDataController() {
    	Msg msg = testToExecuteController.selectTestToExecuteByHod(ChatClient.user);
    	sendMsg(msg);
    	System.out.println("Data = " + AbstractController.msgReceived.getData());
    	arrShowTest = msgReceived.convertData(TestToExecute.class);
        for (TestToExecute test : arrShowTest) {
        	test.setNewButton();
        	test.setButtonText("Show");
        	test.getButton().setOnMouseClicked(event -> { 
        		try { showTestOpen(event);
				} catch (Exception e) {	e.printStackTrace();} 	});
        }
        
        showTestTable = FXCollections.observableArrayList(arrShowTest);
        System.out.println("showTestTable = " + showTestTable); 
        table.setItems(showTestTable);
		table.refresh();
    }
    
    /**
     * Handles the event when a test execution is clicked to be shown.
     * Retrieves the corresponding TestToExecute object based on the clicked button.
     * Sets the selected test execution to be shown and starts the "showStudentTest" view.
     *
     * @param event The mouse event triggered by clicking the show button of a test execution.
     * @throws Exception if an exception occurs during the process.
     */
    @FXML
    private void showTestOpen(MouseEvent event) throws Exception{
    	 Button clickedButton = (Button) event.getSource(); //get the button that has been clicked
         for (TestToExecute testToExecute : showTestTable)  //search for the studentTest.
        	 if (testToExecute.getButton().equals(clickedButton)) { 
             	testToExecuteToShow = testToExecute;
             	start("showStudentTest", "showTestData");
             }		
    }
    
    @FXML
	protected void initialize() {
    	codecol.setCellValueFactory(new PropertyValueFactory<TestToExecute, String>("testCode"));
    	coursnamecol.setCellValueFactory(new PropertyValueFactory<TestToExecute, String>("courseName"));
    	datecol.setCellValueFactory(new PropertyValueFactory<TestToExecute, String>("date"));
    	numTestcol.setCellValueFactory(new PropertyValueFactory<TestToExecute, Integer>("testId"));
    	showcol.setCellValueFactory(new PropertyValueFactory<TestToExecute, String>("button"));
    	typecol.setCellValueFactory(new PropertyValueFactory<TestToExecute, String>("testingType"));

		table.setItems(showTestTable);
		table.refresh();
	}
    
    /**
     * Returns the TestToExecute object to be shown.
     *
     * @return The TestToExecute object to be shown.
     */
    public TestToExecute getTestToShow() {
		return testToExecuteToShow;
    }
}
