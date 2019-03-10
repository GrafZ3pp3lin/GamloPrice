package data;

import data.interfaces.IQuestion;
import data.interfaces.IQuestionLayout;

import java.util.UUID;

/**
 * Question, defined by various Components. Components will be added vertical to the Question Layout. Default Layout is:
 * Title Component + Button Grid Component
 * Each Question has a value, defines its difficulty.
 */
public class Question implements IQuestion {

    private UUID id;
    private int value;
    private IQuestionLayout questionLayout;
    private IQuestionLayout answerLayout;

    // region Constructors

    /**
     * Create new Question with questionLayout and value
     *
     * @param value          value of the Question
     * @param questionLayout Layout of the Question Pane
     */
    public Question(int value, IQuestionLayout questionLayout) {
        id = UUID.randomUUID();
        this.value = value;
        this.questionLayout = questionLayout;
    }

    /**
     * Create a new Question with questionLayout and no value
     *
     * @param questionLayout
     */
    public Question(IQuestionLayout questionLayout) {
        this(0, questionLayout);
    }

    /**
     * Create new Question with value
     *
     * @param value value of the Question
     */
    public Question(int value) {
        this(value, null);
    }

    // endregion

    /**
     * get unique Id of this Question
     *
     * @return Question Id
     */
    @Override
    public UUID getId() {
        return id;
    }

    /**
     * value of Question. The Team, which correctly answered this Question will get the amount of this value as Points.
     *
     * @return value of Question
     */
    @Override
    public int getValue() {
        return value;
    }

    /**
     * set the value for this Question.
     *
     * @param value value of Question
     */
    @Override
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * get all Question Components ordered
     *
     * @return all Question Components
     */
    @Override
    public IQuestionLayout getQuestionLayout() {
        return questionLayout;
    }

    /**
     * set the questionLayout. Override the old Layout.
     *
     * @param layout new questionLayout
     */
    @Override
    public void setQuestionLayout(IQuestionLayout layout) {
        // TODO Ask User for commit, maybe save old Layout
        this.questionLayout = layout;
    }

    /**
     * get the Answer Layout with all Question Components ordered
     *
     * @return all Answer Components
     */
    @Override
    public IQuestionLayout getAnswerLayout() {
        return answerLayout;
    }

    /**
     * set the Answer Layout. Override the old Layout.
     *
     * @param layout new Answer Layout
     */
    @Override
    public void setAnswerLayout(IQuestionLayout layout) {
        answerLayout = layout;
    }

}
