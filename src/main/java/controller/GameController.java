package controller;

import data.Question;
import data.QuestionComponent;
import data.QuestionData;
import data.QuestionLayout;
import data.interfaces.*;
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
public class GameController {

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

        //

        IQuestionData<Double> height = new QuestionData<>("height", 10D);

        IQuestionData<String> titleData = new QuestionData<>("data", "Das ist der Titel");

        IQuestionComponent title = new QuestionComponent("Title", Arrays.asList(titleData));

        IQuestionData<String> textData = new QuestionData<>("data", "Lorem ipsum dolor sit amet, " +
                "consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam " +
                "erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, " +
                "no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr");
        IQuestionData<String> width = new QuestionData<>("width", "80%");
        height = new QuestionData<>("height", 10D);
        IQuestionComponent text = new QuestionComponent("Text", Arrays.asList(textData, width, height));

        IQuestionData<String> videoData = new QuestionData<>("data");
        videoData.setData(new File("C:\\Users\\Johannes\\Videos\\Filme.mp4").toURI().toString());

//        IQuestionData<Boolean> videoRatio = new QuestionData<>("preserveRatio", false);

        height = new QuestionData<>("height", 80D);
        IQuestionComponent video = new QuestionComponent("Video", Arrays.asList(videoData));

        IQuestionLayout layout = new QuestionLayout(Arrays.asList(title, text, video));

        IQuestion question = new Question(layout);

        BorderPane pane = new BorderPane(Global.questionConverter.convertQuestion(question));
//        BorderPane pane = new BorderPane(Global.converter.convertQuestionComponent(video));
//        pane.setStyle("-fx-background-color: yellow;");

        //

        stage.setScene(new Scene(pane));
        stage.show();
        stage.setMaximized(true);
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

}
