package Service;

import Service.interfaces.IMessages;

/**
 * Provides Service Classes as Singletons
 */
public class Global {

    /**
     * load and write XML-Documents
     */
    public static XMLHandler XMLHandler = new XMLHandler();

    /**
     * interact with the User
     */
    public static IMessages Messenger = new DialogMessages();

}
