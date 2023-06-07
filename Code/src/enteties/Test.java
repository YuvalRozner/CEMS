package enteties;

import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

public class Test {
    
    private String id;
    private String course;
    private String date;
    private RadioButton radioButton;
    private String code;
    private String type;
    private  javafx.scene.control.Button showT=new  javafx.scene.control.Button();
    //constructor by lior show test data
    public Test(String id, String course, String date, String code, String type) {
        this.id = id;
        this.course = course;
        this.date = date;
        this.setCode(code);
        this.setType(type);
        
    }
    
    
    //constructor by lior
    
    public Test(String id, String course, String date) {
        this.id = id;
        this.course = course;
        this.date = date;
        this.radioButton = new RadioButton();
        this.radioButton.setToggleGroup(null); // Set the ToggleGroup to null initially
    }
	public Test(String id) {
		this.id=id;;
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


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public javafx.scene.control.Button getShowT() {
		return showT;
	}


	public void setShowT() {
		showT.setText("Show");
		showT.setStyle("-fx-background-color: #CCFFFF; -fx-background-radius: 30 0 0 30;");
        // Add hover effect
		showT.setOnMouseEntered(e -> showT.setStyle("-fx-background-color: #009494; -fx-background-radius: 30 0 0 30;"));
		showT.setOnMouseExited(e -> showT.setStyle("-fx-background-color: #CCFFFF; -fx-background-radius: 30 0 0 30;"));
        // Add pressed effect
		showT.setOnMousePressed(e -> showT.setStyle("-fx-background-color: #82bfb6; -fx-background-radius: 30 0 0 30;"));
		showT.setOnMouseReleased(e -> showT.setStyle("-fx-background-color: #CCFFFF; -fx-background-radius: 30 0 0 30;"));
	}
}
