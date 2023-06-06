package enteties;

import java.sql.Time;
import java.util.ArrayList;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

public class StudentTest {

	private Test test;
	private Course course;
	private String studentId;//change type lior change also get and set
	private int submittingTime;
	private Time time;
	private TestToExexeute testToExecute;
	private ArrayList<String> answers;
	private String grade;//change type lior change also get and set
	
	///added by lior
	private Button show;
	private CheckBox select;
	private TextField note;
	private String comment;
	
	//emptyConstructor
	public StudentTest() {
		
	}
	
	///constructor Grades

	public StudentTest(Course course, String comment, String grade, Test test) {
		this.course=course;
		this.comment=comment;
		this.grade=grade;
		this.test=test;
	}

	////////select
	public CheckBox getSelect() {
		return select;
	}

	public void setSelect(CheckBox select) {
		this.select = select;
	}
	
	public void setNewSelect() { 
		this.select = new CheckBox();
		// Add event handler for pressed and unpressed (checked / unchecked) state of the checkbox.
		this.select.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				//flag = newValue; // why we need it again dor? -rozner.
				//points.setDisable(!newValue);
				System.out.println("Checkbox " + (newValue ? "pressed" : "unpressed")); 	}
		});
	}
	////////note
	public TextField getNote() {
		return note;
	}

	public void setNote(TextField note) {
		this.note = note;	
	}
	
	public void setNewNote() {
		this.note = new TextField();
		//note.setDisable(true);
	}

	//////////comment
	public String getComment() {////////////////////////
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
		
	}
	
	////////show
	public Button getShow() {
		return show;
	}
	
	/////////Course
	public Course getCourse() {
		return this.course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}
	
	/////lior finish/////////////////////////////////////////////////////

	public Test getTest() {
		return this.test;
	}

	public void setTest(Test test) {
		this.test = test;
	}

	public int getSubmittingTime() {
		return this.submittingTime;
	}

	public void setSubmittingTime(int submittingTime) {
		this.submittingTime = submittingTime;
	}

	public Time getTime() {
		return this.time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	public TestToExexeute getTestToExecute() {
		return this.testToExecute;
	}

	public void setTestToExecute(TestToExexeute testToExecute) {
		this.testToExecute = testToExecute;
	}

	public ArrayList<String> getAnswers() {
		return this.answers;
	}

	public void setAnswers(ArrayList<String> answers) {
		this.answers = answers;
	}

	public String getGrade() {///////////
		return this.grade;
	}

	public void setGrade(String grade) {//////////////
		this.grade = grade;
	}

	public String getStudentId() {////////////////////////
		return this.studentId;
	}

	public void setStudentId(String studentId2) {
		this.studentId = studentId2;
		
	}
	//////////show
	public void setNewShow() { ///added by Mor //add style to show button
        this.show = new Button();
        show.setText("Show");
        show.setStyle("-fx-background-color: #CCFFFF; -fx-background-radius: 30 0 0 30;");
        // Add hover effect
        show.setOnMouseEntered(e -> show.setStyle("-fx-background-color: #009494; -fx-background-radius: 30 0 0 30;"));
        show.setOnMouseExited(e -> show.setStyle("-fx-background-color: #CCFFFF; -fx-background-radius: 30 0 0 30;"));
        // Add pressed effect
        show.setOnMousePressed(e -> show.setStyle("-fx-background-color: #82bfb6; -fx-background-radius: 30 0 0 30;"));
        show.setOnMouseReleased(e -> show.setStyle("-fx-background-color: #CCFFFF; -fx-background-radius: 30 0 0 30;"));
    }
	/////////////show
}