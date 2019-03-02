package data.interfaces;

/**
 * Data for Question Components. To init a Component you need to pass an Object of Question Data.
 *
 * @param <T> Question Data Type
 * @author Johannes
 */
public interface IQuestionData<T> {

    /**
     * set the Data value
     *
     * @param value Data value
     */
    void setData(T value);

    /**
     * get the Data value
     *
     * @return Data value
     */
    T getData();
}
