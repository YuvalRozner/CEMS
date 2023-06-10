package gui;

import java.util.ArrayList;

import JDBC.Msg;
import client.ChatClient;
import controllers.CourseController;
import controllers.QuestionController;
import controllers.SubjectController;
import enteties.Course;
import enteties.Question;
import enteties.Subject;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;


public class CreateTestController extends AbstractController{

    @FXML
    private RadioButton answer1RadioButton, answer2RadioButton,answer3RadioButton,answer4RadioButton;

    @FXML
    private ToggleGroup answersToggleGroup;
    @FXML
    private Label questionLabel;
	//@FXML
   // private TableView<Question> table = new TableView<Question>();
    @FXML
    private TableView<Question> table;
    @FXML
    private TableColumn<Question, String>  idCol, lecturerCol, questionTextCol,checkBoxCol,pointsCol;
    @FXML
    private ComboBox<String> subjectComboBox;
    @FXML
    private ComboBox<String> courseComboBox;
    @FXML
    private TextArea commentsForLecturerTextArea;
    @FXML
    private TextArea commentsForStudentTextArea;
    @FXML
    private TextField durationTextField;
    
    /**
	 * the list of subject for the comboBox according to the subjects which match the user.
	 */
    private ArrayList<Subject> subjectsLst;
    /**
	 * the subject selected from the comboBox.
	 */
    private Subject selectedSubject;
    /**
	 * the list of course for the comboBox according to the subjects which match the user.
	 */
    private ArrayList<Course> coursesLst;
    /**
	 * the course selected from the comboBox.
	 */
    private Course selectedCourse;
    /**
	 * object to use the SubjectController class method.
	 */
    private static SubjectController subjectController = new SubjectController();
    /**
	 * object to use the CourseController class method.
	 */
    private static CourseController courseController = new CourseController();
    /**
	 * object to use the QuestionController class method.
	 */
    private static QuestionController questionController = new QuestionController();
    
    
    

    public CreateTestController() {
    	Msg msg = subjectController.selectSubjectByUser(ChatClient.user);
    	sendMsg(msg);
    	subjectsLst = msgReceived.convertData(Subject.class);
    }

    @FXML
	protected void initialize() {
    	subjectComboBox.setItems(subjectController.getSubjectNames(subjectsLst));
    	//initialize what happens when choosing a subject in the comboBox:
        subjectComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
        	@Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    // Code to be executed when the selected item changes and newValue is not null
                    // Find the Subject object based on the new value:
                    selectedSubject = subjectController.findSubjectByName(newValue, subjectsLst);
                    sendMsg(subjectController.getMsgForCourses(selectedSubject));
                    selectedSubject.setCourses(msgReceived.convertData(Course.class)); 
                    // Get the list of courses associated with the selected subject
                    if (selectedSubject != null) {
                    	coursesLst = subjectController.returnCoursesWithCheckbox(selectedSubject);
                        // Set the items of a comboBox with the list of courses:
                    	courseComboBox.setItems(courseController.getCourseNames(coursesLst));
                    }
                }
            }
        });
        
    	//initialize what happens when choosing a course in the comboBox:
        courseComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
        	@Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    // Code to be executed when the selected item changes and newValue is not null
                    // Find the course object based on the new value:
                	selectedCourse = courseController.findCourseByName(newValue, coursesLst);
                    sendMsg(courseController.getMsgForQuestions(selectedCourse));
                    System.out.println("is data empty?  " + (msgReceived.getData()));
                    selectedCourse.setQuestions(msgReceived.convertData(Question.class)); 
                    // Get the list of question associated with the selected subject
                    if (selectedCourse != null) {
                    	ObservableList<Question> questionsObservableList = questionController.getQuestionsForTable(selectedCourse.getQuestions());
                        // Set the items of a table with the list of questions:
                    	table.setItems(questionsObservableList);
                		table.refresh();
                    }
                }
            }
        });
        
    	idCol.setCellValueFactory(new PropertyValueFactory<Question, String>("id"));
    	questionTextCol.setCellValueFactory(new PropertyValueFactory<Question, String>("Question"));
    	lecturerCol.setCellValueFactory(new PropertyValueFactory<Question, String>("id"));
		checkBoxCol.setCellValueFactory(new PropertyValueFactory<Question, String>("checkbox"));
		pointsCol.setCellValueFactory(new PropertyValueFactory<Question, String>("textField"));
	}
    
    @FXML
    private void createNewQuestion(ActionEvent event) throws Exception{
    	start("createQuestion", "createTest");
    }
    
    @FXML
    void showAnswers(MouseEvent event) {
    	Question selectedQuestion = table.getSelectionModel().getSelectedItem();
    	questionLabel.setText(selectedQuestion.getQuestion());
    	answer1RadioButton.setText(selectedQuestion.getAnswers()[0]);
    	answer2RadioButton.setText(selectedQuestion.getAnswers()[1]);
    	answer3RadioButton.setText(selectedQuestion.getAnswers()[2]);
    	answer4RadioButton.setText(selectedQuestion.getAnswers()[3]);
    	ObservableList<Toggle> toggles = answersToggleGroup.getToggles();
        Toggle toggle = toggles.get(selectedQuestion.getCorrectAnswer());  // Index 2 represents the third toggle
        answersToggleGroup.selectToggle(toggle);
        
    }
    @FXML
    void save(ActionEvent event) {

    }
}

