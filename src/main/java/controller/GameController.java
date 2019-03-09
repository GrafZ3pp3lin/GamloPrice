package controller;

import data.Question;
import data.QuestionComponent;
import data.QuestionData;
import data.QuestionLayout;
import data.interfaces.*;
import data.observable.IObserver;
import data.observable.UpdateType;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
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

/**
 * Every Game will be started in a separate Stage. This GameController create a new Game Stage and manage it.
 */
public class GameController implements IObserver {

    private IGame Game;

    private Stage stage;
    private Scene SelectionScene;
    private List<Button> buttons;

    // region Constructors

    public GameController(IGame game) {
        Game = game;
    }

    public GameController() {
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
        if (SelectionScene == null) {
            SelectionScene = createSelectionScene();
        }

        // Example Title
        IQuestionData<Boolean> grow = new QuestionData<>("grow", true);
        IQuestionData<String> height = new QuestionData<>("height", "10");
        IQuestionData<String> titleData = new QuestionData<>("data", "Das ist der Titel");
        IQuestionComponent title = new QuestionComponent("Title", Arrays.asList(titleData, height));

        // Example Text
        IQuestionData<String> textData = new QuestionData<>("data", "Lorem ipsum dolor sit amet, " +
                "consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam " +
                "erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, " +
                "no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr");
        IQuestionData<String> width = new QuestionData<>("width", "80%");
        height = new QuestionData<>("height", "15");
        IQuestionComponent text = new QuestionComponent("Text", Arrays.asList(textData, width));

        // Example Video
        IQuestionData<String> videoData = new QuestionData<>("data");
        String path = new File("").toURI().toString();
        videoData.setData(path);

//        IQuestionData<Boolean> videoRatio = new QuestionData<>("preserveRatio", false);

//        height = new QuestionData<>("height", 80D);
        IQuestionComponent video = new QuestionComponent("Image", Arrays.asList(videoData, grow));

        // ButtonGrid
        IQuestionData<String[]> buttonData = new QuestionData<>("data", new String[] {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten"});
        IQuestionComponent buttonGrid = new QuestionComponent("ButtonGrid", Arrays.asList(buttonData, grow));

        // ImageGrid
        IQuestionData<String[]> imageGridData = new QuestionData<>("data", new String[] {path, path, path, path, path, path});
        IQuestionComponent imageGrid = new QuestionComponent("ImageGrid", Arrays.asList(imageGridData, grow));

        // Layout
        IQuestionLayout layout = new QuestionLayout(Arrays.asList(title, text, buttonGrid));

        IQuestion question = new Question(layout);

        BorderPane pane = new BorderPane(Global.questionConverter.convertQuestion(question));

        stage.setScene(new Scene(pane));
        stage.setWidth(1280);
        stage.setHeight(720);
        stage.setFullScreen(true);
//        stage.setMaximized(true);
        stage.show();
    }

    /**
     * create a new Scene with the first Pane on that an Button for each Question exist.
     *
     * @return Scene with Selection Pane
     */
    private Scene createSelectionScene() {
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
                String[] parts = ((Button) event.getSource()).getId().split(",", 2);
                // TODO show Question
                System.out.println("Event Handler works");
            }
        };

        buttons = new ArrayList<>();
        for (int i = 0; i < Game.getCategories().size(); i++) {
            Label name = new Label(Game.getCategories().get(i).getName());
            name.setTextAlignment(TextAlignment.CENTER);
            name.setWrapText(true);
            HBox box = new HBox(name);
            box.setAlignment(Pos.CENTER);
            buttonGrid.add(box, i, 0);
            for (int j = 0; j < 5; j++) {
                Button b = new Button(((j + 1) * 20) + "");
                b.setId(b.getText() + "," + i);
                b.setOnAction(eh);
                QuestionComponentConverter.setAnchors(b, 20);
                buttons.add(b);
                buttonGrid.add(new AnchorPane(b), i, j + 1);
            }
        }

        // TODO Teams
        return new Scene(buttonGrid);
    }

    @Override
    public void update(UpdateType type, String id) {
        // TODO implement
    }
}
