package ims.tuwien.ac.at.battleground.game.entity.building;

import android.content.Context;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ims.tuwien.ac.at.battleground.game.entity.DrawableObject;
import ims.tuwien.ac.at.battleground.game.entity.enemy.Enemy;

/**
 * Class that handles Towers in the application.
 * It is used as an abstract super class for all Towers
 * in the overall game. Each Tower has attributes such as a
 * firingRate or a range.
 *
 * @author hoch
 * @author schueller
 */
public abstract class Tower extends Building
{
    /**
     * The gun that is attached to the Tower
     */
    protected TowerGun towerGun;

    /**
     * Constructor of the Tower
     *
     * @param x the x-coordinate of the object
     * @param y the y-coordinate of the object
     * @param bitmapId the id of the bitmap to be loaded
     */
    public Tower(float x, float y, int bitmapId)
    {
        super(x, y, bitmapId);
    }

    /**
     * Updates the current object. This method increases the internal
     * reloadCounter and checks if an enemy is in range and can be shot at.
     * @param tpf The timePerFrame that has passed
     */
    @Override
    public void update(float tpf)
    {
        super.update(tpf);
        this.towerGun.setX(this.getX());
        this.towerGun.setY(this.getY());
        this.towerGun.update(tpf);
    }

    /**
     * Kills the object and frees the resources.
     * Additionally the gun of the tower is destroyed as well.
     */
    @Override
    public void die()
    {
        super.die();
        if(this.towerGun != null)
        {
            this.towerGun.die();
        }
    }

    /**
     * Returns a list with all subparts of the drawable object.
     * In this case it is a list of the attached TowerGun
     *
     * @return a list with all subparts of the drawable object
     */
    @Override
    public List<DrawableObject> getSubParts() {
        return Arrays.asList((DrawableObject)this.towerGun);
    }
}
