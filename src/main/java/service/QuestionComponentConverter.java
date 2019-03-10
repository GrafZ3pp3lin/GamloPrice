package service;

import data.interfaces.IQuestionComponent;
import data.interfaces.IQuestionData;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.geometry.*;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.TextAlignment;
import service.interfaces.IQuestionComponentConverter;

import java.util.Arrays;
import java.util.Collections;

/**
 * Component Converter for Default Components
 */
public class QuestionComponentConverter implements IQuestionComponentConverter {

    /*
    ComponentData:
    - data
    - height
    - width
    - preserveRatio
    - autoPlay
    - size
    - correctText
    - wrongText
    - heightProperty
    - fitHeight
    - oneLine
     */

    /**
     * set Space between Control edge of AnchorPane
     *
     * @param node   Control in an AnchorPane
     * @param margin Space to the edge of the AnchorPane
     */
    public static void setAnchors(Node node, double margin) {
        AnchorPane.setBottomAnchor(node, margin);
        AnchorPane.setLeftAnchor(node, margin);
        AnchorPane.setRightAnchor(node, margin);
        AnchorPane.setTopAnchor(node, margin);
    }

    private void initGridPane(GridPane pane, int columns, int rows) {
        pane.getColumnConstraints().clear();
        pane.getRowConstraints().clear();

        for (int i = 0; i < columns; i++) {
            ColumnConstraints cc = new ColumnConstraints();
            cc.setPercentWidth(100D / columns);
            pane.getColumnConstraints().add(cc);
        }

        for (int i = 0; i < rows; i++) {
            RowConstraints rc = new RowConstraints();
            rc.setVgrow(Priority.ALWAYS);
            rc.setPercentHeight(100D / rows);
            pane.getRowConstraints().add(rc);
        }
    }

    /**
     * Way too easy to describe
     *
     * @param media media...
     * @return MediaHolder...
     */
    private Region putMediaInMediaHolder(Node media) {
        StackPane mediaHolder = new StackPane(media);

        ScrollPane scrollPane = new ScrollPane(mediaHolder);
        scrollPane.getStyleClass().add("background");

        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        mediaHolder.minWidthProperty().bind(Bindings.createDoubleBinding(() ->
                scrollPane.getViewportBounds().getWidth(), scrollPane.viewportBoundsProperty()));
        mediaHolder.minHeightProperty().bind(Bindings.createDoubleBinding(() ->
                scrollPane.getViewportBounds().getHeight(), scrollPane.viewportBoundsProperty()));

        return scrollPane;
    }

    private IQuestionData<?> getParameterData(String name, IQuestionData<?>... args) {
        for (IQuestionData<?> data : args) {
            if (data.getName().equalsIgnoreCase(name)) {
                return data;
            }
        }
        return null;
    }

    private Object getComponentData(IQuestionComponent component) {
        if (component.containsComponentData("data")) {
            return component.getComponentData("data").getData();
        }
        return null;
    }

    /*
    Grids:
    size: column x row
    2: 2 x 1
    3: 2 x 2
    4: 2 x 2
    5: 2 x 3
    6: 2 x 3
    7: 2 x 4
    8: 2 x 4
    9: 3 x 3
    10: 3 x 4
    11: 3 x 4
    12: 3 x 4
    13: 3 x 5
    14: 3 x 5
    15: 3 x 5
    16: 4 x 4
    17: 4 x 5
    18: 4 x 5
    19: 4 x 5
    20: 4 x 5
     */

    /**
     * convert default Question Components into JavaFX Nodes.
     *
     * @param component Question Component
     * @return JavaFX Node
     */
    @Override
    @Default
    @Component(types = {"Title", "Image", "ButtonGrid", "ImageGrid", "Video", "Text", "ControlButtons", "Result"})
    public Node convertQuestionComponent(IQuestionComponent component, IQuestionData<?>... args) {
        Node node = null;

        switch (component.getType().toLowerCase()) {
            case "title":
                node = convertTitle(component);
                break;
            case "image":
                node = convertImage(component);
                break;
            case "buttongrid":
                node = convertButtonGrid(component);
                break;
            case "imagegrid":
                node = convertImageGrid(component);
                break;
            case "video":
                node = convertVideo(component);
                break;
            case "text":
                node = convertText(component);
                break;
            case "controlbuttons":
                node = convertControlButtons(component);
                break;
            case "result":
                node = convertResult(component, args);
                break;
            default:
                node = convertDefaultNode();
                break;
        }

        if (node == null) {
            node = convertDefaultNode();
        }
        else {
            if (component.containsComponentData("height")) {
                ((Region) node).minHeightProperty().bind(((ReadOnlyDoubleProperty) getParameterData("heightProperty", args).getData()).multiply(Double.valueOf((String) component.getComponentData("height").getData()) / 100D));
            }
        }

        return node;
    }

