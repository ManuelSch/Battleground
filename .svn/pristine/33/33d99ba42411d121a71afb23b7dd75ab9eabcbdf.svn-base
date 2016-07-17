package ims.tuwien.ac.at.battleground.game.entity;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

import ims.tuwien.ac.at.battleground.game.entity.building.Building;
import ims.tuwien.ac.at.battleground.game.entity.building.TowerGun;
import ims.tuwien.ac.at.battleground.game.entity.bullet.Bullet;
import ims.tuwien.ac.at.battleground.game.entity.enemy.Enemy;
import ims.tuwien.ac.at.battleground.game.entity.enemy.EnemyGun;

/**
 * This class handles the PlayingField of the game. It contains a list of all objects that
 * are currently being displayed on the field. In addition it specifies the size of the field that
 * is being used.
 *
 * @author hoch
 * @author schueller
 */
public class PlayingField {

    /**
     * A list of all Buildings that are currently on the field
     */
    private ArrayList<Building> allBuildings;
    /**
     * a list of all Guns (of buildings) that are currently on the field
     */
    private ArrayList<TowerGun> allTowerGuns;
    /**
     * a list of all Enemies that are currently on the field
     */
    private ArrayList<Enemy> allEnemies;
    /**
     * a list of all Guns (of enemies) that are currently on the field
     */
    private ArrayList<EnemyGun> allEnemyGuns;
    /**
     * a list of all Bullets that are currently on the field
     */
    private ArrayList<Bullet> allBullets;
    /**
     * a list of other objects that are currently on the field
     */
    private ArrayList<DrawableObject> allOthers;

    /**
     * a list of all objects that are to be created
     */
    private ArrayList<DrawableObject> yetToBeCreatedObjects;

    /**
     * a list of all dead objects
     */
    private ArrayList<DrawableObject> deadObjects;

    /**
     * Singleton variable for the PlayingField
     */
    private static PlayingField INSTANCE;


    /**
     * Private Constructor (singleton)
     */
    private PlayingField()
    {
        allBuildings = new ArrayList<Building>();
        allTowerGuns = new ArrayList<TowerGun>();
        allEnemies = new ArrayList<Enemy>();
        allEnemyGuns = new ArrayList<EnemyGun>();
        allBullets = new ArrayList<Bullet>();
        allOthers = new ArrayList<DrawableObject>();
        deadObjects = new ArrayList<DrawableObject>();
        yetToBeCreatedObjects = new ArrayList<DrawableObject>();
    }

