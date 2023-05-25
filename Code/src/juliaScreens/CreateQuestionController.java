package juliaScreens;

import java.util.ArrayList;

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


        subjectComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                
                Subject selectedSubject = findSubjectByName(newValue);
                ArrayList<CourseWithAddition> courses = returnSubjectWithCheckbox(selectedSubject);
                if (selectedSubject != null) {
                    
                    table.setItems(FXCollections.observableArrayList(courses));
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
    
    private ArrayList<CourseWithAddition> returnSubjectWithCheckbox(Subject subject) {
    	ArrayList<CourseWithAddition> courses = new ArrayList<>();
    	for (Course course : subject.getCourses()) {
    		courses.add(new CourseWithAddition(course.getCourseNum(),course.getCourseName()));
        }
        return courses; 
    }

    
}
