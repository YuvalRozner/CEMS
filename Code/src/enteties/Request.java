package enteties;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.CheckBox;

public class Request {
	private String courseName;
	private String explanation;
	private String idTest;
	private String lecturer;
	private CheckBox select;
	private String previousTime;
	private String newTime;
	
	public Request(String courseName,String explanation,String idTest,String lecturer,String previousTime,String newTime) {
		this.courseName=courseName;
		this.explanation=explanation;
		this.idTest=idTest;
		this.lecturer=lecturer;
		this.previousTime=previousTime;
		this.newTime=newTime;
		
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getExplanation() {
		return explanation;
	}
	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}
	public String getIdTest() {
		return idTest;
	}
	public void setIdTest(String idTest) {
		this.idTest = idTest;
	}
	public String getLecturer() {
		return lecturer;
	}
	public void setLecturer(String lecturer) {
		this.lecturer = lecturer;
	}
	public CheckBox getSelect() {
		return select;
	}
	public void setSelect() {
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
	public String getPreviousTime() {
		return previousTime;
	}
	public void setPreviousTime(String previousTime) {
		this.previousTime = previousTime;
	}
	public String getNewTime() {
		return newTime;
	}
	public void setNewTime(String newTime) {
		this.newTime = newTime;
	}
}
