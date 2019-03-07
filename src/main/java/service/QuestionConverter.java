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
    public Parent convertQuestion(IQuestion question) {
        GridPane questionPane = new GridPane();
        questionPane.setGridLinesVisible(true);

        ColumnConstraints cc = new ColumnConstraints();
        cc.setHgrow(Priority.ALWAYS);
        cc.setPercentWidth(100);
        questionPane.getColumnConstraints().add(cc);

        for (IQuestionComponent component : question.getQuestionLayout().getQuestionComponents()) {
            RowConstraints rc = new RowConstraints();
//            if (component.containsComponentData("height")) {
//                rc.setPercentHeight((Double) component.getComponentData("height").getData());
//            }
//            else {
//                rc.setFillHeight(true);
//            }

            rc.setVgrow(Priority.ALWAYS);
            questionPane.getRowConstraints().add(rc);
        }

        IQuestionData<ReadOnlyDoubleProperty> heightProperty = new QuestionData<>("heightProperty", questionPane.heightProperty());

        for (int i = 0; i < question.getQuestionLayout().getQuestionComponents().size(); i++) {
            Node temp = converter.convertQuestionComponent(question.getQuestionLayout().getQuestionComponents().get(i), heightProperty);
            questionPane.add(temp, 0, i);
        }

        return questionPane;
    }

}
