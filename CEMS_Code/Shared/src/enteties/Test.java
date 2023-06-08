package enteties;

import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

public class Test {
    
    private String id;
    private String Course;
    private String date;
    private RadioButton radioButton;
    
    public Test(String id, String course, String date) {
        super();
        this.id = id;
        Course = course;
        this.date = date;
        this.radioButton = new RadioButton();
        this.radioButton.setToggleGroup(null); // Set the ToggleGroup to null initially
    }

	public Test(String id) { //lior
		this.id=id;
	}
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getCourse() {
        return Course;
    }
    
    public void setCourse(String course) {
        Course = course;
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
    
    public void setNewRadioButton() {
    	this.radioButton.setStyle("-fx-border-color: #CCFFFF; -fx-border-width: 2px; -fx-border-radius: 50%; -fx-background-color: #FFFFFF; -fx-box-shadow: 0 2px 4px rgba(0, 0, 0, 0.4);");
        this.radioButton.setOnMousePressed(e -> this.radioButton.setStyle("-fx-border-color: #CCFFFF; -fx-border-width: 2px; -fx-border-radius: 50%; -fx-background-color: #FFFFFF; -fx-box-shadow: 0 2px 4px rgba(0, 0, 0, 0.4);"));
        this.radioButton.setOnMouseReleased(e -> this.radioButton.setStyle("-fx-border-color: #CCFFFF; -fx-border-width: 2px; -fx-border-radius: 50%; -fx-background-color: #FFFFFF; -fx-box-shadow: 0 2px 4px rgba(0, 0, 0, 0.4);"));
    }
}
