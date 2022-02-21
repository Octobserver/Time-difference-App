package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.text.DecimalFormat;

public class HardResult extends AppCompatActivity {

    private Bundle extras;
    private static final DecimalFormat two0s = new DecimalFormat("00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hard_result);
        setTitle("HardResult");

        extras = getIntent().getExtras();

       ((TextView) findViewById(R.id.startHour)).setText(extras.getString("startTime"));
       ((TextView) findViewById(R.id.endHour)).setText(extras.getString("endTime"));

       ((TextView) findViewById(R.id.hardResultHr)).setText(two0s.format(extras.getInt("diffHr")));
       ((TextView) findViewById(R.id.hardResultMin)).setText(two0s.format(extras.getInt("diffMin")));

       if (extras.getInt("diffHrUser") != extras.getInt("diffHr") ||
               extras.getInt("diffMnUser") != extras.getInt("diffMin")) {
           ((TextView) findViewById(R.id.hardResult)).setText("INCORRECT");
       } else {
           ((TextView) findViewById(R.id.hardResult)).setText("CORRECT!");
       }

    }
}