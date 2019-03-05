package service;

import data.interfaces.IQuestion;
import javafx.scene.Parent;
import service.interfaces.IQuestionConverter;

/**
 * Convert Question to a JavaFX Pane
 */
public class QuestionConverter implements IQuestionConverter {

    /**
     * convert a Question to its JavaFX QuestionPane
     *
     * @param question Question
     * @return JavaFX Parent
     */
    @Override
    public Parent convertQuestion(IQuestion question) {
        // TODO implement
        throw new UnsupportedOperationException();
    }

}
