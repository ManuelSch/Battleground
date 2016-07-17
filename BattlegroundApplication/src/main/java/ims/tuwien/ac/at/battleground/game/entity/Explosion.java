package ims.tuwien.ac.at.battleground.game.entity;

import ims.tuwien.ac.at.battleground.R;

/**
 * This class represents an Explosion in the Game. It is
 * used during the death animation of enemies and buildings
 *
 * @author schueller
 */
public class Explosion extends DrawableObject
{
    /**
     * The constructor of the explosion
     * @param x the x-coordinate of the explosion
     * @param y the y-coordinate of the explosion
     */
    public Explosion(float x, float y)
    {
        super(x, y, R.drawable.explosion);
        this.aAnimate = true;
    }

    /**
     * Updates the explosion. It basically goes through all animation
     * steps (if not finished) or deletes the object.
     * @param tpf The timePerFrame that has passed
     */
    @Override
    public void update(float tpf)
    {
        if(aAnimate == true) {
            this.updateAnimation(false);
        }
        else {
            die();
        }
    }
}
