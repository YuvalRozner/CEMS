package enteties;

import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;

public class Request {
	//in DB:
	private Integer testCode;
	private String lecturerId;
	private String hodId;
	private Integer duration;
	private String explanation;
	//not in DB:
	private User user;
	private TestToExecute testToExecute;
	private Test test;
	
	//for FX:
	CheckBox checkbox;
	RadioButton radioButton;
	
	/**
	 * empty constructor.
	 */
	public Request() {super();}

	/**
	 * @param testCode
	 * @param lecurerId
	 * @param hodId
	 * @param duration
	 * @param explanetion
	 */
	public Request(Integer testCode, String lecurerId, String hodId, Integer duration, String explanation) {
		this.testCode = testCode;
		this.lecturerId = lecurerId;
		this.hodId = hodId;
		this.duration = duration;
		this.explanation = explanation;
	}
	
	/**
	 * @param testCode
	 * @param lecurerId
	 * @param hodId
	 * @param duration
	 * @param explanetion
	 * @param testCodeToExecute
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
	 * @param testDuration
	 * @param instructionsForStudent
	 * @param instructionsForLecturer
	 * @param userId
	 * @param name
	 * @param username
	 * @param password
	 * @param premission
	 * @param loggedin
	 */
	public Request(Integer testCode, String lecurerId, String hodId, Integer duration, String explanation, Integer testCodeToExecute, String testId, String testingType, String date, Double average, Double median, String lock, Integer timeExtension,
			String lecturerId, Integer numberOfStudentsStarted, Integer numberOfStudentsFinished,Integer numberOfStudents, Integer dis1,Integer dis2,Integer dis3,Integer dis4,Integer dis5,
			Integer dis6,Integer dis7,Integer dis8,Integer dis9,Integer dis10, 
			String id, String number, String courseNumber, Integer testDuration, String instructionsForStudent, String instructionsForLecturer, String userId, String name, String username, String password, String premission, String loggedin) {
		this.testCode = testCode;
		this.lecturerId = lecurerId;
		this.hodId = hodId;
		this.duration = duration;
		this.explanation = explanation;
		this.testToExecute = new TestToExecute(testCodeToExecute, testId, testingType, date, average, median, lock, timeExtension, lecturerId, numberOfStudentsStarted, numberOfStudentsFinished, numberOfStudents, dis1, dis2, dis3, dis4, dis5, dis6, dis7, dis8, dis9, dis10);
		this.test = new Test(id, number, courseNumber, testDuration, instructionsForStudent, instructionsForLecturer);
		this.user = new User(userId, name, username, password, premission, loggedin);
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
	 * @return the lecurerId
	 */
	public String getLecturerId() {
		return lecturerId;
	}

	/**
	 * @param lecurerId the lecurerId to set
	 */
	public void setLecturerId(String lecurerId) {
		this.lecturerId = lecurerId;
	}

	/**
	 * @return the hodId
	 */
	public String getHodId() {
		return hodId;
	}

	/**
	 * @param hodId the hodId to set
	 */
	public void setHodId(String hodId) {
		this.hodId = hodId;
	}

	/**
	 * @return the duration
	 */
	public Integer getDuration() {
		return duration;
	}

	/**
	 * @param duration the duration to set
	 */
	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	/**
	 * @return the explanation
	 */
	public String getExplanation() {
		return explanation;
	}

	/**
	 * @param explanetion the explanation to set
	 */
	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}
	
	@Override
	public boolean equals(Object obj) {
		Request r = (Request) obj;
		return ( testCode.equals(r.getTestCode()) && lecturerId.equals(r.getLecturerId()));
	}

	@Override
	public String toString() {
		return "Request [testCode=" + testCode + ", lecurerId=" + lecturerId + ", hodId=" + hodId + ", duration=" + duration + ", explanation=" + explanation + "]";
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
	
}
