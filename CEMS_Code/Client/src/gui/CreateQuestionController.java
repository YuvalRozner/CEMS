package gui;

import java.util.ArrayList;

import enteties.Course;
import enteties.Subject;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;

public class CreateQuestionController extends AbstractController{

    @FXML
    private ToggleGroup ChooseCorrectAnswerGroup;

    @FXML
    private TableColumn<Course, String> courseCol;
    @FXML
    private TableColumn<Course, String> selectCol;
    @FXML
    private ComboBox<String> subjectComboBox;

    @FXML
    private TableView<Course> coursesTable;


    @FXML
    private TextField answer1TextField , answer2TextField;



    @FXML
    private TextField answer3TextField;

    @FXML
    private TextField answer4TextField;
    
    
    @FXML
    private TextField instructionTextField;

    @FXML
    private TextField questionTextField;

    @FXML
    private RadioButton radioButton1;

    @FXML
    private RadioButton radioButton2;

    @FXML
    private RadioButton radioButton3;

    @FXML
    private RadioButton radioButton4;

    
    public  ArrayList<Subject> subjectsFake; 
	
    
    
    
   public  CreateQuestionController() {
	   // start build fake data /////////////////////////////////////////////////////
	   	Course course1 = new Course("1","infi", "03");
		Course course2 = new Course("2","logic", "04");
		Course course3 = new Course("3","data sturcture","04");
		ArrayList<Course> CoursesArray1 = new ArrayList<Course>();
		CoursesArray1.add(course1);
		CoursesArray1.add(course2);
		ArrayList<Course> CoursesArray2 = new ArrayList<Course>();
		CoursesArray2.add(course3);
		
		subjectsFake = new ArrayList<Subject>();
		
		Subject s1 = new Subject("1","math",CoursesArray1);
		Subject s2 = new Subject("1","software",CoursesArray2);
		
		subjectsFake.add(s1);
		subjectsFake.add(s2);
	// end build fake data /////////////////////////////////////////////////////////////
   }
   @FXML
    protected void initialize() {
        courseCol.setCellValueFactory(new PropertyValueFactory<Course, String>("name"));
        selectCol.setCellValueFactory(new PropertyValueFactory<Course, String>("checkbox"));

        subjectComboBox.setItems(getSubjectNames());

        subjectComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

        	@Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    // Code to be executed when the selected item changes and newValue is not null

                    // Find the Subject object based on the new value
                    Subject selectedSubject = findSubjectByName(newValue);

                    // Get the list of courses associated with the selected subject
                    ArrayList<Course> courses = returnCoursesWithCheckbox(selectedSubject);

                    if (selectedSubject != null) {
                        // Set the items of a table with the list of courses
                    	coursesTable.setItems(FXCollections.observableArrayList(courses));
                    }
                }
            }
			
        });
    }
	   @FXML
	   void save(ActionEvent event) {
	
	   }

    private ObservableList<String> getSubjectNames() {
        ObservableList<String> subjectNames = FXCollections.observableArrayList();
        for (Subject subject : subjectsFake) {
            subjectNames.add(subject.getName());
        }
        return subjectNames;
    }

    private Subject findSubjectByName(String subjectName) {
        for (Subject subject : subjectsFake) {
            if (subject.getName().equals(subjectName)) {
                return subject;
            }
        }
        return null; // Subject not found
    }
    
    private ArrayList<Course> returnCoursesWithCheckbox(Subject subject) {
    	ArrayList<Course> courses = subject.getCourses();
    	for (Course course : courses) {
    		course.setNewCheckbox();
        }
        return courses; 
    }
    
}
