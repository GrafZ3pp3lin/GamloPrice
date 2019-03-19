package controller;

import data.Question;
import data.QuestionComponent;
import data.QuestionData;
import data.interfaces.IGame;
import data.interfaces.IQuestion;
import data.interfaces.IQuestionComponent;
import data.interfaces.IQuestionData;
import data.observable.IObserver;
import data.observable.UpdateType;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import service.QuestionComponentConverter;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * Every Game will be started in a separate Stage. This GameController create a new Game Stage and manage it.
 */
public class GameController implements IObserver {

    private IGame Game;

    private Stage stage;
    private Parent selectionPane;
    private BorderPane questionPane;
    private List<Button> buttons;

    // region Constructors

    public GameController(IGame game) {
        Game = game;
    }

    // endregion

    public void setGame(IGame game) {
        this.Game = game;
    }

    /**
     * Open the given Game in a separate Stage
     */
    public void showGame() {
        stage = new Stage();
        stage.setTitle(Game.getName());
        if (selectionPane == null) {
            selectionPane = createSelectionScene();
        }
        if (questionPane == null) {
            questionPane = new BorderPane();
        }

        stage.setScene(new Scene(selectionPane));

//        tempCreateCustomQuestion();

        stage.setWidth(1280);
        stage.setHeight(720);
        stage.setFullScreen(true);
        stage.show();
    }

    private void tempCreateCustomQuestion() {
        // Example Title
        IQuestionData<Boolean> grow = new QuestionData<>("grow", true);
        IQuestionData<Double> height = new QuestionData<>("height", 10D);
        IQuestionData<String> titleData = new QuestionData<>("data", "Das ist der Titel");
        IQuestionComponent title = new QuestionComponent("Title", Arrays.asList(titleData, height));

        // Example Text
        IQuestionData<String> textData = new QuestionData<>("data", "Lorem ipsum dolor sit amet, " +
                "consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam " +
                "erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, " +
                "no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr");
        IQuestionData<String> width = new QuestionData<>("width", "80%");
        height = new QuestionData<>("height", 15D);
        IQuestionComponent text = new QuestionComponent("Text", Arrays.asList(textData, width));

        // Example Video
        IQuestionData<String> videoData = new QuestionData<>("data");
        String path = new File("").toURI().toString();
        videoData.setData(path);

//        IQuestionData<Boolean> videoRatio = new QuestionData<>("preserveRatio", false);

//        height = new QuestionData<>("height", 80D);
        IQuestionComponent video = new QuestionComponent("Image", Arrays.asList(videoData, grow));

        // ButtonGrid
        IQuestionData<String[]> buttonData = new QuestionData<>("data", new String[] {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine"});
        IQuestionComponent buttonGrid = new QuestionComponent("ButtonGrid", Arrays.asList(buttonData, grow));

        // ImageGrid
        IQuestionData<String[]> imageGridData = new QuestionData<>("data", new String[] {path, path, path, path, path, path});
        IQuestionComponent imageGrid = new QuestionComponent("ImageGrid", Arrays.asList(imageGridData, grow));

        // Result
        IQuestionData<Boolean> correct = new QuestionData<>("correct", true);
        IQuestionData<Double> size = new QuestionData<>("fitHeight", 100D);
        IQuestionData<Boolean> oneLine = new QuestionData<>("oneLine", true);
        IQuestionData<String> resultText = new QuestionData<>("correctText", "Ja supi, gelöst!");
        IQuestionComponent result = new QuestionComponent("Result", Arrays.asList(resultText));

        //Control Buttons
        IQuestionComponent controlButtons = new QuestionComponent("ControlButtons");

        IQuestion question = new Question(Arrays.asList(title, text, buttonGrid, controlButtons));

        updateQuestionPane(Global.questionConverter.convertQuestion(question, correct));
    }

    /**
     * create a new Scene with the first Pane on that an Button for each Question exist.
     *
     * @return Scene with Selection Pane
     */
    private Parent createSelectionScene() {
        GridPane buttonGrid = new GridPane();
//        buttonGrid.setPadding(new Insets(50));
        for (int i = 0; i < Game.getCategories().size(); i++) {
            ColumnConstraints cc = new ColumnConstraints();
            cc.setPercentWidth(100.0 / Game.getCategories().size());
            cc.setHgrow(Priority.NEVER);
            buttonGrid.getColumnConstraints().add(cc);
        }

        for (int i = 0; i < Game.getQuestionAmount() + 1; i++) {
            RowConstraints rc = new RowConstraints();
            rc.setVgrow(Priority.ALWAYS);
            buttonGrid.getRowConstraints().add(rc);
        }

        EventHandler<ActionEvent> eh = event -> {
            if (event.getSource() instanceof Button) {
                update(UpdateType.Question, ((Button) event.getSource()).getId());
            }
        };

        buttons = new ArrayList<>();
        for (int column = 0; column < Game.getCategories().size(); column++) {
            Label name = new Label(Game.getCategories().get(column).getName());
            name.setTextAlignment(TextAlignment.CENTER);
            name.setWrapText(true);
            HBox box = new HBox(name);
            box.setAlignment(Pos.CENTER);
            buttonGrid.add(box, column, 0);
            int row = 0;
            for (IQuestion question : Game.getCategories().get(column).getQuestions()) {
                Button b = new Button(String.valueOf(question.getValue()));
                b.setId(question.getId().toString());
                b.setOnAction(eh);
                b.getStyleClass().add("select_Button");
                QuestionComponentConverter.setAnchors(b, 20);
                buttons.add(b);
                buttonGrid.add(new AnchorPane(b), column, ++row);
            }
        }

        // TODO Teams

        buttonGrid.getStylesheets().add("/view/style.css");

        return buttonGrid;
    }

    private void updateQuestionPane(Parent center) {
        questionPane.setCenter(center);
        stage.getScene().setRoot(questionPane);
    }

    private void updateHomePane() {
        stage.getScene().setRoot(selectionPane);
    }

    @Override
    public void update(UpdateType type, String id) {
        //TODO init Arguments
        if (type == UpdateType.Question) {
            IQuestion question = Game.getQuestionById(UUID.fromString(id));
            if (question != null) {
                updateQuestionPane(Global.questionConverter.convertQuestion(question));
            }
        }
        else if (type == UpdateType.HomeScreen) {
            updateHomePane();
        }
    }

    public void setButtonHovered(String id) {
        for (Button button : buttons) {
            if (button.getId().equals(id)) {
                button.setStyle("select_Button:hover");
                return;
            }
        }
    }
}
