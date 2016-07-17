package ims.tuwien.ac.at.battleground.game.entity.enemy;

import ims.tuwien.ac.at.battleground.R;
import ims.tuwien.ac.at.battleground.game.entity.building.Building;
import ims.tuwien.ac.at.battleground.game.entity.bullet.Laser;
import ims.tuwien.ac.at.battleground.game.entity.bullet.Rocket;
import ims.tuwien.ac.at.battleground.util.DimensionUtil;


/**
 * This class represents the gun that is attached to
 * the {@link ims.tuwien.ac.at.battleground.game.entity.enemy.Buggy}.
 * It basically handles the firing rate and range of the gun.
 *
 * @author schueller
 * @author hoch
 */
public class BuggyGun extends EnemyGun {

    private Buggy parent;

    /**
     * The constructor of the FlakTowerGun
     *
     * @param x the x-coordinate of the object
     * @param y the y-coordinate of the object
     * @param body the base of the gun
     */
    public BuggyGun(float x, float y, Buggy body)
    {
        super(x, y, R.drawable.buggy_gun, body);

        setFiringRate(200);        //Feuerrate in ms
        setRange(DimensionUtil.SCALE_X(0.3f));
    }

    /**
     * Shoots at the corresponding building. A {@link Laser} is created
     * and added to the playing field
     *
     * @param building The building to be shot at
     */
    public void shootBuilding(Building building)
    {
        PF.addObject(new Laser(this.getX(), this.getY(), building.getX(), building.getY(),false));
    }
}
