package data.interfaces;

import java.io.Serializable;
import java.util.List;

/**
 * Defines the Layout for a Question Pane
 */
public interface IQuestionLayout extends Serializable {

    /**
     * get the name of the Layout
     *
     * @return Name of Layout
     */
    String getName();

    /**
     * get all Question Components, ordered from Top to Bottom
     *
     * @return List with all Question Components
     */
    List<IQuestionComponent> getQuestionComponents();

    /**
     * Initialize the Layout with Question Components
     *
     * @param components List with Components
     */
    void setQuestionComponents(List<IQuestionComponent> components);

    /**
     * get a Component from this Layout by name
     *
     * @param name Name of the Component
     * @return Component wit given name
     */
    IQuestionComponent getQuestionComponent(String name);

    /**
     * add a Question Component to the Bottom of the Layout
     *
     * @param component Question Component to be added
     */
    void addQuestionComponent(IQuestionComponent component);

}
