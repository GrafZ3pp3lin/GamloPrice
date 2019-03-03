package data.components;

import data.components.interfaces.IQuestionComponent;
import data.interfaces.IQuestionData;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;

import java.util.List;

public class QImageGrid implements IQuestionComponent<List<String>> {

    private String name;
    private GridPane imageGrid;

    private IQuestionData<List<String>> questionData;

    // region Constructors

    /**
     * Create a Image Grid with custom name and data
     *
     * @param name         name of Component, if there is more then one ImageGrid
     * @param questionData List with Paths to Images
     */
    public QImageGrid(String name, IQuestionData<List<String>> questionData) {
        this.name = (name == null) ? getClass().getSimpleName() : name;
        this.questionData = questionData;
        imageGrid = new GridPane();
    }

    /**
     * Create a Image Grid with given data
     *
     * @param questionData List with Paths to Images
     */
    public QImageGrid(IQuestionData<List<String>> questionData) {
        this(null, questionData);
    }

    /**
     * Create a empty Image Grid with custom name
     *
     * @param name name of Component, if there is more then one ImageGrid
     */
    public QImageGrid(String name) {
        this(name, null);
    }

    /**
     * Create a Image Grid with default ClassName
     */
    public QImageGrid() {
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
        return imageGrid;
    }

    /**
     * Init Component and load Data from the Game.xml file
     *
     * @param data questionData for this Question from Game Data File
     */
    @Override
    public void initComponent(IQuestionData<List<String>> data) {
        // TODO implement GridPane
    }
}
