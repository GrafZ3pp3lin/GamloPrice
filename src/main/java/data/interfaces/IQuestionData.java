package data.interfaces;

import java.io.Serializable;

/**
 * Data for Question Components. To init a Component you need to pass an Object of Question Data.
 *
 * @param <T> Question Data Type
 */
public interface IQuestionData<T extends Serializable> extends Serializable {

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
