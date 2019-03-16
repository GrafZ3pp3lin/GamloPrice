package data;

import data.interfaces.IQuestionComponent;
import data.interfaces.IQuestionLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Defines the Layout for a Question Pane
 */
public class QuestionLayout implements IQuestionLayout {

    private final String name;

    private List<IQuestionComponent> questionComponents;

    // region Constructors

    /**
     * Creates a Layout with the given Components and a custom name
     *
     * @param name               name of the Layout
     * @param questionComponents Components, which will be added to the Layout
     */
    public QuestionLayout(String name, List<IQuestionComponent> questionComponents) {
        this.name = name;
        this.questionComponents = new ArrayList<>();
        if (questionComponents != null) {
            this.questionComponents.addAll(questionComponents);
        }
    }

    /**
     * Creates a Layout the given Components
     *
     * @param questionComponents Components, which will be added to the Layout
     */
    public QuestionLayout(List<IQuestionComponent> questionComponents) {
        this(null, questionComponents);
    }

    /**
     * Creates an empty Layout
     *
     * @param name name of the Layout
     */
    public QuestionLayout(String name) {
        this(name, null);
    }

    /**
     * Creates an empty Layout
     */
    public QuestionLayout() {
        this(null, null);
    }

    // endregion

    /**
     * add a Question Component to the Bottom of the Layout
     *
     * @param component Question Component to be added
     */
    @Override
    public void addQuestionComponent(IQuestionComponent component) {
        questionComponents.add(component);
    }

    /**
     * get the name of the Layout
     *
     * @return name of Layout
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * get a Component from this Layout by name
     *
     * @param name name of the Component
     * @return Component wit given name
     */
    @Override
    public IQuestionComponent getQuestionComponent(String name) {
        // TODO implement
        throw new UnsupportedOperationException();
    }

    /**
     * get all Question Components, ordered from Top to Bottom
     *
     * @return List with all Question Components
     */
    @Override
    public List<IQuestionComponent> getQuestionComponents() {
        return questionComponents;
    }

    /**
     * Initialize the Layout with Question Components
     *
     * @param components List with Components
     */
    @Override
    public void setQuestionComponents(List<IQuestionComponent> components) {
        questionComponents.clear();
        questionComponents.addAll(components);
    }
}
