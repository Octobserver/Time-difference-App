package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences mode;
    private final View.OnClickListener goToCalculate =  new View.OnClickListener() {
        public void onClick(View v) {
            //Make a new intent and start a new calculate activity
            Log.d("Calculate", "onClick: 1");
            Intent intent = new Intent(MainActivity.this, Calculate.class);
            startActivity(intent);
        }
    };
    private final View.OnClickListener goToLearn =  new View.OnClickListener() {
        public void onClick(View v) {
            //Make a new intent and start a new learn activity
            Log.d("Learn", "onClick: 2");
            Intent intent = new Intent(MainActivity.this, LearnEasy.class);
            startActivity(intent);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Set mode to be the shared preference
        mode = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        if (savedInstanceState != null) {
            mode.edit().putString("ClockMode", savedInstanceState.getString("ClockMode")).apply();
        }

        //Handle switch
        SwitchCompat modeSwitch = findViewById(R.id.clockModeSwitch);

        if (mode.getString("ClockMode", "24").equals("24")) {
            Log.d("Switch", "checked");
            modeSwitch.setChecked(true);
        } else {
            Log.d("Switch", "not checked");
            modeSwitch.setChecked(false);
        }

        modeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // The toggle is enabled
                    Log.d("Switch", "checked");
                    mode.edit().putString("ClockMode", "24").apply();
                    Log.d("Mode", "24");
                } else {
                    // The toggle is disabled
                    Log.d("Switch", "not checked");
                    mode.edit().putString("ClockMode", "12").apply();
                    Log.d("Mode", "12");
                }
            }
        });

        //Handle onClick of buttons
        Button calculate = findViewById(R.id.calculate);
        calculate.setOnClickListener(goToCalculate);
        Button learn = findViewById(R.id.learn);
        learn.setOnClickListener(goToLearn);

    }

    @Override
    protected void onSaveInstanceState(Bundle b) {
        b.putString("ClockMode", mode.getString("ClockMode", "24"));
        super.onSaveInstanceState(b);
    }
}