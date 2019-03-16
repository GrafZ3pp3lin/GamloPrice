package service;

import service.interfaces.IMessages;

/**
 * Interact and inform the User with DialogMessages
 */
public class DialogMessages implements IMessages {

    /**
     * Show a Message that have to be confirmed
     *
     * @param type    Type of the Message
     * @param message Message, that will be shown
     * @param title   Title of the Message
     * @param header  Header of the Message (can be null)
     * @return true, if the User pressed OK
     */
    @Override
    public boolean confirmMessage(MessageType type, String message, String title, String header) {
        // TODO implement
        throw new UnsupportedOperationException();
    }

    /**
     * Show a Message with Message Type and Title
     *
     * @param type    Type of the Message
     * @param message Message, that will be shown
     * @param title   Title of the Message
     */
    @Override
    public void showMessage(MessageType type, String message, String title) {
        // TODO implement
        throw new UnsupportedOperationException();
    }

    /**
     * Show a Message with Message Type, Title and header
     *
     * @param type    Type of the Message
     * @param message Message, that will be shown
     * @param title   Title of the Message
     * @param header  Header of the Message (can be null)
     */
    @Override
    public void showMessage(MessageType type, String message, String title, String header) {
        // TODO implement
        throw new UnsupportedOperationException();
    }

    /**
     * Show a simple Message with given MessageType
     *
     * @param type    Type of the Message
     * @param message Message, that will be shown
     */
    @Override
    public void showMessage(MessageType type, String message) {
        // TODO implement
        throw new UnsupportedOperationException();
    }
}
