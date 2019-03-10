package controller;

import service.*;
import service.interfaces.IFileHandler;
import service.interfaces.IMessages;
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
     * load and write Game-Documents in binaryFormat
     */
    public static IFileHandler binarySerializer = new BinarySerializer();

    /**
     * interact with the User
     */
    public static IMessages messenger = new DialogMessages();

    /**
     * converts a Question into a JavaFX Component
     */
    public static IQuestionConverter questionConverter = new QuestionConverter();

    /**
     * contains the data of the connected Screens
     */
    public static ScreenData screenData = new ScreenData();

}
