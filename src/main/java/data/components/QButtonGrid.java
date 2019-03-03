package data.components;

import data.components.interfaces.IQuestionComponent;
import data.interfaces.IQuestionData;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;

import java.util.List;

public class QButtonGrid implements IQuestionComponent<List<String>> {

    private String Name;
    private GridPane buttonGrid;

    // region Constructors

    /**
     * Question Button Grid with custom Name
     *
     * @param name Name of Component, if there is more then one ButtonGrid
     */
    public QButtonGrid(String name) {
        Name = name;
        buttonGrid = new GridPane();
    }

    /**
     * Question Button Grid with Default Name "ButtonGrid"
     */
    public QButtonGrid() {
        this("ButtonGrid");
    }

    // endregion

    /**
     * Name of Component to identify Data for this Component in case there are more then one of these Components
     *
     * @return Name of Component
     */
    @Override
    public String getName() {
        return Name;
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
     * @param data QuestionData for this Question from Game Data File
     */
    @Override
    public void InitComponent(IQuestionData<List<String>> data) {
        // TODO implement GridPane
    }
}
