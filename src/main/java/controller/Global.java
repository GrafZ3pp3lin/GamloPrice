package controller;

import service.DialogMessages;
import service.XMLHandler;
import service.interfaces.IFileHandler;
import service.interfaces.IMessages;

/**
 * Provides service Classes as Singletons
 */
class Global {

    /**
     * load and write Game-Documents
     */
    public static IFileHandler XMLHandler = new XMLHandler();

    /**
     * interact with the User
     */
    public static IMessages Messenger = new DialogMessages();

}
