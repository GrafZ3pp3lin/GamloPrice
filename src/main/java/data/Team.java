package data;

import data.interfaces.ITeam;

/**
 * A Quiz Game could be played by multiple Teams. Each Team have a name and points.
 */
public class Team implements ITeam {

    private String name;
    private int points;

    // region Constructor

    /**
     * Constructor with Team name
     *
     * @param name name of the Team
     */
    public Team(String name) {
        this.name = name;
    }

    /**
     * empty Constructor
     */
    public Team() {
    }

    // endregion

    /**
     * add some points
     *
     * @param points amount of points
     */
    @Override
    public void addPoints(int points) {
        this.points += points;
    }

    /**
     * get Team name
     *
     * @return Team name
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * set the Team name
     *
     * @param name Team name
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * get Team points
     *
     * @return Team points
     */
    @Override
    public int getPoints() {
        return points;
    }

    /**
     * set Team points
     *
     * @param points Team points
     */
    @Override
    public void setPoints(int points) {
        this.points = points;
    }
}
