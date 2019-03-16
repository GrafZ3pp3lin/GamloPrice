package data.interfaces;

/**
 * Data for Question Components. To init a Component you need to pass an Object of Question Data.
 *
 * @param <T> Question Data Type
 */
public interface IQuestionData<T> {

    /**
     * get the Data value
     *
     * @return Data value
     */
    T getData();

    /**
     * set the Data value
     *
     * @param value Data value
     */
    void setData(T value);

    /**
     * get Name of this data Object
     *
     * @return Name of data Object
     */
    String getName();
}
