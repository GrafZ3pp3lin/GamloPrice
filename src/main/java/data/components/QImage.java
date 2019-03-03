package data.components;

import data.components.interfaces.IQuestionComponent;
import data.interfaces.IQuestionData;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class QImage implements IQuestionComponent<String> {

    private String Name;
    private ImageView image;

    /**
     * Question Image with custom Name
     *
     * @param name Name of Component, if there is more then one Image
     */
    public QImage(String name) {
        Name = name;
        image = new ImageView();
    }

    /**
     * Question Image with Default Name "Image"
     */
    public QImage() {
        this("Image");
    }

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
        return image;
    }

    /**
     * Init Component and load Data from the Game.xml file
     *
     * @param data QuestionData for this Question from Game Data File
     */
    @Override
    public void InitComponent(IQuestionData<String> data) {
        // TODO test if data is valid Path
        image.setImage(new Image(data.getData()));
    }

}
