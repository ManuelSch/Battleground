package ims.tuwien.ac.at.battleground.game.entity.bullet;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import ims.tuwien.ac.at.battleground.R;
import ims.tuwien.ac.at.battleground.util.DimensionUtil;
import ims.tuwien.ac.at.battleground.util.SoundManager;

/**
 * Represents a FlakBullet which is shot at flying enemies. It
 * extends the {@link ims.tuwien.ac.at.battleground.game.entity.bullet.Bullet} class.
 *
 * @author hoch
 * @author schueller
 */
public class FlakBullet extends Bullet
{
    /**
     * The constructor of the FlakBullet
     *
     * @param x the x-coordinate of the object
     * @param y the y-coordinate of the object
     * @param xDest the x-coordinate of the destination
     * @param yDest the y-coordinate of the destination
     * @param shootsEnemies specifies whether the bullet was shot by a building or not
     */
    public FlakBullet(float x, float y, float xDest, float yDest, boolean shootsEnemies)
    {
        super(x, y, R.drawable.flakbullet, xDest, yDest, shootsEnemies);
        this.speedTotal = (int)DimensionUtil.SCALE_X(0.2f)*10;
        this.damage = 5;
        this.shootsFlying = true;
        SOUND_MANAGER.playSound(R.raw.flakbullet, (float) (Math.random() * 0.75 + 0.5));
    }
}

