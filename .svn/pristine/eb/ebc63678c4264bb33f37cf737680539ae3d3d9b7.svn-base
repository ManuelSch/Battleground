package ims.tuwien.ac.at.battleground.game.entity.enemy;

import ims.tuwien.ac.at.battleground.R;
import ims.tuwien.ac.at.battleground.game.entity.building.Building;
import ims.tuwien.ac.at.battleground.game.entity.bullet.FlakBullet;
import ims.tuwien.ac.at.battleground.game.entity.bullet.Rocket;
import ims.tuwien.ac.at.battleground.util.DimensionUtil;

/**
 * This class represents the gun that is attached to
 * the {@link ims.tuwien.ac.at.battleground.game.entity.enemy.Tank}.
 * It basically handles the firing rate and range of the gun.
 *
 * @author schueller
 * @author hoch
 */
public class TankGun extends EnemyGun {

    /**
     * The constructor of the TankGun
     *
     * @param x the x-coordinate of the object
     * @param y the y-coordinate of the object
     * @param body the base of the gun
     */
    public TankGun(float x, float y, Tank body)
    {
        super(x, y, R.drawable.tank_gun, body);

        setFiringRate(1000);        //Feuerrate in ms
        setRange(DimensionUtil.SCALE_X(0.4f));
    }

    /**
     * Shoots at the corresponding building. A {@link Rocket} is created
     * and added to the playing field
     *
     * @param building The building to be shot at
     */
    public void shootBuilding(Building building)
    {
        PF.addObject(new Rocket(this.getX(), this.getY(), building.getX(), building.getY(),false));
    }
}
