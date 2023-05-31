package julia;

import java.util.ArrayList;

import enteties.Course;
import enteties.Subject;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;

public class CreateQuestionController {

    @FXML
    private ToggleGroup ChooseCorrectAnswerGroup;

    @FXML
    private TableColumn<Course, String> courseCol;
    @FXML
    private TableColumn<Course, String> selectCol;
    @FXML
    private ComboBox<String> subjectComboBox;

    @FXML
    private TableView<Course> table;

    private ObservableList<Subject> subjectTable;
    private ArrayList<Subject> arrQuestion;

    public CreateQuestionController() {
        arrQuestion = Main.subject;
    }

    @FXML
    protected void initialize() {
        courseCol.setCellValueFactory(new PropertyValueFactory<Course, String>("CourseName"));
        selectCol.setCellValueFactory(new PropertyValueFactory<Course, String>("Select"));

    
        subjectComboBox.setItems(getSubjectNames());

        subjectComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {


        	@Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    // Code to be executed when the selected item changes and newValue is not null

                    // Find the Subject object based on the new value
                    Subject selectedSubject = findSubjectByName(newValue);

                    // Get the list of courses associated with the selected subject
                    ArrayList<Course> courses = returnSubjectWithCheckbox(selectedSubject);

                    if (selectedSubject != null) {
                        // Set the items of a table with the list of courses
                        table.setItems(FXCollections.observableArrayList(courses));
                    }
                }
            }

			
        });

    }

    private ObservableList<String> getSubjectNames() {
        ObservableList<String> subjectNames = FXCollections.observableArrayList();
        for (Subject subject : Main.subject) {
            subjectNames.add(subject.getSubjectName());
        }
        return subjectNames;
    }

    private Subject findSubjectByName(String subjectName) {
        for (Subject subject : Main.subject) {
            if (subject.getSubjectName().equals(subjectName)) {
                return subject;
            }
        }
        return null; // Subject not found
    }
    
    private ArrayList<Course> returnSubjectWithCheckbox(Subject subject) {
    	ArrayList<Course> courses = new ArrayList<>();
    	for (Course course : subject.getCourses()) {
    		Course tmp = new Course(course.getCourseNum(),course.getCourseName());
    		tmp.setNewSelect();
    		courses.add(tmp);
        }
        return courses; 
    }

    
}
