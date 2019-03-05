package service;

/**
 * Type of a Message
 */
enum MessageType {

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
