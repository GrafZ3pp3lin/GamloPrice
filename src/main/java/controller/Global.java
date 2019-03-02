package controller;

import Service.DialogMessages;
import Service.XMLHandler;
import Service.interfaces.IMessages;

public class Global {

    public static XMLHandler XMLHandler = new XMLHandler();

    public static IMessages Messenger = new DialogMessages();

}
