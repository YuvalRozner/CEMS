package enteties;

import java.util.ArrayList;

import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;

public class Subject {
    //in DB:
	private String number;
	private String name;
	//not in DB:
	private ArrayList<Course> courses;
	//for FX:
	CheckBox checkbox;
	RadioButton radioButton;
	
	
	
	/**
	 * empty constructor.
	 */
	public Subject() {super();}
	
	/**
	 * @param number
	 * @param name
	 * @param courses
	 */
	public Subject(String number, String name, ArrayList<Course> courses) {
		this.number = number;
		this.name = name;
		this.courses = courses;
	}
	
	/**
	 * @return the number
	 */
	public String getNumber() {
		return number;
	}
	
	/**
	 * @param number the number to set
	 */
	public void setNumber(String number) {
		this.number = number;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the courses
	 */
	public ArrayList<Course> getCourses() {
		return courses;
	}
	
	/**
	 * @param courses the courses to set
	 */
	public void setCourses(ArrayList<Course> courses) {
		this.courses = courses;
	}
	
	@Override
	public boolean equals(Object obj) {
		Subject s = (Subject) obj;
		return number.equals(s.getNumber());
	}

	@Override
	public String toString() {
		return "Subject [number=" + number + ", name=" + name + ", courses=" + courses + "]";
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
