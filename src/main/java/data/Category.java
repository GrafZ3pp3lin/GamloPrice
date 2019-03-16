package data;

import data.interfaces.ICategory;
import data.interfaces.IQuestion;

import java.util.ArrayList;
import java.util.List;

/**
 * Set of questions within a Game with the same Category
 */
public class Category implements ICategory {

    private String name;
    private List<IQuestion> questions;

    // region Constructors

    /**
     * Constructor with name of Category
     *
     * @param name name of Category
     */
    public Category(String name) {
        this.name = name;
        questions = new ArrayList<>();
    }

    /**
     * empty Constructor
     */
    public Category() {
        this("Unnamed Category");
    }

    // endregion

    /**
     * add a Question to this Category. Value of questions must be unique!
     *
     * @param question Question that will be added
     */
    @Override
    public void addQuestion(IQuestion question) {
        questions.add(question);
    }

    /**
     * get Category name
     *
     * @return Category name
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * set name of Category
     *
     * @param name name of Category
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get all questions of this Category
     *
     * @return List with all questions
     */
    @Override
    public List<IQuestion> getQuestions() {
        return questions;
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
