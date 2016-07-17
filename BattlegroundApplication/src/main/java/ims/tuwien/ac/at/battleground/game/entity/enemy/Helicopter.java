package ims.tuwien.ac.at.battleground.game.entity.enemy;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import ims.tuwien.ac.at.battleground.R;
import ims.tuwien.ac.at.battleground.game.entity.HittableObject;

/**
 * This class represents a flying Helicopter-Enemy in the Game. It extends the
 * {@link ims.tuwien.ac.at.battleground.game.entity.enemy.Enemy} class.
 *
 * @author hoch
 * @author schueller
 */
public class Helicopter extends Enemy
{
    /**
     * The Constructor of the Helicopter
     * @param x the x-coordinate
     * @param y the y-coordinate
     */
    public Helicopter(float x, float y)
    {
        super(x, y, R.drawable.helicopter);

        setHitPointsMax(150);
        setHitPoints(getHitPointsMax());

        setSpeed(90);
        setMoneyValue(20);
        setScoreValue(10);
        setFlying(true);
    }

    /**
     * Updates the position and the animation of itself.
     *
     * @param tpf the time per frame that has passed
     */
    public void update(float tpf)
    {
        this.moveTowards((int) (this.getSpeed() * tpf), 0);
        this.updateAnimation(true);
        super.update(tpf);
    }
}