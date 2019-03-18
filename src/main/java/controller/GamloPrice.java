package controller;

import data.*;
import data.interfaces.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import service.timer.StandardTimer;
import service.timer.TimerMode;

import java.io.IOException;
import java.util.Arrays;

public class GamloPrice extends Application {

    private Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

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

    private void simpleTestGame() {

        IGame Game = new Game("TestGame");
        for (int j = 0; j < 5; j++) {
            ICategory cat = new Category("TestCategory: " + j);
            for (int i = 20; i < 100; i += 20) {
                IQuestion question = new Question(i);
                IQuestionData<String> questionTitle = new QuestionData<>("data");
                questionTitle.setData("Wann ist Napoleon geboren?");
                IQuestionComponent component = new QuestionComponent("title", "title", Arrays.asList(questionTitle));
                IQuestionLayout layout = new QuestionLayout("Frage", Arrays.asList(component));
                question.setQuestionLayout(layout);
                cat.addQuestion(question);
            }
            Game.addCategory(cat);
        }

        //IGame Game = Global.binarySerializer.readGameFile("C:\\Users\\joels\\Desktop\\test.gp");
        if (Game != null) {
            GameController gc = new GameController(Game);
            gc.showGame();
        }
        else {
            System.out.println("oops, Something went wrong");
        }
        Global.binarySerializer.saveGame(Game, "C:\\Users\\joels\\Desktop\\test.gp");
    }

    private void timerTest() {
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
}
