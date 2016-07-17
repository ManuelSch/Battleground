package ims.tuwien.ac.at.battleground.game.entity.building;

import android.content.Context;

import ims.tuwien.ac.at.battleground.R;
import ims.tuwien.ac.at.battleground.game.util.BuildingType;
import ims.tuwien.ac.at.battleground.game.util.Constants;

/**
 * Represents a Wall in the game. It
 * extends the {@link ims.tuwien.ac.at.battleground.game.entity.building.Building} class.
 * It does not have a firing rate or range.
 *
 * @author schueller
 */
public class Wall extends Building
{
    /**
     * The constructor of the Wall
     *
     * @param x the x-coordinate of the object
     * @param y the y-coordinate of the object
     */
    public Wall(float x, float y)
    {
        super(x, y, R.drawable.wall);
        this.setBuildingType(BuildingType.WALL);

        setHitPointsMax(300);
        setHitPoints(getHitPointsMax());

        setCosts(Constants.WALL_PRICE);
    }
}
