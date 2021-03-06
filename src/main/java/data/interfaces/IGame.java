package data.interfaces;

import java.io.Serializable;
import java.util.List;

/**
 * Represent a Game with different Categories.
 */
public interface IGame extends Serializable {

    /**
     * get Name of Game
     *
     * @return Name
     */
    String getName();

    /**
     * set Name of Game
     *
     * @param name Name of Game
     */
    void setName(String name);

    /**
     * get Path to XML-Document
     *
     * @return absolute Path
     */
    String getPath();

    /**
     * set Path to XML-Document
     *
     * @param path Path to XML-Document
     */
    void setPath(String path);

    /**
     * get Amount of Questions in each Category
     *
     * @return Amount of Questions
     */
    int getQuestionAmount();

    /**
     * set Amount of Questions in each Category
     *
     * @param amount Amount of Questions
     */
    void setQuestionAmount(int amount);

    /**
     * get all Categories of this Game
     *
     * @return all Categories
     */
    List<ICategory> getCategories();

    /**
     * add a Category with a unique Name
     *
     * @param category Category to add
     */
    void addCategory(ICategory category);

    /**
     * get a Category by its Name
     *
     * @param name Name of the Category
     * @return specified Category
     */
    ICategory getCategory(String name);

}
