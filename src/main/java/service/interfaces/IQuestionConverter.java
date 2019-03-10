package service.interfaces;

import data.interfaces.IQuestion;
import data.interfaces.IQuestionData;
import javafx.scene.Parent;

/**
 * Convert Question to a JavaFX Pane
 */
public interface IQuestionConverter {

    /**
     * convert a Question to its JavaFX QuestionPane
     *
     * @param question Question
     * @return JavaFX Parent
     */
    Parent convertQuestion(IQuestion question, IQuestionData<?>... args);
}
