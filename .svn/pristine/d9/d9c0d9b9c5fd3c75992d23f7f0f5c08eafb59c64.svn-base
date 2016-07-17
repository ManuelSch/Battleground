package ims.tuwien.ac.at.battleground;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

/**
 * The VideoActivity is used to display the intro video
 * @author hoch
 */
public class VideoActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_video);

        final VideoView videoView = (VideoView) findViewById(R.id.introVideoView);


        //String path = "android.resource://" + getPackageName() + "/" + R.raw.samplevideo_360x240_2mb;
        String path = "android.resource://" + getPackageName() + "/" + R.raw.introvideo;
        VideoView mVideoView = (VideoView) findViewById(R.id.introVideoView);
        mVideoView.setVideoPath(path);
        mVideoView.setMediaController(new MediaController(this));
        mVideoView.requestFocus();
        // video finish listener
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

            @Override
            public void onCompletion(MediaPlayer mp) {
                Intent i= new Intent(getApplicationContext(), MenuActivity.class);
                startActivity(i);
            }
        });


        Button btn =(Button)findViewById(R.id.btnSkip);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getApplicationContext(), MenuActivity.class);
                startActivity(i);
            }
        });


        mVideoView.start();
    }
}
