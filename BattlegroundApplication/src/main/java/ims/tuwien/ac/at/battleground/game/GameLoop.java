package ims.tuwien.ac.at.battleground.game;

import android.annotation.SuppressLint;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.graphics.Canvas;
import android.view.SurfaceHolder;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import ims.tuwien.ac.at.battleground.BR;
import ims.tuwien.ac.at.battleground.game.entity.GameObjectFactory;
import ims.tuwien.ac.at.battleground.game.entity.Player;
import ims.tuwien.ac.at.battleground.game.util.Constants;
import ims.tuwien.ac.at.battleground.game.entity.DrawableObject;
import ims.tuwien.ac.at.battleground.game.util.GameLevel;
import ims.tuwien.ac.at.battleground.game.util.GameState;
import ims.tuwien.ac.at.battleground.util.DimensionUtil;

/**
 * The GameLoop handles the game logic and implements the clock algorithm used to
 * calculate the frames. The GameLoop loops indefinitely  Here a frame independent approach is used and the time per frame is
 * calculated in each loop.
 *
 * @author hoch
 * @author schueller
 */
public class GameLoop implements Runnable, Constants {
    /**
     * The holder for our surface/canvas that we are drawing on
     */
    private SurfaceHolder holder;
    /**
     * The view of our actual playingField
     */
    private GameSurfaceView view;
    /**
     * specifies whether the GameLoop is running or not
     */
    private boolean running = true;
    /**
     * stores the time of the last frame
     */
    private long lastTime = 0L;
    /**
     * stores the minimum number of frames
     */
    private double lowestFPS = 1000;

    private GameState gameState;
    private GameLevel gameLevel;
    private int frameCounter = 0;
    private int totalFrames = 0;
    private int currentEnemyFrequency = 0;
    private int currentMaxEnemies = 0;

    /**
     * The cunstructor of the GameLoop
     * @param holder The holder for our surface/canvas that we are drawing on
     * @param gameSurfaceView The view of our actual playingField
     */
    public GameLoop(SurfaceHolder holder, GameSurfaceView gameSurfaceView) {
        this.holder = holder;
        this.view = gameSurfaceView;
        this.gameState = GameState.BUILDING_PHASE;
        this.gameLevel = GameLevel.INITLEVEL;
    }

    /**
     * Sets whether the GameLoop is running or not
     * @param running True if the GameLoop should be running, false otherwise
     */
    public void setRunning(boolean running){
        this.running = running;
    }

    /**
     * Updates the game logic and all objects that are available. It depends on the time
     * that has passed in between frames.
     * @param tpf Time per Frame that has passed
     */
    public void updateGame(float tpf){
        //Hier die Spiellogik updaten!

        //Alle im letzten Gameloop-Durchlauf erstellten Objekte mit einem Mal zu den jeweiligen
        // Listen hinzufuegen (verhindert ConcurrentModificationErrors):
        PF.addYetToBeCreatedObjectsToLists();

        //Alle DrawableObjects updaten:

        if((totalFrames > GAME_LEVEL1_ENEMY_FRAME_COUNT) && (PF.getAllEnemies().size() == 0)) {
            // END ROUND
            // PF.clearAllObjects();
            this.gameState = GameState.BUILDING_PHASE;
            this.gameLevel = GameLevel.LEVEL2;
        }

        ArrayList<DrawableObject> allDrawableObjects = PF.getAllDrawableObjects();

        if(allDrawableObjects.size() != 0) {
            Iterator<DrawableObject> iter = allDrawableObjects.iterator();
            while(iter.hasNext()) {
                iter.next().update(tpf);
            }
        }

        //Alle in aktuellen Gameloop-Durchlauf zerstoerten Objekte mit einem Mal aus den jeweiligen
        // Listen entfernen (verhindert ConcurrentModificationErrors):
        PF.removeDeadObjectsFromLists();
    }

    /**
     * Calculates and returns the time that has passed in between frames
     * @return Time per Frame that has passed
     */
    public float getTimePerFrame(){
        //Hier ausrechnen, wie lange das letzte Frame zum Zeichnen benötigt hat!
        long currentTime = System.currentTimeMillis();
        long deltaTime = currentTime - this.lastTime ;
        this.lastTime = currentTime;
        float tpf = deltaTime / 1000.0f;

        lowestFPS = Math.min(lowestFPS, 1 / tpf);

        DecimalFormat f = new DecimalFormat("#0.000");
        //System.out.println("FPS = " + (int)(1/tpf) + "  \ttpf = " + f.format(tpf) + "  \tMinFPS = " + (int)lowestFPS);

        return tpf;
    }

    /**
     * Executes the GameLoop. In each loop it calculates the time that has passed in between
     * frames and updates the game logic. Afterwards the changes are rendered on the canvas.
     */
    @Override
    public void run() {
        float tpf = 0;
        running = true;
        this.lastTime = System.currentTimeMillis();


        while(running){
            //Frame-Unabhängigkeit implementieren
            tpf = getTimePerFrame();
            //"Update"
            if(gameState == GameState.RUNNING) {
                this.processGameState();
                this.frameCounter++;
                this.totalFrames++;
            }

            if(gameState != GameState.PAUSE) {
                // only render if the game is either running or we are building things
                this.updateGame(tpf);
                //"Render"
                this.render();
            }
        }

         //"Destroy"
    }

