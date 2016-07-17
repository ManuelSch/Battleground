package ims.tuwien.ac.at.battleground;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * This Activity is called upon startup of the application
 * @author hoch
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent i= new Intent(getApplicationContext(), VideoActivity.class);
        //Intent i= new Intent(getApplicationContext(), MenuActivity.class);
        startActivity(i);
    }
}
