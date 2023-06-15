package enteties;

import java.util.ArrayList;
import java.util.Arrays;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class TestToExecute{
	//in DB:
	private Integer testCode;
	private String testId;
	private String testingType;
	private String date;
	private Double average;
	private Double median;
	private String lock;
	private Integer timeExtension;
    private String lecturerId;
    private Integer numberOfStudentsStarted;
    private Integer numberOfStudentsFinished;
    private Integer numberOfStudents;
	private Integer [] distribusion = new Integer[10]; // 10 separate columns.
	 
	 
	//not in DB: 
	private Test test;
	private Course course = null;
	@SuppressWarnings("unused") // sometimes in use.
	private ArrayList<StudentTest> studentsTestsLst;
	//for FX:
	CheckBox checkbox;
	RadioButton radioButton;
	Button button;
	@SuppressWarnings("rawtypes") //works without specific type.
	ComboBox comboBox;
	TextField textField;
	TextField textField1;
	
	
	private CheckBox select;
	private TextField codeField=new TextField();
	private TextField type=new TextField();
	private Button show;
	private TextField durationField=new TextField();
	
	
	/**
	 * to get the number of student in general.
	 * @param numberOfStudentsStarted
	 * @param numberOfStudentsFinished
	 * @param numberOfStudents
	 */
	public TestToExecute(Integer numberOfStudentsStarted,Integer numberOfStudentsFinished, Integer numberOfStudents) {
		this.numberOfStudentsStarted=numberOfStudentsStarted;
		this.numberOfStudentsFinished=numberOfStudentsFinished;
		this.numberOfStudents=numberOfStudents;
		
	}
	
	/**
	 * for getting the lock.
	 */
	public TestToExecute(String lock) {this.lock=lock;}
	
	/**
	 * empty constructor.
	 */
	public TestToExecute() {super();}
	
	/**
	 * for getting the object properly from the DB including the Test Object and the Course object inside the Test.
	 * 
	 * @param testCode
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
	public TestToExecute(Integer testCode, String testId, String testingType, String date, Double average, Double median, String lock, Integer timeExtension,
			String lecturerId, Integer numberOfStudentsStarted, Integer numberOfStudentsFinished,Integer numberOfStudents, Integer dis1,Integer dis2,Integer dis3,Integer dis4,Integer dis5,
			Integer dis6,Integer dis7,Integer dis8,Integer dis9,Integer dis10, 
			String id, String number, String courseNumber, Integer duration, String instructionsForStudent, String instructionsForLecturer,
			String course_number, String name, String subjectNum) {
		this.testCode = testCode;
		this.testId = testId;
		this.testingType = testingType;
		this.date = date;
		this.average = average;
		this.median = median;
		this.lock = lock;
		this.timeExtension = timeExtension;
		this.lecturerId = lecturerId;
		this.numberOfStudentsStarted = numberOfStudentsStarted;
		this.numberOfStudentsFinished = numberOfStudentsFinished;
		this.distribusion = new Integer[] {dis1,dis2,dis3,dis4,dis5,dis6,dis7,dis8,dis9,dis10};
		this.test = new Test(id, number, courseNumber, duration, instructionsForStudent, instructionsForLecturer);
		this.test.setCourse(new Course(course_number, name, subjectNum));
	}
	
	/**
	 * for getting the object properly from the DB.
	 * 
	 * @param testCode
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
	 */
	public TestToExecute(Integer testCode, String testId, String testingType, String date, Double average, Double median, String lock, Integer timeExtension,
			String lecturerId, Integer numberOfStudentsStarted, Integer numberOfStudentsFinished,Integer numberOfStudents, Integer dis1,Integer dis2,Integer dis3,Integer dis4,Integer dis5,
			Integer dis6,Integer dis7,Integer dis8,Integer dis9,Integer dis10) {
		this.testCode = testCode;
		this.testId = testId;
		this.testingType = testingType;
		this.date = date;
		this.average = average;
		this.median = median;
		this.lock = lock;
		this.timeExtension = timeExtension;
		this.lecturerId = lecturerId;
		this.numberOfStudentsStarted = numberOfStudentsStarted;
		this.numberOfStudentsFinished = numberOfStudentsFinished;
		this.distribusion = new Integer[] {dis1,dis2,dis3,dis4,dis5,dis6,dis7,dis8,dis9,dis10};
	}
	
	/**
	 * @param testCode
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
	 * @param distribusion
	 */
	public TestToExecute(Integer testCode, String testId, String testingType, String date, Double average, Double median, String lock, Integer timeExtension,
			String lecturerId, Integer numberOfStudentsStarted, Integer numberOfStudentsFinished, Integer[] distribusion) {
		this.testCode = testCode;
		this.testId = testId;
		this.testingType = testingType;
		this.date = date;
		this.average = average;
		this.median = median;
		this.lock = lock;
		this.timeExtension = timeExtension;
		this.lecturerId = lecturerId;
		this.numberOfStudentsStarted = numberOfStudentsStarted;
		this.numberOfStudentsFinished = numberOfStudentsFinished;
		this.distribusion = distribusion;
	}
	/**
	 * constructor for all the db information.
	 * @param testCode
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
	 * @param distribusion
	 */
	public TestToExecute(Integer testCode, String testId, String testingType, String date, Double average, Double median, String lock, Integer timeExtension,
			String lecturerId, Integer numberOfStudentsStarted, Integer numberOfStudentsFinished,Integer numberOfStudents, Integer[] distribusion) {
		this.testCode = testCode;
		this.testId = testId;
		this.testingType = testingType;
		this.date = date;
		this.average = average;
		this.median = median;
		this.lock = lock;
		this.timeExtension = timeExtension;
		this.lecturerId = lecturerId;
		this.numberOfStudentsStarted = numberOfStudentsStarted;
		this.numberOfStudentsFinished = numberOfStudentsFinished;
		this.numberOfStudents = numberOfStudents;
		this.distribusion = distribusion;
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
	 * @return the testId
	 */
	public String getTestId() {
		return testId;
	}

	/**
	 * @param testId the testId to set
	 */
	public void setTestId(String testId) {
		this.testId = testId;
	}

	/**
	 * @return the testingType
	 */
	public String getTestingType() {
		return testingType;
	}

	/**
	 * @param testingType the testingType to set
	 */
	public void setTestingType(String testingType) {
		this.testingType = testingType;
	}

	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * @return the average
	 */
	public Double getAverage() {
		return average;
	}

	/**
	 * @param average the average to set
	 */
	public void setAverage(Double average) {
		this.average = average;
	}

	/**
	 * @return the median
	 */
	public Double getMedian() {
		return median;
	}

	/**
	 * @param median the median to set
	 */
	public void setMedian(Double median) {
		this.median = median;
	}

	/**
	 * @return the lock
	 */
	public String getLock() {
		return lock;
	}
	
	public String getFinished() {
		if(lock.equals("false")) return "running";
		if(lock.equals("true")) return "finished";
		return null;
	}

	/**
	 * @param lock the lock to set
	 */
	public void setLock(String lock) {
		this.lock = lock;
	}

	/**
	 * @return the timeExtension
	 */
	public Integer getTimeExtension() {
		return timeExtension;
	}

	/**
	 * @param timeExtension the timeExtension to set
	 */
	public void setTimeExtension(Integer timeExtension) {
		this.timeExtension = timeExtension;
	}

	/**
	 * @return the lecturerId
	 */
	public String getLecturerId() {
		return lecturerId;
	}

	/**
	 * @param lecturerId the lecturerId to set
	 */
	public void setLecturerId(String lecturerId) {
		this.lecturerId = lecturerId;
	}

	/**
	 * @return the numberOfStudentsStarted
	 */
	public Integer getNumberOfStudentsStarted() {
		return numberOfStudentsStarted;
	}

	/**
	 * @param numberOfStudentsStarted the numberOfStudentsStarted to set
	 */
	public void setNumberOfStudentsStarted(Integer numberOfStudentsStarted) {
		this.numberOfStudentsStarted = numberOfStudentsStarted;
	}

	/**
	 * @return the numberOfStudentsFinished
	 */
	public Integer getNumberOfStudentsFinished() {
		return numberOfStudentsFinished;
	}

	/**
	 * @param numberOfStudentsFinished the numberOfStudentsFinished to set
	 */
	public void setNumberOfStudentsFinished(Integer numberOfStudentsFinished) {
		this.numberOfStudentsFinished = numberOfStudentsFinished;
	}

	/**
	 * @return the distribusion
	 */
	public Integer[] getDistribusion() {
		return distribusion;
	}

	/**
	 * @param distribusion the distribusion to set
	 */
	public void setDistribusion(Integer[] distribusion) {
		this.distribusion = distribusion;
	}
	
	/**
	 * @return the course
	 */
	public Course getCourse() {
		return course;
	}

	/**
	 * @param course the course to set
	 */
	public void setCourse(Course course) {
		this.course = course;
	}

	public Test getTest() {
		return test;
	}

	public void setTest(Test test) {
		this.test = test;
	}
	

	@Override
	public boolean equals(Object obj) {
		TestToExecute t = (TestToExecute) obj;
		try { return testCode.equals(t.getTestCode());}
		catch(Exception e){return false;}
	}

	@Override
	public String toString() {
		return "TestToExecute [testCode=" + testCode + ", testId=" + testId + ", testingType=" + testingType + ", date=" + date + ", average=" + average + ", median="
				+ median + ", lock=" + lock + ", timeExtension=" + timeExtension + ", lecturerId=" + lecturerId + ", numberOfStudentsStarted=" + numberOfStudentsStarted
				+ ", numberOfStudentsFinished=" + numberOfStudentsFinished + ", distribusion=" + Arrays.toString(distribusion) + "]";
	}
	
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//for FX:
	public void setNewCheckbox() {
		checkbox = new CheckBox();
	}
	public CheckBox getCheckbox() {
		return checkbox;
	}
	public void setNewRadioButton() {
		radioButton = new RadioButton();
	}
	public RadioButton getRadioButton() {
		return radioButton;
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
	@SuppressWarnings("rawtypes")
	public void setNewComboBox() {
		comboBox = new ComboBox();
	}
	@SuppressWarnings("rawtypes")
	public ComboBox getComboBox() {
		return comboBox;
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
	
	/**
	 * @return the course name
	 */
	public String getCourseName() {
		if(test==null) return null;
		if(test.getCourse()==null) return null;
		return test.getCourse().getName();
	}
	
	
	
	
	
	public void setNewCodeField() {
		this.codeField = new TextField();
		this.codeField.setStyle("-fx-background-color: #F0F8FF; -fx-border-width: 1px; -fx-border-color: #92bce3;  -fx-border-radius: 7px; -fx-font-weight: bold; -fx-font-family: \"Comic Sans MS\";");
		codeField.setDisable(true);
		
	}
	
	public void setNewDurationField() {
		this.durationField = new TextField();
		this.durationField.setStyle("-fx-background-color: #F0F8FF; -fx-border-width: 1px; -fx-border-color: #92bce3;  -fx-border-radius: 7px; -fx-font-weight: bold; -fx-font-family: \"Comic Sans MS\";");
		durationField.setDisable(true);
		durationField.setText(test.getDuration().toString());
	}
	
	
	////select
	public void setNewSelect() { 
		this.select = new CheckBox();
		this.select.setStyle("-fx-background-color: #F0F8FF; -fx-border-width: 1px; -fx-border-color: #92bce3;  -fx-border-radius: 3px; -fx-font-weight: bold; -fx-font-family: \"Comic Sans MS\";");
		// Add event handler for pressed and unpressed (checked / unchecked) state of the checkbox.
		this.select.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				//flag = newValue; // why we need it again dor? -rozner.
				codeField.setDisable(!newValue);
				type.setDisable(!newValue);
				durationField.setDisable(!newValue);
				
				System.out.println("Checkbox " + (newValue ? "pressed" : "unpressed")); 	}
		});
	}
	

	public void setNewShow() {
		this.show = new Button();
		show.setText("Show");
        show.setStyle("-fx-background-color: #CCFFFF; -fx-background-radius: 30 0 0 30; -fx-font-weight: bold; -fx-font-family: \"Comic Sans MS\";");
        // Add hover effect
        show.setOnMouseEntered(e -> show.setStyle("-fx-background-color: #009494; -fx-background-radius: 30 0 0 30; -fx-font-weight: bold; -fx-font-family: \"Comic Sans MS\";"));
        show.setOnMouseExited(e -> show.setStyle("-fx-background-color: #CCFFFF; -fx-background-radius: 30 0 0 30; -fx-font-weight: bold; -fx-font-family: \"Comic Sans MS\";"));
        // Add pressed effect
        show.setOnMousePressed(e -> show.setStyle("-fx-background-color: #82bfb6; -fx-background-radius: 30 0 0 30; -fx-font-weight: bold; -fx-font-family: \"Comic Sans MS\";"));
        show.setOnMouseReleased(e -> show.setStyle("-fx-background-color: #CCFFFF; -fx-background-radius: 30 0 0 30; -fx-font-weight: bold; -fx-font-family: \"Comic Sans MS\";"));
	}
	
	public void setNewType() {
		this.type = new TextField();
		this.type.setStyle("-fx-background-color: #F0F8FF; -fx-border-width: 1px; -fx-border-color: #92bce3;  -fx-border-radius: 7px; -fx-font-weight: bold; -fx-font-family: \"Comic Sans MS\";");
		type.setDisable(true);
	}
	/**
	 * get the NumberOfStudents parameter.
	 * @return Integer number of student that not succeed to submit.
	 */
	public Integer getNumberOfStudents() {
		return numberOfStudents;
	}
	/**
	 * set the NumberOfStudents parameter.
	 * @param numberOfStudents
	 */
	public void setNumberOfStudents(Integer numberOfStudents) {
		this.numberOfStudents = numberOfStudents;
	}
	

}