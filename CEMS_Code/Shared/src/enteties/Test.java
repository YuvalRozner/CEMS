package enteties;

import javafx.scene.control.RadioButton;

public class Test {
    //in DB:
    private String id; // 6 digits
    private String number; // 2 digits
    private String CourseNumber;
    private Integer duration; //minutes
    private String instructionsForStudent;
    private String instructionsForLecturer;
    //not in DB:
    private Course course;
    //for FX:
	private RadioButton radioButton;
   
	
	/**
	 * empty constructor.
	 */
	public Test() {super();}
    
    /**
	 * @param id
	 * @param number
	 * @param courseNumber
	 * @param duration
	 * @param instructionsForStudent
	 * @param instructionsForLecturer
	 */
	public Test(String id, String number, String courseNumber, Integer duration, String instructionsForStudent, String instructionsForLecturer) {
		this.id = id;
		this.number = number;
		CourseNumber = courseNumber;
		this.duration = duration;
		this.instructionsForStudent = instructionsForStudent;
		this.instructionsForLecturer = instructionsForLecturer;
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
	 * @return the courseNumber
	 */
	public String getCourseNumber() {
		return CourseNumber;
	}

	/**
	 * @param courseNumber the courseNumber to set
	 */
	public void setCourseNumber(String courseNumber) {
		CourseNumber = courseNumber;
	}

	/**
	 * @return the duration
	 */
	public Integer getDuration() {
		return duration;
	}

	/**
	 * @param duration the duration to set
	 */
	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	/**
	 * @return the instructionsForStudent
	 */
	public String getInstructionsForStudent() {
		return instructionsForStudent;
	}

	/**
	 * @param instructionsForStudent the instructionsForStudent to set
	 */
	public void setInstructionsForStudent(String instructionsForStudent) {
		this.instructionsForStudent = instructionsForStudent;
	}

	/**
	 * @return the instructionsForLecturer
	 */
	public String getInstructionsForLecturer() {
		return instructionsForLecturer;
	}

	/**
	 * @param instructionsForLecturer the instructionsForLecturer to set
	 */
	public void setInstructionsForLecturer(String instructionsForLecturer) {
		this.instructionsForLecturer = instructionsForLecturer;
	}
	
    /**
	 * @return the course
	 */
	public Course getCourse() {
		return course;
	}

	/**
	 * @param course the course to set
	 */
	public void setCourse(Course course) {
		this.course = course;
	}
	
	@Override
	public boolean equals(Object obj) {
		Test t = (Test) obj;
		return id.equals(t.getId());
	}

	@Override
	public String toString() {
		return "Test [id=" + id + ", number=" + number + ", CourseNumber=" + CourseNumber + ", duration=" + duration + ", instructionsForStudent="
				+ instructionsForStudent + ", instructionsForLecturer=" + instructionsForLecturer + "]";
	}

	public void setNewRadioButton() {
    	this.radioButton.setStyle("-fx-border-color: #CCFFFF; -fx-border-width: 2px; -fx-border-radius: 50%; -fx-background-color: #FFFFFF; -fx-box-shadow: 0 2px 4px rgba(0, 0, 0, 0.4);");
        this.radioButton.setOnMousePressed(e -> this.radioButton.setStyle("-fx-border-color: #CCFFFF; -fx-border-width: 2px; -fx-border-radius: 50%; -fx-background-color: #FFFFFF; -fx-box-shadow: 0 2px 4px rgba(0, 0, 0, 0.4);"));
        this.radioButton.setOnMouseReleased(e -> this.radioButton.setStyle("-fx-border-color: #CCFFFF; -fx-border-width: 2px; -fx-border-radius: 50%; -fx-background-color: #FFFFFF; -fx-box-shadow: 0 2px 4px rgba(0, 0, 0, 0.4);"));
    }
}
