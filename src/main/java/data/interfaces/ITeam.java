package data.interfaces;

import java.io.Serializable;

/**
 * A Quiz Game could be played by multiple Teams. Each Team have a Name and Points.
 */
public interface ITeam extends Serializable {

    /**
     * set the Team Name
     *
     * @param name Team name
     */
    void setName(String name);

    /**
     * get Team Name
     *
     * @return Team name
     */
    String getName();

    /**
     * set Team points
     *
     * @param points Team points
     */
    void setPoints(int points);

    /**
     * add some Points
     *
     * @param points amount of Points
     */
    void addPoints(int points);

    /**
     * get Team Points
     *
     * @return Team Points
     */
    int getPoints();

}
