package dk.aau.gr6406.trainez;

/**
 * Created by Rolf Oberlin on 04-05-2017.
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageButton;


public class PopContact extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.popcontactwindow);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.9),(int)(height*.9));

        ImageButton c = (ImageButton) findViewById(R.id.CloseContactPop);
        c.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                vibrato();
                finish();
            }
        });


        ImageButton visitRn = (ImageButton) findViewById(R.id.visitrn);
        visitRn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://www.rn.dk";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
    }


    private void vibrato() {
        Vibrator v = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
        // Vibrate for 500 milliseconds
        v.vibrate(50);
    }

}
