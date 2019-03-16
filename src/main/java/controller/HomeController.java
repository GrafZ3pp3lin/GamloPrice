package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

public class HomeController {

    // region FXML Components

    @FXML
    private Menu Menu_File;

    @FXML
    private Menu Menu_Edit;

    @FXML
    private Menu Menu_Game;

    @FXML
    private Menu Menu_Help;

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
    private StackPane StackPane_Content;

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
        //init language
        Menu_File.setText(Global.languageBundle.getString("file"));
        Menu_Edit.setText(Global.languageBundle.getString("edit"));
        Menu_Game.setText(Global.languageBundle.getString("game"));
        Menu_Help.setText(Global.languageBundle.getString("help"));

        MenuItem_Open.setText(Global.languageBundle.getString("file_open"));
        MenuItem_Save.setText(Global.languageBundle.getString("file_save"));
        MenuItem_EditGame.setText(Global.languageBundle.getString("edit_game"));
        MenuItem_Teams.setText(Global.languageBundle.getString("edit_teams"));
        MenuItem_Timer.setText(Global.languageBundle.getString("edit_timer"));
        MenuItem_Start.setText(Global.languageBundle.getString("game_start"));
        MenuItem_End.setText(Global.languageBundle.getString("game_end"));
        MenuItem_About.setText(Global.languageBundle.getString("help_about"));
    }

}
