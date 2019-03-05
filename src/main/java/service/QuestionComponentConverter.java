package service;

import data.interfaces.IQuestionComponent;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;
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
            pane.getRowConstraints().add(rc);
        }
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
    @Component(types = {"Title", "Image", "ButtonGrid", "ImageGrid", "Video", "Text"})
    public Node convertQuestionComponent(IQuestionComponent component) {
        var data = component.getComponentData().getData();
        Node node = null;

        if (component.getType().equalsIgnoreCase("Title")) {
            if (data instanceof String) {
                Label label = new Label((String) data);
                VBox titleBox = new VBox(label);
                titleBox.setAlignment(Pos.CENTER);
                node = titleBox;
            }
        }
        else if (component.getType().equalsIgnoreCase("Image")) {
            if (data instanceof String) {
                ImageView view = new ImageView((String) data);
                VBox imageBox = new VBox(view);
                imageBox.setAlignment(Pos.CENTER);

                view.setPreserveRatio(true);
                view.setSmooth(true);
                view.fitHeightProperty().bind(imageBox.heightProperty());
                view.fitWidthProperty().bind(imageBox.widthProperty());

                node = imageBox;
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

                        ImageView view = new ImageView(castData[i * rows + j]);
                        Button button = new Button();
                        button.setPrefHeight(300);
                        // TODO complex Algo for set Width and Height

                        view.setFitHeight(300);
                        view.fitHeightProperty().bind(button.heightProperty());
                        view.fitWidthProperty().bind(button.widthProperty());

                        view.setPreserveRatio(true);
                        view.setSmooth(true);
                        view.setCache(true);
                        button.setId(castData[i * rows + j]);
                        button.setGraphic(view);
                        setAnchors(button, 10);
                        imageGrid.add(new AnchorPane(button), i, j);
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

                VBox box = new VBox(view);

                view.setPreserveRatio(true);
                player.setAutoPlay(true);
                view.fitHeightProperty().bind(box.heightProperty());
                view.fitWidthProperty().bind(box.widthProperty());

                // TODO play/pause and replay button

                node = box;
            }
        }
        else if (component.getType().equalsIgnoreCase("Text")) {
            if (data instanceof String) {
                Text text = new Text((String) data);

                node = text;
            }
        }

        if (node == null) {
            node = new Label("404 Not Found");
        }

        return node;
    }

}
