package gui;

import java.util.ArrayList;
import JDBC.Msg;
import client.ChatClient;
import controllers.StudentTestController;
import controllers.TestToExecuteController;
import enteties.StudentTest;
import enteties.TestToExecute;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import notifications.NotificationAlertsController;

/**
 * Controller class for the Approve Grades screen.
 * 
 * @author Yuval Rozner 
 */
public class ApproveGradeController extends AbstractController{
	/**
	 * the columns for the table.
	 */
	@FXML
    private TableColumn<StudentTest, String> idCol ,nameCol ,showCol,noteCol,radioButtonCol;
	/**
	 * the columns for the table.
	 */
	@FXML
	private TableColumn<StudentTest, Integer> gradeCol;
	/**
	 * the table with all the grade should get approved.
	 */
    @FXML
    private TableView<StudentTest> table;
    /**
     * comboBox for choosing a testToExecute.
     */
    @FXML
    private ComboBox<String> testComboBox;
    /**
     * the change grade area.
     */
    @FXML
    private VBox changeGradeVbox;
    /**
     * ToggleGroup for choosing if want to change the grade or not.
     */
    @FXML
    private ToggleGroup changeGradeToggleGroup;
    /**
     * to select if want to change the grade or not.
     */
    @FXML
    private RadioButton noRadioButton, yesRadioButton;
    /**
     * inputs.
     */
    @FXML
    private TextField newGradeTextField, reasonTextField;
    /**
     * ObservableList of StudentTest for the table.
     */
	private ObservableList<StudentTest> TestTable;
    /**
     * ToggleGroup for the studentTests in the table.
     */
	private ToggleGroup testToggleGroup;
    /**
	 * boolean variable to indicate that any testToExecute has already been chose.
	 */
    private boolean chooseTestToExecute=false;
    /**
     * the list of StudentTests.
     */
    private ArrayList<StudentTest> studentTestLst;
    /**
     * the chosen TestToExecute from the comboBox.
     */
    private TestToExecute selectedTestToExecute;
    /**
	 * the list of testToExecute for the comboBox according to the user logged in.
	 */
    private ArrayList<TestToExecute> testToExecuteLst;
    /**
	 * object to use the TestToExecuteController class method.
	 */
    private static TestToExecuteController testToExecuteController = new TestToExecuteController();
    /**
	 * object to use the StudentTestController class method.
	 */
    private static StudentTestController studentTestController = new StudentTestController();
    /**
	 * object to use the notifications class.
	 */
    private static NotificationAlertsController notification = new NotificationAlertsController();
    /**
     * the StudentTest test wanted to be shown. 
     */
    public StudentTest StudentTestToShow;
	
    /**
     * Constructs an instance of the ApproveGradeController.
     * Retrieves the testToExecutes for the comboBox and initializes the necessary variables.
     */
    public ApproveGradeController() {
    	Msg msg = testToExecuteController.selectTestToExecuteByUser(ChatClient.user);
    	sendMsg(msg);
    	testToExecuteLst = msgReceived.convertData(TestToExecute.class);
    	testToggleGroup = new ToggleGroup();
    }

