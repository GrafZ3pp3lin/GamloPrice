package service.timer;

import controller.TimerController;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.*;

import java.io.IOException;

public class StandardTimer extends Timer {

    private TimerController controller;

    public StandardTimer(TimerMode mode, int millis) {
        super(mode, millis);
    }

    @Override
    public Region createTimerPane() {
        Region layout = null;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Timer.fxml"));
            controller = new TimerController();
            controller.setTimer(this);
            loader.setController(controller);
            layout = loader.load();
            millis.addListener(e-> controller.update(convertToTimeString(millis.get())));
            controller.update(convertToTimeString(millis.get()));
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return layout;
    }

    private String convertToTimeString(int millis){
        int ms = (millis/10)%100;
        int sec = (millis/1000)%60;
        int min = (millis/60000)%60;
        int hour = millis/3600000;
        return (hour >= 1 ? hour + ":" : "") + ((min >= 1 || hour >= 1) ? (min < 10 ? "0":"") +  min + ":" : "") + (sec < 10 ? "0":"") + sec + ":" + (ms < 10 ? "0":"") +ms;
    }

    @Override
    public void timerEnd() {
        controller.onEnd();
        System.out.println("finished");
    }

    @Override
    public void onReset() {
        controller.onReset();
    }


}
