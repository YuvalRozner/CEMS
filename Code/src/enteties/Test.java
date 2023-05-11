package enteties;

public class Test {

	private int testNum;
	private int courseNum;
	private int subjectNum;
	private Question[] questions;
	private int duration;
	private int[] points;
	private String instructionsForStudents;
	private String instructionsForLecturer;
	private String lecturerCreated;

	public int getTestNum() {
		return this.testNum;
	}

	public void setTestNum(int testNum) {
		this.testNum = testNum;
	}

	public int getCourseNum() {
		return this.courseNum;
	}

	public void setCourseNum(int courseNum) {
		this.courseNum = courseNum;
	}

	public int getSubjectNum() {
		return this.subjectNum;
	}

	public void setSubjectNum(int subjectNum) {
		this.subjectNum = subjectNum;
	}

	public Question[] getQuestions() {
		return this.questions;
	}

	public void setQuestions(Question[] questions) {
		this.questions = questions;
	}

	public int getDuration() {
		return this.duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int[] getPoints() {
		return this.points;
	}

	public void setPoints(int[] points) {
		this.points = points;
	}

	public String getInstructionsForStudents() {
		return this.instructionsForStudents;
	}

	public void setInstructionsForStudents(String instructionsForStudents) {
		this.instructionsForStudents = instructionsForStudents;
	}

	public String getInstructionsForLecturer() {
		return this.instructionsForLecturer;
	}

	public void setInstructionsForLecturer(String instructionsForLecturer) {
		this.instructionsForLecturer = instructionsForLecturer;
	}

	public String getLecturerCreated() {
		return this.lecturerCreated;
	}

	public void setLecturerCreated(String lecturerCreated) {
		this.lecturerCreated = lecturerCreated;
	}

}