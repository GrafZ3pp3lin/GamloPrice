package data;

import data.interfaces.ICategory;
import data.interfaces.IGame;

import java.util.List;

/**
 * Represent a Game with different Categories.
 *
 * @author Johannes
 */
public class Game implements IGame {

    private String Name;
    private String Path;
    private List<ICategory> Categories;

    /**
     * get Name of Game
     *
     * @return Name
     */
    @Override
    public String getName() {
        return Name;
    }

    /**
     * get Path to XML-Document
     *
     * @return absolut Path
     */
    @Override
    public String getPath() {
        return Path;
    }

    /**
     * get all Categories of this Game
     *
     * @return all Categories
     */
    @Override
    public List<ICategory> getCategories() {
        return Categories;
    }

    /**
     * add a Category with a unique Name
     *
     * @param category Category to add
     */
    @Override
    public void addCategory(ICategory category) {
        Categories.add(category);
    }

    /**
     * get a Category by its Name
     *
     * @param name Name of the Category
     * @return specified Category
     */
    @Override
    public ICategory getCategory(String name) {
        //TODO implement
        throw new UnsupportedOperationException();
    }
}
