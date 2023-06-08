package enteties;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

public class TestToExexeute{
	private int testCode;
	private String testNum;
	private int testingType;
	private StudentTest studentsTestsLst;
	private boolean lock;
	private int timeExtension;
	private String date;
	private double average;
	private double median;
	private int[] distribution;
	
	////add by lior
	private String status;/// i did it in string lock and run , you make it bolean , think what you prefer status==lock
	private String course;
	private CheckBox select;
	private TextField codeField=new TextField();
	private TextField type=new TextField();
	private Button show;
	private String duration;
	private TextField durationField=new TextField();
	//constructor empty
	public TestToExexeute() {
		
	}
	////constructor for running test
	public TestToExexeute(String status, String course, String duration,String testNum) {
		this.status=status;
		this.course=course;
		this.duration=duration;
		this.testNum=testNum;
	}
	///Testnum
	public String getTestNum() {/////////////
		return this.testNum;
	}

	public void setTestNum(String testNum) {////////////////
		this.testNum = testNum;
	}

	////status
	public String getStatus() {
	    return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	////duration
	public String getDuration() {
	    return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	
	//////code
	public TextField getCodeField() {
	    return codeField;
	}
	
	public void setNewCodeField() {
		this.codeField = new TextField();
		this.codeField.setStyle("-fx-background-color: #F0F8FF; -fx-border-width: 1px; -fx-border-color: #92bce3;  -fx-border-radius: 7px; -fx-font-weight: bold; -fx-font-family: \"Comic Sans MS\";");
		codeField.setDisable(true);
		
	}
	//////DurationField
	public TextField getDurationField() {
	    return durationField;
	}
	
	public void setNewDurationField() {
		this.durationField = new TextField();
		this.durationField.setStyle("-fx-background-color: #F0F8FF; -fx-border-width: 1px; -fx-border-color: #92bce3;  -fx-border-radius: 7px; -fx-font-weight: bold; -fx-font-family: \"Comic Sans MS\";");
		durationField.setDisable(true);
		durationField.setText(duration);
		
	}
	
	//cours
	public String getCourse() {
		return course;
	}
	
	public void setCourse(String course) {
		this.course = course;
	}
	
	////select
	public void setNewSelect() { 
		this.select = new CheckBox();
		this.select.setStyle("-fx-background-color: #F0F8FF; -fx-border-width: 1px; -fx-border-color: #92bce3;  -fx-border-radius: 3px; -fx-font-weight: bold; -fx-font-family: \"Comic Sans MS\";");
		// Add event handler for pressed and unpressed (checked / unchecked) state of the checkbox.
		this.select.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				//flag = newValue; // why we need it again dor? -rozner.
				codeField.setDisable(!newValue);
				type.setDisable(!newValue);
				durationField.setDisable(!newValue);
				
				System.out.println("Checkbox " + (newValue ? "pressed" : "unpressed")); 	}
		});
	}
	
	public CheckBox getSelect() {
		return select;
	}
	////////show
	public Button getShow() {
		return show;
	}

	public void setNewShow() {
		this.show = new Button();
		show.setText("Show");
        show.setStyle("-fx-background-color: #CCFFFF; -fx-background-radius: 30 0 0 30; -fx-font-weight: bold; -fx-font-family: \"Comic Sans MS\";");
        // Add hover effect
        show.setOnMouseEntered(e -> show.setStyle("-fx-background-color: #009494; -fx-background-radius: 30 0 0 30; -fx-font-weight: bold; -fx-font-family: \"Comic Sans MS\";"));
        show.setOnMouseExited(e -> show.setStyle("-fx-background-color: #CCFFFF; -fx-background-radius: 30 0 0 30; -fx-font-weight: bold; -fx-font-family: \"Comic Sans MS\";"));
        // Add pressed effect
        show.setOnMousePressed(e -> show.setStyle("-fx-background-color: #82bfb6; -fx-background-radius: 30 0 0 30; -fx-font-weight: bold; -fx-font-family: \"Comic Sans MS\";"));
        show.setOnMouseReleased(e -> show.setStyle("-fx-background-color: #CCFFFF; -fx-background-radius: 30 0 0 30; -fx-font-weight: bold; -fx-font-family: \"Comic Sans MS\";"));
	}
	
	//////type
	public TextField getType() {
		return type;
	}
	
	public void setNewType() {
		this.type = new TextField();
		this.type.setStyle("-fx-background-color: #F0F8FF; -fx-border-width: 1px; -fx-border-color: #92bce3;  -fx-border-radius: 7px; -fx-font-weight: bold; -fx-font-family: \"Comic Sans MS\";");
		type.setDisable(true);
	}
	
	
	//finish lior

	public int getTestCode() {
		return this.testCode;
	}

	public void setTestCode(int testCode) {
		this.testCode = testCode;
	}


	public int getTestingType() {
		return this.testingType;
	}

	public void setTestingType(int testingType) {
		this.testingType = testingType;
	}

	public StudentTest getStudentsTestsLst() {
		return this.studentsTestsLst;
	}

	public void setStudentsTestsLst(StudentTest studentsTestsLst) {
		this.studentsTestsLst = studentsTestsLst;
	}

	public boolean isLock() {
		return this.lock;
	}

	public void setLock(boolean lock) {
		this.lock = lock;
	}

	public int getTimeExtension() {
		return this.timeExtension;
	}

	public void setTimeExtension(int timeExtension) {
		this.timeExtension = timeExtension;
	}

	public String getDate() {
		return this.date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public double getAverage() {
		return this.average;
	}

	public void setAverage(double average) {
		this.average = average;
	}

	public double getMedian() {
		return this.median;
	}

	public void setMedian(double median) {
		this.median = median;
	}

	public int[] getDistribution() {
		return this.distribution;
	}

	public void setDistribution(int[] distribution) {
		this.distribution = distribution;
	}

}