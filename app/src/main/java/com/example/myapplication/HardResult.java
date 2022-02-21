package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class HardResult extends AppCompatActivity {

    private Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hard_result);
        setTitle("HardResult");

        extras = getIntent().getExtras();

       ((TextView) findViewById(R.id.startHour)).setText(extras.getString("startTime"));
       ((TextView) findViewById(R.id.endHour)).setText(extras.getString("endTime"));

       ((TextView) findViewById(R.id.hardResultHr)).setText(Integer.toString(extras.getInt("diffHr")));
       ((TextView) findViewById(R.id.hardResultMin)).setText(Integer.toString(extras.getInt("diffMin")));

       if (extras.getInt("diffHrUser") != extras.getInt("diffHr") ||
               extras.getInt("diffMnUser") != extras.getInt("diffMin")) {
           ((TextView) findViewById(R.id.hardResult)).setText("INCORRECT");
       } else {
           ((TextView) findViewById(R.id.hardResult)).setText("CORRECT!");
       }

    }
}