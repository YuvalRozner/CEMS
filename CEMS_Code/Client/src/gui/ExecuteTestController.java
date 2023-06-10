package gui;

import java.util.ArrayList;

import enteties.StudentTest;
import enteties.Test;
import enteties.TestToExecute;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class ExecuteTestController extends AbstractController{
	
	private ArrayList<TestToExecute> executeTests;
	
	ToggleGroup toggleGroupOfTestToExecute;
	TestToExecute testToExecute;
	
	
	
	
	
	private ObservableList<TestToExecute> ETTable;
	
    @FXML
    private TableView<TestToExecute> table = new TableView<TestToExecute>();

    @FXML
    private Button backbtn;

    @FXML
    private TableColumn<TestToExecute,String> codecol,coursnamecol,numTestcol,selectcol,showcol,typecol,datecol;

    @FXML
    private Button executebtn;
    
    public ArrayList<TestToExecute> fakeData(){
    	ArrayList<TestToExecute> fakeDataArrzyList = new ArrayList<TestToExecute>();
    	
    	//public TestToExecute(String testCode, String testId, String testingType, String date, Double average, Double median, Boolean lock, Integer timeExtension,
		//String lecturerId, Integer numberOfStudentsStarted, Integer numberOfStudentsFinished, Integer[] distribusion) {
			
    	double a = 1;
    	TestToExecute testToExecute1 = new TestToExecute("1234","12","", "", a,a ,false, 0, "1111", 0,0, new Integer[1] );
    	TestToExecute testToExecute2 = new TestToExecute("1278","12","", "", a,a ,false, 0, "1111", 0,0, new Integer[1] );
    	TestToExecute testToExecute3 = new TestToExecute("1290","12","", "", a,a ,false, 0, "1111", 0,0, new Integer[1] );
    	
    	//public Test(String id, String number, String courseNumber, Integer duration, String instructionsForStudent, String instructionsForLecturer) {
    	
    	Test t1 = new Test("12","02","14",60,"read cerfully, wnjoy your time. you need to mark one answer of each questopn..", "this test is a tricky one, if you want to fuck your students avarage, give them this test");
    	testToExecute1.setTest(t1);
    	testToExecute2.setTest(t1);
    	testToExecute3.setTest(t1);
    	fakeDataArrzyList.add(testToExecute1);
    	fakeDataArrzyList.add(testToExecute2);
    	fakeDataArrzyList.add(testToExecute3);
    	
    	return fakeDataArrzyList;
    }
    
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public ExecuteTestController() {
    	executeTests = new ArrayList<TestToExecute>(fakeData());
    	toggleGroupOfTestToExecute = new ToggleGroup();
        //ArrayList<Question> convertedList = new ArrayList<>();
        for (TestToExecute test : executeTests) {
        	test.setNewButton();
        	test.setButtonText("Show");
        	test.setNewRadioButton();
        	toggleGroupOfTestToExecute.getToggles().add((RadioButton)test.getRadioButton());
        	test.setNewComboBox();
        	((ComboBox)test.getComboBox()).getItems().addAll("Online", "Manual");
        	((ComboBox)test.getComboBox()).setValue("Online");
        	test.setNewTextField();
        	test.setNewTextField1();
        	test.getButton().setOnMouseClicked(event -> {
        		try {
        			showTestOpen(event);
				} catch (Exception e) {
					e.printStackTrace();
				}
        	});
        	
        	
        	
        	
        	test.getComboBox().setDisable(true);
        	test.getTextField().setDisable(true);
         	test.getTextField1().setDisable(true);
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
        ETTable = FXCollections.observableArrayList(executeTests);
       
    }
    	
    
    @FXML
	protected void initialize() {
    	numTestcol.setCellValueFactory(new PropertyValueFactory<TestToExecute, String>("testCode"));
    	coursnamecol.setCellValueFactory(new PropertyValueFactory<TestToExecute, String>("testCode"));
    	datecol.setCellValueFactory(new PropertyValueFactory<TestToExecute, String>("textField"));
    	showcol.setCellValueFactory(new PropertyValueFactory<TestToExecute, String>("button"));
    	selectcol.setCellValueFactory(new PropertyValueFactory<TestToExecute, String>("radioButton"));	
    	codecol.setCellValueFactory(new PropertyValueFactory<TestToExecute, String>("textField1"));
    	typecol.setCellValueFactory(new PropertyValueFactory<TestToExecute, String>("comboBox"));
    	
    	
		table.setItems(ETTable);
		table.refresh();

	}

    @FXML
    void executeTestBtn(ActionEvent event) {
    }
    @FXML
    private void showTestOpen(MouseEvent event) throws Exception{
    	 Button clickedButton = (Button) event.getSource(); //get the button that has been clicked
         for (TestToExecute testToExecute : ETTable) { 
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