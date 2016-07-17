package ims.tuwien.ac.at.battleground.game.entity.enemy;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import java.util.Arrays;
import java.util.List;

import ims.tuwien.ac.at.battleground.R;
import ims.tuwien.ac.at.battleground.game.Game;
import ims.tuwien.ac.at.battleground.game.GameSurfaceView;
import ims.tuwien.ac.at.battleground.game.entity.DrawableObject;
import ims.tuwien.ac.at.battleground.game.entity.HittableObject;

/**
 * This class represents an Enemy in the Game. It extends the
 * HittableObject class and thus can be shot at.
 *
 * @author hoch
 * @author schueller
 */
public abstract class Enemy extends HittableObject implements Comparable<Enemy>
{
    private double speed;
    private int moneyValue;
    private int scoreValue;
    private boolean flying;
    /**
     * Specifies the gun that is attached to the enemy
     */
    protected EnemyGun enemyGun = null;
    /**
     * Specifies whether the enemy is blocked or not
     */
    protected boolean blocked = false;

    /**
     * The Constructor of the Enemy
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @param bitmapId the id of the corresponding bitmap
     */
    public Enemy(float x, float y, int bitmapId)
    {
        super(x, y, bitmapId);
        this.setAngle(0);
    }

    /**
     * Updates the position of itself as well as
     * the position of the attached gun
     *
     * @param tpf the time per frame that has passed
     */
    @Override
    public void update(float tpf)
    {
        super.update(tpf);
        if(this.enemyGun != null)
        {
            this.enemyGun.setX(this.getX());
            this.enemyGun.setY(this.getY());
            this.enemyGun.update(tpf);
        }
    }

    /**
     * Destroys the enemy and the attached gun. In addition the
     * corresponding score and money values are added to the player.
     */
    @Override
    public void die() {
        super.die();
        if(this.enemyGun != null)
        {
            this.enemyGun.die();
        }
        GAME.addScore(this.scoreValue);
        GAME.addMoney(this.moneyValue);
    }

    //Wird verwendet, um die PF-ArrayList "allEnemies" nach den X-Koordinaten aller Gegner zu
    //sortieren. (Gegner weiter rechts am Spielfeld kommen zuerst)
    /**
     * Is used to compare enemies to each other. In this case the x-coordinate of the
     * enemies are compared and sorted accordingly.
     *
     * @param other the enemy to be compared
     * @return -1,0,1 depending on the x-coordinates
     */
    @Override
    public int compareTo(Enemy other)
    {
        return -1*(int)(other.getX() - this.getX());
    }

    /**
     * Returns the speed of the enemy
     * @return the speed of the enemy
     */
    public double getSpeed()
    {
        return speed;
    }

    /**
     * Sets the speed of the enemy
     * @param speed the speed of the enemey
     */
    public void setSpeed(double speed)
    {
        this.speed = speed;
    }

    /**
     * Returns the money value of the enemy
     * @return the money value of the enemy
     */
    public double getMoneyValue()
    {
        return moneyValue;
    }

    /**
     * Sets the money value of the enemy
     * @param moneyValue the money value of the enemey
     */
    public void setMoneyValue(int moneyValue)
    {
        this.moneyValue = moneyValue;
    }

    /**
     * Returns the score that the enemy provides
     * @return the score that the enemy provides
     */
    public double getScoreValue()
    {
        return scoreValue;
    }

    /**
     * Sets the score value of the enemy
     * @param scoreValue the score value of the enemy
     */
    public void setScoreValue(int scoreValue)
    {
        this.scoreValue = scoreValue;
    }

    /**
     * Returns if the enemy is flying or not
     * @return true if the enemy is flying, false otherwise
     */
    public boolean isFlying()
    {
        return flying;
    }

    /**
     * Sets whether the enemy is flying or not
     * @param flying true if the enemy is flying, false otherwise
     */
    public void setFlying(boolean flying)
    {
        this.flying = flying;
    }


    /*
    public void move(float tpf, int screenWidth) {
        float enemyDistanceInTotal = 0;
        int enemyDistance = Math.round(enemyDistanceInTotal * tpf);

        int newLeft = 0;
        int newRight = 0;

        if(this.direction == 0) {
            newLeft = destRect.left + enemyDistance;
            newRight = destRect.right + enemyDistance;
        }

        if(this.direction == 1) {
            newLeft = destRect.left - enemyDistance;
            newRight = destRect.right - enemyDistance;
        }

        if(screenWidth < newRight) {
            direction = 1;
        }
        if(newLeft < 0) {
            direction = 0;
        }

        this.destRect = new Rect(newLeft, this.getDestRect().top, newRight, this.getDestRect().top + this.bitmap.getHeight());
    }*/


    /**
     * Returns a list with all subparts of the enemy. In this case it is
     * a list of the attached gun.
     * @return a list of all subparts of the enemy.
     */
    @Override
    public List<DrawableObject> getSubParts() {
        return Arrays.asList((DrawableObject) this.enemyGun);
    }
}
