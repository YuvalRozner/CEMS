package controllers;

public class TimeController {
	/*private Integer hours;
	private Integer minutes;
	private Integer seconds;
	private boolean isCountingDown = false;
	private Timeline time;
	private CountDownTimerController countDownTimerController; 

	
	//the controller CountDownTimerController is here for the setText on the label
	public TimeController(int hours, int minutes, int seconds, CountDownTimerController countDownTimerController) {
		this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
        this.countDownTimerController = countDownTimerController;
     }
	
	 private String formatTime(int hours, int minutes, int seconds) {
	        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
	 }
	 
	 public void startTimer() {
		 if (isCountingDown) { //only for someone called startTimer() more then once without stop
	            time.stop();
	        }
		 this.countDownTimerController.countdown.setText(formatTime(hours, minutes, seconds));
	        time = new Timeline();
	        time.setCycleCount(Timeline.INDEFINITE); //infinity untill stop() was called
	        
	        
	        
	        KeyFrame frame = new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event) {
	                if (seconds > 0 || minutes > 0 || hours > 0) {
	                    seconds--;
	                    if (seconds < 0) {
	                        seconds = 59;
	                        minutes--;
	                        if (minutes < 0) {
	                            minutes = 59;
	                            hours--;
	                        }
	                    }
	                    countDownTimerController.countdown.setText(formatTime(hours, minutes, seconds));
	                } else {
	                	countDownTimerController.countdown.setText("Normal End!");
	                    time.stop();
	                }
	            }
	        });
	        
	        time.getKeyFrames().add(frame);
	        time.playFromStart();
	        isCountingDown = true;
	    }
	 public void stopTimer() {
		 if (isCountingDown) {
			 countDownTimerController.countdown.setText("Force End!");
			 time.stop();
		 }
			 
	 }
	 
	 public String timeLeft() {
		return ("Time left for the countdown is: "+ String.format("%02d:%02d:%02d", hours, minutes, seconds));
		 
	 }
	 */
}