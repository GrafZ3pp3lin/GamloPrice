package controller;

import data.Game;
import data.interfaces.ICategory;
import data.interfaces.IGame;
import data.interfaces.IQuestion;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;

import java.io.IOException;

public class GameEditor extends Tab {

    // region GameEdit

    @FXML
    private ListView listView_Questions;

    @FXML
    private Button button_addQuestion;

    @FXML
    private GridPane gridPane_Game;

    @FXML
    private TextField textField_name;

    @FXML
    private Button button_start;

    // endregion

    private IGame game;

    // region Constructors

    public GameEditor(IGame game) {
        super();
        this.setContent(loadContent());
        this.game = game;
        visualizeGame();
    }

    public GameEditor() {
        this(new Game());
    }

    // endregion

    @FXML
    private void initialize() {
        textField_name.setText("New Game");
        this.textProperty().bind(textField_name.textProperty());

        gridPane_Game.setGridLinesVisible(true);

        ContextMenu contextMenu = new ContextMenu();
        MenuItem newColumn = new MenuItem("New Column");
        contextMenu.getItems().add(newColumn);

        gridPane_Game.setOnContextMenuRequested(contextMenuEvent -> contextMenu.show(gridPane_Game, contextMenuEvent.getScreenX(), contextMenuEvent.getScreenY()));
        gridPane_Game.setOnMouseClicked(mouseEvent -> contextMenu.hide());
    }

    private Node loadContent() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/GameEdit.fxml"));
            loader.setController(this);
            return loader.load();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Node[] visualizeCategory(ICategory category) {
        Node[] nodes = new Node[category.getQuestions().size() + 1];

        TextField categoryName = new TextField(category.getName());
        categoryName.setPromptText("Category Name");
        nodes[0] = categoryName;

        int index = 0;
        for (IQuestion question : category.getQuestions()) {
            nodes[++index] = visualizeQuestion(question);
        }

        return nodes;
    }

    private void visualizeGame() {
        gridPane_Game.getRowConstraints().clear();
        gridPane_Game.getColumnConstraints().clear();
        for (int row = 0; row <= game.getQuestionAmount(); row++) {
            RowConstraints rowConstraints = new RowConstraints();
            rowConstraints.setVgrow(Priority.ALWAYS);
            gridPane_Game.getRowConstraints().add(rowConstraints);
        }

        int index = 0;
        for (ICategory category : game.getCategories()) {
            ColumnConstraints columnConstraints = new ColumnConstraints();
            columnConstraints.setHgrow(Priority.ALWAYS);
            columnConstraints.setHalignment(HPos.CENTER);
            gridPane_Game.getColumnConstraints().add(columnConstraints);

            gridPane_Game.addColumn(index++, visualizeCategory(category));
        }
    }

    private Node visualizeQuestion(IQuestion question) {
        return new Label(String.valueOf(question.getValue()));
    }

}
