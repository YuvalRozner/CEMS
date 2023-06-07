package controllers;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import javafx.application.Platform;

public class TimeController1 {
    private boolean isCountingDown = false;
    private CountDown countDown;
    private LocalDateTime endTime;
    private LocalDateTime timeToCountdown;
    private ScheduledExecutorService executor;
    private ScheduledFuture<?> scheduledFuture;
    Duration durationLeft;
    public TimeController1(int hours, int minutes, int seconds, CountDown onlineTestController) {
        this.countDown = onlineTestController;

        // Calculate the end time based on the provided hours, minutes, and seconds
        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.ofHours(hours).plusMinutes(minutes).plusSeconds(seconds);
        this.endTime = now.plus(duration);
        this.timeToCountdown = now; // Initialize timeToCountdown to the current time
    }

    public void startTimer() {
        if (!isCountingDown) {
            isCountingDown = true;
            updateLabel();

            // Calculate the initial duration based on the current time to endTime
            durationLeft = Duration.between(timeToCountdown, endTime);

            // Create a ScheduledExecutorService with a single thread
            executor = Executors.newSingleThreadScheduledExecutor();

            // Schedule the label update task to run every second
            scheduledFuture = executor.scheduleAtFixedRate(new Runnable() {
                @Override
                public void run() {
                    updateLabel();
                }
            }, 0, 1, TimeUnit.SECONDS);

            // Adjust the endTime based on the initial duration
            endTime = LocalDateTime.now().plus(durationLeft);
        }
    }

    public void stopTimer() {
        if (isCountingDown) {
            isCountingDown = false;
            scheduledFuture.cancel(false);
            executor.shutdown();

            // Update the timeToCountdown to the current time
            timeToCountdown = LocalDateTime.now();
        }
    }

    private void updateLabel() {
        if (isCountingDown) {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    // Calculate the time remaining from current time to the end time
                    durationLeft = Duration.between(LocalDateTime.now(), endTime);

                    // Extract the individual components: hours, minutes, seconds
                    long hours = durationLeft.toHours();
                    long minutes = durationLeft.toMinutes() % 60;
                    long seconds = durationLeft.getSeconds() % 60;

                    // Format the time remaining as a string
                    String timeRemaining = String.format("%02d:%02d:%02d", hours, minutes, seconds);

                    // Update the label with the time remaining
                    countDown.setTextCountdown(timeRemaining);
                }
            });
        }
    }

    public String timeLeft() {
       // Duration durationLeft = Duration.between(timeToCountdown, endTime);
        long hours = durationLeft.toHours();
        long minutes = durationLeft.toMinutes() % 60;
        long seconds = durationLeft.getSeconds() % 60;
        return String.format("Time left for the countdown is: %02d:%02d:%02d", hours, minutes, seconds);
    }
}
