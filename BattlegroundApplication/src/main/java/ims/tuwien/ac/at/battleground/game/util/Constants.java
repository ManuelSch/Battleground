package ims.tuwien.ac.at.battleground.game.util;

import ims.tuwien.ac.at.battleground.game.Game;
import ims.tuwien.ac.at.battleground.game.entity.PlayingField;
import ims.tuwien.ac.at.battleground.util.SoundManager;
import ims.tuwien.ac.at.battleground.util.SpriteSheetManager;

/**
 * Specifies some Constants that are used in the application
 * @author hoch
 */
public interface Constants {

    /**
     * The length of the playing field
     */
    public final static int SIDE_LENGTH = 15;
    /**
     * Reference to singleton instance of {@link ims.tuwien.ac.at.battleground.game.entity.PlayingField}
     */
    public final static PlayingField PF = PlayingField.getInstance();

    /**
     * Reference to singleton instance of {@link ims.tuwien.ac.at.battleground.util.SpriteSheetManager}
     */
    public final static SpriteSheetManager SPRITE_SHEET_MANAGER = SpriteSheetManager.GET_INSTANCE();

    /**
     * Reference to singleton instance of {@link ims.tuwien.ac.at.battleground.game.Game}
     */
    public final static Game GAME = Game.GET_INSTANCE();

    /**
     * Reference to singleton instance of {@link ims.tuwien.ac.at.battleground.util.SoundManager}
     */
    public final static SoundManager SOUND_MANAGER = SoundManager.GET_INSTANCE();

    /**
     * The frequency of enemys in Level1
     */
    public final static int GAME_LEVEL1_FREQUENCY = 50;
    /**
     * The upper limit of frames during which enemies spawn in level 1
     */
    public final static int GAME_LEVEL1_ENEMY_FRAME_COUNT = 500;

    /**
     * The frequency of enemys in Level2
     */
    public final static int GAME_LEVEL2_FREQUENCY = 45;
    /**
     * The upper limit of frames during which enemies spawn in level 2
     */
    public final static int GAME_LEVEL2_ENEMY_FRAME_COUNT = 500;

    /**
     * The frequency of enemys in Level3
     */
    public final static int GAME_LEVEL3_FREQUENCY = 40;
    /**
     * The upper limit of frames during which enemies spawn in level 3
     */
    public final static int GAME_LEVEL3_ENEMY_FRAME_COUNT = 600;

    /**
     * The frequency of enemys in Level4
     */
    public final static int GAME_LEVEL4_FREQUENCY = 35;
    /**
     * The upper limit of frames during which enemies spawn in level 4
     */
    public final static int GAME_LEVEL4_ENEMY_FRAME_COUNT = 700;

    /**
     * The frequency of enemys in Level5
     */
    public final static int GAME_LEVEL5_FREQUENCY = 30;
    /**
     * The upper limit of frames during which enemies spawn in level 5
     */
    public final static int GAME_LEVEL5_ENEMY_FRAME_COUNT = 800;

    /**
     * The frequency of enemys in Level6
     */
    public final static int GAME_LEVEL6_FREQUENCY = 25;
    /**
     * The upper limit of frames during which enemies spawn in level 6
     */
    public final static int GAME_LEVEL6_ENEMY_FRAME_COUNT = 1000;

    /**
     * The frequency of enemys in Level7
     */
    public final static int GAME_LEVEL7_FREQUENCY = 20;
    /**
     * The upper limit of frames during which enemies spawn in level 7
     */
    public final static int GAME_LEVEL7_ENEMY_FRAME_COUNT = 500;

    /**
     * The frequency of enemys in Level8
     */
    public final static int GAME_LEVEL8_FREQUENCY = 15;
    /**
     * The upper limit of frames during which enemies spawn in level 8
     */
    public final static int GAME_LEVEL8_ENEMY_FRAME_COUNT = 600;

    /**
     * The frequency of enemys in Level9
     */
    public final static int GAME_LEVEL9_FREQUENCY = 25;
    /**
     * The upper limit of frames during which enemies spawn in level 9
     */
    public final static int GAME_LEVEL9_ENEMY_FRAME_COUNT = 750;

    /**
     * The frequency of enemys in Level10
     */
    public final static int GAME_LEVEL10_FREQUENCY = 15;
    /**
     * The upper limit of frames during which enemies spawn in level 10
     */
    public final static int GAME_LEVEL10_ENEMY_FRAME_COUNT = 500;

    /**
     * The price of the {@link ims.tuwien.ac.at.battleground.game.entity.building.FlakTower}
     */
    public final static int FLAK_TOWER_PRICE = 100;
    /**
     * The price of the {@link ims.tuwien.ac.at.battleground.game.entity.building.RocketTower}
     */
    public final static int ROCKET_TOWER_PRICE = 150;
    /**
     * The price of the {@link ims.tuwien.ac.at.battleground.game.entity.building.Turret}
     */
    public final static int TURRET_PRICE = 75;
    /**
     * The price of the {@link ims.tuwien.ac.at.battleground.game.entity.building.Wall}
     */
    public final static int WALL_PRICE = 25;

    /**
     * The initial money that the player receives
     */
    public final static int PLAYER_INITIAL_MONEY = 1000;

    /**
     * The name of the variable that is used to identify the player
     */
    public final static String INTENT_VARIABLE_PLAYER_NAME = "player_name";
    /**
     * If no name for a player is given, this default name is used
     */
    public final static String DEFAULT_PLAYER_NAME = "TestPlayer";
}
