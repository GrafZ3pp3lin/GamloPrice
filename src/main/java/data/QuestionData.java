package data;

import data.interfaces.IQuestionData;

/**
 * Data for Question Components. To init a Component you need to pass an Object of Question Data.
 *
 * @param <T> Question Data Type
 * @author Johannes
 */
public class QuestionData<T> implements IQuestionData<T> {

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
