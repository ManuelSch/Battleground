package ims.tuwien.ac.at.battleground.game.entity.bullet;

import android.content.Context;

import ims.tuwien.ac.at.battleground.R;
import ims.tuwien.ac.at.battleground.util.DimensionUtil;

/**
 * Represents a Rocket which is shot at enemies. It
 * extends the {@link ims.tuwien.ac.at.battleground.game.entity.bullet.Bullet} class.
 *
 * @author schueller
 * @author hoch
 */
public class Rocket extends Bullet
{
    /**
     * The constructor of the Rocket
     *
     * @param x the x-coordinate of the object
     * @param y the y-coordinate of the object
     * @param xDest the x-coordinate of the destination
     * @param yDest the y-coordinate of the destination
     * @param shootsEnemies specifies whether the bullet was shot by a building or not
     */
    public Rocket(float x, float y, float xDest, float yDest, boolean shootsEnemies)
    {
        super(x, y, R.drawable.rocket, xDest, yDest, shootsEnemies);
        this.speedTotal = (int) DimensionUtil.SCALE_X(0.2f)*10;
        this.damage = 35;
        this.shootsFlying = false;
        SOUND_MANAGER.playSound(R.raw.rocket, (float) (Math.random() * 0.75 + 0.5));
    }
}
