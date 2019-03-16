package controller;

import data.*;
import data.interfaces.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;

public class GamloPrice extends Application {

    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        Locale.setDefault( new Locale("en", "US") );
        this.primaryStage = primaryStage;
        primaryStage.setTitle(Global.languageBundle.getString("title"));
        primaryStage.setOnCloseRequest(e -> Platform.exit());

        loadSampleLayout();

        primaryStage.show();

        //simpleTestGame();

    }

    private void simpleTestGame() {

        IGame Game = new Game("TestGame");
        for(int j = 0; j < 5; j++){
            ICategory cat = new Category("TestCategory: " + j);
            for (int i = 20; i < 100; i += 20) {
                IQuestion question = new Question(i);/*
                IQuestionData<String> questionTitle = new QuestionData<>();
                questionTitle.setData("Wann ist Napoleon geboren?");
                IQuestionComponent component = new QuestionComponent("type", "title", questionTitle);
                List<IQuestionComponent> componentList = new ArrayList<>();
                IQuestionLayout layout = new QuestionLayout("Frage", componentList);
                question.setQuestionLayout(layout);*/
                cat.addQuestion(question);
            }
            Game.addCategory(cat);
        }

        //IGame Game = Global.binarySerializer.readGameFile("C:\\Users\\joels\\Desktop\\test.gp");
        if(Game != null){
            GameController gc = new GameController(Game);
            gc.showGame();
        }
        else{
            System.out.println("oops, Something went wrong");
        }
        Global.binarySerializer.saveGame(Game, "C:\\Users\\joels\\Desktop\\test.gp");
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
