package data.interfaces;

import data.components.interfaces.IQuestionComponent;

import java.util.List;

/**
 * Defines the Layout for a Question Pane
 */
public interface IQuestionLayout {

    /**
     * get the name of the Layout
     *
     * @return Name of Layout
     */
    String GetName();

    /**
     * get all Question Components, ordered from Top to Bottom
     *
     * @return List with all Question Components
     */
    List<IQuestionComponent> GetQuestionComponents();

    /**
     * Initialize the Layout with Question Components
     *
     * @param components List with Components
     */
    void SetQuestionComponents(List<IQuestionComponent> components);

    /**
     * get a Component from this Layout by name
     *
     * @param name Name of the Component
     * @return Component wit given name
     */
    IQuestionComponent GetQuestionComponent(String name);

    /**
     * add a Question Component to the Bottom of the Layout
     *
     * @param component Question Component to be added
     */
    void AddQuestionComponent(IQuestionComponent component);

}
