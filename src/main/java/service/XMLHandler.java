package service;

import service.interfaces.IFileHandler;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;

/**
 * Helper Class to work with XML-Documents
 */
public class XMLHandler<T> implements IFileHandler<T> {

    // TODO das funktioniert nicht!

    /**
     * read a XML-Document
     *
     * @param path Path to the XML-Document
     * @return generic Object
     */
    public T readFromFile(String path) {
        T object = null;
        XMLDecoder decoder = null;
        try {
            decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(path)));
            object = (T) decoder.readObject();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        finally {
            if (decoder != null) {
                decoder.close();
            }
        }
        return object;
    }

    /**
     * save a Object as XML-Document
     *
     * @param object generic Object, that will be saved
     * @param path   Path to the XML-Document
     * @return true if operation was successful
     */
    public boolean saveToFile(T object, String path) {
        boolean success = true;
        XMLEncoder encoder = null;
        try {
            encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(path)));
            encoder.writeObject(object);
            encoder.flush();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
            success = false;
        }
        finally {
            if (encoder != null) {
                encoder.close();
            }
        }
        return success;
    }

}

