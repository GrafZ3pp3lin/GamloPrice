package service;

import data.interfaces.IQuestionComponent;
import data.interfaces.IQuestionData;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
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
    - columns
    - fontSize
    - dummy
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

    private Node convertButtonGrid(IQuestionComponent component, IQuestionData<?>... args) {
        var data = getComponentData(component, "data", null);
        if (data instanceof String[]) {
            String[] castData = (String[]) data;
            // TODO make Bounds editable
            if (castData.length > 20 || castData.length < 2) {
                var label = new Label("IndexOutOfBounsException");
                label.setTextAlignment(TextAlignment.CENTER);
                return label;
            }

            GridPane buttonGrid = new GridPane();
            if ((Boolean) getParameterData("dummy", false, args)) {
                Collections.shuffle(Arrays.asList(castData));
            }

            Integer columns = (Integer) getComponentData(component, "columns", (castData.length - 2) / 7 + 2);
            int rows = (castData.length + (columns - 1)) / columns;

            initGridPane(buttonGrid, columns, rows);

            Double fontSize = (Double) getComponentData(component, "fontSize", null);

            for (int row = 0; row < rows; row++) {
                for (int column = 0; column < columns; column++) {
                    if (row * columns + column >= castData.length) {
                        break;
                    }

                    Button button = new Button(castData[row * columns + column]);
                    button.setId(castData[row * columns + column]);
                    setAnchors(button, 20);

                    if (fontSize != null) {
                        button.setStyle("-fx-font-size: " + fontSize + ";");
                    }

                    buttonGrid.add(new AnchorPane(button), column, row);
                }
            }

            return buttonGrid;
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
        ImageView returnView = new ImageView("/images/return.png");
        returnView.setPreserveRatio(true);
        returnButton.setGraphic(returnView);
        box.getChildren().add(returnButton);

        Button continueButton = new Button();
        continueButton.getStyleClass().add("continue_Button");
        ImageView continueView = new ImageView("/images/continue.png");
        continueView.setPreserveRatio(true);
        continueButton.setGraphic(continueView);
        box.getChildren().add(continueButton);

        return box;
    }

    private Node convertDefaultNode() {
        var label = new Label("404 Not Found");
        label.setTextAlignment(TextAlignment.CENTER);
        return label;
    }

    private Node convertImage(IQuestionComponent component) {
        var data = getComponentData(component, "data", null);
        if (data instanceof String) {
            ImageView view = new ImageView((String) data);

            Region mediaHolder = putMediaInMediaHolder(view);

            Boolean preserveRatio = (Boolean) getComponentData(component, "preserveRatio", true);

            view.setPreserveRatio(preserveRatio);
            view.setSmooth(true);

            view.fitHeightProperty().bind(mediaHolder.heightProperty());
            view.fitWidthProperty().bind(mediaHolder.widthProperty());

            return mediaHolder;
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

    private Node convertImageGrid(IQuestionComponent component, IQuestionData<?>... args) {
        var data = getComponentData(component, "data", null);
        if (data instanceof String[]) {
            String[] castData = (String[]) data;
            // TODO make Bounds editable
            if (castData.length > 9 || castData.length < 2) {
                var label = new Label("IndexOutOfBounsException");
                label.setTextAlignment(TextAlignment.CENTER);
                return label;
            }

            GridPane imageGrid = new GridPane();
            if ((Boolean) getParameterData("dummy", false, args)) {
                Collections.shuffle(Arrays.asList(castData));
            }

            Integer columns = (Integer) getComponentData(component, "columns", (castData.length - 2) / 5 + 2);
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

                    Boolean preserveRatio = (Boolean) getComponentData(component, "preserveRatio", true);

                    imageView.setPreserveRatio(preserveRatio);
                    imageView.setSmooth(true);

                    imageGrid.add(mediaHolder, column, row);
                }
            }

            return imageGrid;
        }
        return null;
    }

    private Node convertResult(IQuestionComponent component, IQuestionData<?>... args) {
        Boolean correct = (Boolean) getParameterData("correct", null, args);
        if (correct != null) {
            String src;
            String content;
            if (correct) {
                src = "/images/correct.png";
                content = (String) getComponentData(component, "correctText", "Richtig!");
            }
            else {
                src = "/images/wrong.png";
                content = (String) getComponentData(component, "wrongText", "Falsch!");
            }

            FlowPane flowPane = new FlowPane(Orientation.VERTICAL);
            flowPane.setAlignment(Pos.CENTER);
            flowPane.setColumnHalignment(HPos.CENTER);
            flowPane.setHgap(20);
            flowPane.setVgap(20);

            if ((Boolean) getComponentData(component, "oneLine", false)) {
                flowPane.setOrientation(Orientation.HORIZONTAL);
            }

            Label label = new Label(content);
            flowPane.getChildren().add(label);

            Double fontSize = (Double) getComponentData(component, "fontSize", null);
            if (fontSize != null) {
                label.setStyle("-fx-font-size: " + fontSize + ";");
            }
            else {
                label.setStyle("-fx-font-size: 100;");
            }

            ImageView imageView = new ImageView(src);
            imageView.setPreserveRatio(true);

            Double fitHeight = (Double) getComponentData(component, "fitHeight", null);
            if (fitHeight != null) {
                imageView.setFitHeight(fitHeight);
            }

            flowPane.getChildren().add(imageView);

            return flowPane;
        }
        return null;
    }

    private Node convertText(IQuestionComponent component) {
        var data = getComponentData(component, "data", null);
        if (data instanceof String) {
            Label text = new Label((String) data);
            text.setTextAlignment(TextAlignment.CENTER);
            text.setWrapText(true);

            StackPane pane = new StackPane(text);
            pane.minHeightProperty().bind(text.heightProperty().multiply(1.2));

            Double fontSize = (Double) getComponentData(component, "fontSize", null);
            if (fontSize != null) {
                text.setStyle("-fx-font-size: " + fontSize + ";");
            }

            String widthString = (String) getComponentData(component, "width", null);
            if (widthString != null) {
                boolean percentage = false;
                if (widthString.endsWith("%")) {
                    widthString = widthString.substring(0, widthString.length() - 1);
                    percentage = true;
                }

                int value = Integer.valueOf(widthString);

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

    private Node convertTitle(IQuestionComponent component) {
        var data = getComponentData(component, "data", null);
        if (data instanceof String) {
            Label label = new Label((String) data);
            label.setWrapText(true);
            label.getStyleClass().add("title");

            Double fontSize = (Double) getComponentData(component, "fontSize", null);
            if (fontSize != null) {
                label.setStyle("-fx-font-size: " + fontSize + ";");
            }

            VBox titleBox = new VBox(label);
            titleBox.setAlignment(Pos.CENTER);
            titleBox.getStyleClass().add("title_background");

            return titleBox;
        }
        return null;
    }

    private Node convertVideo(IQuestionComponent component) {
        var data = getComponentData(component, "data", null);
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

            Boolean preserveRatio = (Boolean) getComponentData(component, "preserveRatio", true);
            Boolean autoPlay = (Boolean) getComponentData(component, "autoPlay", false);

            view.setPreserveRatio(preserveRatio);
            view.setSmooth(true);
            player.setAutoPlay(autoPlay);

            return mediaHolder;
        }
        return null;
    }

    /**
     * get Data from Component or get defaultValue
     *
     * @param component    Question Component
     * @param name         Data name
     * @param defaultValue default Value
     * @return data or default value
     */
    private Object getComponentData(IQuestionComponent component, String name, Object defaultValue) {
        if (component.containsComponentData(name)) {
            return component.getComponentData(name).getData();
        }
        return defaultValue;
    }

    /**
     * get Parameter data or default value
     *
     * @param name         Data name
     * @param defaultValue default value
     * @param args         Parameter data
     * @return data or default value
     */
    private Object getParameterData(String name, Object defaultValue, IQuestionData<?>... args) {
        for (IQuestionData<?> data : args) {
            if (data.getName().equalsIgnoreCase(name)) {
                return data.getData();
            }
        }
        return defaultValue;
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
        Node node;

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
            Double height = (Double) getComponentData(component, "height", null);
            if (height != null) {
                ReadOnlyDoubleProperty heightProperty = (ReadOnlyDoubleProperty) getParameterData("heightProperty", null, args);
                if (heightProperty != null) {
                    ((Region) node).minHeightProperty().bind(heightProperty.multiply(height / 100D));
                }
            }
        }

        return node;
    }

}
