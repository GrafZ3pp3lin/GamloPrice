package controller;

import data.QuestionComponent;
import data.QuestionData;
import data.interfaces.IQuestionComponent;
import data.interfaces.IQuestionData;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;

public class QuestionEditor {

    // region QuestionEdit
    // TODO Event on close Stage to get the Question
    public Event FinishEvent;
    @FXML
    private TextField textField_value;
    @FXML
    private ListView listView_Components;
    @FXML
    private Button button_addComponent;
    @FXML
    private Button button_addComponentData;
    @FXML
    private Button button_OKComponent;
    @FXML
    private TextField textField_type;
    @FXML
    private TextField textField_name;
    @FXML
    private GridPane gridPane_Components;
    @FXML
    private AnchorPane anchorPane_Component;
    @FXML
    private ScrollPane scrollPane_data;
    @FXML
    private Button button_cancel;

    // endregion
    @FXML
    private Button button_OK;
    private Stage questionStage;
    private boolean newComponent;

    public QuestionEditor() {
//        questionStage = new Stage(StageStyle.UNDECORATED);
        questionStage = new Stage();
        questionStage.setHeight(720);
        questionStage.setWidth(1280);
        loadQuestionStage();

        FinishEvent = new Event(Event.ANY);
    }

    private void addComponent(ActionEvent actionEvent) {
        resetComponent();
        addGridRow(actionEvent);
        newComponent = true;
        anchorPane_Component.setVisible(true);
    }

    private void addGridRow(ActionEvent actionEvent) {
        addGridRow("", "");
    }

    private void addGridRow(String name, String value) {
        TextField field_name = new TextField(name);
        field_name.setPromptText("Name");
        field_name.setPadding(new Insets(10));

        TextField field_value = new TextField(value);
        field_value.setPromptText("Value");
        field_value.setPadding(new Insets(10));

        Button delete = new Button("X");
        delete.getStyleClass().add("no_button");

        gridPane_Components.addRow(gridPane_Components.getRowCount(), field_name, field_value, delete);
    }

    private void duplicateText(KeyEvent keyEvent) {
        textField_name.setText(((TextField) keyEvent.getSource()).getText() + keyEvent.getText());
    }

    private void finishComponent(ActionEvent actionEvent) {
        setData();
        anchorPane_Component.setVisible(false);
    }

    @FXML
    private void initialize() {
        button_OK.setOnAction(this::close);

        button_cancel.setOnAction(this::close);

        button_addComponent.setShape(new Circle(10));
        button_addComponent.setMinHeight(20);
        button_addComponent.setMinWidth(20);
        button_addComponent.setOnAction(this::addComponent);

        button_addComponentData.setOnAction(this::addGridRow);

        button_OKComponent.setOnAction(this::finishComponent);

        listView_Components.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        listView_Components.setOnMouseClicked(this::openComponent);

        anchorPane_Component.setVisible(false);

        textField_type.setOnAction(this::finishComponent);
        textField_type.setOnKeyPressed(this::duplicateText);

        textField_name.setOnAction(this::finishComponent);

        gridPane_Components.setPadding(new Insets(10));
        gridPane_Components.minWidthProperty().bind(scrollPane_data.widthProperty());
    }

    private void loadQuestionStage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/QuestionEdit.fxml"));
            loader.setController(this);
            Parent layout = loader.load();
            questionStage.setScene(new Scene(layout));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void openComponent(MouseEvent mouseEvent) {
        if (listView_Components.getSelectionModel().getSelectedItem() != null) {
            IQuestionComponent component = (IQuestionComponent) listView_Components.getSelectionModel().getSelectedItem();
            resetComponent();
            textField_type.setText(component.getType());
            textField_name.setText(component.getName());
            for (IQuestionData<?> data : component.getComponentData()) {
                addGridRow(data.getName(), (String) data.getData());
            }
            newComponent = false;
            anchorPane_Component.setVisible(true);
        }
    }

    private void resetComponent() {
        textField_type.clear();
        textField_name.clear();
        gridPane_Components.getChildren().clear();
    }

    private void setData() {
        if (!textField_type.getText().isBlank()) {
            IQuestionComponent component;
            if (newComponent) {
                component = new QuestionComponent(textField_type.getText());
                listView_Components.getItems().add(component);
            }
            else {
                component = ((IQuestionComponent) listView_Components.getSelectionModel().getSelectedItem());
            }

            component.setType(textField_type.getText());
            component.setName(textField_name.getText());
            component.getComponentData().clear();
            for (int row = 0; row < gridPane_Components.getRowCount(); row++) {
                String name = ((TextField) gridPane_Components.getChildren().get(row * gridPane_Components.getColumnCount())).getText();
                String value = ((TextField) gridPane_Components.getChildren().get(row * gridPane_Components.getColumnCount() + 1)).getText();

                IQuestionData<String> data = new QuestionData<>(name, value);
                component.addComponentData(data);
            }

            listView_Components.refresh();
        }
    }

    public void close(ActionEvent actionEvent) {
        questionStage.close();
    }

    public void openQuestionPane() {
        resetPane();
        questionStage.showAndWait();
    }

    public void resetPane() {
        listView_Components.getItems().clear();
        textField_value.clear();
        resetComponent();
    }

}
