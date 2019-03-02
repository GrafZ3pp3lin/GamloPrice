package data.interfaces;

import java.util.List;

/**
 * Represent a Game with different Categories.
 *
 * @author Johannes
 */
public interface IGame {

    /**
     * get Name of Game
     *
     * @return Name
     */
    String getName();

    /**
     * get Path to XML-Document
     *
     * @return absolut Path
     */
    String getPath();

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
