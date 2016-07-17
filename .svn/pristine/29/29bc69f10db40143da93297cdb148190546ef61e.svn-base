package ims.tuwien.ac.at.battleground.game;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;
import android.view.SurfaceHolder;

import ims.tuwien.ac.at.battleground.GameActivity;
import ims.tuwien.ac.at.battleground.game.entity.GameObjectFactory;
import ims.tuwien.ac.at.battleground.game.entity.Player;
import ims.tuwien.ac.at.battleground.game.entity.PlayingField;
import ims.tuwien.ac.at.battleground.game.entity.building.Building;
import ims.tuwien.ac.at.battleground.game.util.GameLevel;
import ims.tuwien.ac.at.battleground.game.util.GameState;
import ims.tuwien.ac.at.battleground.game.util.BuildingType;
import ims.tuwien.ac.at.battleground.persistence.ScoreContract;
import ims.tuwien.ac.at.battleground.persistence.ScoreProvider;

/**
 * This class handles the logic of the application. It initializes the {@link GameLoop} and
 * toggles the {@link GameState}. If a round ends it notifies the corresponding view and handles
 * the logic that is necessary to advance in the game.
 *
 * @author hoch
 */
public class Game {

    private static Game INSTANCE;

    /**
     * The {@link GameLoop} that is used to calculate the game logic
     */
    private GameLoop loop;
    /**
     * The Thread that executes our {@link GameLoop}
     */
    private Thread loopThread;

    private GameSurfaceView view;
    private SurfaceHolder holder;

    private Player player;
    private BuildingType currentSelectedBuilding;
    private boolean sound = true;

    private GameActivity gameActivity;
    private Context context;


    /**
     * private constructor (singleton)
     */
    private Game() {

    }

    /**
     * private constructor (singleton)
     * @param context The context of the application
     */
    private Game(Context context) {
        this.context = context;
    }

