package data;

import data.interfaces.IQuestionData;

import java.io.Serializable;

/**
 * Data for Question Components. To init a Component you need to pass an Object of Question Data.
 *
 * @param <T> Question Data Type
 */
public class QuestionData<T extends Serializable> implements IQuestionData<T> {

    private T Value;

    /**
     * set the Data value
     *
     * @param value Data value
     */
    @Override
    public void setData(T value) {
        Value = value;
    }

    /**
     * get the Data value
     *
     * @return Data value
     */
    @Override
    public T getData() {
        return Value;
    }
}
