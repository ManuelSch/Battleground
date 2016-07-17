package ims.tuwien.ac.at.battleground.game.entity.bullet;

import android.content.Context;
import android.graphics.Rect;

import java.sql.SQLOutput;

import ims.tuwien.ac.at.battleground.game.entity.DrawableObject;
import ims.tuwien.ac.at.battleground.game.entity.building.Building;
import ims.tuwien.ac.at.battleground.game.entity.enemy.Enemy;

/**
 * Class that handles Bullets in the application.
 * It is used as an abstract super class for all Bullets
 * in the overall game. Each Bullet has attributes such as a
 * damage or speed.
 *
 * @author hoch
 * @author schueller
 */
public abstract class Bullet extends DrawableObject
{
    /**
     * Specifies the speed of the Bullet
     */
    protected int speedTotal;
    /**
     * Specifies the damage that the Bullet deals
     */
    protected int damage;
    /**
     * Specifies the x-coordinate of the destination
     */
    private float xDest;
    /**
     * Specifies the y-coordinate of the destination
     */
    private float yDest;

    /**
     * Specifies whether this Bullet hits flying enemies or not
     */
    protected boolean shootsFlying;

    /**
     * Specifies whether the bullet was shot from a building or not
     */
    protected boolean shootsEnemies;

    /**
     * The constructor of the Bullet
     *
     * @param x the x-coordinate of the object
     * @param y the y-coordinate of the object
     * @param bitmapId the id of the bitmap to be loaded
     * @param xDest the x-coordinate of the destination
     * @param yDest the y-coordinate of the destination
     * @param shootsEnemies specifies whether the bullet was shot by a building or not
     */
    public Bullet(float x, float y, int bitmapId, float xDest, float yDest, boolean shootsEnemies)
    {
        super(x, y, bitmapId);
        this.xDest = xDest;
        this.yDest = yDest;
        this.shootsEnemies = shootsEnemies;

        this.setAngle(calcAngle(x, y, xDest, yDest));
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void update(float tpf)
    {
        if(this.shootsEnemies==true)    //Bullet wurde von einem Gebaeude abgefeuert
        {
            if (PF.getAllEnemies().size() != 0) {
                for (Enemy enemy : PF.getAllEnemies()) {
                    if(this.shootsFlying == enemy.isFlying())
                    {
                        if(Rect.intersects(this.getDestRect(), enemy.getDestRect())) {
                            enemy.setHitPoints(enemy.getHitPoints() - this.damage);
                            this.die();
                            return;
                        }
                    }
                }
            }
        }
        else    //Bullet wurde von einem Gegner abgefeuert
        {
            if (PF.getAllBuildings().size() != 0) {
                for (Building building : PF.getAllBuildings()) {
                    if(Rect.intersects(this.getDestRect(), building.getDestRect())) {
                        building.setHitPoints(building.getHitPoints() - this.damage);
                        this.die();
                        return;
                    }
                }
            }
        }

        this.moveTowards(tpf);
    }

    /**
     * Moves the Bullet towards the enemy
     * @param tpf The time per frame that has passed
     */
    public void moveTowards(float tpf) {
        int speed = Math.round(this.speedTotal * tpf);
        this.moveTowards(speed, this.getAngle());
    }

    /**
     * Gets the x-coordinate of the destination
     * @return The x-coordinate of the destination
     */
    public float getXDest()
    {
        return xDest;
    }

    /**
     * Sets the y-coordinate of the destination
     * @return The y-coordinate of the destination
     */
    public float getYDest()
    {
        return yDest;
    }
}
