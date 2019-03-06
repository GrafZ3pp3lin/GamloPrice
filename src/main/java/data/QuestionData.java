package data;

import data.interfaces.IQuestionData;

/**
 * Data for Question Components. To init a Component you need to pass an Object of Question Data.
 *
 * @param <T> Question Data Type
 */
public class QuestionData<T> implements IQuestionData<T> {

    private final String name;
    private T Value;

    // region Constructors

    /**
     * create a new Question Data Object with custom name
     *
     * @param name Name of Question data
     * @param value Value for this Question data
     */
    public QuestionData(String name, T value) {
        this.name = name;
        Value = value;
    }

    /**
     * create an empty Question Data Object with custom name
     *
     * @param name Name of Question data
     */
    public QuestionData(String name) {
        this(name, null);
    }

    // endregion

    /**
     * get Name of this data Object
     *
     * @return Name of data Object
     */
    @Override
    public String getName() {
        return name;
    }

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