    private Node convertTitle(IQuestionComponent component) {
        var data = getComponentData(component);
        if (data instanceof String) {
            Label label = new Label((String) data);
            label.setWrapText(true);
            label.getStyleClass().add("title");

            VBox titleBox = new VBox(label);
            titleBox.setAlignment(Pos.CENTER);
            titleBox.getStyleClass().add("title_background");

            return titleBox;
        }
        return null;
    }

    private Node convertImage(IQuestionComponent component) {
        var data = getComponentData(component);
        if (data instanceof String) {
            ImageView view = new ImageView((String) data);

            Region mediaHolder = putMediaInMediaHolder(view);

            Boolean preserveRatio = true;
            if (component.containsComponentData("preserveRatio")) {
                preserveRatio = (Boolean) component.getComponentData("preserveRatio").getData();
            }

            view.setPreserveRatio(preserveRatio);
            view.setSmooth(true);

            view.fitHeightProperty().bind(mediaHolder.heightProperty());
            view.fitWidthProperty().bind(mediaHolder.widthProperty());

            return mediaHolder;
        }
        return null;
    }

    private Node convertButtonGrid(IQuestionComponent component) {
        var data = getComponentData(component);
        if (data instanceof String[]) {
            String[] castData = (String[]) data;
            // TODO make Bounds editable
            if (castData.length > 20 || castData.length < 2) {
                var label = new Label("IndexOutOfBounsException");
                label.setTextAlignment(TextAlignment.CENTER);
                return label;
            }

            GridPane buttonGrid = new GridPane();
            Collections.shuffle(Arrays.asList(castData));

            int columns = (castData.length - 2) / 7 + 2;
            int rows = (castData.length + (columns - 1)) / columns;

            initGridPane(buttonGrid, columns, rows);

            for (int row = 0; row < rows; row++) {
                for (int column = 0; column < columns; column++) {
                    if (row * columns + column >= castData.length) {
                        break;
                    }

                    Button button = new Button(castData[row * columns + column]);
                    button.setId(castData[row * columns + column]);
                    setAnchors(button, 20);
                    buttonGrid.add(new AnchorPane(button), column, row);
                }
            }

            return buttonGrid;
        }
        return null;
    }

    private Node convertImageGrid(IQuestionComponent component) {
        var data = getComponentData(component);
        if (data instanceof String[]) {
            String[] castData = (String[]) data;
            // TODO make Bounds editable
            if (castData.length > 9 || castData.length < 2) {
                var label = new Label("IndexOutOfBounsException");
                label.setTextAlignment(TextAlignment.CENTER);
                return label;
            }

            GridPane imageGrid = new GridPane();
            Collections.shuffle(Arrays.asList(castData));

            int columns = (castData.length - 2) / 5 + 2;
            int rows = (castData.length + (columns - 1)) / columns;

            initGridPane(imageGrid, columns, rows);

            for (int row = 0; row < rows; row++) {
                for (int column = 0; column < columns; column++) {
                    if (row * columns + column >= castData.length) {
                        break;
                    }
                    ImageView imageView = new ImageView(castData[row * columns + column]);

                    Region mediaHolder = putMediaInMediaHolder(imageView);

                    imageView.fitWidthProperty().bind(mediaHolder.widthProperty());
                    imageView.fitHeightProperty().bind(mediaHolder.heightProperty());

                    Boolean preserveRatio = true;
                    if (component.containsComponentData("preserveRatio")) {
                        preserveRatio = (Boolean) component.getComponentData("preserveRatio").getData();
                    }

                    imageView.setPreserveRatio(preserveRatio);
                    imageView.setSmooth(true);

                    imageGrid.add(mediaHolder, column, row);
                }
            }

            return imageGrid;
        }
        return null;
    }

