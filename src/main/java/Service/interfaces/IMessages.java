package Service.interfaces;

import Service.MessageType;

public interface IMessages {

    void ShowMessage(MessageType type, String message);

    void ShowMessage(MessageType type, String message, String title);

    void ShowMessage(MessageType type, String message, String title, String header);

    boolean ConfirmMessage(MessageType type, String message, String title, String header);

}
