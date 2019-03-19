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
import java.util.Arrays;

public class GamloPrice extends Application {

    private Stage primaryStage;
    private HomeController homeController;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("GamloPrice");
        primaryStage.setOnCloseRequest(e -> Platform.exit());

        homeController = new HomeController();

        loadSampleLayout();

        primaryStage.show();

//        simpleTestGame();
//        testXMLLayoutHandler();
//        testQuestionEdit();
        testGameEditor();
    }

    private void testQuestionEdit() {
        QuestionEditor editor = new QuestionEditor();
        editor.openQuestionPane(null);
    }

    private IGame simpleTestGame() {
        IGame Game = new Game("TestGame");
        ICategory cat = new Category("TestCategory");
        for (int i = 20; i <= 100; i += 20) {
            IQuestion question = new Question(i);
            cat.addQuestion(question);
        }
        Game.addCategory(cat);
        return Game;
    }

    private void testXMLLayoutHandler() {
        IQuestionComponent title = new QuestionComponent("Title");
        IQuestionComponent buttonGrid = new QuestionComponent("ButtonGrid");
        IQuestionLayout layout = new QuestionLayout("Question", Arrays.asList(title, buttonGrid));
        IQuestionLayout[] layouts = new IQuestionLayout[1];
        layouts[0] = layout;

        Global.XMLHandler.saveToFile(layouts, "C:\\Users\\Johannes\\Desktop\\Test.xml");

        IQuestionLayout[] layout2 = Global.XMLHandler.readFromFile("C:\\Users\\Johannes\\Desktop\\Test.xml");
        System.out.println(layout2.length);
    }

    private void testGameEditor() {
        GameEditor editor = new GameEditor(new Game());
        homeController.addGame(editor);
    }

    private void loadSampleLayout() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Home.fxml"));
            loader.setController(homeController);
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
