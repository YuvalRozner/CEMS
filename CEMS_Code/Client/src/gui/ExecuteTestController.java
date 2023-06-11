package gui;

import java.util.ArrayList;

import JDBC.Msg;
import client.ChatClient;
import controllers.TestController;
import controllers.TestToExecuteController;
import enteties.Test;
import enteties.TestToExecute;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class ExecuteTestController extends AbstractController{
	
	ToggleGroup toggleGroupOfTestToExecute;
	TestToExecute testToExecute;
	
	
	private ObservableList<TestToExecute> testToExecuteTable;
	
    @FXML
    private TableView<TestToExecute> table = new TableView<TestToExecute>();

    @FXML
    private Button backbtn;

    @FXML
    private TableColumn<TestToExecute,String> codecol,coursnamecol,numTestcol,selectcol,showcol,typecol,datecol;

    @FXML
    private Button executebtn;
    
    
    
    
    
    
    
    
    
    /**
	 * the list of testToExecute for the table created by the list of tests.
	 */
    private ArrayList<TestToExecute> executeTests;
    /**
	 * the list of tests for the comboBox according to the user logged in.
	 */
    private ArrayList<Test> testLst;
    /**
	 * object to use the TestController class method.
	 */
    private static TestController testController = new TestController();
    /**
	 * object to use the TestToExecuteController class method.
	 */
    private static TestToExecuteController testToExecuteController = new TestToExecuteController();
    
    
  
    
    

	public ExecuteTestController() {
    	toggleGroupOfTestToExecute = new ToggleGroup();
    	Msg msg = testController.selectTestByUser(ChatClient.user);
    	sendMsg(msg);
    	testLst = msgReceived.convertData(Test.class);
    	System.out.println("testLst: "+testLst); //////////////////////////////////////////////////////////////////
    	executeTests = testToExecuteController.executeListOfTests(testLst, ChatClient.user);
    	
    	//executeTests = new ArrayList<TestToExecute>(fakeData());
    	
        for (TestToExecute test : executeTests) {
        	toggleGroupOfTestToExecute.getToggles().add((RadioButton)test.getRadioButton()); //
        	test.getButton().setOnMouseClicked(event -> { 
        		try { showTestOpen(event);
				} catch (Exception e) {	e.printStackTrace();} 	});
        	
        	test.getRadioButton().selectedProperty().addListener((observable, oldValue, newValue) -> {
        		if (newValue == true) {
        			test.getComboBox().setDisable(false);
                	test.getTextField().setDisable(false);
                 	test.getTextField1().setDisable(false);
        		}
        		else {
        			test.getComboBox().setDisable(true);
                	test.getTextField().setDisable(true);
                 	test.getTextField1().setDisable(true);
        		} 
        	});
        }
        testToExecuteTable = FXCollections.observableArrayList(executeTests);
        
        table.setItems(testToExecuteTable);
		table.refresh();
    }
    	
    
    @FXML
	protected void initialize() {
    	numTestcol.setCellValueFactory(new PropertyValueFactory<TestToExecute, String>("testId"));
    	coursnamecol.setCellValueFactory(new PropertyValueFactory<TestToExecute, String>("courseName"));
    	datecol.setCellValueFactory(new PropertyValueFactory<TestToExecute, String>("textField"));
    	showcol.setCellValueFactory(new PropertyValueFactory<TestToExecute, String>("button"));
    	selectcol.setCellValueFactory(new PropertyValueFactory<TestToExecute, String>("radioButton"));	
    	codecol.setCellValueFactory(new PropertyValueFactory<TestToExecute, String>("textField1"));
    	typecol.setCellValueFactory(new PropertyValueFactory<TestToExecute, String>("comboBox"));
    	
    	System.out.println("testToExecuteTable: "+testToExecuteTable); //////////////////////////////////////////////////////////////////
		table.setItems(testToExecuteTable);
		table.refresh();

	}

    @FXML
    void executeTestBtn(ActionEvent event) {
    }
    @FXML
    private void showTestOpen(MouseEvent event) throws Exception{
    	 Button clickedButton = (Button) event.getSource(); //get the button that has been clicked
         for (TestToExecute testToExecute : testToExecuteTable) { 
             if (testToExecute.getButton() == clickedButton) { //search for he studenttest //need to change to equals? didnt have time to check it
             	System.out.println("i am print from ExecuteTestController and now i save "+ testToExecute);
             	this.testToExecute = testToExecute;
                 break;
             }
         }
		start("showStudentTest", "executeTest");
    }
    public TestToExecute getTestToShow() {
		return testToExecute;
    	
    }

}