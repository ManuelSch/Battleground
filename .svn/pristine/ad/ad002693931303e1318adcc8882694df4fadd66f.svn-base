package ims.tuwien.ac.at.battleground.game.entity.building;

import android.graphics.Canvas;

import java.util.ArrayList;

import ims.tuwien.ac.at.battleground.game.entity.DrawableObject;
import ims.tuwien.ac.at.battleground.game.entity.enemy.Enemy;

/**
 * This class acts as the superclass for all gun implementations of buildings
 * in the application. It handles firing rate, damage, range...
 *
 * @author schueller
 */
public abstract class TowerGun extends DrawableObject {

    /**
     * specifies the time in between two shots (stored in ms).
     */
    private double firingRate;
    /**
     * specifies the damage that this Tower deals with a single shot
     */
    private double damage;
    /**
     * specifies the range of the tower (in units)
     */
    private double range;
    /**
     * specifies if this tower can shoot at flying objects (true if it can, false otherwise)
     */
    private boolean shootsFlying;
    /**
     * an internal counter that checks if the firingRate has been reached
     */
    protected double LastShotTime;
    /**
     * the Enemy which is currently under attack by this gun
     */
    protected Enemy currentTarget = null;

    /**
     * Constructor of the Tower
     *
     * @param x        the x-coordinate of the object
     * @param y        the y-coordinate of the object
     * @param bitmapId the id of the bitmap to be loaded
     */
    public TowerGun(float x, float y, int bitmapId) {
        super(x, y, bitmapId);

        this.LastShotTime = 0;
    }

    /**
     * Updates the current object. This method increases the internal
     * reloadCounter and checks if an enemy is in range and can be shot at.
     *
     * @param tpf The timePerFrame that has passed
     */
    public void update(float tpf) {
        // check if we can shoot
        ArrayList<Enemy> allEnemies = PF.getAllEnemies();
        if (allEnemies.size() != 0) {
            if (currentTarget == null) {
                for (Enemy enemy : allEnemies) {
                    if (enemy.isFlying() == this.isShootsFlying()) {
                        if (isEnemyInRange(enemy) == true) {
                            currentTarget = enemy;
                            break;
                        }
                    }
                }
            } else {
                if (allEnemies.contains(currentTarget) && isEnemyInRange(currentTarget)) {
                    this.setAngle(this.calcAngle(this.getX(), this.getY(), currentTarget.getX(), currentTarget.getY()));

                    double currentTime = System.currentTimeMillis();
                    if (LastShotTime + getFiringRate() < currentTime) {
                        shootEnemy(currentTarget);
                        LastShotTime = currentTime;
                        this.aAnimate = true;
                    }
                }
                else {
                    currentTarget = null;
                }
            }
        }


        // reload animation
        if(aAnimate == true) {
            this.updateAnimation(false);
        }
        else {
            aCurrentFrame = 0;
        }
    }

    /**
     * Checks wether a given Enemy is in range and
     * can be shot at or not
     *
     * @param enemy The enemy that should be checked
     * @return true if the enemy is in range, false otherwise
     */
    public boolean isEnemyInRange(Enemy enemy)
    {
        float dx = this.getX() - enemy.getX();
        float dy = this.getY() - enemy.getY();

        return (this.getRange()*this.getRange()) >= (dx*dx + dy*dy);
    }

    /**
     * Checks whether an {@Link Enemy} is in range or not. If the {@Link Enemy} is
     * in range the tower fires a bullet.
     *
     * @param enemy The enemy to be checked
     * @return True if the enemy is in range and can be shot at, false if otherwise
     */
    public abstract void shootEnemy(Enemy enemy);

    /**
     * Gets the time in between two shots (stored in ms).
     * @return The time in between two shots (stored in ms).
     */
    public double getFiringRate()
    {
        return firingRate;
    }

    /**
     * Sets the time in between two shots (stored in ms).
     * @param firingRate The time in between two shots (stored in ms).
     */
    public void setFiringRate(double firingRate)
    {
        this.firingRate = firingRate;
    }

    /**
     * Gets the damage that this Tower deals with a single shot
     * @return The damage that this Tower deals with a single shot
     */
    public double getDamage()
    {
        return damage;
    }

    /**
     * Sets the damage that this Tower deals with a single shot
     * @param damage The damage that this Tower deals with a single shot
     */
    public void setDamage(double damage)
    {
        this.damage = damage;
    }

    /**
     * Gets the range of the tower (in unit)
     * @return The range of the tower (in unit)
     */
    public double getRange()
    {
        return range;
    }

    /**
     * Sets the range of the tower (in pixel)
     * @param range The range of the tower (in pixel)
     */
    public void setRange(double range)
    {
        this.range = range;
    }

    /**
     * Returns whether the Tower can shoot flying enemies or not
     * @return True if the tower can shoot flying enemies, false otherwise
     */
    public boolean isShootsFlying()
    {
        return shootsFlying;
    }

    /**
     * Sets if the gun is able to shoot flying enemies
     * @param shootsFlying True for being able to shoot flying enemies, false otherwise
     */
    public void setShootsFlying(boolean shootsFlying)
    {
        this.shootsFlying = shootsFlying;
    }
}
