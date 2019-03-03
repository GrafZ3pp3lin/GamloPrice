package service;

import service.interfaces.IFileHandler;
import data.interfaces.IGame;

/**
 * Helper Class to work with XML-Documents
 */
public class XMLHandler implements IFileHandler {

    /**
     * read a Game XML-Document
     *
     * @param path Path to the XML-Document
     * @return IGame Object
     */
    public IGame readGameFile(String path) {
        throw new UnsupportedOperationException();
    }

    /**
     * save a Game Object as XML-Document
     *
     * @param game Game Object, that will be saved
     * @param path Path to the XML-Document
     * @return true if operation was successful
     */
    public boolean saveGame(IGame game, String path) {
        throw new UnsupportedOperationException();
    }

}

