package gui;

import java.util.ArrayList;

import JDBC.Msg;
import client.ChatClient;
import controllers.UserController;
import enteties.Course;
import enteties.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import notifications.NotificationAlertsController;
import java.util.Set;
import java.util.HashSet;

/**
 * Controller class for the Choose Report Type screen.
 *  
 * @author Mor Shmuel 
 */
public class ChooseReportTypeController extends AbstractController{

    @FXML
    private Button back;
    /**
	 * ToggleGroup for selecting the report type.
	 */
    @FXML
    private ToggleGroup chooseTypeOfReport;

    /**
	 * comboBox to choose a report by name of chosen type.
	 */
    @FXML
    private ComboBox<String> comboBoxNames;
    
    /**
	 * the options for the correct answer.
	 */
    @FXML
    private RadioButton courseRadiobtn, lecturerRadiobtn, studentradiobtn;

    @FXML
    private Button show;
    
    private ObservableList<User> studentTable, lecturerTable;
    private ObservableList<Course> courseTable;
    
    /**
	 * the list of users for the comboBox according to the subjects which math the hod user.
	 */
    private ArrayList<User> userLecturerLst, userStudentLst;
    
    /**
	 * the list of users for the comboBox according to the subjects which math the hod user.
	 */
    private ArrayList<Course> courseLst;
    
    /**
	 * the user selected from the comboBox.
	 */
    public User selectedLecturer, selectedStudent;
    
    /**
	 * the course selected from the comboBox.
	 */
    public Course selectedCourse;
    
    //options - lecturer, student, course
    public String reportType;
    
    /**
	 * object to use the UserController class method.
	 */
    private static UserController userController = new UserController();
    /**
	 * object to use the notifications class.
	 */
    private static NotificationAlertsController notification = new NotificationAlertsController();
    /**
	 * boolean variable to indicate that any subject has already been chose. for not letting the user create a question without choosing subject.
	 */
    private boolean lecturerChoose = false, studentChoose = false; //indicates that a user was chosen.
    /**
	 * boolean variable to indicate that any subject has already been chose. for not letting the user create a question without choosing subject.
	 */
    private boolean courseChoose = false; //indicates that a course was chosen.
    
    LecturerStaticsReportController lecturerStaticsReportController = new LecturerStaticsReportController();

    /**
     * Default constructor for the ChooseReportTypeController class.
     * Initializes the data for comboBox that fits to each radio button(lecturer,student and course).
     */
    public ChooseReportTypeController() {
    	//msgLecturer ---> users of lecturers belongs to hod subject
    	Msg msgLecturer = userController.selectUserByHodAndLecturer(ChatClient.user.getId());
    	sendMsg(msgLecturer);
    	System.out.println("Lecturer Data = " + AbstractController.msgReceived.getData());
    	userLecturerLst = msgReceived.convertData(User.class);
    	lecturerTable = FXCollections.observableArrayList(userLecturerLst);
    	System.out.println("lecturerTable = " + lecturerTable);
    	
    	//msgStudent ---> users of students belongs to hod subject
    	Msg msgStudent = userController.selectUserByHodAndStudent(ChatClient.user.getId());
    	sendMsg(msgStudent);
    	System.out.println("Student Data = " + AbstractController.msgReceived.getData());
    	userStudentLst = msgReceived.convertData(User.class);
    	studentTable = FXCollections.observableArrayList(userStudentLst);
    	System.out.println("studentTable = " + studentTable);
    	
    	//msgCourse ---> courses belongs to hod subject
    	Msg msgCourse = userController.selectUserByHodAndCourse(ChatClient.user.getId());
    	sendMsg(msgCourse);
    	//System.out.println("Corse Data = " + AbstractController.msgReceived.getData());
    	courseLst = msgReceived.convertData(Course.class);
    	courseTable = FXCollections.observableArrayList(courseLst);
    	//System.out.println("courseTable = " + courseTable);
    	
    }
    
    @FXML
    void onCourseSelected(ActionEvent event) {
    	ObservableList<String> courseNames = FXCollections.observableArrayList();
        for (Course course : courseLst) {
            courseNames.add(course.getName());
        }
        comboBoxNames.setItems(courseNames);
        
        comboBoxNames.setOnAction(e -> {
            selectedCourse = getSelectedCourse();
            if (selectedCourse != null) {
            	courseChoose = true;
                System.out.println("Selected Course Subject: " + selectedCourse.getSubjectNum());
            }
        });
    }

    @FXML
    void onLecturerSelected(ActionEvent event) {
    	Set<String> lecturerNames = new HashSet<>();
        for (User lecturer : userLecturerLst) {
            lecturerNames.add(lecturer.getName());
        }
        ObservableList<String> uniqueLecturerNames = FXCollections.observableArrayList(lecturerNames);
        comboBoxNames.setItems(uniqueLecturerNames);
        
        comboBoxNames.setOnAction(e -> {
            selectedLecturer = getSelectedLecturer();
            if (selectedLecturer != null) {
            	lecturerChoose = true;
                System.out.println("Selected Lecturer ID: " + selectedLecturer.getId());
            }
        });
    }

    @FXML
    void onStudentSelected(ActionEvent event) {
    	Set<String> studentNames = new HashSet<>();
        for (User student : userStudentLst) {
            studentNames.add(student.getName());
        }
        ObservableList<String> uniqueStudentNames = FXCollections.observableArrayList(studentNames);
        comboBoxNames.setItems(uniqueStudentNames);
        
        comboBoxNames.setOnAction(e -> {
            selectedStudent = getSelectedStudent();
            if (selectedStudent != null) {
            	studentChoose = true;
                System.out.println("Selected Lecturer ID: " + selectedStudent.getId());
            }
        });
    }
    
    private Course getSelectedCourse() {
        String selectedCourseName = comboBoxNames.getSelectionModel().getSelectedItem();
        for (Course course : courseLst) {
            if (course.getName().equals(selectedCourseName)) {
                return course;
            }
        }
        return null;
    }
    
    private User getSelectedStudent() {
        String selectedUserName = comboBoxNames.getSelectionModel().getSelectedItem();
        for (User user : userStudentLst) {
            if (user.getName().equals(selectedUserName)) {
                return user;
            }
        }
        return null;
    }
    
    private User getSelectedLecturer() {
        String selectedUserName = comboBoxNames.getSelectionModel().getSelectedItem();
        for (User user : userLecturerLst) {
            if (user.getName().equals(selectedUserName)) {
                return user;
            }
        }
        return null;
    }
    

    @FXML
    void showReport(ActionEvent event) throws Exception {
        if (lecturerChoose == true) {
        	reportType = "lecturer";
        	LecturerStaticsReportController.selectedLecturer = selectedLecturer;
        }
        else if(studentChoose == true) {
        	reportType = "student";
        	LecturerStaticsReportController.selectedStudent = selectedStudent;
        }
        else if(courseChoose == true) {
        	reportType = "course";
        	LecturerStaticsReportController.selectedCourse = selectedCourse;
        }
    	start("lecturerStaticsReport", "chooseReportType");
    }
}