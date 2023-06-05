package enteties;

import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

public class Test {
    
    private String id;
    private String course;
    private String date;
    private RadioButton radioButton;
    
    //constructor by lior
    
    public Test(String id, String course, String date) {
        super();
        this.id = id;
        this.course = course;
        this.date = date;
        this.radioButton = new RadioButton();
        this.radioButton.setToggleGroup(null); // Set the ToggleGroup to null initially
    }
	public Test(String id) {
		this.id=id;
	}

	public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getCourse() {
        return course;
    }
    
    public void setCourse(String course) {
    	this.course = course;
    }
    
    public String getDate() {
        return date;
    }
    
    public void setDate(String date) {
        this.date = date;
    }
    
    public RadioButton getRadioButton() {
        return radioButton;
    }
    
    public void setToggleGroup(ToggleGroup toggleGroup) {
        this.radioButton.setToggleGroup(toggleGroup); // Set the ToggleGroup for the radio button
    }
}
