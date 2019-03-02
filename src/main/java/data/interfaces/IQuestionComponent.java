package data.interfaces;

import javafx.scene.layout.Region;

/**
 * Every Question consist of Question Components. Question Components is e.g. the Title of the Question or an Image.
 * a Question Component provides a Region, which is added to the Question Pane.
 *
 * @param <T> Component Data Type
 * @author Johannes
 */
public interface IQuestionComponent<T> {

    /**
     * Name of Component to identify Data for this Component in case there are more then one of these Components
     *
     * @return Name of Component
     */
    String getName();

    /**
     * Component, which is displayed on QuestionPane
     *
     * @return Component as Region
     */
    Region getComponent();

    /**
     * Init Component and load Data from the Game.xml file
     *
     * @param data QuestionData for this Question from xml Document
     */
    void InitComponent(IQuestionData<T> data);

}
