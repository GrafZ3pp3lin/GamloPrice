package service;

import data.QuestionData;
import data.interfaces.IQuestion;
import data.interfaces.IQuestionComponent;
import data.interfaces.IQuestionData;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import service.interfaces.IQuestionComponentConverter;
import service.interfaces.IQuestionConverter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Convert Question to a JavaFX Pane
 */
public class QuestionConverter implements IQuestionConverter {

    IQuestionComponentConverter converter = new QuestionComponentConverter();

    /**
     * convert a Question to its JavaFX QuestionPane
     *
     * @param question Question
     * @return JavaFX Parent
     */
    @Override
    public Parent convertQuestion(IQuestion question, IQuestionData<?>... args) {
        GridPane questionPane = new GridPane();

        ColumnConstraints cc = new ColumnConstraints();
        cc.setHgrow(Priority.ALWAYS);
//        cc.setPercentWidth(100);
        questionPane.getColumnConstraints().add(cc);

        for (IQuestionComponent component : question.getQuestionLayout().getQuestionComponents()) {
            RowConstraints rc = new RowConstraints();
            if (component.containsComponentData("grow")) {
                if ((Boolean) component.getComponentData("grow").getData()) {
                    rc.setVgrow(Priority.ALWAYS);
                }
                else {
                    rc.setVgrow(Priority.NEVER);
                }
            }
            else {
                rc.setVgrow(Priority.SOMETIMES);
            }
            questionPane.getRowConstraints().add(rc);
        }

        IQuestionData<ReadOnlyDoubleProperty> heightProperty = new QuestionData<>("heightProperty", questionPane.heightProperty());

        List<IQuestionData<?>> arguments = new ArrayList<>();
        arguments.addAll(Arrays.asList(args));
        arguments.add(heightProperty);

        for (int i = 0; i < question.getQuestionLayout().getQuestionComponents().size(); i++) {
            Node temp = converter.convertQuestionComponent(question.getQuestionLayout().getQuestionComponents().get(i), arguments.toArray(new IQuestionData[0]));
            questionPane.add(temp, 0, i);
        }

        questionPane.setStyle("-fx-background: #CCC;-fx-accent: #007206;-fx-text-color: black;-fx-button-color: #808080;");

        questionPane.getStylesheets().add("/view/style.css");
        questionPane.getStyleClass().add("background");

        return questionPane;
    }

}
