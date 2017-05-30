package dk.aau.gr6406.trainez;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.AlarmClock;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

/**
 *  This class is the activity from where the patient can set up its trainingprogramme.
 *  This is done by using a exp.view.
 */
public class ComposeProgrammeActivity extends AppCompatActivity {

    private static final String TAG = "dk.aau.trainez";
    ExpandableListView expListView;
    ExpAdapter adapter;
    String[] categories;
    Exercise groupedExercises[][];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose_programme);
        Log.i(TAG, "Starting the adapter - In onCreate");
        startAdapter();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    /**
     * This method starts the adapter.
     */
    private void startAdapter() {
        TrainingProgramme trainingProgramme = new TrainingProgramme(this);

        categories = trainingProgramme.getCategoriesArray();
        groupedExercises = trainingProgramme.getGroupedExercises();

        adapter = new ExpAdapter(this, categories, groupedExercises);
        //Linking expandable list view to this activity
        expListView = (ExpandableListView) findViewById(R.id.elv);
        expListView.setAdapter(adapter);
        expandListview();

    }


    public void saveButtonClicked(View view) {

        showDialog();


    }


    public void goBackButtonClicked(View view) {
        finish();
    }


    /**
     * When the patient press save, the reps. are saved in sharedpref.
     *
     */
    private void saveRepetitions() {

        Toast.makeText(ComposeProgrammeActivity.this, "Program saved", Toast.LENGTH_LONG).show();

        SharedPreferences sharedPref = getSharedPreferences("RepetitionInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        int entryInList = 1;
        for (int row = 0; row < ExpAdapter.childelements.length; row++) {
            for (int col = 0; col < ExpAdapter.childelements[row].length; col++) {
                editor.putInt("e" + String.valueOf(entryInList), ExpAdapter.childelements[row][col].getRepetitions());
                entryInList++;
            }
        }
        editor.apply();
    }


    /**
     * The default for exp. is that it is collapsed. We want to start with an expanded view.
     *
     */
    private void expandListview(){
        for (int i = 0; i < categories.length; i++) {
            expListView.expandGroup(i);
        }
    }


  private void showDialog(){
      AlertDialog alertDialog = new AlertDialog.Builder(ComposeProgrammeActivity.this, R.style.AlertDialogCustom).create();
      //alertDialog.setTitle(" : ) ! ");


      alertDialog.setMessage( getResources().getString(R.string.reminder));

      alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, getResources().getString(R.string.yesplease),
              new DialogInterface.OnClickListener() {
                  public void onClick(DialogInterface dialog, int which) {
                      Intent i = new Intent(AlarmClock.ACTION_SET_ALARM);
                      i.putExtra(AlarmClock.EXTRA_MESSAGE, "Train EZ");
                      i.putExtra(AlarmClock.EXTRA_HOUR, 10);
                      i.putExtra(AlarmClock.EXTRA_MINUTES, 30);
                      startActivity(i);
                      saveRepetitions();
                      finish();
                      dialog.dismiss();
                  }
              });



      alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, getResources().getString(R.string.noplease),
              new DialogInterface.OnClickListener() {
                  public void onClick(DialogInterface dialog, int which) {
                      saveRepetitions();
                     finish();

                      dialog.dismiss();
                  }
              });




      alertDialog.show();
  }




}
