package controller;

import service.DialogMessages;
import service.QuestionComponentConverter;
import service.QuestionConverter;
import service.XMLHandler;
import service.interfaces.IFileHandler;
import service.interfaces.IMessages;
import service.interfaces.IQuestionComponentConverter;
import service.interfaces.IQuestionConverter;

/**
 * Provides service Classes as Singletons
 */
public class Global {

    /**
     * load and write Game-Documents
     */
    public static IFileHandler XMLHandler = new XMLHandler();

    /**
     * interact with the User
     */
    public static IMessages messenger = new DialogMessages();

    /**
     * converts a Question into a JavaFX Component
     */
    public static IQuestionConverter questionConverter = new QuestionConverter();

    /**
     * converts default Components into JavaFX Nodes
     */
    public static IQuestionComponentConverter questionComponentConverter = new QuestionComponentConverter();

}
