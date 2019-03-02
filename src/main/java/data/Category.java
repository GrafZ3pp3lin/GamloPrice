package data;

import data.interfaces.ICategory;
import data.interfaces.IQuestion;

import java.util.ArrayList;
import java.util.List;

/**
 * Set of Questions within a Game with the same Category
 */
public class Category implements ICategory {

    private String Name;
    private List<IQuestion> Questions;

    // region Constructors

    /**
     * Constructor with name of Category
     *
     * @param name Name of Category
     */
    public Category(String name) {
        this();
        Name = name;
    }

    /**
     * empty Constructor
     */
    public Category() {
        Questions = new ArrayList<>();
    }

    // endregion

    /**
     * get Category Name
     *
     * @return Category Name
     */
    @Override
    public String getName() {
        return Name;
    }

    /**
     * set Name of Category
     *
     * @param name Name of Category
     */
    @Override
    public void setName(String name) {
        this.Name = name;
    }

    /**
     * Get all Questions of this Category
     *
     * @return List with all Questions
     */
    @Override
    public List<IQuestion> getQuestions() {
        return Questions;
    }

    /**
     * add a Question to this Category. Value of Questions must be unique!
     *
     * @param question Question that will be added
     */
    @Override
    public void addQuestion(IQuestion question) {
        Questions.add(question);
    }

    /**
     * get a Question by value.
     *
     * @param value Value of Question
     * @return Question with specified Value
     */
    @Override
    public IQuestion getQuestions(int value) {
        //TODO implement
        throw new UnsupportedOperationException();
    }
}
