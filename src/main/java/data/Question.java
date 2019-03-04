package data;

import data.interfaces.IQuestion;
import data.interfaces.IQuestionLayout;

/**
 * Question, defined by various Components. Components will be added vertical to the Question Layout. Default Layout is:
 * Title Component + Button Grid Component
 * Each Question has a value, defines its difficulty.
 */
public class Question implements IQuestion {

    private int value;
    private boolean continueButtonEnabled;
    private IQuestionLayout questionLayout;

    // region Constructors

    /**
     * Create new Question with questionLayout and value
     *
     * @param value          value of the Question
     * @param questionLayout Layout of the Question Pane
     */
    public Question(int value, IQuestionLayout questionLayout) {
        this.value = value;
        this.questionLayout = questionLayout;
        continueButtonEnabled = false;
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
     * initialize all Components with their data
     */
    @Override
    public void loadData() {
        //TODO implement
        throw new UnsupportedOperationException();
    }

    /**
     * Is there a continue Button on the Question Page
     *
     * @return true, if continue Button is enabled
     */
    @Override
    public boolean isContinueButtonEnabled() {
        return continueButtonEnabled;
    }

    /**
     * Some Questions need a continue Button, to get to the next Page.
     * If enabled there will be Button on the Question Page, to continue to the Result Page.
     *
     * @param enable en- or disabled the continue Button
     */
    @Override
    public void enableContinueButton(boolean enable) {
        continueButtonEnabled = enable;
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

}
