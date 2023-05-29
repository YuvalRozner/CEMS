package julia;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

public class QuestionWithAddition extends Question{
	
	private CheckBox select;
	private TextField points;
	private int pointsnum = 0;
	private Boolean flag = false;
	
	public QuestionWithAddition(Question question) {
			super(question.getID(), question.getSubjectNum(), question.getCourseName(), question.getQuestion(), question.getNumber(), question.getLecturereCreated());
			this.select = new CheckBox();
			this.points = new TextField();
			this.points.setDisable(true);
			// Add event handler for pressed (checked) state of the checkbox
			this.select.selectedProperty().addListener(new ChangeListener<Boolean>() {
				@Override
				public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
					if (newValue) {
						// Checkbox is pressed (checked)
						flag = true;
						points.setDisable(false);
						System.out.println("Checkbox pressed");
					}
				}
			});
			
			// Add event handler for unpressed (unchecked) state of the checkbox
			this.select.selectedProperty().addListener(new ChangeListener<Boolean>() {
				@Override
				public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
					if (!newValue) {
						// Checkbox is unpressed (unchecked)
						flag = false;
						points.setDisable(true);
						System.out.println("Checkbox unpressed");
					}
				}
			});
			/*
			this.points.textProperty().addListener(new ChangeListener<String>() {
			    @Override
			    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
			        // Code to be executed when the text property of 'points' TextField changes
			        pointsnum = Integer.parseInt(newValue); // Assuming pointsnum is an integer variable
			        System.out.println(pointsnum);
			    }
			});
			*/
		// TODO Auto-generated constructor stub
	}

	
	public int getPointsInt() {
		return(Integer.parseInt(points.getText()));
	}
	
	
	public TextField getPoints() {
		return points;
	}

	public void setPoints(TextField points) {
		this.points = points;
		
	}

	public CheckBox getSelect() {
		return select;
	}

	public void setSelect(CheckBox select) {
		this.select = select;
	}

}