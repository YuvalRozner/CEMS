package enteties;

import java.util.ArrayList;

public class Question {

	private String id;
	private String subjectNum;
	private String courseName;
	private String question;
	private String number;
	private String lecturerCreated;
	
	private String[] answers;
	private int correctAns;
	private String instructions;
	private ArrayList<Integer> relevantCourses;
	
	
	public Question(String id, String subjectNum, String courseName, String question, String number,
			String lecturerCreated) {
		super();
		this.id = id;
		this.subjectNum = subjectNum;
		this.courseName = courseName;
		this.question = question;
		this.number = number;
		this.lecturerCreated = lecturerCreated;
	}

	public String getQNum() {
		return this.id;
	}

	public void setQNum(String qNum) {
		this.id = qNum;
	}

	public String getSubjectNum() {
		return this.subjectNum;
	}

	public void setSubjectNum(String subjectNum) {
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
		return this.lecturerCreated;
	}

	public void setLecturereCreated(String lecturereCreated) {
		this.lecturerCreated = lecturereCreated;
	}

	public ArrayList<Integer> getRelevantCourses() {
		return this.relevantCourses;
	}

	public void setRelevantCourses(ArrayList<Integer> relevantCourses) {
		this.relevantCourses = relevantCourses;
	}
	
	public String toString() {
		return "{"+id+", "+subjectNum+", "+courseName+", "+question+", "+number+", "+lecturerCreated+"}";
	
	}
}