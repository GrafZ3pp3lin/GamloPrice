package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;

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

    private Parent gameEditPane;

    public GameEditor() {
        loadGameEditPane();
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

    public Parent getGameEditPane() {
        return gameEditPane;
    }

}
