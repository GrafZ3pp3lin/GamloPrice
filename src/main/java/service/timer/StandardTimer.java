package service.timer;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;

import java.io.IOException;

public class StandardTimer extends Timer {

    /**
     * Fields for TimerControl Pane
     */
    @FXML
    private Button playpause;

    @FXML
    private Button reset;

    @FXML
    private Label timeC;

    /**
     * Fields for TimerShow Pane
     */
    @FXML
    private Label timeS;


    /**
     * constructor
     *
     * @param mode   TimerMode (stopwatch or Countdown)
     * @param millis start value in ms
     */
    public StandardTimer(TimerMode mode, int millis) {
        super(mode, millis);
    }

    @FXML
    private void playTimer() {
        if (isStarted()) {
            TimerState state = togglePlayPause();
            renameButtons(state);
        }
        else {
            start();
            renameButtons(TimerState.PLAY);
        }
    }

    @FXML
    private void resetTimer() {
        reset();
    }

    @Override
    public Region createTimerControlPane() {
        Region layout = null;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/TimerControl.fxml"));
            loader.setController(this);
            layout = loader.load();
            millis.addListener(e -> update(convertToTimeString(millis.get())));
            update(convertToTimeString(millis.get()));
            playpause.setOnAction(e -> playTimer());
            reset.setOnAction(e -> reset());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return layout;
    }

    @Override
    public Region createTimerShowPane() {
        Region layout = null;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/TimerShow.fxml"));
            loader.setController(this);
            layout = loader.load();
            millis.addListener(e -> update(convertToTimeString(millis.get())));
            update(convertToTimeString(millis.get()));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return layout;
    }

    @Override
    public void timerEnd() {
        playpause.setDisable(true);
        System.out.println("finished");
    }

    @Override
    public void onReset() {
        playpause.setText("start");
        playpause.setDisable(false);
    }

    /**
     * converts amount of inputmillis in a readable outputstring
     *
     * @param millis
     * @return String in format hh:mm:ss:msms (hh and mm only if millis >= 1 min(60000ms))
     */
    private String convertToTimeString(int millis) {
        int ms = (millis / 10) % 100;
        int sec = (millis / 1000) % 60;
        int min = (millis / 60000) % 60;
        int hour = millis / 3600000;
        return (hour >= 1 ? hour + ":" : "") + ((min >= 1 || hour >= 1) ? (min < 10 ? "0" : "") + min + ":" : "") + (sec < 10 ? "0" : "") + sec + ":" + (ms < 10 ? "0" : "") + ms;
    }

    private void update(String time) {
        try {
            timeC.setText(time);
        }
        catch (NullPointerException e) {
        }
        try {
            timeS.setText(time);
        }
        catch (NullPointerException e) {
        }
    }

    private void renameButtons(TimerState state) {
        if (state == TimerState.PLAY) {
            playpause.setText("pause");
        }
        else if (state == TimerState.PAUSE) {
            playpause.setText("play");
        }
    }
}
