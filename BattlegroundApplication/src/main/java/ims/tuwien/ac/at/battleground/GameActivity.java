package ims.tuwien.ac.at.battleground;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import ims.tuwien.ac.at.battleground.databinding.ActivityGameBinding;
import ims.tuwien.ac.at.battleground.game.Game;
import ims.tuwien.ac.at.battleground.game.entity.GameObjectFactory;
import ims.tuwien.ac.at.battleground.game.util.Constants;
import ims.tuwien.ac.at.battleground.game.entity.Player;
import ims.tuwien.ac.at.battleground.game.util.BuildingType;
import ims.tuwien.ac.at.battleground.util.CustomClickListener;
import ims.tuwien.ac.at.battleground.util.SoundManager;
import ims.tuwien.ac.at.battleground.util.SpriteSheetManager;

/**
 * The GameActivity handles the Game itself. It provides the layout for the
 * playing field, the menu and the player information
 *
 * @author hoch
 */
public class GameActivity extends Activity implements Constants {

    /**
     * Creates a Player and initializes the {@link Game} class. Afterwards the
     * UI of the game is loaded.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle extras = getIntent().getExtras();
        String player_name = null;
        if (extras != null) {
            player_name = extras.getString(Constants.INTENT_VARIABLE_PLAYER_NAME);
        }

        SpriteSheetManager.CREATE_INSTANCE(this.getBaseContext());
        SoundManager.CREATE_INSTANCE(this.getBaseContext());

        Game.GET_INSTANCE(this.getBaseContext()).registerGameActivity(this);
        if(Game.GET_INSTANCE().getPlayer() == null) {
            Player player = new Player(player_name);
            Game.GET_INSTANCE().setPlayer(player);
        }

        this.loadGameUI();
    }

    /**
     * Loads and initializes the UI of the game. Here all listeners on the Buttons are registered
     * as well.
     */
    private void loadGameUI() {

        ActivityGameBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_game);
        binding.setPlayer(Game.GET_INSTANCE().getPlayer());

        final ImageView flakTowerButtonView = (ImageView) this.findViewById(R.id.imageViewFlakTowerButton);
        flakTowerButtonView.setOnClickListener(new CustomClickListener(BuildingType.FLAK_TOWER));
        final ImageView rocketTowerButtonView = (ImageView) this.findViewById(R.id.imageViewRocketTowerButton);
        rocketTowerButtonView.setOnClickListener(new CustomClickListener(BuildingType.ROCKET_TOWER));
        final ImageView turretButtonView = (ImageView) this.findViewById(R.id.imageViewTurretButton);
        turretButtonView.setOnClickListener(new CustomClickListener(BuildingType.TURRET));
        final ImageView wallButtonView = (ImageView) this.findViewById(R.id.imageViewWallButton);
        wallButtonView.setOnClickListener(new CustomClickListener(BuildingType.WALL));

