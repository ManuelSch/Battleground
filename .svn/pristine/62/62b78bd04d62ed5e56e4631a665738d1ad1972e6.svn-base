package ims.tuwien.ac.at.battleground.game.entity;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import ims.tuwien.ac.at.battleground.BR;
import ims.tuwien.ac.at.battleground.entity.Score;
import ims.tuwien.ac.at.battleground.game.util.Constants;

/**
 * Entity class for a single Player. Contains all necessary data to specify
 * the Player object (such as name, money or score)
 *
 * @author hoch
 */
public class Player extends BaseObservable {

    /**
     * The name of the player
     */
    private String name;
    /**
     * The money that the player has
     */
    private double money;
    /**
     * The current score of the player
     */
    private Score score;

    /**
     * The current number of lives
     */
    private int lives;

    /**
     * Constructor of the Player
     * @param name The name of the player
     */
    public Player(String name) {
        this.name = name;
        this.money = Constants.PLAYER_INITIAL_MONEY;
        this.lives = 3;
        this.score = new Score(name, 0);
    }

    /**
     * Returns the name of the player
     * @return The name of the player
     */
    @Bindable
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the player
     * @param name The name of the player
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the current money of the player
     * @return The current money of the player
     */
    @Bindable
    public double getMoney() {
        return money;
    }

    /**
     * Sets the money for the player
     * @param money The money for the player
     */
    public void setMoney(int money) {
        this.money = money;
    }

    /**
     * Returns the score of the player
     * @return The score of the player
     */
    @Bindable
    public int getScore() {
        return score.getScore();
    }

    /**
     * Adds the score to the player
     * @param score The score to add to the player
     */
    public void addScore(int score) {
        this.score.addScore(score);
        notifyPropertyChanged(BR.score);
    }

    /**
     * Adds the money to the player
     * @param money The money to add to the player
     */
    public void addMoney(double money) {
        this.money += money;
        notifyPropertyChanged(BR.money);
    }

    /**
     * Subtracts money from the player
     * @param money The money to be subtracted
     */
    public void subtractMoney(double money) {
        this.money -= money;
        notifyPropertyChanged(BR.money);
    }

    /**
     * Resets the money value of the player
     */
    public void resetMoney() {
        if(this.money < Constants.PLAYER_INITIAL_MONEY) {
            this.money = Constants.PLAYER_INITIAL_MONEY;
            notifyPropertyChanged(BR.money);
        }
    }

    /**
     * Checks whether the player has enough money to create a building
     * @param buildingPrice The price of the building
     * @return true if the player has enough money, false otherwise
     */
    public boolean checkEnoughMoney(double buildingPrice) {
        if((this.money - buildingPrice) >= 0) {
            return true;
        }
        return false;
    }

    /**
     * Returns the number of lives
     * @return The number of lives
     */
    @Bindable
    public int getLives() {
        return this.lives;
    }

    /**
     * Adds one life to the Player
     */
    public void addLife() {
        this.lives++;
        notifyPropertyChanged(BR.lives);
    }

    /**
     * Removes one life from the Player
     */
    public void loseLife() {
        this.lives--;
        notifyPropertyChanged(BR.lives);
    }
}
