package ims.tuwien.ac.at.battleground.util;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.HashMap;
import java.util.Map;

import ims.tuwien.ac.at.battleground.R;

/**
 * This class handles loading and storing bitmaps
 * @author hoch
 */
public class SpriteSheetManager extends ContextWrapper {

    private Map<Integer, SpriteSheet> spriteSheetMap = new HashMap<Integer, SpriteSheet>();

    private static SpriteSheetManager INSTANCE = null;

    /**
     * Private Constructor (Singleton)
     * @param context The context of the application
     */
    private SpriteSheetManager(Context context) {
        super(context);
        this.loadBitmaps();
    }

    /**
     * Returns the instance of the SpriteSheetManager
     * @param context The context of the application
     * @return the instance of the SpriteSheetManager
     */
    public static SpriteSheetManager CREATE_INSTANCE(Context context) {
        if(INSTANCE == null) {
            INSTANCE = new SpriteSheetManager(context);
        }
        return INSTANCE;
    }

    /**
     * Returns the instance of the SpriteSheetManager
     * @return the instance of the SpriteSheetManager
     */
    public static SpriteSheetManager GET_INSTANCE() {
        return INSTANCE;
    }

    /**
     * Loads all bitmaps that are used in the application and stores them in a map.
     */
    private void loadBitmaps() {

        SpriteSheet flakTowerBody = new SpriteSheet(this.getBaseContext(), R.drawable.flaktower_body, 1);
        SpriteSheet flakTowerGun = new SpriteSheet(this.getBaseContext(), R.drawable.flaktower_gun, 1);
        SpriteSheet rocketTowerBody = new SpriteSheet(this.getBaseContext(), R.drawable.rockettower_body, 1);
        SpriteSheet rocketTowerGun = new SpriteSheet(this.getBaseContext(), R.drawable.rockettower_gun, 4);
        SpriteSheet turretBody = new SpriteSheet(this.getBaseContext(), R.drawable.turret_body, 1);
        SpriteSheet turretGun = new SpriteSheet(this.getBaseContext(), R.drawable.turret_gun, 1);
        SpriteSheet wall = new SpriteSheet(this.getBaseContext(), R.drawable.wall, 1);

        SpriteSheet buggyBody = new SpriteSheet(this.getBaseContext(), R.drawable.buggy_body, 2);
        SpriteSheet buggyGun = new SpriteSheet(this.getBaseContext(), R.drawable.buggy_gun, 1);
        SpriteSheet helicopter = new SpriteSheet(this.getBaseContext(), R.drawable.helicopter, 5);
        SpriteSheet robotBody = new SpriteSheet(this.getBaseContext(), R.drawable.robot_body, 12);
        SpriteSheet robotGun = new SpriteSheet(this.getBaseContext(), R.drawable.robot_gun, 1);
        SpriteSheet tankBody = new SpriteSheet(this.getBaseContext(), R.drawable.tank_body, 3);
        SpriteSheet tankGun = new SpriteSheet(this.getBaseContext(), R.drawable.tank_gun, 4);

        SpriteSheet flakBullet = new SpriteSheet(this.getBaseContext(), R.drawable.flakbullet, 1);
        SpriteSheet laser = new SpriteSheet(this.getBaseContext(), R.drawable.laser, 1);
        SpriteSheet rocket = new SpriteSheet(this.getBaseContext(), R.drawable.rocket, 1);

        SpriteSheet explosion = new SpriteSheet(this.getBaseContext(), R.drawable.explosion, 42);
        SpriteSheet kachel = new SpriteSheet(this.getBaseContext(), R.drawable.kachel, 1);


        spriteSheetMap.put(flakTowerBody.getBitmapID(), flakTowerBody);
        spriteSheetMap.put(flakTowerGun.getBitmapID(), flakTowerGun);
        spriteSheetMap.put(rocketTowerBody.getBitmapID(), rocketTowerBody);
        spriteSheetMap.put(rocketTowerGun.getBitmapID(), rocketTowerGun);
        spriteSheetMap.put(turretBody.getBitmapID(), turretBody);
        spriteSheetMap.put(turretGun.getBitmapID(), turretGun);
        spriteSheetMap.put(wall.getBitmapID(), wall);

        spriteSheetMap.put(buggyBody.getBitmapID(), buggyBody);
        spriteSheetMap.put(buggyGun.getBitmapID(), buggyGun);
        spriteSheetMap.put(helicopter.getBitmapID(), helicopter);
        spriteSheetMap.put(robotBody.getBitmapID(), robotBody);
        spriteSheetMap.put(robotGun.getBitmapID(), robotGun);
        spriteSheetMap.put(tankBody.getBitmapID(), tankBody);
        spriteSheetMap.put(tankGun.getBitmapID(), tankGun);

        spriteSheetMap.put(flakBullet.getBitmapID(), flakBullet);
        spriteSheetMap.put(laser.getBitmapID(), laser);
        spriteSheetMap.put(rocket.getBitmapID(), rocket);

        spriteSheetMap.put(explosion.getBitmapID(), explosion);
        spriteSheetMap.put(kachel.getBitmapID(), kachel);
    }

    /**
     * Returns the corresponding SpriteSheet from the internal store
     * @param id The ID of the SpriteSheet
     * @return The SpriteSheet
     */
    public SpriteSheet getSpriteSheet(int id) {
        return spriteSheetMap.get(id);
    }
}
