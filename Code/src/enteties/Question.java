package enteties;

public class Question {

	private int id;
	private int subjectNum;
	private String courseName;
	private String question;
	private int number;
	private String lecturereCreated;
	
	private String[] answers;
	private int correctAns;
	private String instructions;
	private int[] relevantCourses;

	public int getQNum() {
		return this.id;
	}

	public void setQNum(int qNum) {
		this.id = qNum;
	}

	public int getSubjectNum() {
		return this.subjectNum;
	}

	public void setSubjectNum(int subjectNum) {
		this.subjectNum = subjectNum;
	}

	public String getQuestion() {
		return this.question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String[] getAnswers() {
		return this.answers;
	}

	public void setAnswers(String[] answers) {
		this.answers = answers;
	}

	public int getCorrectAns() {
		return this.correctAns;
	}

	public void setCorrectAns(int correctAns) {
		this.correctAns = correctAns;
	}

	public String getInstructions() {
		return this.instructions;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

	public String getLecturereCreated() {
		return this.lecturereCreated;
	}

	public void setLecturereCreated(String lecturereCreated) {
		this.lecturereCreated = lecturereCreated;
	}

	public int[] getRelevantCourses() {
		return this.relevantCourses;
	}

	public void setRelevantCourses(int[] relevantCourses) {
		this.relevantCourses = relevantCourses;
	}

}