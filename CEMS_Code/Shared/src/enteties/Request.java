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
