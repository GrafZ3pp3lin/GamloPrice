package service.interfaces;

/**
 * read and write Game Data from Files
 */
public interface IFileHandler<T> {

    /**
     * read a Document. The Document have to contains the specified generic Type.
     *
     * @param path Path to the Document
     * @return generic Object
     */
    T readFromFile(String path);

    /**
     * save a Object to a File
     *
     * @param object generic Object, that will be saved
     * @param path   Path to the File
     * @return true if operation was successful
     */
    boolean saveToFile(T object, String path);
}