    /**
     * Returns an instance of the Game class (singleton)
     * @param context The context of application
     * @return The instance of the Game
     */
    public static Game GET_INSTANCE(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new Game(context);
        }
        return INSTANCE;
    }

    /**
     * Returns an instance of the Game class (singleton)
     * @return The instance of the Game
     */
    public static Game GET_INSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new Game();
        }
        return INSTANCE;
    }

    /**
     * Registers an Activity so that the game logic can notify it about certain events
     * @param gameActivity The activity that should be notified later on
     */
    public void registerGameActivity(GameActivity gameActivity) {
        this.gameActivity = gameActivity;
    }

    /**
     * Ends the game and terminates the {@link GameLoop}.
     */
    public void endGame() {
        loop.setRunning(false);
        try
        {
            loopThread.join();
        }
        catch (InterruptedException e)
        {
            Log.e("Error", e.getMessage());
        }
    }

    /**
     * Creates a building of the corresponding type if the player has enough money.
     * It handles the creation and adds the building to the playing field.
     * @param type The type of the building
     * @param x the x-coordinate
     * @param y the y-coordinate
     */
    public void createBuilding(BuildingType type, int x, int y) {
        if(player.checkEnoughMoney(GameObjectFactory.getPrice(type))) {
            Building building = GameObjectFactory.getBuilding(type, x, y);
            player.subtractMoney(building.getCosts());
            PlayingField.getInstance().addObject(building);
            this.gameActivity.toggleBuildingMenuButtons();
            if(this.currentSelectedBuilding != null) {
                if (player.checkEnoughMoney(GameObjectFactory.getPrice(this.currentSelectedBuilding)) == false) {
                    this.currentSelectedBuilding = null;
                }
            }
        }
    }

    /**
     * Starts a level and sets the {@link GameState} to running.
     */
    public void startLevel() {
        this.loop.setGameState(GameState.RUNNING);
    }

    /**
     * Sets the player for the game
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * Adds score to the player
     * @param score The score to be added
     */
    public void addScore(int score) {
        synchronized(player) {
            player.addScore(score);
        }
    }

    /**
     * Adds money to the player
     * @param money The money to be added
     */
    public void addMoney(int money) {
        synchronized(player) {
            player.addMoney(money);
        }
    }

    /**
     * sets a reference for the {@link GameSurfaceView} and {@link SurfaceHolder}. It also
     * triggers the initialization of the {@link GameLoop}
     * @param view The reference of the view
     * @param holder The reference of the SurfaceHolder
     */
    public void setGameSurfaceView(GameSurfaceView view, SurfaceHolder holder) {
        this.view = view;
        this.holder = holder;
        this.runGame();
    }

    /**
     * Runs the game, initializes the GameLoop and starts its thread.
     */
    public void runGame() {
        loop = new GameLoop(holder, view);
        this.loop.advanceLevel();
        loopThread = new Thread(loop);
        loopThread.start();
    }

    /**
     * If a round is lost, the game is paused and the playing field is cleaned. Then
     * the Activity is notified and a SplashScreen is presented.
     */
    public void lostRound() {
        this.loop.setGameState(GameState.PAUSE);
        if(player.getLives() > 1) {
            player.loseLife();
            this.resetPlayingField();
            this.player.resetMoney();
            this.loop.setGameState(GameState.BUILDING_PHASE);
            this.gameActivity.showLostLifeScreen();
        }
        else {
            this.saveHighscore();
            this.cleanup();
            this.gameActivity.showGameOverScreen();
        }
    }

    /**
     * If a round is won, the game is paused and the level is advanced. Then
     * the Activity is notified and a SplashScreen is presented.
     */
    public void wonRound() {
        this.loop.setGameState(GameState.PAUSE);
        this.loop.advanceLevel();
        this.clearPlayingFieldOfEnemies();
        this.loop.setGameState(GameState.BUILDING_PHASE);

        this.gameActivity.showRoundWonScreen();
    }

    /**
     * Saves the score of the Player in the database
     */
    private void saveHighscore() {
        ContentValues values = new ContentValues();
        values.put(ScoreContract.ScoreEntry.COLUMN_NAME_USERNAME, player.getName());
        values.put(ScoreContract.ScoreEntry.COLUMN_NAME_SCORE, player.getScore());

        context.getContentResolver().insert(ScoreProvider.CONTENT_URI, values);
    }

    /**
     * Resets the playing field (clears all objects)
     */
    private void resetPlayingField() {
        PlayingField.getInstance().clearAllObjects();
    }

    /**
     * Clears all Enemies and Bullets from the playing field
     */
    private void clearPlayingFieldOfEnemies() {
        PlayingField.getInstance().clearEnemies();
        PlayingField.getInstance().clearBullets();
    }

    /**
     * Returns the currently in the menu selected building
     * @return the currently in the menu selected building
     */
    public BuildingType getCurrentSelectedBuilding() {
        return currentSelectedBuilding;
    }

    /**
     * Sets the currently in the menu selected building
     * @param currentSelectedBuilding The currently in the menu selected Building
     */
    public void setCurrentSelectedBuilding(BuildingType currentSelectedBuilding) {
        this.currentSelectedBuilding = currentSelectedBuilding;
    }

    /**
     * Toggles the GameState (either pauses or continues the game)
     */
    public void toggleGameRunning() {
        this.loop.toggleGameRunning();
    }

    /**
     * checks whether the game is running or not
     * @return true if the game is running, false otherwise
     */
    public boolean isGameRunning() {
        return this.loop.isGameRunning();
    }

    /**
     * Turns the sound on or off
     */
    public void toggleSound() {
        this.sound = !this.sound;
    }

    /**
     * checks whether the sound is on or not
     * @return true if the sound is on, false otheriwse
     */
    public boolean isSoundOn() {
        return this.sound;
    }

    /**
     * Returns the player of the current game
     * @return the player of the current game
     */
    public Player getPlayer() {
        return this.player;
    }

    /**
     * Returns the current state of the game
     * @return the current state of the game
     */
    public GameState getGameState() {
        if(this.loop == null) {
            return GameState.BUILDING_PHASE;
        }
        return this.loop.getGameState();
    }

    /**
     * Returns the current level of the game
     * @return the current level of the game
     */
    public GameLevel getGameLevel() {
        return this.loop.getGameLevel();
    }

    /**
     * Resets the game, cleans the playing field and resets the player
     */
    public void cleanup() {
        this.loop.setGameState(GameState.BUILDING_PHASE);
        PlayingField.getInstance().clearAllObjects();
        this.loop.resetGameLevel();
        this.player = null;
    }
}
