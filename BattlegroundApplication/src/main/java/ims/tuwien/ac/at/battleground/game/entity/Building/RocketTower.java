package ims.tuwien.ac.at.battleground.game.entity.building;

import android.content.Context;

import ims.tuwien.ac.at.battleground.R;
import ims.tuwien.ac.at.battleground.game.util.BuildingType;
import ims.tuwien.ac.at.battleground.game.util.Constants;

/**
 * Represents a RocketTower. It
 * extends the {@link ims.tuwien.ac.at.battleground.game.entity.building.Tower} class.
 *
 * @author hoch
 */
public class RocketTower extends Tower
{
    /**
     * The constructor of the RocketTower
     *
     * @param x the x-coordinate of the object
     * @param y the y-coordinate of the object
     */
    public RocketTower(float x, float y)
    {
        super(x, y, R.drawable.rockettower_body);
        this.towerGun = new RocketTowerGun(getX(),getY());
        this.setBuildingType(BuildingType.ROCKET_TOWER);

        setHitPointsMax(150);
        setHitPoints(getHitPointsMax());

        setCosts(Constants.ROCKET_TOWER_PRICE);
    }
}