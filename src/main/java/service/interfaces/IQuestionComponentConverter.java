package service.interfaces;

import data.interfaces.IQuestionComponent;
import data.interfaces.IQuestionData;
import javafx.scene.Node;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Converts a Question Component into a JavaFX Node
 */
public interface IQuestionComponentConverter {

    /**
     * convert any QuestionComponent into JavaFX Nodes.
     * Each QuestionComponent needs its own Code.
     *
     * @param component Question Component
     * @param args      Additional Component parameters
     * @return JavaFX Node
     */
    Node convertQuestionComponent(IQuestionComponent component, IQuestionData<?>... args);

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @interface Component {

        PRIORITY priority() default PRIORITY.NORMAL;

        String[] types();

        enum PRIORITY {
            LOW, NORMAL, HIGH, HIGHEST
        }

    }

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @interface Default {
    }

}
