package data;

import data.interfaces.ITeam;

/**
 * A Quiz Game could be played by multiple Teams. Each Team have a Name and Points.
 */
public class Team implements ITeam {

    private String Name;
    private int Points;

    // region Constructor

    /**
     * Constructor with Team Name
     *
     * @param name Name of the Team
     */
    public Team(String name) {
        Name = name;
    }

    /**
     * empty Constructor
     */
    public Team() {
    }

    // endregion

    /**
     * set the Team Name
     *
     * @param name Team name
     */
    @Override
    public void setName(String name) {
        Name = name;
    }

    /**
     * get Team Name
     *
     * @return Team name
     */
    @Override
    public String getName() {
        return Name;
    }

    /**
     * set Team points
     *
     * @param points Team points
     */
    @Override
    public void setPoints(int points) {
        Points = points;
    }

    /**
     * add some Points
     *
     * @param points amount of Points
     */
    @Override
    public void addPoints(int points) {
        Points += points;
    }

    /**
     * get Team Points
     *
     * @return Team Points
     */
    @Override
    public int getPoints() {
        return Points;
    }
}
