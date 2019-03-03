package data.components;

import data.components.interfaces.IQuestionComponent;
import data.interfaces.IQuestionData;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;

import java.util.List;

public class QButtonGrid implements IQuestionComponent<List<String>> {

    private String name;
    private GridPane buttonGrid;

    private IQuestionData<List<String>> questionData;

    // region Constructors

    /**
     * Create a Button Grid with custom name and data
     *
     * @param name         name of Component, if there is more then one ButtonGrid
     * @param questionData List with Strings for Buttons
     */
    public QButtonGrid(String name, IQuestionData<List<String>> questionData) {
        this.name = (name == null) ? getClass().getSimpleName() : name;
        this.questionData = questionData;
        buttonGrid = new GridPane();
    }

    /**
     * Create a Button Grid with given data
     *
     * @param questionData List with Strings for Buttons
     */
    public QButtonGrid(IQuestionData<List<String>> questionData) {
        this(null, questionData);
    }

    /**
     * Create a empty Button Grid with custom name
     *
     * @param name name of Component, if there is more then one ButtonGrid
     */
    public QButtonGrid(String name) {
        this(name, null);
    }

    /**
     * Create a empty Button Grid with default ClassName
     */
    public QButtonGrid() {
        this(null, null);
    }

    // endregion

    /**
     * name of Component to identify Data for this Component in case there are more then one of these Components
     *
     * @return name of Component
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Component, which is displayed on QuestionPane
     *
     * @return Component as Region
     */
    @Override
    public Node getComponent() {
        return buttonGrid;
    }

    /**
     * Init Component and load Data from the Game.xml file
     *
     * @param data questionData for this Question from Game Data File
     */
    @Override
    public void initComponent(IQuestionData<List<String>> data) {
        questionData = data;
        // TODO implement GridPane
    }
}
