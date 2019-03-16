package controller;

import data.*;
import data.interfaces.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import service.timer.StandardTimer;
import service.timer.TimerMode;

import java.io.IOException;

public class GamloPrice extends Application {

    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("GamloPrice");
        primaryStage.setOnCloseRequest(e -> Platform.exit());

        loadSampleLayout();

        primaryStage.show();

        //simpleTestGame();


        timerTest();

    }

    private void timerTest(){
        StandardTimer standard = new StandardTimer(TimerMode.COUNTDOWN, 11110);
        Stage control = new Stage();
        control.setTitle("Timercontrol");

        Scene scenec = new Scene(standard.createTimerControlPane(), 400, 400);
        control.setScene(scenec);
        control.setX(500);
        control.setY(0);

        Stage show = new Stage();
        show.setTitle("Timershow");
        show.setX(1000);
        show.setY(400);

        Scene scenes = new Scene(standard.createTimerShowPane(), 400, 400);
        show.setScene(scenes);

        control.show();
        show.show();
    }

    private void simpleTestGame() {
        IGame Game = new Game("TestGame");
        ICategory cat = new Category("TestCategory");
        for (int i = 20; i < 100; i += 20) {
            IQuestion question = new Question(i);
            cat.addQuestion(question);
        }
        Game.addCategory(cat);
        GameController gc = new GameController(Game);
        gc.showGame();
    }

    private void loadSampleLayout() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Home.fxml"));
            loader.setController(new HomeController());
            Parent layout = loader.load();
            primaryStage.setScene(new Scene(layout));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