    private Node convertVideo(IQuestionComponent component) {
        var data = getComponentData(component);
        if (data instanceof String) {
            Media media = new Media((String) data);
            MediaPlayer player = new MediaPlayer(media);
            MediaView view = new MediaView(player);

            view.setOnMouseClicked(mouseEvent -> {
                if (player.getStatus() == MediaPlayer.Status.PLAYING) {
                    player.pause();
                }
                else {
                    player.play();
                }
            });

            view.setOnContextMenuRequested(mouseEvent -> player.stop());

            Region mediaHolder = putMediaInMediaHolder(view);

            view.fitHeightProperty().bind(mediaHolder.heightProperty());
            view.fitWidthProperty().bind(mediaHolder.widthProperty());

            Boolean preserveRatio = true;
            if (component.containsComponentData("preserveRatio")) {
                preserveRatio = (Boolean) component.getComponentData("preserveRatio").getData();
            }

            Boolean autoPlay = false;
            if (component.containsComponentData("autoPlay")) {
                autoPlay = (Boolean) component.getComponentData("autoPlay").getData();
            }

            view.setPreserveRatio(preserveRatio);
            view.setSmooth(true);
            player.setAutoPlay(autoPlay);

            return mediaHolder;
        }
        return null;
    }

    private Node convertText(IQuestionComponent component) {
        var data = getComponentData(component);
        if (data instanceof String) {
            Label text = new Label((String) data);
            text.setTextAlignment(TextAlignment.CENTER);
            text.setWrapText(true);

            StackPane pane = new StackPane(text);
            pane.minHeightProperty().bind(text.heightProperty().multiply(1.2));

            if (component.containsComponentData("width")) {
                String widthString = (String) component.getComponentData("width").getData();
                boolean percentage = false;
                if (widthString.endsWith("%")) {
                    widthString = widthString.substring(0, widthString.length() - 1);
                    percentage = true;
                }

                Integer value = Integer.valueOf(widthString);

                if (percentage) {
                    text.maxWidthProperty().bind(pane.widthProperty().multiply(value / 100D));
                }
                else {
                    text.setMaxWidth(value);
                }
            }

            return pane;
        }
        return null;
    }

    private Node convertControlButtons(IQuestionComponent component) {
        HBox box = new HBox();
        box.setAlignment(Pos.CENTER_RIGHT);
        box.setPadding(new Insets(10));
        box.setSpacing(10);

        Button returnButton = new Button();
        returnButton.getStyleClass().add("return_Button");
//        ImageView returnView = new ImageView("/images/return.png");
//        returnView.setPreserveRatio(true);
//        returnButton.setGraphic(returnView);
        box.getChildren().add(returnButton);

        Button continueButton = new Button();
        continueButton.getStyleClass().add("continue_Button");
//        ImageView continueView = new ImageView("/images/continue.png");
//        continueView.setPreserveRatio(true);
//        continueButton.setGraphic(continueView);
        box.getChildren().add(continueButton);

        return box;
    }

    private Node convertResult(IQuestionComponent component, IQuestionData<?>... args) {
        IQuestionData<?> correct = getParameterData("correct", args);
        if (correct != null) {
            String src = null;
            String content = null;
            if ((Boolean) correct.getData()) {
                src = "/images/correct.png";
                content = "Richtig!";
                if (component.containsComponentData("correctText")) {
                    content = (String) component.getComponentData("correctText").getData();
                }
            }
            else {
                src = "/images/wrong.png";
                content = "Falsch!";
                if (component.containsComponentData("wrongText")) {
                    content = (String) component.getComponentData("wrongText").getData();
                }
            }

            FlowPane flowPane = new FlowPane(Orientation.VERTICAL);
            flowPane.setAlignment(Pos.CENTER);
            flowPane.setColumnHalignment(HPos.CENTER);
            flowPane.setHgap(20);
            flowPane.setVgap(20);

            if (component.containsComponentData("oneLine")) {
                if ((Boolean) component.getComponentData("oneLine").getData()) {
                    flowPane.setOrientation(Orientation.HORIZONTAL);
                }
            }

            Label label = new Label(content);
            label.setStyle("-fx-font-size: 100;");
            flowPane.getChildren().add(label);

            ImageView imageView = new ImageView(src);
            imageView.setPreserveRatio(true);

            if (component.containsComponentData("fitHeight")) {
                double size = (Double) component.getComponentData("fitHeight").getData();
                label.setStyle("-fx-font-size: " + size + ";");
                imageView.setFitHeight(size);
            }

            flowPane.getChildren().add(imageView);

            return flowPane;
        }
        return null;
    }

    private Node convertDefaultNode() {
        var label = new Label("404 Not Found");
        label.setTextAlignment(TextAlignment.CENTER);
        return label;
    }

}
