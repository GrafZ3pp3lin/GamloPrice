package data;

import service.QuestionLayoutProvider;
import data.interfaces.ICategory;
import data.interfaces.IGame;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Represent a Game with different categories.
 */
public class Game implements IGame {

    private UUID id;
    private String name;
    private String path;
    private int amount;

    private List<ICategory> categories;

    private QuestionLayoutProvider layoutProvider;

    // region Constructors

    /**
     * Constructor with name of Game and amount of Questions in each Category
     *
     * @param name   name of Game
     * @param amount amount of Questions in each Category
     */
    public Game(String name, int amount) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.amount = amount;
        categories = new ArrayList<>();
        // TODO init layoutProvider
        layoutProvider = new QuestionLayoutProvider();
    }

    /**
     * Constructor with Game name
     *
     * @param name name of the Game
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
     * get unique Id of this Game
     *
     * @return Game Id
     */
    @Override
    public UUID getId() {
        return id;
    }

    /**
     * get name of Game
     *
     * @return name
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * set name of Game
     *
     * @param name name of Game
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * get path to Game Data File
     *
     * @return absolute path
     */
    @Override
    public String getPath() {
        return path;
    }

    /**
     * set path to XML-Document
     *
     * @param path path to XML-Document
     */
    @Override
    public void setPath(String path) {
        // TODO tet for valid path
        this.path = path;
    }

    /**
     * get amount of Questions in each Category
     *
     * @return amount of Questions
     */
    @Override
    public int getQuestionAmount() {
        return amount;
    }

    /**
     * set amount of Questions in each Category
     *
     * @param amount amount of Questions
     */
    @Override
    public void setQuestionAmount(int amount) {
        this.amount = amount;
    }

    /**
     * get all categories of this Game
     *
     * @return all categories
     */
    @Override
    public List<ICategory> getCategories() {
        return categories;
    }

    /**
     * add a Category with a unique name
     *
     * @param category Category to add
     */
    @Override
    public void addCategory(ICategory category) {
        categories.add(category);
    }

    /**
     * get a Category by its name
     *
     * @param name name of the Category
     * @return specified Category
     */
    @Override
    public ICategory getCategory(String name) {
        //TODO implement
        throw new UnsupportedOperationException();
    }
}
