package data;

import data.interfaces.IQuestion;
import data.interfaces.IQuestionLayout;
import javafx.scene.Parent;

/**
 * Question, defined by various Components. Components will be added vertical to the Question Pane. Default Layout is:
 * Title Component + Button Grid Component
 * Each Question has a Value, defines its difficulty.
 */
public class Question implements IQuestion {

    private int Value;
    private boolean continueButtonEnabled;
    private IQuestionLayout QuestionLayout;

    // region Constructors

    /**
     * Create new Question with Value
     *
     * @param value Value of the Question
     */
    public Question(int value) {
        Value = value;
        QuestionLayout = new QuestionLayout();
        continueButtonEnabled = false;
    }

    // endregion

    /**
     * Value of Question. The Team, which correctly answered this Question will get the amount of this Value as Points.
     *
     * @return Value of Question
     */
    @Override
    public int getValue() {
        return Value;
    }

    /**
     * initialize all Components with their data
     */
    @Override
    public void LoadData() {
        //TODO implement
        throw new UnsupportedOperationException();
    }

    /**
     * Create the Pane, which is displayed. The Pane uses the defined Layout and Data.
     *
     * @return Question Pane as Parent
     */
    @Override
    public Parent CreatePane() {
        //TODO implement
        throw new UnsupportedOperationException();
    }

    /**
     * Is there a continue Button on the Question Page
     *
     * @return true, if continue Button is enabled
     */
    @Override
    public boolean IsContinueButtonEnabled() {
        return continueButtonEnabled;
    }

    /**
     * Some Questions need a continue Button, to get to the next Page.
     * If enabled there will be Button on the Question Page, to continue to the Result Page.
     *
     * @param enable en- or disabled the continue Button
     */
    @Override
    public void EnableContinueButton(boolean enable) {
        continueButtonEnabled = enable;
    }

    /**
     * get all Question Components ordered
     *
     * @return all Question Components
     */
    @Override
    public IQuestionLayout getAllQuestionComponents() {
        return QuestionLayout;
    }

}
