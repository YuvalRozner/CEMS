package enteties;

import java.sql.Time;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

public class StudentTest {

	private Test test;
	private String studentId;//change lior change also get and set
	private int submittingTime;
	private Time time;
	private TestToExexeute testToExecute;
	private int[] answers;
	private String grade;//change lior change also get and set
	
	///added by lior
	private Button show;
	private CheckBox select;
	private TextField note;
	
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
	
	////////show
	public Button getShow() {
		return show;
	}

	public void setNewShow() {
		this.show = new Button();
		show.setText("Show");
	}
	
	/////lior finish/////////////////////////////////////////////////////

	public Test getTest() {
		return this.test;
	}

	public void setTest(Test test) {
		this.test = test;
	}

	public String getStudentId() {
		return this.studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
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

	public int[] getAnswers() {
		return this.answers;
	}

	public void setAnswers(int[] answers) {
		this.answers = answers;
	}

	public String getGrade() {///////////
		return this.grade;
	}

	public void setGrade(String grade) {//////////////
		this.grade = grade;
	}

}