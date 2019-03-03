package data.components;

import data.components.interfaces.IQuestionComponent;
import data.interfaces.IQuestionData;
import javafx.scene.Node;
import javafx.scene.control.Label;

/**
 * QTitle is a Question Component, used for the Title of a Question.
 */
public class QTitle implements IQuestionComponent<String> {

    private String Name;
    private Label label;

    // region Constructors

    /**
     * Question Title with custom Name
     *
     * @param name Name of Component, if there is more then one Title
     */
    public QTitle(String name) {
        Name = name;
        label = new Label();
    }

    /**
     * Question Title with Default Name "Title"
     */
    public QTitle() {
        this("Title");
    }

    // endregion

    /**
     * Name of Component to identify Data for this Component in case there are more then one of these Components
     *
     * @return Name of Component
     */
    @Override
    public String getName() {
        return Name;
    }

    /**
     * Component, which is displayed on QuestionPane
     *
     * @return Label with Title
     */
    @Override
    public Node getComponent() {
        return label;
    }

    /**
     * Init Component with Question Data
     *
     * @param data QuestionData for this Question from Game Document
     */
    @Override
    public void InitComponent(IQuestionData<String> data) {
        label.setText(data.getData());
    }
}
