package enteties;

import java.util.ArrayList;
import java.util.Arrays;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

public class TestToExecute{
	//in DB:
	private String testCode;
	private String testId;
	private String testingType;
	private String date;
	private Double average;
	private Double median;
	private Boolean lock;
	private Integer timeExtension;
    private String lecturerId;
    private Integer numberOfStudentsStarted;
    private Integer numberOfStudentsFinished;
	private Integer [] distribusion = new Integer[10]; // 10 separate columns.
	//not in DB: 
	private Test test;
	private ArrayList<StudentTest> studentsTestsLst;
	//for FX:
	private CheckBox select;
	private TextField codeField=new TextField();
	private TextField type=new TextField();
	private Button show;
	private TextField durationField=new TextField();
	
	
	/**
	 * empty constructor.
	 */
	public TestToExecute() {super();}
	
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
	public TestToExecute(String testCode, String testId, String testingType, String date, Double average, Double median, Boolean lock, Integer timeExtension,
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
	 * @return the testCode
	 */
	public String getTestCode() {
		return testCode;
	}

	/**
	 * @param testCode the testCode to set
	 */
	public void setTestCode(String testCode) {
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
	public Boolean getLock() {
		return lock;
	}

	/**
	 * @param lock the lock to set
	 */
	public void setLock(Boolean lock) {
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
	
	@Override
	public boolean equals(Object obj) {
		TestToExecute t = (TestToExecute) obj;
		return testCode.equals(t.getTestCode());
	}

	@Override
	public String toString() {
		return "TestToExecute [testCode=" + testCode + ", testId=" + testId + ", testingType=" + testingType + ", date=" + date + ", average=" + average + ", median="
				+ median + ", lock=" + lock + ", timeExtension=" + timeExtension + ", lecturerId=" + lecturerId + ", numberOfStudentsStarted=" + numberOfStudentsStarted
				+ ", numberOfStudentsFinished=" + numberOfStudentsFinished + ", distribusion=" + Arrays.toString(distribusion) + "]";
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
	

}