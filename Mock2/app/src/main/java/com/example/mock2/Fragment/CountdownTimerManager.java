package com.example.mock2.Fragment;

import android.os.CountDownTimer;
import android.widget.TextView;

public class CountdownTimerManager {
    private CountDownTimer countDownTimer;
    private TextView timerDisplay;
    private TimerStateListener timerStateListener;
    private long timeLeftInMillis;
    private boolean isTimerRunning = false;

    public interface TimerStateListener {
        void onTimerFinish();
        void onTimerTick(long millisUntilFinished);
    }

    public CountdownTimerManager(TextView timerDisplay) {
        this.timerDisplay = timerDisplay;
    }

    public void setTimerStateListener(TimerStateListener listener) {
        this.timerStateListener = listener;
    }

    public void startTimer(int hours, int minutes, int seconds) {
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }

        // Convert time to milliseconds
        timeLeftInMillis = ((hours * 3600L) + (minutes * 60L) + seconds) * 1000;

        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateTimerDisplay();
                if (timerStateListener != null) {
                    timerStateListener.onTimerTick(millisUntilFinished);
                }
            }

            @Override
            public void onFinish() {
                isTimerRunning = false;
                if (timerStateListener != null) {
                    timerStateListener.onTimerFinish();
                }
                updateTimerDisplay();
            }
        }.start();

        isTimerRunning = true;
    }

    public void pauseTimer() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
            isTimerRunning = false;
        }
    }

    public void resumeTimer() {
        if (!isTimerRunning && timeLeftInMillis > 0) {
            startTimer(0, 0, (int)(timeLeftInMillis / 1000));
        }
    }

    public void cancelTimer() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
            timeLeftInMillis = 0;
            isTimerRunning = false;
            updateTimerDisplay();
        }
    }

    private void updateTimerDisplay() {
        int hours = (int) (timeLeftInMillis / 1000) / 3600;
        int minutes = (int) ((timeLeftInMillis / 1000) % 3600) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;

        String timeLeftFormatted = String.format("%02d:%02d:%02d", hours, minutes, seconds);
        timerDisplay.setText(timeLeftFormatted);
    }

    public boolean isTimerRunning() {
        return isTimerRunning;
    }

    public long getTimeLeftInMillis() {
        return timeLeftInMillis;
    }
}