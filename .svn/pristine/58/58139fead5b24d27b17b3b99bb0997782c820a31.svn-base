package ims.tuwien.ac.at.battleground.game.entity.building;

import android.content.Context;

import ims.tuwien.ac.at.battleground.R;
import ims.tuwien.ac.at.battleground.game.util.BuildingType;
import ims.tuwien.ac.at.battleground.game.util.Constants;

/**
 * Represents a Turret. It
 * extends the {@link ims.tuwien.ac.at.battleground.game.entity.building.Tower} class.
 *
 * @author schueller
 */
public class Turret extends Tower
{
    /**
     * The constructor of the Turret
     *
     * @param x the x-coordinate of the object
     * @param y the y-coordinate of the object
     */
    public Turret(float x, float y)
    {
        super(x, y, R.drawable.turret_body);
        this.towerGun = new TurretGun(getX(),getY());
        this.setBuildingType(BuildingType.TURRET);

        setHitPointsMax(100);
        setHitPoints(getHitPointsMax());

        setCosts(Constants.TURRET_PRICE);
    }
}