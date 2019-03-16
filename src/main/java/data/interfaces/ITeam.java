package data.interfaces;

/**
 * A Quiz Game could be played by multiple Teams. Each Team have a Name and Points.
 */
public interface ITeam {

    /**
     * add some Points
     *
     * @param points amount of Points
     */
    void addPoints(int points);

    /**
     * get Team Name
     *
     * @return Team name
     */
    String getName();

    /**
     * set the Team Name
     *
     * @param name Team name
     */
    void setName(String name);

    /**
     * get Team Points
     *
     * @return Team Points
     */
    int getPoints();

    /**
     * set Team points
     *
     * @param points Team points
     */
    void setPoints(int points);

}
