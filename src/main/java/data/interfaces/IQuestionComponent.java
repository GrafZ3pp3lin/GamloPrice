package data.interfaces;

import java.io.Serializable;

/**
 * Every Question consist of Question Components. Question Components is e.g. the Title of the Question or an Image.
 * a Question Component provides a Region, which is added to the Question Pane.
 */
public interface IQuestionComponent extends Serializable {

    /**
     * get Component Type. Dependent on this type the GUI Component will be created.
     *
     * @return Component Type
     */
    String getType();

    /**
     * Name of Component to identify Data for this Component in case there are more then one of these Components
     *
     * @return Name of Component
     */
    String getName();

    /**
     * set the Component Name if there are more then one on one Question Layout
     *
     * @param name Name of the Component
     */
    void setName(String name);

    /**
     * Init Component with Content Data
     *
     * @param data QuestionData for this Question from Game Data File
     */
    void setComponentData(IQuestionData<?> data);

    /**
     * get the specified Content Data for this Component
     *
     * @return Component Data
     */
    IQuestionData<?> getComponentData();

}
