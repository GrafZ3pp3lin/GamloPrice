package data.components;

import data.components.interfaces.IQuestionComponent;
import data.interfaces.IQuestionData;

public class QText implements IQuestionComponent<String> {

    private final String name;

    private IQuestionData<String> questionData;

    // region Constructors

    /**
     * Create Text with custom name and data
     *
     * @param name         name of Component, if there is more then one Text
     * @param questionData Content for Text
     */
    public QText(String name, IQuestionData<String> questionData) {
        this.name = (name == null) ? getClass().getSimpleName() : name;
        this.questionData = questionData;
    }

    /**
     * Create a Image Grid with given data
     *
     * @param questionData Content for Text
     */
    public QText(IQuestionData<String> questionData) {
        this(null, questionData);
    }

    /**
     * Create Text with custom name
     *
     * @param name name of Component, if there is more then one Text
     */
    public QText(String name) {
        this(name, null);
    }

    /**
     * Create Text with default ClassName
     */
    public QText() {
        this(null, null);
    }

    // endregion

    /**
     * name of Component to identify Data for this Component in case there are more then one of these Components
     *
     * @return name of Component
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Init Component and load Data from the Game.xml file
     *
     * @param data questionData for this Question from Game Data File
     */
    @Override
    public void initComponent(IQuestionData<String> data) {
        questionData = data;
    }
}
