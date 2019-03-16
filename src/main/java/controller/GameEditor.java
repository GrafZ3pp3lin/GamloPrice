package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class GameEditor {

    // region GameEdit

    @FXML
    private ListView listView_Questions;

    @FXML
    private Button button_addQuestion;

    @FXML
    private GridPane gridPane_Game;

    // endregion

    // region QuestionEdit

    @FXML
    private ListView listView_Components;

    @FXML
    private Button button_addComponent;

    @FXML
    private TextField textField_type;

    @FXML
    private GridPane gridPane_Components;

    // endregion

    private Stage questionStage;
    private Scene questionEditScene;
    private Parent gameEditPane;

    public GameEditor() {
        loadQuestionStage();
        loadGameEditPane();
        questionStage = new Stage(StageStyle.UNDECORATED);
        questionStage.setScene(questionEditScene);
        questionStage.setHeight(720);
        questionStage.setWidth(1280);
    }

    private void loadGameEditPane() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/GameEdit.fxml"));
            gameEditPane = loader.load();
            loader.setController(this);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadQuestionStage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/QuestionEdit.fxml"));
            Parent layout = loader.load();
            loader.setController(this);
            questionEditScene = new Scene(layout);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Parent getGameEditPane() {
        return gameEditPane;
    }

    public void openQuestionPane() {
        questionStage.showAndWait();
    }

}
