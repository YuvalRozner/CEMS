package gui;

import controllers.CountDown;
import controllers.TimeController1;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;


public class CountDownTimerController extends AbstractController implements CountDown {
	private TimeController1 timeController;
	
	@FXML
	public Label countdown;

	 
	public CountDownTimerController() {
		timeController = new TimeController1(1, 1, 4,this);
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
    
    @Override
    public void setTextCountdown(String s) {
    	countdown.setText(s);
    }
    
   
 

}
