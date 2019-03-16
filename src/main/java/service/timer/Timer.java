package service.timer;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.layout.Region;
import javafx.util.Duration;

public abstract class Timer {

    private static final int period = 10;
    protected TimerMode timerMode = TimerMode.STOPWATCH;
    protected IntegerProperty millis = new SimpleIntegerProperty(0);
    protected int startTime;
    private boolean alreadyStarted = false;
    private boolean pause = false;
    private Timeline timeline;

    public Timer(TimerMode mode, int millis) {
        this.timerMode = mode;
        setDuration(millis);
    }

    public boolean setTimerMode(TimerMode timerMode) {
        if (!alreadyStarted) {
            this.timerMode = timerMode;
        }
        return !alreadyStarted;
    }

    public void setDuration(int millis) {
        this.startTime = millis + 1;
        this.millis.set(millis + 1);
    }

    public TimerState play() {
        timeline.play();
        pause = false;
        return TimerState.PLAY;
    }

    public TimerState pause() {
        timeline.pause();
        pause = true;
        return TimerState.PAUSE;
    }

    public TimerState togglePlayPause() {
        if (pause) {
            return play();
        }
        return pause();
    }

    public void reset() {
        alreadyStarted = false;
        if (timeline != null) {
            timeline.stop();
        }
        timeline = null;
        millis.set(startTime);
        pause = false;
        onReset();
    }

    public boolean isStarted() {
        return alreadyStarted;
    }

    public void start() {
        timeline = new Timeline();
        millis.set(startTime);
        if (timerMode == TimerMode.COUNTDOWN) {
            timeline.getKeyFrames().add(new KeyFrame(Duration.millis(startTime), new KeyValue(millis, 0)));
        }
        else if (timerMode == TimerMode.STOPWATCH) {
            timeline.getKeyFrames().add(new KeyFrame(Duration.millis(Integer.MAX_VALUE), new KeyValue(millis, Integer.MAX_VALUE)));
        }
        timeline.playFromStart();
        timeline.setOnFinished(e -> timerEnd());
        alreadyStarted = true;
    }

    /**
     * here you have to generate your Timerpane, you get current time value with direct binding to millis
     * or by adding a EventHandler millis.addListener(e -> ... );
     *
     * @return Region with the timerpane for Control
     */
    public abstract Region createTimerControlPane();

    /**
     * here you have to generate your Timerpane, you get current time value with direct binding to millis
     * or by adding a EventHandler millis.addListener(e -> ... );
     *
     * @return Region with the timerpane for Show
     */
    public abstract Region createTimerShowPane();

    /**
     * called when countdown is finished (never called in stopwatch mode)
     */
    public abstract void timerEnd();

    public abstract void onReset();

}
