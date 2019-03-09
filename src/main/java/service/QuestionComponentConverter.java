package service;

import data.interfaces.IQuestionComponent;
import data.interfaces.IQuestionData;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyDoubleProperty;
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

    //TODO height property als parameter angeben. Componenten können darauf binden mit gesetzer Formel

    /**
     * convert default Question Components into JavaFX Nodes.
     *
     * @param component Question Component
     * @return JavaFX Node
     */
    @Override
    @Default
    @Component(types = {"Title", "Image", "ButtonGrid", "ImageGrid", "Video", "Text"})
    public Node convertQuestionComponent(IQuestionComponent component, IQuestionData<?>... args) {
        var data = component.getComponentData("data").getData();
        Node node = null;

        if (component.getType().equalsIgnoreCase("Title")) {
            if (data instanceof String) {
                Label label = new Label((String) data);
                label.setWrapText(true);
                label.getStyleClass().add("title");

                VBox titleBox = new VBox(label);
                titleBox.setAlignment(Pos.CENTER);
                titleBox.getStyleClass().add("title_background");

                node = titleBox;
            }
        }
        else if (component.getType().equalsIgnoreCase("Image")) {
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

                node = mediaHolder;
            }
        }
        else if (component.getType().equalsIgnoreCase("ButtonGrid")) {
            if (data instanceof String[]) {
                String[] castData = (String[]) data;
                // TODO make Bounds editable
                if (castData.length > 20 || castData.length < 2) {
                    node = new Label("IndexOutOfBounsException");
                    return node;
                }

                GridPane buttonGrid = new GridPane();
                Collections.shuffle(Arrays.asList(castData));

                int rows = (castData.length - 2) / 7 + 2;
                int columns = (castData.length + (rows - 1)) / rows;

                initGridPane(buttonGrid, columns, rows);

                for (int i = 0; i < columns; i++) {
                    for (int j = 0; j < rows; j++) {
                        if (i * rows + j >= castData.length) {
                            break;
                        }

                        Button button = new Button(castData[i * rows + j]);
                        button.setId(castData[i * rows + j]);
                        setAnchors(button, 20);
                        buttonGrid.add(new AnchorPane(button), i, j);
                    }
                }

                node = buttonGrid;
            }
        }
        else if (component.getType().equalsIgnoreCase("ImageGrid")) {
            if (data instanceof String[]) {
                String[] castData = (String[]) data;
                // TODO make Bounds editable
                if (castData.length > 9 || castData.length < 2) {
                    node = new Label("IndexOutOfBounsException");
                    return node;
                }

                GridPane imageGrid = new GridPane();
                Collections.shuffle(Arrays.asList(castData));

                int rows = (castData.length - 2) / 5 + 2;
                int columns = (castData.length + (rows - 1)) / rows;

                initGridPane(imageGrid, columns, rows);

                for (int i = 0; i < columns; i++) {
                    for (int j = 0; j < rows; j++) {
                        if (i * rows + j >= castData.length) {
                            break;
                        }
                        ImageView imageView = new ImageView(castData[i * rows + j]);

                        Region mediaHolder = putMediaInMediaHolder(imageView);

                        imageView.fitWidthProperty().bind(mediaHolder.widthProperty());
                        imageView.fitHeightProperty().bind(mediaHolder.heightProperty());

                        Boolean preserveRatio = true;
                        if (component.containsComponentData("preserveRatio")) {
                            preserveRatio = (Boolean) component.getComponentData("preserveRatio").getData();
                        }

                        imageView.setPreserveRatio(preserveRatio);
                        imageView.setSmooth(true);

                        imageGrid.add(mediaHolder, i, j);
                    }
                }

                node = imageGrid;
            }
        }
        else if (component.getType().equalsIgnoreCase("Video")) {
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
//                view.fitHeightProperty().bind(((ReadOnlyDoubleProperty) getParameterData("heightProperty", args).getData()).multiply((Double) component.getComponentData("height").getData() / 100D));
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

                node = mediaHolder;
            }
        }
        else if (component.getType().equalsIgnoreCase("Text")) {
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

                node = pane;
            }
        }

        if (node == null) {
            node = new Label("404 Not Found");
        }
        else {
            if (component.containsComponentData("height")) {
                ((Region) node).minHeightProperty().bind(((ReadOnlyDoubleProperty) getParameterData("heightProperty", args).getData()).multiply(Double.valueOf((String) component.getComponentData("height").getData()) / 100D));
            }
        }

        return node;
    }

}
