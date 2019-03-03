package data.interfaces;

import javafx.scene.Parent;

/**
 * Question, defined by various Components. Components will be added vertical to the Question Pane. Default Layout is:
 * Title Component + Button Grid Component
 * Each Question has a Value, defines its difficulty.
 */
public interface IQuestion {

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

    /**
     * initialize all Components with their data
     */
    void loadData();

    /**
     * Create the Pane, which is displayed. The Pane uses the defined Layout and Data.
     *
     * @return Question Pane as Parent
     */
    Parent createPane();

    /**
     * Is there a continue Button on the Question Page
     *
     * @return true, if continue Button is enabled
     */
    boolean isContinueButtonEnabled();

    /**
     * Some Questions need a continue Button, to get to the next Page.
     * If enabled there will be Button on the Question Page, to continue to the Result Page.
     *
     * @param enable en- or disabled the continue Button
     */
    void enableContinueButton(boolean enable);

    /**
     * set the QuestionLayout. Override the old Layout.
     *
     * @param layout new QuestionLayout
     */
    void setQuestionLayout(IQuestionLayout layout);

    /**
     * get the Question Layout with all Question Components ordered
     *
     * @return all Question Components
     */
    IQuestionLayout getQuestionLayout();

}
