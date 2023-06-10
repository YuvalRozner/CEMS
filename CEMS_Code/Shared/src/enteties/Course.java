package enteties;

import java.util.ArrayList;

import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;

public class Course {
    //in DB:
	private String number;
	private String name;
	private String subjectNum;
	//not in DB:
	ArrayList<Question> questions;
	
	//for FX:
	CheckBox checkbox;
	RadioButton radioButton;


	/**
	 * empty constructor.
	 */
	public Course() {super();}
	
	/**
	 * @param number
	 * @param name
	 * @param subjectNum
	 */
	public Course(String number, String name, String subjectNum) {
		this.number = number;
		this.name = name;
		this.subjectNum = subjectNum;
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
	 * @return the subjectNum
	 */
	public String getSubjectNum() {
		return subjectNum;
	}
	
	/**
	 * @param subjectNum the subjectNum to set
	 */
	public void setSubjectNum(String subjectNum) {
		this.subjectNum = subjectNum;
	}
	

	/**
	 * @return the questions
	 */
	public ArrayList<Question> getQuestions() {
		return questions;
	}

	/**
	 * @param questions the questions to set
	 */
	public void setQuestions(ArrayList<Question> questions) {
		this.questions = questions;
	}

	@Override
	public boolean equals(Object obj) {
		Course c = (Course) obj;
		return number.equals(c.getNumber());
	}
	
	@Override
	public String toString() {
		return "Course [number=" + number + ", name=" + name + ", subjectNum=" + subjectNum + "]";
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
