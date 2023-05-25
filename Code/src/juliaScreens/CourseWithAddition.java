package juliaScreens;

import javafx.scene.control.CheckBox;

public class CourseWithAddition extends Course{

	private CheckBox select;
	
	public CheckBox getSelect() {
		return select;
	}

	public void setSelect(CheckBox select) {
		this.select = select;
	}

	public CourseWithAddition(String courseNum, String courseName) {
		super(courseNum, courseName);
		this.select = new CheckBox();
	
	}

}
