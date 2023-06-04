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
	private String course;
	private CheckBox select;
	private TextField codeField;
	private TextField type;
	private Button show;
	
	//////code
	public TextField getCodeField() {
	    return codeField;
	}
	
	public void setNewCodeField() {
		this.codeField = new TextField();
		codeField.setDisable(true);
		
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
		// Add event handler for pressed and unpressed (checked / unchecked) state of the checkbox.
		this.select.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				//flag = newValue; // why we need it again dor? -rozner.
				codeField.setDisable(!newValue);
				type.setDisable(!newValue);
				
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
	}
	
	//////type
	public TextField getType() {
		return type;
	}
	
	public void setNewType() {
		this.type = new TextField();
		type.setDisable(true);
	}
	
	
	//finish lior

	public int getTestCode() {
		return this.testCode;
	}

	public void setTestCode(int testCode) {
		this.testCode = testCode;
	}

	public String getTestNum() {/////////////
		return this.testNum;
	}

	public void setTestNum(String testNum) {////////////////
		this.testNum = testNum;
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