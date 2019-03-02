package Service;

/**
 * Type of a Message
 *
 * @author Johannes
 */
public enum MessageType {

    /**
     * Message to inform the User
     */
    INFO,

    /**
     * User have to confirm an operation
     */
    CONFIRMATION,

    /**
     * Message to warn the User
     */
    WARNING,

    /**
     * Something bad happened
     */
    ERROR
}
