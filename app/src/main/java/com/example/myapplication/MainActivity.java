package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

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
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Set default preference
        mode = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor peditor = mode.edit();
        peditor.putString("ClockMode", "24");
        peditor.apply();

        //Handle onClick of buttons
        Button calculate = (Button) findViewById(R.id.calculate);
        calculate.setOnClickListener(goToCalculate);
        Button learn = (Button) findViewById(R.id.learn);
        learn.setOnClickListener(goToLearn);

        //Handle switch
        Switch modeSwitch = (Switch) findViewById(R.id.clockModeSwitch);
        modeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // The toggle is enabled
                    Log.d("Switch", "checked");
                    SharedPreferences.Editor peditor = mode.edit();
                    peditor.putString("ClockMode", "24");
                    peditor.apply();
                    Log.d("Mode", mode.getString("ClockMode", "24"));
                } else {
                    // The toggle is disabled
                    Log.d("Switch", "not checked");
                    SharedPreferences.Editor peditor = mode.edit();
                    peditor.putString("ClockMode", "12");
                    peditor.apply();
                    Log.d("Mode", mode.getString("ClockMode", "24"));
                }
            }
        });

    }


    protected void onStart() {
        super.onStart();
    }

    protected void onResume() {
        super.onResume();
    }

    protected void onPause() {
 /*     SharedPreferences.Editor peditor = mode.edit();
        peditor.putString("ClockMode", "military");
        peditor.apply();*/
        super.onPause();
    }
}