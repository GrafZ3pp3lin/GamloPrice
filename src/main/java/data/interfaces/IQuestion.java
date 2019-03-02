package data.interfaces;

import javafx.scene.Parent;

import java.util.List;

/**
 * Question, definied by various Components. Components will be added vertikal to the Question Pane. Default Layout is:
 * Title Component + Button Grid Component
 * Each Question has a Value, defines its difficulty.
 *
 * @author Johannes
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
     * get all Question Components ordered
     *
     * @return all Question Components
     */
    List<IQuestionComponent> getAllQuestionComponents();

    /**
     * get a Question Component by its name
     *
     * @param name name of the Component
     * @return Component with given name
     */
    IQuestionComponent getQuestionComponent(String name);

    /**
     * add a Question Component to this Question
     *
     * @param component Question Component, to be added
     */
    void addQuestionComponent(IQuestionComponent component);

}
