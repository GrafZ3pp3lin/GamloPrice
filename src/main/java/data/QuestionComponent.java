package data;

import data.interfaces.IQuestionComponent;
import data.interfaces.IQuestionData;
import data.observable.IObserver;

import java.util.ArrayList;
import java.util.List;

public class QuestionComponent implements IQuestionComponent {

    private String type;
    private String name;
    private List<IQuestionData<?>> questionData;

    private IObserver observer;

    // region Constructors

    /**
     * Create a Question Component with a custom Name and data
     *
     * @param type         specify Type of Question Component (like Title)
     * @param name         Name of Component, if there are more then one
     * @param questionData Data from this Component
     */
    public QuestionComponent(String type, String name, List<IQuestionData<?>> questionData) {
        this.type = type;
        this.name = name;
        this.questionData = new ArrayList<>();
        if (questionData != null) {
            this.questionData.addAll(questionData);
        }
    }

    /**
     * Create a Question Component with default name and data
     *
     * @param type         specify Type of Question Component (like Title)
     * @param questionData Data from this Component
     */
    public QuestionComponent(String type, List<IQuestionData<?>> questionData) {
        this(type, type, questionData);
    }

    /**
     * Create an empty Question Component with custom name
     *
     * @param type specify Type of Question Component (like Title)
     * @param name Name of Component, if there are more then one
     */
    public QuestionComponent(String type, String name) {
        this(type, name, null);
    }

    /**
     * Create an empty Question Component with default name
     *
     * @param type specify Type of Question Component (like Title)
     */
    public QuestionComponent(String type) {
        this(type, type, null);
    }

    /**
     * add Data to Component
     *
     * @param data QuestionData
     */
    @Override
    public void addComponentData(IQuestionData<?> data) {
        this.questionData.add(data);
    }

    // endregion

    /**
     * contains this Question Component the specified Data
     *
     * @param name Name of the data Object
     * @return true if data exist within this Component
     */
    @Override
    public boolean containsComponentData(String name) {
        for (IQuestionData<?> data : this.questionData) {
            if (data.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * check if Component Data contains a correct Answer
     *
     * @return true if a correct Answer exists
     */
    @Override
    public boolean containsCorrectAnswer() {
        for (IQuestionData<?> data : this.questionData) {
            if (data.getName().equalsIgnoreCase(CORRECT_ANSWER)) {
                return true;
            }
        }
        return false;
    }

    /**
     * get all Component data
     *
     * @return List with all component datas
     */
    @Override
    public List<IQuestionData<?>> getComponentData() {
        return questionData;
    }

    /**
     * Init Component with Content Data
     *
     * @param data List of QuestionData for this Component from Game Data File
     */
    @Override
    public void setComponentData(List<IQuestionData<?>> data) {
        this.questionData.clear();
        questionData.addAll(data);
    }

    /**
     * get the specified Content Data for this Component
     *
     * @param name Name of Data
     * @return Component Data
     */
    @Override
    public IQuestionData<?> getComponentData(String name) {
        for (IQuestionData<?> data : this.questionData) {
            if (data.getName().equalsIgnoreCase(name)) {
                return data;
            }
        }
        return null;
    }

    /**
     * get the correct Answer to this Component
     *
     * @return Data with correct Answer
     */
    @Override
    public IQuestionData<?> getCorrectAnswer() {
        for (IQuestionData<?> data : this.questionData) {
            if (data.getName().equalsIgnoreCase(CORRECT_ANSWER)) {
                return data;
            }
        }
        return null;
    }

    /**
     * Name of Component to identify Data for this Component in case there are more then one of these Components
     *
     * @return Name of Component
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * set the Component Name if there are more then one on one Question Layout
     *
     * @param name Name of the Component
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * get Component Type. Dependent on this type the GUI Component will be created.
     *
     * @return Component Type
     */
    @Override
    public String getType() {
        return type;
    }

    /**
     * set Component Type
     *
     * @param type Component Type
     */
    @Override
    public void setType(String type) {
        this.type = type;
    }

    /**
     * notify Observer
     */
    @Override
    public void notifyObserver() {
        // TODO implement
        // observer.update(UpdateType.Answer, "");
        throw new UnsupportedOperationException();
    }

    /**
     * set Observer to this Event Sender
     *
     * @param observer
     */
    @Override
    public void setObserver(IObserver observer) {
        this.observer = observer;
    }

    @Override
    public String toString() {
        return type + " (" + name + ")";
    }
}
