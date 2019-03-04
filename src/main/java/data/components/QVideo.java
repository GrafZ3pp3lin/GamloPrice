package data.components;

import data.components.interfaces.IQuestionComponent;
import data.interfaces.IQuestionData;

public class QVideo implements IQuestionComponent<String> {

    private final String name;

    private IQuestionData<String> questionData;

    // region Constructors

    /**
     * Create a Video with custom name and data
     *
     * @param name         name of Component, if there is more then one Video
     * @param questionData Path to Video
     */
    public QVideo(String name, IQuestionData<String> questionData) {
        this.name = (name == null) ? getClass().getSimpleName() : name;
        this.questionData = questionData;
    }

    /**
     * Create a Video with given data
     *
     * @param questionData Path to Video
     */
    public QVideo(IQuestionData<String> questionData) {
        this(null, questionData);
    }

    /**
     * Question Video with custom name
     *
     * @param name name of Component, if there is more then one Video
     */
    public QVideo(String name) {
        this(name, null);
    }

    /**
     * Question Video with Default name "Video"
     */
    public QVideo() {
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
        // TODO implement
    }
}
