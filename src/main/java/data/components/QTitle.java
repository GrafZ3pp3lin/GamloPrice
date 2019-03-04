package data.components;

import data.components.interfaces.IQuestionComponent;
import data.interfaces.IQuestionData;

/**
 * QTitle is a Question Component, used for the Title of a Question.
 */
public class QTitle implements IQuestionComponent<String> {

    private final String name;

    private IQuestionData<String> questionData;

    // region Constructors

    /**
     * Create a Title with custom name and data
     *
     * @param name         name of Component, if there is more then one Title
     * @param questionData Content for Title
     */
    public QTitle(String name, IQuestionData<String> questionData) {
        this.name = (name == null) ? getClass().getSimpleName() : name;
        this.questionData = questionData;
    }

    /**
     * Create a Title with given data
     *
     * @param questionData Content for Title
     */
    public QTitle(IQuestionData<String> questionData) {
        this(null, questionData);
    }

    /**
     * Question Title with custom name
     *
     * @param name name of Component, if there is more then one Title
     */
    public QTitle(String name) {
        this(name, null);
    }

    /**
     * Question Title with default ClassName
     */
    public QTitle() {
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
     * Init Component with Question Data
     *
     * @param data questionData for this Question from Game Document
     */
    @Override
    public void initComponent(IQuestionData<String> data) {
        questionData = data;
    }
}
