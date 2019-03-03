package data;

import data.interfaces.IQuestionComponent;
import data.interfaces.IQuestionData;
import javafx.scene.Node;
import javafx.scene.text.Text;

public class QText implements IQuestionComponent<String> {

    private String Name;
    private Text content;

    // region Constructors

    /**
     * Question Text with custom Name
     *
     * @param name Name of Component, if there is more then one Text
     */
    public QText(String name) {
        Name = name;
        content = new Text();
    }

    /**
     * Question Text with Default Name "Text"
     */
    public QText() {
        this("Text");
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
     * @return Component as Region
     */
    @Override
    public Node getComponent() {
        return content;
    }

    /**
     * Init Component and load Data from the Game.xml file
     *
     * @param data QuestionData for this Question from Game Data File
     */
    @Override
    public void InitComponent(IQuestionData<String> data) {
        content.setText(data.getData());
    }
}
