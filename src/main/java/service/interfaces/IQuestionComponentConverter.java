package service.interfaces;

import data.interfaces.IQuestionComponent;
import javafx.scene.Node;

import java.lang.annotation.*;

/**
 * Converts a Question Component into a JavaFX Node
 */
public interface IQuestionComponentConverter {

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @interface Component {

        enum PRIORITY {
            LOW, NORMAL, HIGH, HIGHEST
        }
        PRIORITY priority() default PRIORITY.NORMAL;

        String[] types();

    }

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @interface Default {
    }

    /**
     * convert any QuestionComponent into JavaFX Nodes.
     * Each QuestionComponent needs its own Code.
     *
     * @param component Question Component
     * @return JavaFX Node
     */
    Node convertQuestionComponent(IQuestionComponent component);

}