    /**
     * Returns the instance of the active playing field (singleton)
     * @return The instance of the active playing field (singleton)
     */
    public static PlayingField getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new PlayingField();
        }

        return INSTANCE;
    }

    /**
     * Adds an object to the playing field
     * @param obj DrawableObject that should be added
     * @return True if the object has been added, false otherwise
     */
    public boolean addObject(DrawableObject obj)
    {
        if(obj instanceof Building) {
            synchronized (allBuildings) {
                return allBuildings.add((Building) obj);
            }
        }
        else if(obj instanceof TowerGun) {
            synchronized (allTowerGuns) {
                return allTowerGuns.add((TowerGun) obj);
            }
        }
        else if(obj instanceof Enemy) {
            synchronized (allEnemies) {
                return allEnemies.add((Enemy) obj);
            }
        }
        else if(obj instanceof EnemyGun) {
            synchronized (allEnemyGuns) {
                return allEnemyGuns.add((EnemyGun) obj);
            }
        }
        else if(obj instanceof Bullet) {
            synchronized (allBullets) {
                return allBullets.add((Bullet) obj);
            }
        }
        else {
            return allOthers.add(obj);
        }
    }

    /**
     * Removes an object from the playing field
     * @param obj The object to be removed
     * @return True if the object was removed, false otherwise
     */
    private boolean removeObject(DrawableObject obj)
    {
        if(obj instanceof Building) {
            synchronized (allBuildings) {
                return allBuildings.remove((Building) obj);
            }
        }
        else if(obj instanceof TowerGun) {
            synchronized (allTowerGuns) {
                return allTowerGuns.remove((TowerGun) obj);
            }
        }
        else if(obj instanceof Enemy) {
            synchronized (allEnemies) {
                return allEnemies.remove((Enemy) obj);
            }
        }
        else if(obj instanceof EnemyGun) {
            synchronized (allEnemyGuns) {
                return allEnemyGuns.remove((EnemyGun) obj);
            }
        }
        else if(obj instanceof Bullet) {
            synchronized (allBullets) {
                return allBullets.remove((Bullet) obj);
            }
        }
        else {
            return allOthers.remove(obj);
        }
    }

    /**
     * Returns a list of all {@link DrawableObject}drawable objects.
     * @return a list with all currently active objects
     */
    public ArrayList<DrawableObject> getAllDrawableObjects()
    {
        ArrayList<DrawableObject> allDrawableObjects = new ArrayList<DrawableObject>();

        //Hier wird die Reihenfolge bestimmt, in welcher die Objekte gezeichnet werden:
        allDrawableObjects.addAll(getAllBuildings());
        allDrawableObjects.addAll(getAllTowerGuns());
        allDrawableObjects.addAll(getAllEnemies());
        allDrawableObjects.addAll(getAllEnemiyGuns());
        allDrawableObjects.addAll(getAllOthers());
        allDrawableObjects.addAll(getAllBullets());

        return allDrawableObjects;
    }

    /**
     * Returns a list of all {@link Building} buildings
     * @return List of all {@link Building} buildings
     */
    public ArrayList<Building> getAllBuildings()
    {
        return allBuildings;
    }

    /**
     * Returns a list of all {@Link TowerGun} guns (of buildings)
     * @return List of all {@Link TowerGun} guns (of buildings)
     */
    public ArrayList<TowerGun> getAllTowerGuns()
    {
        return allTowerGuns;
    }

    /**
     * Returns a list of all {@link Enemy} enemies
     * @return List of all {@link Enemy} enemies
     */
    public ArrayList<Enemy> getAllEnemies()
    {
        Collections.sort(allEnemies);   //Sortieren nach den X-Koordinaten aller Gegner sortieren.
                                        //(Gegner weiter rechts am Spielfeld kommen zuerst)
        return allEnemies;
    }

    /**
     * Returns a list of all {@Link EnemyGun} guns (of enemies)
     * @return List of all {@Link EnemyGun} guns (of enemies)
     */
    public ArrayList<EnemyGun> getAllEnemiyGuns()
    {
        return allEnemyGuns;
    }

    /**
     * Returns a list of all {@link Bullet} bullets
     * @return List of all {@link Bullet} bullets
     */
    public ArrayList<Bullet> getAllBullets()
    {
        return allBullets;
    }

    /**
     * Returns a list of all other objects on the playing field
     * @return List of all other objects on the playing field
     */
    public ArrayList<DrawableObject> getAllOthers()
    {
        return allOthers;
    }

    /**
     * Deletes all objects from the playing field
     */
    public void clearAllObjects() {
        allBullets = new ArrayList<Bullet>();
        allBuildings = new ArrayList<Building>();
        allTowerGuns = new ArrayList<TowerGun>();
        allEnemies = new ArrayList<Enemy>();
        allEnemyGuns = new ArrayList<EnemyGun>();
        allOthers = new ArrayList<DrawableObject>();
    }

    /**
     * deletes all enemies on the playing field
     */
    public void clearEnemies() {
        allEnemies = new ArrayList<Enemy>();
        allEnemyGuns = new ArrayList<EnemyGun>();
    }

    /**
     * deletes all bullets that are currently on the playing field
     */
    public void clearBullets() {
        allBullets = new ArrayList<Bullet>();
    }

    /**
     * Adds a object that should be created on the playing field
     * @param obj The object to be created
     */
    public void addToYetToBeCreatedObjects(DrawableObject obj)
    {
        yetToBeCreatedObjects.add(obj);
    }

    /**
     * Adds the objects that are to be created to the corresponding lists
     */
    public void addYetToBeCreatedObjectsToLists()
    {
        for(DrawableObject obj : yetToBeCreatedObjects)
        {
            this.addObject(obj);
        }
        yetToBeCreatedObjects = new ArrayList<>();
    }

    /**
     * Adds the object to the dead objects list
     * @param obj The object to be added
     */
    public void addToDeadObjects(DrawableObject obj)
    {
        deadObjects.add(obj);
    }

    /**
     * Removes all dead objects from their corresponding lists
     */
    public void removeDeadObjectsFromLists()
    {
        for(DrawableObject obj : deadObjects)
        {
            this.removeObject(obj);
        }
        deadObjects = new ArrayList<>();
    }
}
