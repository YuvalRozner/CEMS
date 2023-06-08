package enteties;

import javafx.scene.control.CheckBox;

public class Course {
	private String courseNum;
	private String courseName;
	private CheckBox select;

	public Course(String courseNum, String courseName) {
		this.courseNum = courseNum;
		this.courseName = courseName;
	}

	public String getCourseNum() {
		return this.courseNum;
	}

	public void setCourseNum(String courseNum) {
		this.courseNum = courseNum;
	}

	public String getCourseName() {
		return this.courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
	public CheckBox getSelect() {
		return select;
	}

	public void setSelect(CheckBox select) {
		this.select = select;
	}
	
	// Initialize the checkBok special field.
	public void setNewSelect() {
		this.select = new CheckBox();
		this.select.setStyle("-fx-background-color: #F0F8FF; -fx-border-width: 1px; -fx-border-color: #92bce3;  -fx-border-radius: 3px; -fx-font-weight: bold; -fx-font-family: \"Comic Sans MS\";");
	}
}
