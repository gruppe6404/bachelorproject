package dk.aau.gr6406.trainez;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Environment;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class EnterWeightActivity extends AppCompatActivity {

    private static final String TAG = "dk.aau.trainez";
    private DbHandler dbHandler;
    private EditText weightText;
    private float lastWeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_weight);
        dbHandler = new DbHandler(this, null, null, 1);
        weightText = (EditText) findViewById(R.id.enter_weight_edit_text);
        promtText();
        textfieldListener();
    }

    private void promtText() {
        ArrayList<WeightMeasurement> weight = dbHandler.databaseToStringWeightDate();
        lastWeight = weight.get(weight.size()-1).get_kilo();

        weightText.setHint(String.valueOf(lastWeight));
    }

    private void textfieldListener(){

        weightText.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if(s.length() > 1){
                    validateWeight();
                }


            }
        });


    }

    private void validateWeight() {
        TextView warningText = (TextView) findViewById(R.id.WarnWeightText);
        float weightDif = Math.abs(lastWeight-Float.parseFloat(String.valueOf(weightText.getText())));
        if(weightDif>10){
            warningText.setText(getResources().getString(R.string.weight_warning));
        }
        else {
            warningText.setText("");
        }
    }



    /*
    * This method is called when the save button has been clicked
    */
    public void saveButtonClicked(View view) {
        vibrato();

        setFirstTimePref();


        Log.i(TAG,"tytytj");
        float weight = 0;
     try{
         weight = Float.parseFloat(weightText.getText().toString());
         makeTimeFramedNotification(5);
     }catch (Exception e){

     }

        Log.i(TAG,"bæf");
        if (weight>20 && weight<300) {
            Log.i(TAG,"tytytj");
            float enteredWeight = Float.parseFloat(weightText.getText().toString());
            WeightMeasurement weightMeasurement = new WeightMeasurement(enteredWeight);
            weightMeasurement.set_date(getCurrentDate());

            dbHandler.addWeightMeasurement(weightMeasurement);

            Log.i(TAG, dbHandler.databaseToStringWeight());

            Toast.makeText(EnterWeightActivity.this, "Weight registered", Toast.LENGTH_SHORT).show();
            makeTimeFramedNotification(3); // pareameter = seconds before notification
            Intent intent = new Intent(this,MainMenuActivity.class);
            startActivity(intent);

        } else {
            Toast.makeText(EnterWeightActivity.this, "Invalid value!", Toast.LENGTH_SHORT).show();
        }

    }

    private void setFirstTimePref() {
        SharedPreferences sharedPreferences = getSharedPreferences("FirstTimeUse", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("valueFTU", "FTUcomplete9");
        editor.apply();
    }

    /**
     * This method brings you to the menu.
     *
     * @param view
     */
    public void goBackButtonClicked(View view) {
        vibrato();
        Toast.makeText(EnterWeightActivity.this, "No weight registred",Toast.LENGTH_LONG).show();
        finish();
    }




    /**
     * This method is used to set to alert (notification)
     */
    private void makeTimeFramedNotification(int seconds) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        Long alertTime = calendar.getTimeInMillis() + 1000 * seconds;
        Log.i(TAG, "HEJHEJHEJ" + alertTime);
        Intent alertIntent = new Intent(this, dk.aau.gr6406.trainez.Alarm.class);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        alarmManager.set(AlarmManager.RTC_WAKEUP, alertTime, PendingIntent.getBroadcast(this,
                1, alertIntent, PendingIntent.FLAG_UPDATE_CURRENT));
    }

    private String getCurrentDate() {
        long unixTime = System.currentTimeMillis();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        return formatter.format(unixTime);
    }



    private void vibrato(){
        Vibrator v = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
        // Vibrate for 500 milliseconds
        v.vibrate(50);
    }
}






