package enteties;

import java.util.ArrayList;
import java.util.Arrays;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class Question {
	//in DB:
	private String id;
	private Integer number;
	private String question;
	private String subjectNum;
	private String lecturerId;
	private String[] answers = new String[4]; //in DB its 4 separate strings
	private Integer correctAnswer;
	private String instructions;
	//not in DB:
	private Subject subject = null;
	private ArrayList<Course> courses = null;
	private Integer points = null;
	private Course course = null; //added by mor
	//for FX:
	CheckBox checkbox;
	RadioButton radioButton;
	TextField textField;
	
	
	//private CheckBox select;
	//private TextField points;
	private Button showQ = new Button();
	

	/**
	 * empty constructor.
	 */
	public Question() {super();}
	
	/**
	 * @param id
	 * @param number
	 * @param question
	 * @param subjectNum
	 * @param lecturerId
	 * @param answers
	 * @param correctAns
	 * @param instructions
	 * @param courses
	 */
	public Question(String id, Integer number, String question, String subjectNum, String lecturerId, String[] answers, Integer correctAnswer, String instructions, ArrayList<Course> courses) {
		this.id = id;
		this.number = number;
		this.question = question;
		this.subjectNum = subjectNum;
		this.lecturerId = lecturerId;
		this.answers = answers;
		this.correctAnswer = correctAnswer;
		this.instructions = instructions;
		this.courses = courses;
	}
	
	/**
	 * @param id
	 * @param number
	 * @param question
	 * @param subjectNum
	 * @param lecturerId
	 * @param answers
	 * @param correctAns
	 * @param instructions
	 */
	public Question(String id, Integer number, String question, String subjectNum, String lecturerId, String ans1, String ans2, String ans3, String ans4, Integer correctAnswer, String instructions) {
		this.id = id;
		this.number = number;
		this.question = question;
		this.subjectNum = subjectNum;
		this.lecturerId = lecturerId;
		this.answers = new String[] {ans1, ans2, ans3, ans4};
		this.correctAnswer = correctAnswer;
		this.instructions = instructions;
	}
	
	public Question(String id, Integer number, String question, String subjectNum, String lecturerId, String[] answers, Integer correctAnswer, String instructions, ArrayList<Course> courses, String subjectName) {
		this.id = id;
		this.number = number;
		this.question = question;
		this.subjectNum = subjectNum;
		this.lecturerId = lecturerId;
		this.answers = answers;
		this.correctAnswer = correctAnswer;
		this.instructions = instructions;
		this.courses = courses;
		this.subject = new Subject(subjectNum, subjectName);
	}
	/**
	 * constructor for creating Question object with course parameter, belongs to that specific question
	 * 
	 * @param id
	 * @param number
	 * @param question
	 * @param subjectNum
	 * @param lecturerId
	 * @param answers - ans1,ans2,ans3,ans4
	 * @param correctAnswer
	 * @param instructions
	 * @param numberCourse
	 * @param name
	 * @param subjectNumber
	 */
	public Question(String id, Integer number, String question, String subjectNum, String lecturerId,  String ans1, String ans2, String ans3, String ans4, Integer correctAnswer, String instructions, String numberCourse, String name, String subjectNumber) {
		this.id = id;
		this.number = number;
		this.question = question;
		this.subjectNum = subjectNum;
		this.lecturerId = lecturerId;
		this.answers = new String[] {ans1, ans2, ans3, ans4};
		this.correctAnswer = correctAnswer;
		this.instructions = instructions;
		this.course = new Course(numberCourse, name, subjectNumber);
	}
	
	public Question(String id, Integer number, String question, String subjectNum, String lecturerId, String ans1, String ans2, String ans3, String ans4, Integer correctAnswer, String instructions,Integer points) {
		this.id = id;
		this.number = number;
		this.question = question;
		this.subjectNum = subjectNum;
		this.lecturerId = lecturerId;
		this.answers = new String[] {ans1, ans2, ans3, ans4};
		this.correctAnswer = correctAnswer;
		this.instructions = instructions;
		this.points = points;
	}
	
	public String getSubjectName() {
		return subject.getName();
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the number
	 */
	public Integer getNumber() {
		return number;
	}

	/**
	 * @param number the number to set
	 */
	public void setNumber(Integer number) {
		this.number = number;
	}

	/**
	 * @return the question
	 */
	public String getQuestion() {
		return question;
	}

	/**
	 * @param question the question to set
	 */
	public void setQuestion(String question) {
		this.question = question;
	}

	/**
	 * @return the lecturerId
	 */
	public String getLecturerId() {
		return lecturerId;
	}

	/**
	 * @param lecturerId the lecturerId to set
	 */
	public void setLecturerId(String lecturerId) {
		this.lecturerId = lecturerId;
	}

	/**
	 * @return the answers
	 */
	public String[] getAnswers() {
		return answers;
	}
	/**
	 * get one answer
	 */
	public String getOneAnswer(Integer i) {
		return answers[i];
	}
	/**
	 * @param answers the answers to set
	 */
	public void setAnswers(String[] answers) {
		this.answers = answers;
	}

	/**
	 * @return the correctAns
	 */
	public Integer getCorrectAnswer() {
		return correctAnswer;
	}

	/**
	 * @param correctAns the correctAns to set
	 */
	public void setCorrectAnswer(Integer correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	/**
	 * @return the instructions
	 */
	public String getInstructions() {
		return instructions;
	}

	/**
	 * @param instructions the instructions to set
	 */
	public void setInstructions(String instructions) {
		this.instructions = instructions;
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
	 * @return the points
	 */
	public Integer getPoints() {
		return points;
	}

	/**
	 * @param points the points to set
	 */
	public void setPoints(Integer points) {
		this.points = points;
	}
	
	/**
	 * @return the course object
	 */
	public Course getCourse() {
		return course;
	}
	
	/**
	 * @return the course name
	 */
	public String getCourseName() {
		if(course==null)
			return null;
		return course.getName();
	}
	
	/**
	 * @param course
	 */
	public void setCourse(Course course) {
		this.course = course;
	}

	@Override
	public boolean equals(Object obj) {
		Question q = (Question) obj;
		return id.equals(q.getId());
	}
	
	@Override
	public String toString() {
		return "Question [id=" + id + ", number=" + number + ", question=" + question + ", lecturerId=" + lecturerId + ", answers=" + Arrays.toString(answers)
				+ ", correctAns=" + correctAnswer + ", instructions=" + instructions + ", courses=" + courses + "]";
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
	
	public void setNewTextField() {
		textField = new TextField();
	}
	public TextField getTextField() {
		return textField;
	}
	
	

	//////////////////////////
	
	public Button getShowQ() {
		return showQ;
	}

	public void setNewShowQ() {
		showQ.setText("Show");
		showQ.setStyle("-fx-background-color: #CCFFFF; -fx-background-radius: 30 0 0 30; -fx-font-weight: bold; -fx-font-family: \"Comic Sans MS\";");
        // Add hover effect
		showQ.setOnMouseEntered(e -> showQ.setStyle("-fx-background-color: #009494; -fx-background-radius: 30 0 0 30; -fx-font-weight: bold; -fx-font-family: \"Comic Sans MS\";"));
		showQ.setOnMouseExited(e -> showQ.setStyle("-fx-background-color: #CCFFFF; -fx-background-radius: 30 0 0 30; -fx-font-weight: bold; -fx-font-family: \"Comic Sans MS\";"));
        // Add pressed effect
		showQ.setOnMousePressed(e -> showQ.setStyle("-fx-background-color: #82bfb6; -fx-background-radius: 30 0 0 30; -fx-font-weight: bold; -fx-font-family: \"Comic Sans MS\";"));
		showQ.setOnMouseReleased(e -> showQ.setStyle("-fx-background-color: #CCFFFF; -fx-background-radius: 30 0 0 30; -fx-font-weight: bold; -fx-font-family: \"Comic Sans MS\";"));
		
	}
}
