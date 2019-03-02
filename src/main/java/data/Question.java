package data;

import data.interfaces.IQuestion;
import data.interfaces.IQuestionComponent;
import javafx.scene.Parent;

import java.util.List;

/**
 * Question, definied by various Components. Components will be added vertikal to the Question Pane. Default Layout is:
 * Title Component + Button Grid Component
 * Each Question has a Value, defines its difficulty.
 *
 * @author Johannes
 */
public class Question implements IQuestion {

    private int Value;
    private List<IQuestionComponent> QuestionComponents;

    /**
     * Create new Question with Value
     *
     * @param value Value of the Question
     */
    public Question(int value) {
        Value = value;
    }

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
     * get all Question Components ordered
     *
     * @return all Question Components
     */
    @Override
    public List<IQuestionComponent> getAllQuestionComponents() {
        return QuestionComponents;
    }

    /**
     * get a Question Component by its name
     *
     * @param name name of the Component
     * @return Component with given name
     */
    @Override
    public IQuestionComponent getQuestionComponent(String name) {
        //TODO implement
        throw new UnsupportedOperationException();
    }

    /**
     * add a Question Component to this Question
     *
     * @param component Question Component, to be added
     */
    @Override
    public void addQuestionComponent(IQuestionComponent component) {
        QuestionComponents.add(component);
    }
}
