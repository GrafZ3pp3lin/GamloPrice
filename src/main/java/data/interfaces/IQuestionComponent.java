package data.interfaces;

import data.observable.ISender;

import java.io.Serializable;
import java.util.List;

/**
 * Every Question consist of Question Components. Question Components is e.g. the Title of the Question or an Image.
 * a Question Component provides a Region, which is added to the Question Pane.
 */
public interface IQuestionComponent extends Serializable, ISender {

    String CORRECT_ANSWER = "%CORRECT_ANSWER:<name>%";
    String SELECTED_ANSWER = "%SELECTED_ANSWER:<name>%";

    /**
     * add Data to Component
     *
     * @param data QuestionData
     */
    void addComponentData(IQuestionData<?> data);

    /**
     * contains this Question Component the specified Data
     *
     * @param name Name of the data Object
     * @return true if data exist within this Component
     */
    boolean containsComponentData(String name);

    /**
     * check if Component Data contains a correct Answer
     *
     * @return true if a correct Answer exists
     */
    boolean containsCorrectAnswer();

    /**
     * get all Component data
     *
     * @return List with all component datas
     */
    List<IQuestionData<?>> getComponentData();

    /**
     * Init Component with Content Data
     *
     * @param data List of QuestionData for this Component from Game Data File
     */
    void setComponentData(List<IQuestionData<?>> data);

    /**
     * get the specified Content Data for this Component
     *
     * @param name Name of Data
     * @return Component Data
     */
    IQuestionData<?> getComponentData(String name);

    /**
     * get the correct Answer to this Component
     *
     * @return Data with correct Answer
     */
    IQuestionData<?> getCorrectAnswer();

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
     * get Component Type. Dependent on this type the GUI Component will be created.
     *
     * @return Component Type
     */
    String getType();

    /**
     * set Component Type
     *
     * @param type Component Type
     */
    void setType(String type);

}
