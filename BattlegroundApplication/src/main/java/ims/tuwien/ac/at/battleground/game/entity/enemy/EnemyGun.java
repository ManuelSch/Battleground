package ims.tuwien.ac.at.battleground.game.entity.enemy;

import android.os.Build;

import java.sql.SQLOutput;
import java.util.ArrayList;

import ims.tuwien.ac.at.battleground.game.entity.DrawableObject;
import ims.tuwien.ac.at.battleground.game.entity.building.Building;

/**
 * This class acts as the superclass for all gun implementations of enemies
 * in the application. It handles firing rate, damage, range...
 *
 * @author schueller
 * @author hoch
 */
public abstract class EnemyGun extends DrawableObject {

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
     * an internal counter that checks if the firingRate has been reached
     */
    protected double LastShotTime;
    /**
     * The base of the gun
     */
    private Enemy body;
    /**
     * the Building which is currently under attack by this gun
     */
    protected Building currentTarget = null;

    /**
     * Constructor of the EnemyGun
     *
     * @param x the x-coordinate of the object
     * @param y the y-coordinate of the object
     * @param bitmapId the id of the bitmap to be loaded
     * @param body the base of the gun
     */
    public EnemyGun(float x, float y, int bitmapId, Enemy body)
    {
        super(x, y, bitmapId);
        this.setAngle(0);
        this.body = body;

        this.LastShotTime = 0;
    }

    /**
     * Updates the tower gun. It updates the position, checks if buildings are in range and sets
     * an angle if necessary.
     * @param tpf The timePerFrame that has passed
     */
    @Override
    public void update(float tpf)
    {
        //Position mit dem Parent abgleichen:
        this.setX(body.getX());
        this.setY(body.getY());

        // check if we can shoot
        ArrayList<Building> allBuildings = PF.getAllBuildings();
        if (allBuildings.size() != 0) {
            if (currentTarget == null) {
                for (Building building : allBuildings) {
                    if (isBuildingInRange(building) == true) {
                        currentTarget = building;
                        break;
                    }
                }
            } else {
                if (allBuildings.contains(currentTarget) && isBuildingInRange(currentTarget)) {
                    this.setAngle(this.calcAngle(this.getX(), this.getY(), currentTarget.getX(), currentTarget.getY()));

                    double currentTime = System.currentTimeMillis();
                    if (LastShotTime + getFiringRate() < currentTime) {
                        shootBuilding(currentTarget);
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
     * Checks wehter a given building is in range of the gun or not.
     * @param building the building to be checked
     * @return true if the building is in range, false otherwise
     */
    public boolean isBuildingInRange(Building building)
    {
        float dx = this.getX() - building.getX();
        float dy = this.getY() - building.getY();

        return (this.getRange()*this.getRange()) >= (dx*dx + dy*dy);
    }

    /**
     * Shoots at a given building.
     * @param building the building to be shot at
     */
    public abstract void shootBuilding(Building building);

    /**
     * Returns the firing rate of the gun
     * @return the firing rate of the gun
     */
    public double getFiringRate()
    {
        return firingRate;
    }

    /**
     * Sets the firing rate of the gun
     * @param firingRate the firing rate of the gun
     */
    public void setFiringRate(double firingRate)
    {
        this.firingRate = firingRate;
    }

    /**
     * Returns the damage that the gun deals
     * @return the damage that the gun deals
     */
    public double getDamage()
    {
        return damage;
    }

    /**
     * Sets the damage that the gun deals
     * @param damage the damage that the gun deals
     */
    public void setDamage(double damage)
    {
        this.damage = damage;
    }

    /**
     * Returns the range of the gun
     * @return the range of the gun
     */
    public double getRange()
    {
        return range;
    }

    /**
     * Sets the range of the gun
     * @param range the range of the gun
     */
    public void setRange(double range)
    {
        this.range = range;
    }
}
