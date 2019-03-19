package controller;

import data.interfaces.ICategory;
import data.interfaces.IGame;
import data.interfaces.IQuestion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import service.QuestionComponentConverter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

    private ObservableList<IQuestion> queue;
    private List<IQuestion> usedQuestion;

    private IGame game;

    // region Constructors

    public GameEditor(IGame game) {
        super();

        queue = FXCollections.observableArrayList();
        usedQuestion = new ArrayList<>();

        this.setContent(loadContent());

        if (game != null) {
            this.game = game;
            visualizeGame(game);
        }
    }

    public GameEditor() {
        this(null);
    }

    // endregion

    @FXML
    private void initialize() {
        textField_name.setText("New Game");
        this.textProperty().bind(textField_name.textProperty());

        gridPane_Game.setGridLinesVisible(true);

        button_addQuestion.setOnAction(this::addQuestion);

        ContextMenu contextMenu = new ContextMenu();
        MenuItem newColumn = new MenuItem("Add Category");
        newColumn.setOnAction(actionEvent -> addCategory(null));

        contextMenu.getItems().add(newColumn);

        listView_Questions.setItems(queue);

        gridPane_Game.setOnContextMenuRequested(contextMenuEvent -> contextMenu.show(gridPane_Game, contextMenuEvent.getScreenX(), contextMenuEvent.getScreenY()));
        gridPane_Game.setOnMouseClicked(mouseEvent -> contextMenu.hide());
    }

    public IGame createGame() {
        // TODO edit Game with data
        throw new UnsupportedOperationException();
    }

    private void addCategory(ICategory category) {
        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setHgrow(Priority.ALWAYS);
        columnConstraints.setHalignment(HPos.CENTER);
        gridPane_Game.getColumnConstraints().add(columnConstraints);

        gridPane_Game.addColumn(gridPane_Game.getColumnConstraints().size() - 1, visualizeCategory(category));
    }

    private void addQuestion(ActionEvent actionEvent) {
        IQuestion question = Global.questionEditor.openQuestionPane(null);
        queue.add(question);

        listView_Questions.refresh();
    }

    private void editQuestion(ActionEvent actionEvent) {
        if (actionEvent.getSource() instanceof Button && !Global.questionEditor.isOpened()) {
            Button button = (Button) actionEvent.getSource();

            IQuestion question = getQuestion(button.getId());

            question = Global.questionEditor.openQuestionPane(question);

            if (question != null) {
                if (!usedQuestion.contains(question)) {
                    usedQuestion.add(question);
                }
                button.setText(String.valueOf(question.getValue()));
                button.setId(question.getId().toString());
            }
        }
    }

    private IQuestion getQuestion(String id) {
        for (IQuestion question : usedQuestion) {
            if (question.getId().equals(UUID.fromString(id))) {
                return question;
            }
        }
        return null;
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
        Node[] nodes = new Node[gridPane_Game.getRowConstraints().size()];

        AnchorPane textFieldHolder = new AnchorPane();
        TextField categoryName = new TextField();
        if (category != null) {
            categoryName.setText(category.getName());
        }
        QuestionComponentConverter.setAnchors(categoryName, 10D);
        categoryName.setPromptText("Category Name");
        textFieldHolder.getChildren().addAll(categoryName);

        nodes[0] = textFieldHolder;

        for (int index = 1; index < gridPane_Game.getRowConstraints().size(); index++) {
            nodes[index] = visualizeQuestion(category == null ? null : category.getQuestions().size() > index ? category.getQuestions(index) : null);
        }

        return nodes;
    }

    private void visualizeGame(IGame game) {
        gridPane_Game.getRowConstraints().clear();
        gridPane_Game.getColumnConstraints().clear();
        for (int row = 0; row <= game.getQuestionAmount(); row++) {
            RowConstraints rowConstraints = new RowConstraints();
            rowConstraints.setVgrow(Priority.ALWAYS);
            gridPane_Game.getRowConstraints().add(rowConstraints);
        }

        if (game.getCategories().size() == 0) {
            addCategory(null);
        }
        else {
            int index = 0;
            for (ICategory category : game.getCategories()) {
                addCategory(category);
                index++;
            }
        }
    }

    private Node visualizeQuestion(IQuestion question) {
        AnchorPane buttonHolder = new AnchorPane();

        Button button = new Button();
        button.setOnAction(this::editQuestion);
        if (question != null) {
            button.setText(String.valueOf(question.getValue()));
            button.setId(question.getId().toString());
            usedQuestion.add(question);
        }
        QuestionComponentConverter.setAnchors(button, 10D);

        buttonHolder.getChildren().add(button);
        return buttonHolder;
    }

}
