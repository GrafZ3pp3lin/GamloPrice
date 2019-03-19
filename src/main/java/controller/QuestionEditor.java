package controller;

import data.Question;
import data.QuestionComponent;
import data.QuestionData;
import data.interfaces.IQuestion;
import data.interfaces.IQuestionComponent;
import data.interfaces.IQuestionData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
    private Button button_addComponentData;
    @FXML
    private Button button_cancelComponent;
    @FXML
    private Button button_OKComponent;
    @FXML
    private TextField textField_type;
    @FXML
    private TextField textField_name;
    @FXML
    private GridPane gridPane_Components;
    @FXML
    private ColumnConstraints nameColumn;
    @FXML
    private ColumnConstraints valueColumn;
    @FXML
    private AnchorPane anchorPane_Component;
    @FXML
    private ScrollPane scrollPane_data;
    @FXML
    private Button button_cancel;
    @FXML
    private RadioButton radioButton_question;
    @FXML
    private RadioButton radioButton_answer;

    // endregion
    @FXML
    private Button button_OK;
    private Stage questionStage;
    private boolean newComponent;
    private boolean canceled;

    private ObservableList<IQuestionComponent> questionComponents;
    private ObservableList<IQuestionComponent> answerComponents;

    public QuestionEditor() {
        questionComponents = FXCollections.observableArrayList();
        answerComponents = FXCollections.observableArrayList();

//        questionStage = new Stage(StageStyle.UNDECORATED);
        questionStage = new Stage();
        questionStage.setHeight(720);
        questionStage.setWidth(1280);
        loadQuestionStage();
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

        button_cancelComponent.setOnAction(actionEvent -> anchorPane_Component.setVisible(false));

        radioButton_answer.setOnAction(this::changeLayoutPane);

        radioButton_question.setOnAction(this::changeLayoutPane);

        listView_Components.setItems(questionComponents);
        listView_Components.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        listView_Components.setOnMouseClicked(this::openComponent);

        anchorPane_Component.setVisible(false);

        textField_type.setOnAction(this::finishComponent);
        textField_type.setOnKeyPressed(this::duplicateText);

        textField_name.setOnAction(this::finishComponent);

        gridPane_Components.setPadding(new Insets(10));
        gridPane_Components.prefWidthProperty().bind(scrollPane_data.widthProperty());
        gridPane_Components.maxWidthProperty().bind(gridPane_Components.prefWidthProperty());

        nameColumn.prefWidthProperty().bind(gridPane_Components.widthProperty().multiply(0.35));
        valueColumn.prefWidthProperty().bind(gridPane_Components.widthProperty().multiply(0.65));
    }

    /**
     * Gets the value of the property showing.
     *
     * @return Whether or not this Window is showing (that is, open on the user's system)
     */
    public boolean isOpened() {
        return questionStage.isShowing();
    }

    /**
     * Open the Question Pane and Wait for closing it.
     *
     * @return edited Question
     */
    public IQuestion openQuestionPane(IQuestion question) {
        if (!isOpened()) {
            resetPane();
            if (question != null) {
                initPane(question);
            }
            questionStage.showAndWait();
            return canceled ? null : initQuestion(question);
        }
        return null;
    }

    private void initPane(IQuestion question) {
        textField_value.setText(String.valueOf(question.getValue()));
        questionComponents.addAll(question.getQuestionLayout().getQuestionComponents());
        answerComponents.addAll(question.getAnswerLayout().getQuestionComponents());
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

        Region delete;

        if (gridPane_Components.getChildren().size() > 0) {
            delete = new Button();
            ((Button) delete).setOnAction(this::deleteRow);
            delete.getStyleClass().add("delete_button");
        }
        else {
            delete = new Label();
        }

        gridPane_Components.addRow(gridPane_Components.getRowCount(), field_name, field_value, delete);
    }

    private void changeLayoutPane(ActionEvent actionEvent) {
        if (actionEvent.getSource().equals(radioButton_question)) {
            listView_Components.setItems(questionComponents);
        }
        else {
            listView_Components.setItems(answerComponents);
        }
        anchorPane_Component.setVisible(false);
    }

    private void close(ActionEvent actionEvent) {
        canceled = !actionEvent.getSource().equals(button_OK);
        questionStage.close();
    }

    private void deleteRow(ActionEvent actionEvent) {
        if (gridPane_Components.getChildren().contains(actionEvent.getSource())) {
            int index = gridPane_Components.getChildren().indexOf(actionEvent.getSource());
            int row = index / 3;
            gridPane_Components.getChildren().remove(row * 3, row * 3 + 3);
        }

    }

    private void duplicateText(KeyEvent keyEvent) {
        textField_name.setText(((TextField) keyEvent.getSource()).getText() + keyEvent.getText());
    }

    private void finishComponent(ActionEvent actionEvent) {
        setData();
        anchorPane_Component.setVisible(false);
    }

    private IQuestion initQuestion(IQuestion question) {
        if (question == null) {
            question = new Question();
        }

        if (!textField_value.getText().isBlank()) {
            try {
                int value = Integer.parseInt(textField_value.getText());
                question.setValue(value);
            }
            catch (NumberFormatException e) {
                // TODO cancel and ask Client
            }
        }

        question.getQuestionLayout().setQuestionComponents(questionComponents);
        question.getAnswerLayout().setQuestionComponents(answerComponents);

        return question;
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

    private void resetPane() {
        listView_Components.getItems().clear();
        textField_value.clear();
        resetComponent();
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

}
