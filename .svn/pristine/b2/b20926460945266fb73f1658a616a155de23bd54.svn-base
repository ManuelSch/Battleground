package ims.tuwien.ac.at.battleground.game.entity;

import ims.tuwien.ac.at.battleground.game.entity.building.Building;
import ims.tuwien.ac.at.battleground.game.entity.building.FlakTower;
import ims.tuwien.ac.at.battleground.game.entity.building.RocketTower;
import ims.tuwien.ac.at.battleground.game.entity.building.Turret;
import ims.tuwien.ac.at.battleground.game.entity.building.Wall;
import ims.tuwien.ac.at.battleground.game.entity.enemy.Buggy;
import ims.tuwien.ac.at.battleground.game.entity.enemy.Enemy;
import ims.tuwien.ac.at.battleground.game.entity.enemy.Helicopter;
import ims.tuwien.ac.at.battleground.game.entity.enemy.Robot;
import ims.tuwien.ac.at.battleground.game.entity.enemy.Tank;
import ims.tuwien.ac.at.battleground.game.util.BuildingType;
import ims.tuwien.ac.at.battleground.game.util.Constants;
import ims.tuwien.ac.at.battleground.game.util.EnemyType;

/**
 * This class is used as a Factory for game objects. It allows
 * the creation of game objects based on {@link ims.tuwien.ac.at.battleground.game.util.BuildingType}
 * and coordinates
 *
 * @author hoch
 */
public class GameObjectFactory {

    /**
     * Based on the buildingType a corresponding Building is created.
     *
     * @param buildingType The type of the building to be created
     * @param x The x-coordinate of the building
     * @param y The y-coordinate of the building
     * @return The created building according to the passed type
     */
    public static Building getBuilding(BuildingType buildingType, float x, float y) {
        switch(buildingType) {
            case FLAK_TOWER:
                return new FlakTower(x, y);
            case ROCKET_TOWER:
                return new RocketTower(x, y);
            case TURRET:
                return new Turret(x, y);
            case WALL:
                return new Wall(x, y);
            default:
                return null;
        }
    }

    /**
     * Returns the price for the given buildingType
     * @param buildingType The type of the building
     * @return The price of the building
     */
    public static double getPrice(BuildingType buildingType) {
        switch(buildingType) {
            case FLAK_TOWER:
                return Constants.FLAK_TOWER_PRICE;
            case ROCKET_TOWER:
                return Constants.ROCKET_TOWER_PRICE;
            case TURRET:
                return Constants.TURRET_PRICE;
            case WALL:
                return Constants.WALL_PRICE;
            default:
                return 0;
        }
    }

    /**
     * Based on the enemyType a corresponding Enemy is created.
     *
     * @param enemyType The type of the enemy to be created
     * @param x The x-coordinate of the enemy
     * @param y The y-coordinate of the enemy
     * @return The created enemy according to the passed type
     */
    public static Enemy getEnemy(EnemyType enemyType, float x, float y) {
        switch(enemyType) {
            case BUGGY:
                return new Buggy(x, y);
            case TANK:
                return new Tank(x, y);
            case HELICOPTER:
                return new Helicopter(x, y);
            case ROBOT:
                return new Robot(x, y);
            default:
                return null;
        }
    }

    /**
     * Returns a random enemy type
     * @return the type of the enemy
     */
    public static EnemyType getRandomEnemy() {
        double rand = Math.random();

        //return EnemyType.BUGGY;

        if(rand <= 0.3) {
            return EnemyType.BUGGY;
        }
        else if(rand <= 0.5) {
            return EnemyType.HELICOPTER;
        }
        else if(rand <= 0.95) {
                return EnemyType.ROBOT;
        }
        else {
                return EnemyType.TANK;
        }
    }
}
