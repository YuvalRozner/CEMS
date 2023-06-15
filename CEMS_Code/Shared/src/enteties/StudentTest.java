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
	private String approved = "false";
	private String changeReason = null;
	//not in DB:
	private String studentName;
	private TestToExecute testToExecute;
	private Test test;
	
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
	 * to get all grade to calculate average and median.
	 * @param grade
	 */
	public StudentTest (Integer grade) {
		this.grade=grade;
	}
	
	/**
	 * to check if student aleady do this test.
	 * @param studentId
	 */
	
	public StudentTest(String studentId) {
		this.studentId=studentId;
	}
	
	/**
	 * return string that build from two notes , one that the lecturer gave on the test and ond on the grade.
	 * @return
	 */
	
	public String getAllNotes() {
		if (changeReason.equals("")) {
			return lecturerNotes;
		}
		if(lecturerNotes.equals("")) {
			return changeReason;
		}
		char lastChar = lecturerNotes.charAt(lecturerNotes.length() - 1);
		if(lastChar=='.') {
			lecturerNotes=lecturerNotes.substring(0, lecturerNotes.length() - 1);
		}
		lastChar = changeReason.charAt(changeReason.length() - 1);
		if(lastChar=='.') {
			changeReason=changeReason.substring(0, changeReason.length() - 1);
		}
		return lecturerNotes+","+changeReason+".";
	}
	
	/**
	 * for getting the object properly from the DB including the test to execute Object ,
	 * test to execute include -Test Object 
	 * test object include - the Course object.
	 * 
	 * @param studentId
	 * @param testCode
	 * @param timePassed
	 * @param answers
	 * @param grade
	 * @param lecturerNotes
	 * @param approved
	 * @param changeReason
	 * @param testCode1
	 * @param testId
	 * @param testingType
	 * @param date
	 * @param average
	 * @param median
	 * @param lock
	 * @param timeExtension
	 * @param lecturerId
	 * @param numberOfStudentsStarted
	 * @param numberOfStudentsFinished
	 * @param numberOfStudents
	 * @param dis1
	 * @param dis2
	 * @param dis3
	 * @param dis4
	 * @param dis5
	 * @param dis6
	 * @param dis7
	 * @param dis8
	 * @param dis9
	 * @param dis10
	 * @param id
	 * @param number
	 * @param courseNumber
	 * @param duration
	 * @param instructionsForStudent
	 * @param instructionsForLecturer
	 * @param course_number
	 * @param name
	 * @param subjectNum
	 */
	public StudentTest(String studentId, Integer testCode, Integer timePassed, String answers, Integer grade,
			String lecturerNotes, String approved, String changeReason,Integer testCode1,///only 4
			String testId, String testingType, String date, Double average, Double median,
			String lock, Integer timeExtension,	String lecturerId, Integer numberOfStudentsStarted, Integer numberOfStudentsFinished,
			Integer numberOfStudents, Integer dis1,Integer dis2,Integer dis3,Integer dis4,
			Integer dis5,Integer dis6,Integer dis7,Integer dis8,Integer dis9,
			Integer dis10, String id, String number, String courseNumber, Integer duration, 
			String instructionsForStudent, String instructionsForLecturer,String course_number, String name, String subjectNum) {
		this.studentId = studentId;
		this.testCode = testCode;
		this.timePassed = timePassed;
		this.answers = answers;
		this.grade = grade;
		this.lecturerNotes = lecturerNotes;
		this.approved = approved;
		this.changeReason = changeReason;
		this.testToExecute=new TestToExecute(testCode1,testId,testingType,date,average,
				median,lock, timeExtension,lecturerId,  numberOfStudentsStarted,
				numberOfStudentsFinished, numberOfStudents,  dis1, dis2, dis3,
				dis4, dis5,	dis6, dis7, dis8,
				dis9, dis10, id,  number,  courseNumber,
				duration,  instructionsForStudent,  instructionsForLecturer,course_number,  name,
				subjectNum);
	}
	
	/**
	 * empty constructor.
	 */
	public StudentTest() {super();}
	
	/**
	 * used to get object from DB including student name.
	 * 
	 * @param studentId
	 * @param testCode
	 * @param timePassed
	 * @param answers
	 * @param grade
	 * @param lecturerNotes
	 * @param approved
	 * @param changeReason
	 * @param studentName
	 */
	public StudentTest(String studentId, Integer testCode, Integer timePassed, String answers, Integer grade, String lecturerNotes, String approved, String changeReason, String studentName) {
		this.studentId = studentId;
		this.testCode = testCode;
		this.timePassed = timePassed;
		this.answers = answers;
		this.grade = grade;
		this.lecturerNotes = lecturerNotes;
		this.approved = approved;
		this.changeReason = changeReason;
		this.studentName = studentName;
	}
	
	/**
	 * @param studentId
	 * @param testCode
	 * @param timePassed
	 * @param answers
	 * @param grade
	 * @param lecturerNotes
	 * @param approved
	 * @param changeReason
	 */
	public StudentTest(String studentId, Integer testCode, Integer timePassed, String answers, Integer grade, String lecturerNotes, String approved, String changeReason) {
		this.studentId = studentId;
		this.testCode = testCode;
		this.timePassed = timePassed;
		this.answers = answers;
		this.grade = grade;
		this.lecturerNotes = lecturerNotes;
		this.approved = approved;
		this.changeReason = changeReason;
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
	public String getApproved() {
		return approved;
	}

	/**
	 * @param approved the approved to set
	 */
	public void setApproved(String approved) {
		this.approved = approved;
	}

	/**
	 * @return the changeReason
	 */
	public String getChangeReason() {
		return changeReason;
	}

	/**
	 * @param changeReason the changeReason to set
	 */
	public void setChangeReason(String changeReason) {
		this.changeReason = changeReason;
	}

	/**
	 * @return the studentName
	 */
	public String getStudentName() {
		return studentName;
	}

	/**
	 * @param studentName the studentName to set
	 */
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	/**
	 * @return the test
	 */
	public TestToExecute getTestToExecute() {
		return testToExecute;
	}

	/**
	 * @param test the test to set
	 */
	public void setTestToExecute(TestToExecute testToExecute) {
		this.testToExecute = testToExecute;
	}
	
	/**
	 * @return the test
	 */
	public Test getTest() {
		return test;
	}

	/**
	 * @param test the test to set
	 */
	public void setTest(Test test) {
		this.test = test;
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
	
	public Button getShow() {
		return show;
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