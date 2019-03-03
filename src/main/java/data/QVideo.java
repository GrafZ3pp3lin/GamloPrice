package data;

import data.interfaces.IQuestionComponent;
import data.interfaces.IQuestionData;
import javafx.scene.Node;
import javafx.scene.media.MediaView;

public class QVideo implements IQuestionComponent<String> {

    private String Name;
    private MediaView mediaView;

    // region Constructors

    /**
     * Question Video with custom Name
     *
     * @param name Name of Component, if there is more then one Video
     */
    public QVideo(String name) {
        Name = name;
        mediaView = new MediaView();
    }

    /**
     * Question Video with Default Name "Video"
     */
    public QVideo() {
        this("Video");
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
        // TODO wrap MediaView in Container and add Control Buttons
        return mediaView;
    }

    /**
     * Init Component and load Data from the Game.xml file
     *
     * @param data QuestionData for this Question from Game Data File
     */
    @Override
    public void InitComponent(IQuestionData<String> data) {
        // TODO implement
    }
}
