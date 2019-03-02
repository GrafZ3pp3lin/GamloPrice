package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

public class HomeController {

    // region FXML Components

    @FXML
    private MenuItem MenuItem_Open;

    @FXML
    private MenuItem MenuItem_Save;

    @FXML
    private MenuItem MenuItem_EditGame;

    @FXML
    private MenuItem MenuItem_Teams;

    @FXML
    private MenuItem MenuItem_Timer;

    @FXML
    private MenuItem MenuItem_Start;

    @FXML
    private MenuItem MenuItem_End;

    @FXML
    private MenuItem MenuItem_About;

    @FXML
    private StackPane StackPane_Conetnt;

    @FXML
    private BorderPane BorderPane_Default;

    @FXML
    private BorderPane BorderPane_Content;

    @FXML
    private TabPane TabPane_Games;

    @FXML
    private Button Button_Start;

    @FXML
    private Button Button_Edit;

    @FXML
    private HBox HBox_Controls;

    // endregion

    @FXML
    private void initialize() {
//        Button_Start.setVisible(true);
    }

}
