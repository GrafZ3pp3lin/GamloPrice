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
     * initialize all Components with their data
     */
    void LoadData();

    /**
     * Create the Pane, which is displayed. The Pane uses the defined Layout and Data.
     *
     * @return Question Pane as Parent
     */
    Parent CreatePane();

    /**
     * Is there a continue Button on the Question Page
     *
     * @return true, if continue Button is enabled
     */
    boolean IsContinueButtonEnabled();

    /**
     * Some Questions need a continue Button, to get to the next Page.
     * If enabled there will be Button on the Question Page, to continue to the Result Page.
     *
     * @param enable en- or disabled the continue Button
     */
    void EnableContinueButton(boolean enable);

    /**
     * get all Question Components ordered
     *
     * @return all Question Components
     */
    IQuestionLayout getAllQuestionComponents();

}
