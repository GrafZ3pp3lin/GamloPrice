package controller;

import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

public class HomeController {

    // region FXML Components

    @FXML
    private MenuItem menuItem_Open;

    @FXML
    private MenuItem menuItem_Save;

    @FXML
    private MenuItem menuItem_EditGame;

    @FXML
    private MenuItem menuItem_Teams;

    @FXML
    private MenuItem menuItem_Timer;

    @FXML
    private MenuItem menuItem_Start;

    @FXML
    private MenuItem menuItem_End;

    @FXML
    private MenuItem menuItem_About;

    @FXML
    private StackPane stackPane_Content;

    @FXML
    private BorderPane borderPane_Default;

    @FXML
    private BorderPane borderPane_Content;

    @FXML
    private TabPane tabPane_Games;

    // endregion

    @FXML
    private void initialize() {
    }

    public void addGame(GameEditor gameEditor) {
        tabPane_Games.getTabs().add(gameEditor);
    }

}
