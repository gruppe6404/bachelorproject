package dk.aau.gr6406.trainez;

/**
 * Created by Rolf Oberlin on 04-05-2017.
 */

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageButton;


public class PopHelp extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.pophelpwindow);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.9),(int)(height*.9));

        ImageButton b = (ImageButton) findViewById(R.id.CloseHelpPop);
        b.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                vibrato();
                finish();
            }
        });
    }

    private void vibrato() {
        Vibrator v = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
        // Vibrate for 500 milliseconds
        v.vibrate(50);
    }


}
