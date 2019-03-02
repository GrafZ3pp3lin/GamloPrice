package controller;

import data.Category;
import data.Game;
import data.Question;
import data.interfaces.ICategory;
import data.interfaces.IGame;
import data.interfaces.IQuestion;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

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

        SimpleTestGame();
    }

    private void SimpleTestGame() {
        IGame Game = new Game("TestGame");
        ICategory cat = new Category("TestCategory");
        for (int i = 20; i < 100; i += 20) {
            IQuestion question = new Question(i);
            cat.addQuestion(question);
        }
        Game.addCategory(cat);
        GameController gc = new GameController(Game);
        gc.ShowGame();
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