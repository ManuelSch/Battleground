package ims.tuwien.ac.at.battleground.game.entity.building;

import ims.tuwien.ac.at.battleground.R;
import ims.tuwien.ac.at.battleground.game.entity.bullet.FlakBullet;
import ims.tuwien.ac.at.battleground.game.entity.bullet.Laser;
import ims.tuwien.ac.at.battleground.game.entity.enemy.Enemy;
import ims.tuwien.ac.at.battleground.util.DimensionUtil;

/**
 * This class represents the gun that is attached to
 * the {@link ims.tuwien.ac.at.battleground.game.entity.building.Turret}.
 * It basically handles the firing rate and range of the gun.
 *
 * @author schueller
 */
public class TurretGun extends TowerGun {

    /**
     * The constructor of the TurretGun
     *
     * @param x the x-coordinate of the object
     * @param y the y-coordinate of the object
     */
    public TurretGun(float x, float y)
    {
        super(x, y, R.drawable.turret_gun);

        setFiringRate(200);        //Feuerrate in ms
        setRange(DimensionUtil.SCALE_X(0.35f));
        setShootsFlying(false);
    }

    /**
     * Shoots at the passed enemy. A {@link FlakBullet} is created
     * and added to the playing field
     *
     * @param enemy The enemy to be shot
     */
    public void shootEnemy(Enemy enemy)
    {
        PF.addObject(new Laser(this.getX(), this.getY(), enemy.getX(), enemy.getY(),true));
    }
}
