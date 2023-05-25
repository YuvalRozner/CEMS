package juliaScreens;

import java.util.ArrayList;


public class Question {

	//private String id;
	private String subjectNum;
	private String ID;
	private String courseName;
	private String question;
	private String number;
	private String lecturerCreated;
	
	private String[] answers;
	private int correctAns;
	private String instructions;
	private ArrayList<Integer> relevantCourses;
	
	
	public Question(String ID, String subjectNum, String courseName, String question, String number,
			String lecturerCreated) {
		this.ID = ID;
		this.subjectNum = subjectNum;
		this.courseName = courseName;
		this.question = question;
		this.number = number;
		this.lecturerCreated = lecturerCreated;
	}
	
	
	public String getID() {
		return this.ID;
	}

	public void setID(String ID) {
		this.ID = ID;
	}
	
	public boolean equals1(Object obj) {
		Question q = (Question) obj;
		if(!(this.ID.equals(q.getID()))){
			return false;
		}
		if(!(this.subjectNum.equals(q.getSubjectNum()))){
			return false;
		}
		if(!(this.courseName.equals(q.getCourseName()))){
			return false;
		}
		if(!(this.question.equals(q.getQuestion()))){
			return false;
		}
		if(!(this.number.equals(q.getNumber()))){
			return false;
		}
		if(!(this.lecturerCreated.equals(q.getLecturereCreated()))){
			return false;
		}
		return true;
	}
	@Override
	public boolean equals(Object obj) {
		Question q = (Question) obj;
		if(ID.equals(q.getID()) && subjectNum.equals(q.getSubjectNum()) && courseName.equals(q.getCourseName())
				&& question.equals(q.getQuestion())&& number.equals(q.getNumber()) && lecturerCreated.equals(q.getLecturereCreated()))
			return true;
		return false;
	}
	
	public String getSubjectNum() {
		return this.subjectNum;
	}

	public void setSubjectNum(String subjectNum) {
		this.subjectNum = subjectNum;
	}

	public String getCourseName() {
		return this.courseName;
	}
	
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getNumber() {
		return this.number;
	}

	public void setNumber(String number) {
		this.number = number;
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
		return "{"+ID+", "+subjectNum+", "+courseName+", "+question+", "+number+", "+lecturerCreated+"}";
	
	}

}
