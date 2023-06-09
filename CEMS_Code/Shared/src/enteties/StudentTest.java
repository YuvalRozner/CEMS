package enteties;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class StudentTest {
	//in DB:
	private String studentId;
	private Integer testCode; //connection between StudentTest and TestToExecute.
	private Integer timePassed; //in minutes.
	private String answers; //for exaple- "2113" meanes ans1=2, ans2=1, ans3=1, ans4=3.
	private Integer grade = null;
	private String lecturerNotes = null;
	private Boolean approved = false;
	//not in DB:
	private TestToExecute testToExecute;
	private Test test;
	
	private String studentName;


	//for FX:
	CheckBox checkBox;
	RadioButton radioButton;
	Button button;
	TextField textField;
	TextField textField1;
	
	private Button show;
	private CheckBox select;
	private TextField note;

	
	/**
	 * empty constructor.
	 */
	public StudentTest() {super();}
	
	/**
	 * @param studentId
	 * @param testCode
	 * @param timePassed
	 * @param answers
	 * @param grade
	 * @param lecturerNotes
	 * @param approved
	 */
	public StudentTest(String studentId, Integer testCode, Integer timePassed, String answers, Integer grade, String lecturerNotes, Boolean approved) {
		this.studentId = studentId;
		this.testCode = testCode;
		this.timePassed = timePassed;
		this.answers = answers;
		this.grade = grade;
		this.lecturerNotes = lecturerNotes;
		this.approved = approved;
	}

	public StudentTest(String studentId, Integer testCode, Integer timePassed, String answers, Integer grade, String lecturerNotes, Boolean approved,String studentName ) {
		this.studentId = studentId;
		this.testCode = testCode;
		this.timePassed = timePassed;
		this.answers = answers;
		this.grade = grade;
		this.lecturerNotes = lecturerNotes;
		this.approved = approved;
		this.studentName = studentName;
	}
	
	/**
	 * @return the studentId
	 */
	public String getStudentId() {
		return studentId;
	}

	/**
	 * @param studentId the studentId to set
	 */
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	/**
	 * @return the testCode
	 */
	public Integer getTestCode() {
		return testCode;
	}

	/**
	 * @param testCode the testCode to set
	 */
	public void setTestCode(Integer testCode) {
		this.testCode = testCode;
	}

	/**
	 * @return the timePassed
	 */
	public Integer getTimePassed() {
		return timePassed;
	}

	/**
	 * @param timePassed the timePassed to set
	 */
	public void setTimePassed(Integer timePassed) {
		this.timePassed = timePassed;
	}

	/**
	 * @return the answers
	 */
	public String getAnswers() {
		return answers;
	}

	/**
	 * @param answers the answers to set
	 */
	public void setAnswers(String answers) {
		this.answers = answers;
	}

	/**
	 * @return the grade
	 */
	public Integer getGrade() {
		return grade;
	}

	/**
	 * @param grade the grade to set
	 */
	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	/**
	 * @return the lecturerNotes
	 */
	public String getLecturerNotes() {
		return lecturerNotes;
	}

	/**
	 * @param lecturerNotes the lecturerNotes to set
	 */
	public void setLecturerNotes(String lecturerNotes) {
		this.lecturerNotes = lecturerNotes;
	}

	/**
	 * @return the approved
	 */
	public Boolean getApproved() {
		return approved;
	}

	/**
	 * @param approved the approved to set
	 */
	public void setApproved(Boolean approved) {
		this.approved = approved;
	}

	
	
	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	
	@Override
	public boolean equals(Object obj) {
		StudentTest st = (StudentTest) obj;
		return ( studentId.equals(st.getStudentId()) && testCode.equals(st.getTestCode()));
	}

	@Override
	public String toString() {
		return "StudentTest [studentId=" + studentId + ", testCode=" + testCode + ", timePassed=" + timePassed + ", answers=" + answers + ", grade=" + grade
				+ ", lecturerNotes=" + lecturerNotes + ", approved=" + approved + "]";
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//for FX:
	public void setNewCheckBox() {
		checkBox = new CheckBox();
	}
	public CheckBox getCheckBox() {
		return checkBox;
	}
	public void setNewRadioButton() {
		radioButton = new RadioButton();
	}
	public RadioButton getRadioButton() {
		return radioButton;
	}
	public void setSelect(CheckBox select) {
		this.select = select;
	}
	public void setNewButton() {
		button = new Button();
	}
	public Button getButton() {
		return button;
	}
	public void setButtonText(String s) {
		button.setText(s);
	}
	
	
	public void setNewTextField() {
		textField = new TextField();
	}
	public TextField getTextField() {
		return textField;
	}
	public void setNewTextField1() {
		textField1 = new TextField();
	}
	public TextField getTextField1() {
		return textField1;
	}
	
	
	
	public void setNewSelect() { 
		this.select = new CheckBox();
		this.select.setStyle("-fx-background-color: #F0F8FF; -fx-border-width: 1px; -fx-border-color: #92bce3;  -fx-border-radius: 3px; -fx-font-weight: bold; -fx-font-family: \"Comic Sans MS\";");
		// Add event handler for pressed and unpressed (checked / unchecked) state of the checkbox.
		this.select.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				//flag = newValue; // why we need it again dor? -rozner.
				//points.setDisable(!newValue);
				System.out.println("Checkbox " + (newValue ? "pressed" : "unpressed")); 	}
		});
	}
	
	public void setNewNote() {
		this.note = new TextField();
		this.note.setStyle("-fx-background-color: #F0F8FF; -fx-border-width: 1px; -fx-border-color: #92bce3;  -fx-border-radius: 7px; -fx-font-weight: bold; -fx-font-family: \"Comic Sans MS\";");
		//note.setDisable(true);
	}

	//////////show
	public void setNewShow() { ///added by Mor //add style to show button
        this.show = new Button();
        show.setText("Show");
        show.setStyle("-fx-background-color: #CCFFFF; -fx-background-radius: 30 0 0 30; -fx-font-weight: bold; -fx-font-family: \\\"Comic Sans MS\\\";");
        // Add hover effect
        show.setOnMouseEntered(e -> show.setStyle("-fx-background-color: #009494; -fx-background-radius: 30 0 0 30; -fx-font-weight: bold; -fx-font-family: \\\"Comic Sans MS\\\";"));
        show.setOnMouseExited(e -> show.setStyle("-fx-background-color: #CCFFFF; -fx-background-radius: 30 0 0 30; -fx-font-weight: bold; -fx-font-family: \\\"Comic Sans MS\\\";"));
        // Add pressed effect
        show.setOnMousePressed(e -> show.setStyle("-fx-background-color: #82bfb6; -fx-background-radius: 30 0 0 30; -fx-font-weight: bold; -fx-font-family: \\\"Comic Sans MS\\\";"));
        show.setOnMouseReleased(e -> show.setStyle("-fx-background-color: #CCFFFF; -fx-background-radius: 30 0 0 30; -fx-font-weight: bold; -fx-font-family: \\\"Comic Sans MS\\\";"));
    }
	/////////////show
}