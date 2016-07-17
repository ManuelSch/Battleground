package ims.tuwien.ac.at.battleground.entity;

/**
 * Score is an entity class that is used as a means to keep the score
 * of a user. Additionally this entity class can be used in connection
 * with a database
 *
 * @author hoch
 * @author schueller
 */
public class Score {

    /**
     * The id of the score object
     */
    private long id;
    /**
     * The {@link String} instance representing the username associated with this score.
     */
    private String username;
    /**
     * The score value
     */
    private int score;

    /**
     *
     * @param username
     * @param score
     */
    public Score(String username, int score) {
        this.username = username;
        this.score = score;
    }

    /**
     * Gets the id of the score instance.
     * @return The id of the score instance
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the id of the score instance.
     * @param id The id of the score instance.
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Returns the {@link String} instance representing the username associated with this score.
     * @return The {@link String} username of this score.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the {@link String} instance representing the username associated with this score.
     * @param username The {@link String}  username of the score instance.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Returns the the score value associated with this score.
     * @return The score value associated with this score.
     */
    public int getScore() {
        return score;
    }

    /**
     * Sets the score value associated with this score.
     * @param score The score value associated with this score.
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Adds the score value associated with this score.
     * @param score The score value associated with this score.
     */
    public void addScore(int score) {
        this.score += score;
    }

    @Override
    public String toString() {
        return "Score{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", score=" + score +
                '}';
    }
}
