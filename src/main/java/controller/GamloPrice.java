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

public class GamloPrice extends Application {

    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("GamloPrice");
        primaryStage.setOnCloseRequest(e -> Platform.exit());

        loadSampleLayout();

        primaryStage.show();

        simpleTestGame();
    }

    private void simpleTestGame() {

        IGame Game = new Game("TestGame");
        for(int j = 0; j < 2; j++){
            ICategory cat = new Category("TestCategory: " + j);
            for (int i = 20; i < 100; i += 20) {
                IQuestion question = new Question(i);
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
