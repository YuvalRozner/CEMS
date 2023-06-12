package gui;

import java.util.ArrayList;

import enteties.Test;
import enteties.TestToExecute;
import javafx.beans.property.SimpleIntegerProperty;
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

public class RunningTestController extends AbstractController{
	
	private ArrayList<TestToExecute> arrRunningTest;	
	private ObservableList<TestToExecute> RuningTestTable;
    @FXML
    private Button backbtn;

    @FXML
    private Button changebtn;

    @FXML
    private TableColumn<TestToExecute, String> corsecol,numcol,selectcol,durationcol;
   
    
    
    @FXML
    private Button lockbtn;
    
    @FXML
    private TableView<TestToExecute> table= new TableView<TestToExecute>();
    
    ToggleGroup toggleGroupOfTestToExecute;
    
    
    public ArrayList<TestToExecute> fakeData(){
    	ArrayList<TestToExecute> fakeDataArrzyList = new ArrayList<TestToExecute>();
    	
    	//public TestToExecute(String testCode, String testId, String testingType, String date, Double average, Double median, Boolean lock, Integer timeExtension,
		//String lecturerId, Integer numberOfStudentsStarted, Integer numberOfStudentsFinished, Integer[] distribusion) {
			
    	double a = 1;
    	//TestToExecute testToExecute1 = new TestToExecute("1234","12","", "", a,a ,false, 0, "1111", 0,0, new Integer[1] );
    	//TestToExecute testToExecute2 = new TestToExecute("1278","12","", "", a,a ,false, 0, "1111", 0,0, new Integer[1] );
    	//TestToExecute testToExecute3 = new TestToExecute("1290","12","", "", a,a ,false, 0, "1111", 0,0, new Integer[1] );
    	
    	//public Test(String id, String number, String courseNumber, Integer duration, String instructionsForStudent, String instructionsForLecturer) {
    	
    	Test t1 = new Test("12","02","14",60,"read cerfully, wnjoy your time. you need to mark one answer of each questopn..", "this test is a tricky one, if you want to fuck your students avarage, give them this test");
    	//testToExecute1.setTest(t1);
    	//testToExecute2.setTest(t1);
    	//testToExecute3.setTest(t1);
    	//fakeDataArrzyList.add(testToExecute1);
    	//fakeDataArrzyList.add(testToExecute2);
    	//fakeDataArrzyList.add(testToExecute3);
    	
    	return fakeDataArrzyList;
    }
    
    
    public RunningTestController() {
    	arrRunningTest =new ArrayList<TestToExecute>(fakeData());
    	toggleGroupOfTestToExecute = new ToggleGroup();
        for (TestToExecute runingTest : arrRunningTest) {
        	runingTest.setNewRadioButton();
        	toggleGroupOfTestToExecute.getToggles().add((RadioButton)runingTest.getRadioButton());
        	runingTest.setNewTextField();
        	runingTest.getTextField().setText(Integer.toString(runingTest.getTest().getDuration()));
        	runingTest.getTextField().setDisable(true);
        	
        
        	runingTest.getRadioButton().selectedProperty().addListener((observable, oldValue, newValue) -> {
        		if (newValue == true) {
        			runingTest.getTextField().setDisable(false);
        		}
        		else {
        			runingTest.getTextField().setDisable(true);
        			//return to the initial number, need to change only when press change
        			//runingTest.getTextField().setText(Integer.toString(runingTest.getTest().getDuration())); 
        		}
        		
                 
             });
        }
        RuningTestTable = FXCollections.observableArrayList(arrRunningTest);
    }
    
    @FXML
	protected void initialize() {
    	numcol.setCellValueFactory(new PropertyValueFactory<TestToExecute, String>("testCode"));
    	corsecol.setCellValueFactory(new PropertyValueFactory<TestToExecute, String>("testCode"));
    	selectcol.setCellValueFactory(new PropertyValueFactory<TestToExecute, String>("radioButton"));
    	durationcol.setCellValueFactory(new PropertyValueFactory<TestToExecute, String>("textField"));
    
    	/*problem : i want to get duration but i dont have it in testtoexecute
    	 * i noticed i have test and in test i have duration
    	 * this is the solotion for it:
    	 * */
    	/*
    	durationcol.setCellValueFactory(data -> {
    	    TestToExecute testToExecute = data.getValue();
    	    Test test = testToExecute.getTest();
    	    Integer duration = test.getDuration();
    	    return new SimpleIntegerProperty(duration).asObject();
    	});
    	
    		*/
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