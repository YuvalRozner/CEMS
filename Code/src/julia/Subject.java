package julia;

import java.util.ArrayList;

public class Subject {




	private String subjectNum;
	private String subjectName;
	private ArrayList<Course> courses;
	
	
	public Subject(String subjectNum,String subjectName, ArrayList<Course> courses) {
		this.subjectNum = subjectNum;
		this.subjectName = subjectName;
		this.courses = courses;
	}
	
	public String getSubjectNum() {
		return this.subjectNum;
	}

	public void setSubjectNum(String subjectNum) {
		this.subjectNum = subjectNum;
	}

	public ArrayList<Course> getCourses() {
		return this.courses;
	}

	public void setCourses(ArrayList<Course> courses) {
		this.courses = courses;
	}
	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
}
