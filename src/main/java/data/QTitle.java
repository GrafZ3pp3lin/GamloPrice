package data;

import data.interfaces.IQuestionComponent;
import data.interfaces.IQuestionData;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;

/**
 * QTitle is a Question Component, used for the Title of a Question.
 */
public class QTitle implements IQuestionComponent<String> {

    private String Name;
    private Label label;

    /**
     * Question Title with custom Name
     *
     * @param name Name of Component, if there is more then one Title
     */
    public QTitle(String name) {
        Name = name;
    }

    /**
     * Question Title with Default Name "Title"
     */
    public QTitle() {
        Name = "Title";
    }

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
    public Region getComponent() {
        return label;
    }

    /**
     * Init Component with Question Data
     *
     * @param data QuestionData for this Question from xml Document
     */
    @Override
    public void InitComponent(IQuestionData<String> data) {
        label.setText(data.getData());
    }
}
