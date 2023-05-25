package juliaScreens;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

public class QuestionWithAddition extends Question{
	
	private CheckBox select;
	private TextField points;
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
						// Add your logic here
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
						// Add your logic here
						flag = false;
						points.setDisable(true);
						
						System.out.println("Checkbox unpressed");
					}
				}
			});
		// TODO Auto-generated constructor stub
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