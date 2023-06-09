package enteties;

import java.util.ArrayList;

public class Subject {
    //in DB:
	private String number;
	private String name;
	//not in DB:
	private ArrayList<Course> courses;
	//for FX:
	
	
	
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

}
