package ims.tuwien.ac.at.battleground.game.entity.enemy;

import android.content.Context;
import android.graphics.Rect;

import java.util.ArrayList;

import ims.tuwien.ac.at.battleground.R;
import ims.tuwien.ac.at.battleground.game.entity.building.Building;

/**
 * This class represents a Robot-Enemy in the Game. It extends the
 * {@link ims.tuwien.ac.at.battleground.game.entity.enemy.Enemy} class.
 *
 * @author hoch
 * @author schueller
 */
public class Robot extends Enemy
{
    /**
     * The Constructor of the Robot
     * @param x the x-coordinate
     * @param y the y-coordinate
     */
    public Robot(float x, float y)
    {
        super(x, y, R.drawable.robot_body);
        this.enemyGun = new RobotGun(getX(), getY(), this);

        setHitPointsMax(100);
        setHitPoints(getHitPointsMax());

        setSpeed(80);
        setMoneyValue(20);
        setScoreValue(10);
        setFlying(false);
    }

    /**
     * Updates the position of itself as well as
     * the position of the attached gun. If a building is blocking
     * its way, the blocked status is set.
     *
     * @param tpf the time per frame that has passed
     */
    @Override
    public void update(float tpf)
    {
        //Ist der Weg blockiert?
        ArrayList<Building> allBuildings = PF.getAllBuildings();
        blocked = false;
        if (allBuildings.size() != 0) {
            for (Building building : allBuildings) {
                if(Rect.intersects(this.destRect, building.getDestRect())) {
                    blocked = true;
                    break;
                }
                else {
                    blocked = false;
                }
            }
        }

        if(this.blocked == true)
        {
            aAnimate =false;
        }
        else
        {
            this.moveTowards((int) (this.getSpeed() * tpf), 0);
            aAnimate = true;
        }


        //movement animation
        if(aAnimate == true) {
            this.updateAnimation(false);
        }
        else {
            aCurrentFrame = 0;
        }

        super.update(tpf);
    }
}