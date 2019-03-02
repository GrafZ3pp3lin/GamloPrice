package Service.interfaces;

import Service.MessageType;

/**
 * Offer Methods to interact and inform the User
 *
 * @author Johannes
 */
public interface IMessages {

    /**
     * Show a simple Message with given MessageType
     *
     * @param type    Type of the Message
     * @param message Message, that will be shown
     */
    void ShowMessage(MessageType type, String message);

    /**
     * Show a Message with Message Type and Title
     *
     * @param type    Type of the Message
     * @param message Message, that will be shown
     * @param title   Title of the Message
     */
    void ShowMessage(MessageType type, String message, String title);

    /**
     * Show a Message with Message Type, Title and header
     *
     * @param type    Type of the Message
     * @param message Message, that will be shown
     * @param title   Title of the Message
     * @param header  Header of the Message (can be null)
     */
    void ShowMessage(MessageType type, String message, String title, String header);

    /**
     * Show a Message that have to be confirmed
     *
     * @param type    Type of the Message
     * @param message Message, that will be shown
     * @param title   Title of the Message
     * @param header  Header of the Message (can be null)
     * @return true, if the User pressed OK
     */
    boolean ConfirmMessage(MessageType type, String message, String title, String header);

}
