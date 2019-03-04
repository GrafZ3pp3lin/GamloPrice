package data.components;

import data.components.interfaces.IQuestionComponent;
import data.interfaces.IQuestionData;

public class QImage implements IQuestionComponent<String> {

    private final String name;

    private IQuestionData<String> questionData;

    // region Constructors

    /**
     * Create a Image with custom name and data
     *
     * @param name         name of Component, if there is more then one Image
     * @param questionData Path to Image
     */
    public QImage(String name, IQuestionData<String> questionData) {
        this.name = (name == null) ? getClass().getSimpleName() : name;
        this.questionData = questionData;
    }

    /**
     * Create a Image with given data
     *
     * @param questionData Path to Image
     */
    public QImage(IQuestionData<String> questionData) {
        this(null, questionData);
    }

    /**
     * Question Image with custom name
     *
     * @param name name of Component, if there is more then one Image
     */
    public QImage(String name) {
        this(name, null);
    }

    /**
     * Question Image with default ClassName
     */
    public QImage() {
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
        // TODO test if data is valid Path
    }

}
