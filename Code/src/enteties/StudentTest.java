package enteties;

public class StudentTest {

	private Test test;
	private int studentId;
	private int submittingTime;
	private Time time;
	private TestToExexeute testToExecute;
	private int[] answers;
	private int grade;

	public Test getTest() {
		return this.test;
	}

	public void setTest(Test test) {
		this.test = test;
	}

	public int getStudentId() {
		return this.studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public int getSubmittingTime() {
		return this.submittingTime;
	}

	public void setSubmittingTime(int submittingTime) {
		this.submittingTime = submittingTime;
	}

	public Time getTime() {
		return this.time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	public TestToExexeute getTestToExecute() {
		return this.testToExecute;
	}

	public void setTestToExecute(TestToExexeute testToExecute) {
		this.testToExecute = testToExecute;
	}

	public int[] getAnswers() {
		return this.answers;
	}

	public void setAnswers(int[] answers) {
		this.answers = answers;
	}

	public int getGrade() {
		return this.grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

}