    /**
     * Processes the current level logic. It checks if there are more enemies to be created and
     * positions them on screen.
     */
    private void processGameState() {
        Random rand = new Random();
        int randomY = rand.nextInt((DimensionUtil.DIMENSION_Y_PIXEL - 0) + 1) + 0;
        int yGrid = randomY;
        //Am Raster ausrichten:
        if(view.getGRID_SIDELENGTH() > 0) {
            yGrid = randomY - randomY % view.getGRID_SIDELENGTH() + view.getGRID_SIDELENGTH() / 2;
        }

        if(totalFrames < this.currentMaxEnemies) {
            if (this.frameCounter > this.currentEnemyFrequency) {
                PF.addObject(GameObjectFactory.getEnemy(GameObjectFactory.getRandomEnemy(), 0, yGrid));
                this.frameCounter = 0;
            }
        }
        else {
            this.checkRoundFinished();
        }
    }

    /**
     * checks whether the current round (level) is finished or not
     */
    private void checkRoundFinished() {
        if(PF.getAllEnemies().size() == 0) {
            Game.GET_INSTANCE().getPlayer().addMoney(100);
            Game.GET_INSTANCE().wonRound();
        }
    }

    /**
     * Runs or pauses the game depending on the current state.
     */
    public void toggleGameRunning() {
        if(gameState == GameState.RUNNING) {
            gameState = GameState.PAUSE;
        }
        else if(gameState == GameState.PAUSE || gameState == GameState.BUILDING_PHASE) {
            gameState = GameState.RUNNING;
        }
        else {
            // DO NOTHING
        }
    }

    /**
     * Returns true if the game is running, false otherwise.
     * @return true if the game is running, false otherwise
     */
    public boolean isGameRunning() {
        if(gameState == GameState.RUNNING) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Sets the state of the game
     * @param gameState The state of the game
     */
    public void setGameState(GameState gameState) {
        synchronized (this.gameState) {
            this.gameState = gameState;
            this.totalFrames = 0;
        }
    }

    /**
     * Returns the state of the game
     * @return The state of the game
     */
    public GameState getGameState() {
        synchronized (this.gameState) {
            return this.gameState;
        }
    }

    /**
     * Returns the current level of the game
     * @return the current level of the game
     */
    public GameLevel getGameLevel() {
        return this.gameLevel;
    }

    /**
     * Resets the GameLevel to the initial level
     */
    public void resetGameLevel() {
        this.gameLevel = GameLevel.INITLEVEL;
    }

    /**
     * Resets the framecounters of the level and advances one level forward.
     */
    public void advanceLevel() {
        this.totalFrames = 0;
        this.frameCounter = 0;
        if(this.gameLevel == GameLevel.INITLEVEL) {
            this.gameLevel = GameLevel.LEVEL1;
            this.currentEnemyFrequency = Constants.GAME_LEVEL1_FREQUENCY;
            this.currentMaxEnemies = Constants.GAME_LEVEL1_ENEMY_FRAME_COUNT;
            return;
        }
        if(this.gameLevel == GameLevel.LEVEL1) {
            this.gameLevel = GameLevel.LEVEL2;
            this.currentEnemyFrequency = Constants.GAME_LEVEL2_FREQUENCY;
            this.currentMaxEnemies = Constants.GAME_LEVEL2_ENEMY_FRAME_COUNT;
            return;
        }
        if(this.gameLevel == GameLevel.LEVEL2) {
            this.gameLevel = GameLevel.LEVEL3;
            this.currentEnemyFrequency = Constants.GAME_LEVEL3_FREQUENCY;
            this.currentMaxEnemies = Constants.GAME_LEVEL3_ENEMY_FRAME_COUNT;
            return;
        }
        if(this.gameLevel == GameLevel.LEVEL3) {
            this.gameLevel = GameLevel.LEVEL4;
            this.currentEnemyFrequency = Constants.GAME_LEVEL4_FREQUENCY;
            this.currentMaxEnemies = Constants.GAME_LEVEL4_ENEMY_FRAME_COUNT;
            return;
        }
        if(this.gameLevel == GameLevel.LEVEL4) {
            this.gameLevel = GameLevel.LEVEL5;
            this.currentEnemyFrequency = Constants.GAME_LEVEL5_FREQUENCY;
            this.currentMaxEnemies = Constants.GAME_LEVEL5_ENEMY_FRAME_COUNT;
            return;
        }
        if(this.gameLevel == GameLevel.LEVEL5) {
            this.gameLevel = GameLevel.LEVEL6;
            this.currentEnemyFrequency = Constants.GAME_LEVEL6_FREQUENCY;
            this.currentMaxEnemies = Constants.GAME_LEVEL6_ENEMY_FRAME_COUNT;
            return;
        }
        if(this.gameLevel == GameLevel.LEVEL6) {
            this.gameLevel = GameLevel.LEVEL7;
            this.currentEnemyFrequency = Constants.GAME_LEVEL7_FREQUENCY;
            this.currentMaxEnemies = Constants.GAME_LEVEL7_ENEMY_FRAME_COUNT;
            return;
        }
        // TODO: add logic for other levels;
    }

    /**
     * Renders the canvas
     */
    @SuppressLint("WrongCall")
    private void render() {
        Canvas c = null;
        try {
            c = holder.lockCanvas(null);
            synchronized (holder) {
                if (c != null) {
                    view.onDraw(c);
                }
            }
        } finally{
            if(c != null)
            {
                holder.unlockCanvasAndPost(c);
            }
        }
    }
}
