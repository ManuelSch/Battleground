package ims.tuwien.ac.at.battleground;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

/**
 * This class represents a SplashScreen for when the player
 * has won a round.
 *
 * @author hoch
 */
public class RoundWonActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.won_round_screen);


        Animation mViewAnimation = AnimationUtils.loadAnimation(this, R.anim.won_round_anim);
        ImageView RoundWonImage = (ImageView) findViewById(R.id.view_anim_round_won);

        mViewAnimation.reset();
        RoundWonImage.clearAnimation();
        RoundWonImage.startAnimation(mViewAnimation);

        final Button btn_Resume = (Button) findViewById(R.id.btn_resume_next_round);
        btn_Resume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RoundWonActivity.this.finish();
            }
        });
    }
}