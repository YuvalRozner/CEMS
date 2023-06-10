package gui;

import java.util.ArrayList;

import JDBC.Msg;
import client.ChatClient;
import controllers.CourseController;
import controllers.QuestionController;
import controllers.SubjectController;
import controllers.TestController;
import enteties.Course;
import enteties.Question;
import enteties.Subject;
import enteties.Test;
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
import notifications.NotificationAlertsController;

/**
 * Controller class for the Create Test screen.
 */
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
	 * the number of the question (01-99).
	 */
    @FXML
    private TextField testNumberTextField;
    /**
	 * boolean variable to indicate that any subject has already been chose. for not letting the user create a test without choosing subject.
	 */
    private boolean subjectChoose = false; //indicates that a subject was chosen.
    /**
	 * boolean variable to indicate that any course has already been chose. for not letting the user create a test without choosing course.
	 */
    private boolean courseChoose = false; //indicates that a subject was chosen.
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
	 * object to use the TestController class method.
	 */
    private static TestController testController = new TestController();
    /**
	 * object to use the QuestionController class method.
	 */
    private static QuestionController questionController = new QuestionController();
    /**
	 * object to use the notifications class.
	 */
    private static NotificationAlertsController notification = new NotificationAlertsController();
    
    
    /**
     * Default constructor for the CreateTestController class.
     * Initializes the subjectsLst.
     */
    public CreateTestController() {
    	Msg msg = subjectController.selectSubjectByUser(ChatClient.user);
    	sendMsg(msg);
    	subjectsLst = msgReceived.convertData(Subject.class);
    }

    /**
     * Initializes the controller after its root element has been completely processed.
     * Sets up the courses comboBox according to the chosen subject and then, sets up the question table.
     */
    @FXML
	protected void initialize() {
    	subjectComboBox.setItems(subjectController.getSubjectNames(subjectsLst));
    	//initialize what happens when choosing a subject in the comboBox:
        subjectComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
        	@Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                	subjectChoose = true;
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
                	courseChoose = true;
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
        // sets up the question table columns:
    	idCol.setCellValueFactory(new PropertyValueFactory<Question, String>("id"));
    	questionTextCol.setCellValueFactory(new PropertyValueFactory<Question, String>("Question"));
    	lecturerCol.setCellValueFactory(new PropertyValueFactory<Question, String>("id"));
		checkBoxCol.setCellValueFactory(new PropertyValueFactory<Question, String>("checkbox"));
		pointsCol.setCellValueFactory(new PropertyValueFactory<Question, String>("textField"));
	}
    
    /**
     * show another screen - createQuestion.
     * @param event
     * @throws Exception
     */
    @FXML
    private void createNewQuestion(ActionEvent event) throws Exception{
    	start("createQuestion", "createTest");
    }
    
    /**
     * Event handler for displaying the answers of the selected question.
     *
     * @param event The MouseEvent triggering the event.
     */
    @FXML
    void showAnswers(MouseEvent event) {
    	// Get the selected question from the table.
    	Question selectedQuestion = table.getSelectionModel().getSelectedItem();
    	// Display the question and answers in the UI.
    	questionLabel.setText(selectedQuestion.getQuestion());
    	answer1RadioButton.setText(selectedQuestion.getAnswers()[0]);
    	answer2RadioButton.setText(selectedQuestion.getAnswers()[1]);
    	answer3RadioButton.setText(selectedQuestion.getAnswers()[2]);
    	answer4RadioButton.setText(selectedQuestion.getAnswers()[3]);
    	// Select the correct answer toggle.
    	ObservableList<Toggle> toggles = answersToggleGroup.getToggles();
        Toggle toggle = toggles.get(selectedQuestion.getCorrectAnswer());  // Index 2 represents the third toggle
        answersToggleGroup.selectToggle(toggle); 
    }
    
    @FXML
    void save(ActionEvent event) {
    	if(!subjectChoose) { notification.showErrorAlert("you must choose a subject."); return; } //checks if chose subject.
    	if(!courseChoose) { notification.showErrorAlert("you must choose a course."); return; } //checks if chose course.
    	//tries to create a Test Object:
    	Object newTest = testController.checkInputs(selectedSubject.getNumber()+selectedCourse.getNumber()+testNumberTextField.getText(),
    			testNumberTextField.getText(), selectedCourse.getNumber(), durationTextField.getText(),
    			commentsForStudentTextArea.getText(), commentsForLecturerTextArea.getText());
    	// if there are issues in inputs:
    	if(newTest instanceof String) {	notification.showErrorAlert((String)newTest); return; }
    	Msg msg = testController.insertTest((Test)newTest); // create an insert query msg.
    	if(msg==null) {System.out.println("cant create this test.."); return;}
    	sendMsg(msg);
    	notification.showInformationAlert("Data inserted to the DB.");
    	resetFields();
    }
    
    /**
     * Resets all input fields to their default values.
     */
    private void resetFields() {
    	testNumberTextField.setText("");
    	durationTextField.setText("");
    	commentsForStudentTextArea.setText("");
    	commentsForLecturerTextArea.setText("");
    	table.refresh();
    }
}

