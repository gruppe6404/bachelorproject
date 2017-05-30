package dk.aau.gr6406.trainez;


import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.os.Vibrator;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class ViewStatisticsActivity extends AppCompatActivity {

    private static final String TAG = "dk.aau.trainez";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_statistics);
        // Find the view pager that will allow the user to swipe between fragments
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);

        // Create an adapter that knows which fragment should be shown on each page
        StatisticsFragmentAdapter adapter = new StatisticsFragmentAdapter(this, getSupportFragmentManager());

        // Set the adapter onto the view pager
        viewPager.setAdapter(adapter);

        // Give the TabLayout the ViewPager
        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);


        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.smallweighticon);
        tabLayout.getTabAt(1).setIcon(R.drawable.smallexerciseicon);
    }


    public void saveToTxtClicked(View view) {
        vibrato();
        DbHandler dbHandler = new DbHandler(this, null, null, 1);
        String exercises = dbHandler.databaseToStringExercise();
        SDTextWriter exerciseWriter = new SDTextWriter("exercise_data.txt", exercises, this.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS));
        exerciseWriter.checkExternalMedia();
        exerciseWriter.writeToSDFile();

        String weights = dbHandler.databaseToStringWeight();
        SDTextWriter sDTextWriter = new SDTextWriter("weight_data.txt", weights, this.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS));
        sDTextWriter.checkExternalMedia();
        sDTextWriter.writeToSDFile();

        Toast.makeText(ViewStatisticsActivity.this, "Data saved on external memory", Toast.LENGTH_LONG).show();

    }


    public void backToMainMenu(View view) {
        vibrato();
        finish();
    }


    private void vibrato() {
        Vibrator v = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
        // Vibrate for 500 milliseconds
        v.vibrate(50);
    }


}
