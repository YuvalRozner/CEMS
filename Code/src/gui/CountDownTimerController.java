package gui;

import controllers.TimeController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;


public class CountDownTimerController extends AbstractController {
	private TimeController timeController;
	
	@FXML
	public Label countdown;

	 
	public CountDownTimerController() {
		timeController = new TimeController(1, 1, 4,this);
	}
    
    
   
    @FXML
    void startBtn(ActionEvent event) {
    	timeController.startTimer();
    }
    @FXML
    void stopBtn(ActionEvent event) {
    	timeController.stopTimer();
    }
    
    @FXML
    void timeLeftBtn(ActionEvent event) {
    	Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(timeController.timeLeft());
        alert.show();
    }
}
