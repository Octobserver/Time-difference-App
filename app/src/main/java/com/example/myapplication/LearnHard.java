package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class LearnHard extends AppCompatActivity {

    private int diffHrUser;
    private int diffMnUser;
    private Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_hard);
        setTitle("LearnHard");

        extras = getIntent().getExtras();

        ((TextView) findViewById(R.id.startHour)).setText(extras.getString("startTime"));
        ((TextView) findViewById(R.id.endHour)).setText(extras.getString("endTime"));

        findViewById(R.id.check_Hard).setOnClickListener(check_Hard);

    }

    private final View.OnClickListener check_Hard =  new View.OnClickListener() {
        public void onClick(View v) {

            Log.d("Check_LearnHard", "onClick: 1");
            diffHrUser = Integer.parseInt(((TextView)findViewById(R.id.hrInputHard)).getText().toString());
            diffMnUser = Integer.parseInt(((TextView)findViewById(R.id.mnInputHard)).getText().toString());

            extras.putInt("diffHrUser", diffHrUser);
            extras.putInt("diffMnUser", diffMnUser);

            Intent intent = new Intent(LearnHard.this, HardResult.class);
            intent.putExtras(extras);

            startActivity(intent);
        }
    };

}