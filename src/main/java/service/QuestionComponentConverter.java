package service;

import data.interfaces.IQuestionComponent;
import javafx.scene.Node;
import service.interfaces.IQuestionComponentConverter;

/**
 * Component Converter for Default Components
 */
public class QuestionComponentConverter implements IQuestionComponentConverter {

    /**
     * convert default Question Components into JavaFX Nodes.
     *
     * @param component Question Component
     * @return JavaFX Node
     */
    @Override
    @Default
    @Component(types = { "Title", "Image", "ButtonGrid", "ImageGrid", "Video", "Text", "ControlButtons" })
    public Node convertQuestionComponent(IQuestionComponent component) {
        // TODO implement
        throw new UnsupportedOperationException();
    }
}
