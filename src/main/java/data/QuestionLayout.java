package data;

import data.components.interfaces.IQuestionComponent;
import data.interfaces.IQuestionLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Defines the Layout for a Question Pane
 */
public class QuestionLayout implements IQuestionLayout {

    private String Name;
    private List<IQuestionComponent> QuestionComponents;

    // region Constructors

    /**
     * Creates a Layout with the given Components
     *
     * @param name               Name of the Layout
     * @param questionComponents Components, which will be added to the Layout
     */
    public QuestionLayout(String name, List<IQuestionComponent> questionComponents) {
        this.Name = name;
        QuestionComponents = new ArrayList<>();
        if (questionComponents != null) {
            QuestionComponents.addAll(questionComponents);
        }
    }

    /**
     * Creates an empty Layout
     *
     * @param name Name of the Layout
     */
    public QuestionLayout(String name) {
        this(name, null);
    }

    // endregion

    /**
     * get the name of the Layout
     *
     * @return Name of Layout
     */
    @Override
    public String GetName() {
        return Name;
    }

    /**
     * get all Question Components, ordered from Top to Bottom
     *
     * @return List with all Question Components
     */
    @Override
    public List<IQuestionComponent> GetQuestionComponents() {
        return QuestionComponents;
    }

    /**
     * Initialize the Layout with Question Components
     *
     * @param components List with Components
     */
    @Override
    public void SetQuestionComponents(List<IQuestionComponent> components) {
        QuestionComponents.clear();
        QuestionComponents.addAll(components);
    }

    /**
     * get a Component from this Layout by name
     *
     * @param name Name of the Component
     * @return Component wit given name
     */
    @Override
    public IQuestionComponent GetQuestionComponent(String name) {
        // TODO implement
        throw new UnsupportedOperationException();
    }

    /**
     * add a Question Component to the Bottom of the Layout
     *
     * @param component Question Component to be added
     */
    @Override
    public void AddQuestionComponent(IQuestionComponent component) {
        QuestionComponents.add(component);
    }
}
