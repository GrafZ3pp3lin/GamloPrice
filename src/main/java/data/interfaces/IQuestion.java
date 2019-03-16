package data.interfaces;

import java.util.UUID;

/**
 * Question, defined by various Components. Components will be added vertical to the Question Layout. Default Layout is:
 * Title Component + Button Grid Component
 * Each Question has a Value, defines its difficulty.
 */
public interface IQuestion {

    /**
     * get the Answer Layout with all Question Components ordered
     *
     * @return all Answer Components
     */
    IQuestionLayout getAnswerLayout();

    /**
     * set the Answer Layout. Override the old Layout.
     *
     * @param layout new Answer Layout
     */
    void setAnswerLayout(IQuestionLayout layout);

    /**
     * get unique Id of this Question
     *
     * @return Question Id
     */
    UUID getId();

    /**
     * get the Question Layout with all Question Components ordered
     *
     * @return all Question Components
     */
    IQuestionLayout getQuestionLayout();

    /**
     * set the QuestionLayout. Override the old Layout.
     *
     * @param layout new QuestionLayout
     */
    void setQuestionLayout(IQuestionLayout layout);

    /**
     * Value of Question. The Team, which correctly answered this Question will get the amount of this Value as Points.
     *
     * @return Value of Question
     */
    int getValue();

    /**
     * set the Value for this Question.
     *
     * @param value Value of Question
     */
    void setValue(int value);

}
