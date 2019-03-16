package controller;

import data.QuestionComponent;
import data.QuestionData;
import data.interfaces.IQuestionComponent;
import data.interfaces.IQuestionData;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;

public class QuestionEditor {

    // region QuestionEdit

    @FXML
    private TextField textField_value;

    @FXML
    private ListView listView_Components;

    @FXML
    private Button button_addComponent;

    @FXML
    private Button button_OK;

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

    // endregion

    private Stage questionStage;

    private ObservableList<IQuestionComponent> components;

    public QuestionEditor() {
//        questionStage = new Stage(StageStyle.UNDECORATED);
        questionStage = new Stage();
        questionStage.setHeight(720);
        questionStage.setWidth(1280);
        loadQuestionStage();
        components = listView_Components.getItems();
    }

    @FXML
    private void initialize() {
        button_addComponent.setShape(new Circle(10));
        button_addComponent.setMinHeight(20);
        button_addComponent.setMinWidth(20);
        button_addComponent.setOnAction(event -> addComponent());

        button_OK.setOnAction(actionEvent -> close());

        button_addComponentData.setOnAction(actionEvent -> addGridRow());

        button_OKComponent.setOnAction(actionEvent -> finishComponent());

        listView_Components.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        listView_Components.setOnMouseClicked(mouseEvent -> openComponent());

        anchorPane_Component.setVisible(false);

        textField_type.setOnAction(actionEvent -> setData());
        textField_type.setOnKeyPressed(keyEvent -> duplicateText(keyEvent));

        textField_name.setOnAction(actionEvent -> setData());

        gridPane_Components.setPadding(new Insets(10));
        gridPane_Components.minWidthProperty().bind(scrollPane_data.widthProperty());
    }

    @FXML
    private void addComponent() {
        resetComponent();
        addGridRow();
        anchorPane_Component.setVisible(true);
    }

    private void resetComponent() {
        textField_type.clear();
        textField_name.clear();
        gridPane_Components.getChildren().clear();
    }

    @FXML
    private void setData() {
        if (!textField_type.getText().isBlank()) {
            if (listView_Components.getSelectionModel().getSelectedItem() == null) {
                QuestionComponent component = new QuestionComponent(textField_type.getText());
                components.add(component);
                listView_Components.getSelectionModel().select(component);
            }
            else {
                ((IQuestionComponent) listView_Components.getSelectionModel().getSelectedItem()).setType(textField_type.getText());
            }

            if (listView_Components.getSelectionModel().getSelectedItem() != null) {
                ((IQuestionComponent) listView_Components.getSelectionModel().getSelectedItem()).setName(textField_name.getText());
            }

            for (int row = 0; row < gridPane_Components.getRowCount(); row++) {

                String name = ((TextField) gridPane_Components.getChildren().get(row * 2)).getText();
                String value = ((TextField) gridPane_Components.getChildren().get(row * 2 + 1)).getText();

                IQuestionData<String> data = new QuestionData<>(name, value);
                ((IQuestionComponent) listView_Components.getSelectionModel().getSelectedItem()).addComponentData(data);
            }

        }
    }

    @FXML
    private void finishComponent() {
        setData();
        anchorPane_Component.setVisible(false);
    }

    @FXML
    private void openComponent() {
        if (listView_Components.getSelectionModel().getSelectedItem() != null) {
            IQuestionComponent component = (IQuestionComponent) listView_Components.getSelectionModel().getSelectedItem();
            resetComponent();
            textField_type.setText(component.getType());
            textField_name.setText(component.getName());
            for (IQuestionData<?> data : component.getComponentData()) {
                addGridRow(data.getName(), (String) data.getData());
            }
            anchorPane_Component.setVisible(true);
        }
    }

    @FXML
    private void duplicateText(KeyEvent keyEvent) {
        textField_name.setText(((TextField) keyEvent.getSource()).getText() + keyEvent.getText());
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

    public void openQuestionPane() {
//        resetPane();
        questionStage.showAndWait();
    }

    public void resetPane() {
        listView_Components.getItems().clear();
        resetComponent();
    }

    public void close() {
        questionStage.close();
    }

    private void addGridRow(String name, String value) {
        TextField field_name = new TextField(name);
        field_name.setPromptText("Name");
        field_name.setPadding(new Insets(10));

        TextField field_value = new TextField(value);
        field_value.setPromptText("Value");
        field_value.setPadding(new Insets(10));

        gridPane_Components.addRow(gridPane_Components.getRowCount(), field_name, field_value);
    }

    private void addGridRow() {
        addGridRow("", "");
    }

}
