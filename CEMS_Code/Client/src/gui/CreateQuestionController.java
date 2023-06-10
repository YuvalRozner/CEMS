package gui;

import java.util.ArrayList;
import JDBC.Msg;
import JDBC.MsgType;
import client.ChatClient;
import controllers.QuestionController;
import enteties.Course;
import enteties.Question;
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
import notifications.NotificationAlertsController;

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
    private TextField answer1TextField , answer2TextField, answer3TextField, answer4TextField;
    @FXML
    private TextField instructionTextField;
    @FXML
    private TextField questionNumberTextField;
    @FXML
    private TextField questionTextField;
    @FXML
    private RadioButton radioButton1, radioButton2, radioButton3, radioButton4;
    
    private ArrayList<Subject> subjectsLSt;
    private Subject selectedSubject;
    private static QuestionController questionController = new QuestionController();
    private static NotificationAlertsController notification = new NotificationAlertsController();
    private boolean subjectChoose = false; //indicates that a subject was chosen.
   
    
    public  CreateQuestionController() {
    	Msg msg = new Msg(MsgType.select);
    	msg.setSelect("subject.number, subject.name");
    	msg.setFrom("cems.subject, cems.user_subject");
    	msg.setWhereCol("subject.number", "user_subject.subjectNum"); 
    	msg.setWhere("user_subject.userId", ChatClient.user.getId()); 
    	sendMsg(msg);
    	subjectsLSt = msgReceived.convertData(Subject.class);
	    System.out.println("subjectsFake = " + subjectsLSt);
	  
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
                	subjectChoose = true;
                    // Code to be executed when the selected item changes and newValue is not null

                    // Find the Subject object based on the new value
                    selectedSubject = findSubjectByName(newValue);
                    sendMsg(selectedSubject.getMsgForCourses());
                    selectedSubject.setCourses(msgReceived.convertData(Course.class)); 
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
    	if(!subjectChoose) { notification.showErrorAlert("you must choose a subject."); return; }
    	Object newQuestion  = questionController.checkInputs(selectedSubject.getNumber()+questionNumberTextField.getText(),
    			questionNumberTextField.getText(), questionTextField.getText(), selectedSubject.getNumber(),
    			ChatClient.user.getId(), answer1TextField.getText(), answer2TextField.getText(), answer3TextField.getText(),
    			answer4TextField.getText(),	Integer.valueOf(((RadioButton)ChooseCorrectAnswerGroup.getSelectedToggle()).getText()), instructionTextField.getText());
    	if(newQuestion instanceof String) {	notification.showErrorAlert((String)newQuestion); return; }
    	Msg msg = questionController.insertQuestion((Question)newQuestion);
    	if(msg==null) {System.out.println("cant create this question.."); return;}
    	sendMsg(msg);
    	notification.showInformationAlert("Data inserted to the DB.");
    	resetFields();
    }
    
    private void resetFields() {
    	answer1TextField.setText("");
    	answer2TextField.setText("");
    	answer3TextField.setText("");
    	answer4TextField.setText("");
    	instructionTextField.setText("");
    	questionTextField.setText("");
    	questionNumberTextField.setText("");
    	radioButton1.setSelected(true);
    }

    private ObservableList<String> getSubjectNames() {
        ObservableList<String> subjectNames = FXCollections.observableArrayList();
        for (Subject subject : subjectsLSt) {
            subjectNames.add(subject.getName());
        }
        return subjectNames;
    }

    private Subject findSubjectByName(String subjectName) {
        for (Subject subject : subjectsLSt) {
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
