package gui;
import java.util.ArrayList;

import JDBC.Msg;
import client.ChatClient;
import controllers.CourseController;
import controllers.TestToExecuteController;
import enteties.Course;
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


public class ApproveGradeController extends AbstractController{
	
	@FXML
    private TableColumn<StudentTest, String> idCol ,nameCol ,showCol,noteCol,radioButtonCol;
	@FXML
	private TableColumn<StudentTest, Integer> gradeCol;
    @FXML
    private TableView<StudentTest> table;
    @FXML
    private ComboBox<String> testComboBox;
    @FXML
    private VBox changeGradeVbox;
    @FXML
    private ToggleGroup changeGradeToggleGroup;
    @FXML
    private RadioButton noRadioButton;
    @FXML
    private RadioButton yesRadioButton;
    @FXML
    private TextField newGradeTextField;
    @FXML
    private TextField reasonTextField;
   
    public StudentTest StudentTestToShow;

	private ArrayList<StudentTest> arrStudentTest;	
	private ObservableList<StudentTest> TestTable;
  
    ToggleGroup testToggleGroup;
	
	
    int counter = 0;
    
    
    /**
	 * the list of course for the comboBox according to the user logged in.
	 */
    private ArrayList<TestToExecute> testToExecuteLst;
    /**
	 * object to use the TestToExecuteController class method.
	 */
    private static TestToExecuteController testToExecuteController = new TestToExecuteController();
	
	
	public  ArrayList<StudentTest> fakeDataToTabel(){
		ArrayList<StudentTest> fakeArrayListStudentTest = new ArrayList<StudentTest>();

		StudentTest student1 = new StudentTest("111", 2, 120, "1214",50,"note from the lecturer","false","Dor Shabat");
		StudentTest student2 = new StudentTest("222", 2, 120, "2124",75,"note from the lecturer","false", "Yuval Rozner");
		StudentTest student3 = new StudentTest("333", 2, 120, "1124",100,"note from the lecturer","false", "Yuval Mintz");
		
		fakeArrayListStudentTest.add(student1);
		fakeArrayListStudentTest.add(student2);
		fakeArrayListStudentTest.add(student3);
		
		return fakeArrayListStudentTest;
		
	}
	
    public ApproveGradeController() {
    	Msg msg = testToExecuteController.selectTestToExecuteByUser(ChatClient.user);
    	sendMsg(msg);
    	testToExecuteLst = msgReceived.convertData(TestToExecute.class);
    	
    	
    	
    	arrStudentTest = new ArrayList<StudentTest>(fakeDataToTabel());
    	testToggleGroup = new ToggleGroup();
        for (StudentTest studentTest :arrStudentTest) {
        	studentTest.setNewTextField(); //change reason textfield
        	studentTest.setNewTextField1(); // note textfield
        	studentTest.setNewButton(); //show button
        	
        	
        	studentTest.setButtonText("Show"); //set the button text
        	studentTest.getButton().setOnMouseClicked(event -> { //set on action event - click show button
        		try {
        			showTestOpen(event);
				} catch (Exception e) {
					e.printStackTrace();
				}
        	});
        	studentTest.setNewRadioButton(); //select radio button
        	testToggleGroup.getToggles().add(studentTest.getRadioButton()); // add the radio button into toggleGroup
        	
        	
        }
        TestTable = FXCollections.observableArrayList(arrStudentTest);      
    }

    @FXML
	protected void initialize() {
    	//testComboBox.setItems(testToExecuteController.getTestToExecuteNames(testToExecuteLst));
    	System.out.println("testToExecuteLst: " + testToExecuteLst);
    	testComboBox.getItems().addAll(testToExecuteController.getTestToExecuteNames(testToExecuteLst));

    	idCol.setCellValueFactory(new PropertyValueFactory<StudentTest, String>("studentId"));
    	nameCol.setCellValueFactory(new PropertyValueFactory<StudentTest, String>("studentName"));
    	gradeCol.setCellValueFactory(new PropertyValueFactory<StudentTest, Integer>("grade"));
    	showCol.setCellValueFactory(new PropertyValueFactory<StudentTest, String>("button"));
    	noteCol.setCellValueFactory(new PropertyValueFactory<StudentTest, String>("textField1"));
    	radioButtonCol.setCellValueFactory(new PropertyValueFactory<StudentTest, String>("radioButton"));
    	
    	
    	
		table.setItems(TestTable);
		table.refresh();
		//testComboBox.getItems().addAll("infi - 17/02 - id ...", "logic");
		
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
    
  
    @FXML
    private void showTestOpen(MouseEvent event) throws Exception {
        Button clickedButton = (Button) event.getSource(); //get the button that has been clicked
        for (StudentTest studentTest : TestTable) { 
            if (studentTest.getButton() == clickedButton) { //search for he studenttest //need to change to equals? didnt have time to check it
            	System.out.println("i am print from approveGeadeController and now i save "+ studentTest);
                StudentTestToShow = studentTest;
                break;
            }
        }
        
        start("showStudentTest", "approveGrade");
            
    }

    
    @FXML
    void confirm(ActionEvent event) {
    	boolean found = false;
    	for (StudentTest studentTest :TestTable) {
    		if (studentTest.getRadioButton().isSelected()) {
    			found = true;
    			System.out.println("i found the one that the user want to change the student is: " +studentTest );
    			if (changeGradeVbox.isDisable() == false && yesRadioButton.isSelected()) {
    				
        			String text = reasonTextField.getText();
        		    if (text.isEmpty()) {
        		    	System.out.println("alert: you didnt wrote a change reason");
        		    } else {
        		    	System.out.println("update chane of reason in the db?....");
        		    	System.out.println("update the grade in the db....");
        		    	System.out.println("update the approve boolean in the db....");
        		    }
        			
    			}
    			else {
    				System.out.println("the user didnt want to change the grade....");
    				System.out.println("update the approve boolean in the db....");
    			}
    			
    			break;
    		}
    	}
    	if (found == false) {
    		System.out.println("alert: you didnt selected");
    	}
    }
    
	
    
    public StudentTest getStudentTestToShow() {
		return StudentTestToShow;
	}
    
    
    

}