        Button btn_startLevel = (Button) findViewById(R.id.btnStartStopLevel);
        btn_startLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Game.GET_INSTANCE().toggleGameRunning();
                GameActivity.this.setStartStopButtonText();
            }
        });
        this.setStartStopButtonText();

        final ImageButton btn_sound = (ImageButton) findViewById(R.id.btnSoundButton);
        btn_sound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Game.GET_INSTANCE().toggleSound();
                GameActivity.this.toggleSoundButton();
                SoundManager.GET_INSTANCE().toggleSound();
            }
        });

        final ImageButton btn_back_to_menu = (ImageButton) findViewById(R.id.btnBackToMenu);
        btn_back_to_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Game.GET_INSTANCE().cleanup();
                GameActivity.this.finish();
            }
        });

    }

    /**
     * Toggles (activate/deactivate) the Buttons in the menu depending on the current money
     * of the player.
     */
    public void toggleBuildingMenuButtons() {
        final ImageButton btn_flak = (ImageButton) findViewById(R.id.imageViewFlakTowerButton);
        if (Game.GET_INSTANCE().getPlayer().checkEnoughMoney(GameObjectFactory.getPrice(BuildingType.FLAK_TOWER))) {
            btn_flak.setEnabled(true);
        } else {
            btn_flak.setEnabled(false);
        }
        final ImageButton btn_rocket_tower = (ImageButton) findViewById(R.id.imageViewRocketTowerButton);
        if (Game.GET_INSTANCE().getPlayer().checkEnoughMoney(GameObjectFactory.getPrice(BuildingType.ROCKET_TOWER))) {
            btn_rocket_tower.setEnabled(true);
        } else {
            btn_rocket_tower.setEnabled(false);
        }
        final ImageButton btn_turret = (ImageButton) findViewById(R.id.imageViewTurretButton);
        if (Game.GET_INSTANCE().getPlayer().checkEnoughMoney(GameObjectFactory.getPrice(BuildingType.TURRET))) {
            btn_turret.setEnabled(true);
        } else {
            btn_turret.setEnabled(false);
        }
        final ImageButton btn_wall = (ImageButton) findViewById(R.id.imageViewWallButton);
        if (Game.GET_INSTANCE().getPlayer().checkEnoughMoney(GameObjectFactory.getPrice(BuildingType.WALL))) {
            btn_wall.setEnabled(true);
        } else {
            btn_wall.setEnabled(false);
        }
    }

    /**
     * Toggles the text of the Start-Button in the Menu (depending on the Game State)
     */
    private void setStartStopButtonText() {
        Button btn_startLevel = (Button) findViewById(R.id.btnStartStopLevel);
        switch(Game.GET_INSTANCE().getGameState()) {
            case BUILDING_PHASE:
                btn_startLevel.setText(R.string.txt_start_level);
                break;
            case RUNNING:
                btn_startLevel.setText(R.string.txt_pause_game);
                break;
            case PAUSE:
                btn_startLevel.setText(R.string.txt_resume_game);
                break;
            default:
                btn_startLevel.setText(R.string.txt_start_level);
                break;
        }
    }

    /**
     * Toggles the Sound-Button in the Menu (depending on whether the sound is
     * turned on or not)
     */
    private void toggleSoundButton() {
        final ImageButton btn_sound = (ImageButton) findViewById(R.id.btnSoundButton);
        if (Game.GET_INSTANCE().isSoundOn()) {
            btn_sound.setImageResource(R.drawable.soundon);
        } else {
            btn_sound.setImageResource(R.drawable.soundoff);
        }
    }

    /**
     * Shows a SplashScreen if the player is game over
     */
    public void showGameOverScreen() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                View inflatedView = View.inflate(GameActivity.this, R.layout.game_over_splash_screen, null);
                inflatedView.getBackground().setAlpha(50);
                GameActivity.this.setContentView(inflatedView);

                final Button btn_return_to_menu = (Button) findViewById(R.id.btn_return_to_menu_game_over);
                btn_return_to_menu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        GameActivity.this.finish();
                    }
                });

                TextView textView = (TextView) findViewById(R.id.prop_anim_textview);
                AnimatorSet animatorSet = (AnimatorSet) AnimatorInflater
                        .loadAnimator(GameActivity.this, R.animator.game_over_property_animator);
                animatorSet.setTarget(textView);
                animatorSet.start();
            }
        });
    }

    /**
     * Shows the LostLifeActivity when the player loses a life
     */
    public void showLostLifeScreen() {
        Intent i = new Intent(this.getBaseContext(), LostLifeActivity.class);
        startActivity(i);

       /* runOnUiThread(new Runnable() {
            @Override
            public void run() {
                View inflatedView = View.inflate(GameActivity.this, R.layout.lost_life_splash_screen, null);
                inflatedView.getBackground().setAlpha(50);
                GameActivity.this.setContentView(inflatedView);

                final Button btn_return_to_menu = (Button) findViewById(R.id.btn_resume_next_round);
                btn_return_to_menu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        GameActivity.this.loadGameUI();
                    }
                });

                Animation mViewAnimation = AnimationUtils.loadAnimation(GameActivity.this, R.anim.lose_life_anim);
                ImageView mPokeballImage = (ImageView) findViewById(R.id.view_anim_imageview);

                mViewAnimation.reset();
                mPokeballImage.clearAnimation();
                mPokeballImage.startAnimation(mViewAnimation);


            }
        });*/
    }

    /**
     * Shows the RoundWonActivity when the player wins a round
     */
    public void showRoundWonScreen() {
        Intent i = new Intent(this.getBaseContext(), RoundWonActivity.class);
        startActivity(i);
    }


    /**
     * If the GameActivity is resumed the view is reset according to the internal values
     */
    @Override
    public void onResume() {
        super.onResume();
        this.setStartStopButtonText();
        this.toggleSoundButton();
        this.toggleBuildingMenuButtons();
    }
}