    /**
     * Initializes the controller after its root element has been completely processed.
     * Sets up event listeners and populates the necessary data.
     */
    @FXML
	protected void initialize() {
    	// set the list of TestToExecute in the comboBox:
    	testComboBox.getItems().addAll(testToExecuteController.getTestToExecuteNames(testToExecuteLst));
    	// initialize what happens when choosing a TestToExecute in the comboBox:
    	testComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
        	@Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                	chooseTestToExecute = true;
                    // Code to be executed when the selected item changes and newValue is not null
                    // Find the StudentTests object based on the new value:
                    selectedTestToExecute = testToExecuteController.findTestToExecuteByName(newValue, testToExecuteLst);
                    // Get the list of StudentTests associated with the selected TestToExecute:
                    if (selectedTestToExecute != null) {
                        sendMsg(studentTestController.getMsgForStudentTestsByTestToExecute(selectedTestToExecute));
                        studentTestLst = new ArrayList<>(msgReceived.convertData(StudentTest.class));
                        // set the table of StudentTests according to the selected TestToExecute:
                        for (StudentTest s :studentTestLst) {
                        	s.setNewTextField(); //change reason textField
                        	s.setNewTextField1(); // note textField
                        	s.setNewButton(); //show button
                        	s.setButtonText("Show"); //set the button text
                        	s.getButton().setOnMouseClicked(event -> { //set on action event - click show button
                        		try {
                        			showTestOpen(event);
                				} catch (Exception e) {
                					e.printStackTrace();
                				}
                        	});
                        	s.setNewRadioButton(); //select radio button
                        	testToggleGroup.getToggles().add(s.getRadioButton()); // add the radio button into toggleGroup
                        }
                        TestTable = FXCollections.observableArrayList(studentTestLst);
                		table.setItems(TestTable);
                		table.refresh();
                    }
                }
            }
        });
    	idCol.setCellValueFactory(new PropertyValueFactory<StudentTest, String>("studentId"));
    	nameCol.setCellValueFactory(new PropertyValueFactory<StudentTest, String>("studentName"));
    	gradeCol.setCellValueFactory(new PropertyValueFactory<StudentTest, Integer>("grade"));
    	showCol.setCellValueFactory(new PropertyValueFactory<StudentTest, String>("button"));
    	noteCol.setCellValueFactory(new PropertyValueFactory<StudentTest, String>("textField1"));
    	radioButtonCol.setCellValueFactory(new PropertyValueFactory<StudentTest, String>("radioButton"));
		table.setItems(TestTable);
		table.refresh();
		
		//add Listener to the toggle group of yes/no of change grade . if yes -> set enable the vbox that information needed for change, else -> set disable
		changeGradeToggleGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
		    @Override
		    public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
		        RadioButton selectedRadioButton = (RadioButton) newValue;
		        if (selectedRadioButton != null) {
		            if (selectedRadioButton == yesRadioButton) {
		            	changeGradeVbox.setDisable(false);
		            } else if (selectedRadioButton == noRadioButton) {
		            	changeGradeVbox.setDisable(true);
		            }
		        }
		    }
		});
	}  
    
    /**
     * Handles the event when the "Show" button is clicked to display a specific student test.
     *
     * @param event The MouseEvent representing the button click event.
     * @throws Exception if an error occurs during the execution of the method.
     */
    @FXML
    private void showTestOpen(MouseEvent event) throws Exception {
        Button clickedButton = (Button) event.getSource(); //get the button that has been clicked
        for (StudentTest studentTest : TestTable)  //search for the studentTest.
            if (studentTest.getButton().equals(clickedButton)) { 
                StudentTestToShow = studentTest;
                start("showStudentTest", "approveGrade");
            }
    }

    /**
     * Handles the event when the "Confirm" button is clicked to approve a grade.
     *
     * @param event The ActionEvent representing the button click event.
     */
    @FXML
    void confirm(ActionEvent event) { 
    	if(!chooseTestToExecute) {notification.showErrorAlert("you didnt select a grade to approve."); return;}
    	boolean found = false;
    	for (StudentTest studentTest : TestTable) {
    		if (studentTest.getRadioButton().isSelected()) {
    			found = true;
    			if (!changeGradeVbox.isDisable() && yesRadioButton.isSelected()) { // wanted to change grade.
        		    if (newGradeTextField.getText().isEmpty()) {notification.showErrorAlert("you must enter the new grade."); return;}
        		    try{
        		    	if(Integer.valueOf(newGradeTextField.getText())<0 || Integer.valueOf(newGradeTextField.getText())>100)
        		    		{notification.showErrorAlert("the new grade must be a number between 0 and 100."); return;}
        		    	studentTest.setGrade(Integer.valueOf(newGradeTextField.getText()));}
        		    catch(Exception e) {notification.showErrorAlert("the new grade must be a number between 0 and 100."); return;}
        		    if (reasonTextField.getText().isEmpty()) {notification.showErrorAlert("you must add a reason for changing the grade."); return;}
        		    studentTest.setChangeReason(reasonTextField.getText()); 
    			}
    			studentTest.setApproved("true");
    			if (studentTest.getTextField1().getText().isEmpty()) {notification.showErrorAlert("you must add a note for the grade approved grade."); return;}
    		    studentTest.setLecturerNotes(studentTest.getTextField1().getText());
    		    notification.setOnCancelAction(new Runnable() {	@Override public void run() {return;}});
    		    notification.setOnOkAction(new Runnable() {
					@Override
					public void run() {
						Msg msg = studentTestController.getMsgToUpdateStudentTests(studentTest);
				    	sendMsg(msg);
				    	notification.showInformationAlert("grade approved in DB.");
				    	resetFields();
		    			return;
					}});
    		    notification.showConfirmationAlert("you can't unapprove after that.", "Are you sure you want to approve the grade?");
    		}
    	}
    	if (found == false) notification.showErrorAlert("you didnt select a grade to approve.");
    }
    
    /**
     * Resets all input fields to their default values.
     */
    private void resetFields() {
    	reasonTextField.setText("");
    	newGradeTextField.setText("");
    	testComboBox.setValue(null);
    	table.getItems().clear();
		table.refresh();
    }
    
    /**
     * Returns the StudentTest object to be shown.
     *
     * @return The StudentTest object to be shown.
     */
    public StudentTest getStudentTestToShow() {
		return StudentTestToShow;
	}
}