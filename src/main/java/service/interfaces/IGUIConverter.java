package service.interfaces;

import data.components.interfaces.IQuestionComponent;
import data.interfaces.IQuestion;
import javafx.scene.Node;
import javafx.scene.Parent;

/**
 * Convert Question Data to JavaFX Components
 */
public interface IGUIConverter {

    /**
     * convert any QuestionComponent into JavaFX Nodes.
     * Each QuestionComponent needs its own Code.
     *
     * @param component Question Component
     * @return JavaFX Node
     */
    Node convertQuestionComponent(IQuestionComponent component);

    /**
     * convert a Question to its JavaFX QuestionPane
     *
     * @param question Question
     * @return JavaFX Parent
     */
    Parent convertQuestion(IQuestion question);
}
