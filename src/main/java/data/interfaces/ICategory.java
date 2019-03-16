package data.interfaces;

import java.util.List;

/**
 * Set of Questions within a Game with the same Category
 */
public interface ICategory {

    /**
     * add a Question to this Category. Value of Questions must be unique!
     *
     * @param question Question that will be added
     */
    void addQuestion(IQuestion question);

    /**
     * get Category Name
     *
     * @return Category Name
     */
    String getName();

    /**
     * set Name of Category
     *
     * @param name Name of Category
     */
    void setName(String name);

    /**
     * Get all Questions of this Category
     *
     * @return List with all Questions
     */
    List<IQuestion> getQuestions();

    /**
     * get a Question by value.
     *
     * @param value Value of Question
     * @return Question with specified Value
     */
    IQuestion getQuestions(int value);

}
