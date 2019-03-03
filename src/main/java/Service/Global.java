package Service;

import Service.interfaces.IFileHandler;
import Service.interfaces.IMessages;

/**
 * Provides Service Classes as Singletons
 */
public class Global {

    /**
     * load and write Game-Documents
     */
    public static IFileHandler XMLHandler = new XMLHandler();

    /**
     * interact with the User
     */
    public static IMessages Messenger = new DialogMessages();

}
