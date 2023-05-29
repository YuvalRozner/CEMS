package julia;

public class Course {
	private String courseNum;
	private String courseName;

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
}
