package data;

import data.interfaces.ICategory;
import data.interfaces.IGame;

import java.util.ArrayList;
import java.util.List;

/**
 * Represent a Game with different Categories.
 */
public class Game implements IGame {

    private String Name;
    private String Path;
    private int Amount;
    private List<ICategory> Categories;

    // region Constructors

    /**
     * Constructor with name of Game and amount of Questions in each Category
     *
     * @param name   Name of Game
     * @param amount Amount of Questions in each Category
     */
    public Game(String name, int amount) {
        Name = name;
        Amount = amount;
        Categories = new ArrayList<>();
    }

    /**
     * Constructor with Game Name
     *
     * @param name Name of the Game
     */
    public Game(String name) {
        this(name, 5);
    }

    /**
     * empty Constructor
     */
    public Game() {
        this("Unnamed Game", 5);
    }

    // endregion

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
     * set Name of Game
     *
     * @param name Name of Game
     */
    @Override
    public void setName(String name) {

    }

    /**
     * get Path to Game Data File
     *
     * @return absolute Path
     */
    @Override
    public String getPath() {
        return Path;
    }

    /**
     * set Path to XML-Document
     *
     * @param path Path to XML-Document
     */
    @Override
    public void setPath(String path) {

    }

    /**
     * get Amount of Questions in each Category
     *
     * @return Amount of Questions
     */
    @Override
    public int getQuestionAmount() {
        return Amount;
    }

    /**
     * set Amount of Questions in each Category
     *
     * @param amount Amount of Questions
     */
    @Override
    public void setQuestionAmount(int amount) {
        this.Amount = amount;
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
