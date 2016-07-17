package ims.tuwien.ac.at.battleground;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * This class represents a Help Dialog
 *
 * @author hoch
 */
public class HelpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.help_dialog);

        TextView helpText = (TextView) findViewById(R.id.txtview_help_text);
        helpText.setMovementMethod(new ScrollingMovementMethod());

        final Button btn_Resume = (Button) findViewById(R.id.btn_return_to_menu);
        btn_Resume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HelpActivity.this.finish();
            }
        });
    }
}
