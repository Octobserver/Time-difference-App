package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class EasyResult extends AppCompatActivity {

    private Bundle extras;

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy_result);
        setTitle("EasyResult");

        extras = getIntent().getExtras();

        ((TextView) findViewById(R.id.startHour)).setText(extras.getString("startTime"));
        ((TextView) findViewById(R.id.endHour)).setText(extras.getString("endTime"));

        if (extras.getInt("correctID") == extras.getInt("chosenID")) {
            findViewById(extras.getInt("chosenID")).setBackground(getResources().getDrawable(R.drawable.imagebuttonbg));
        } else {
            findViewById(extras.getInt("correctID")).setBackground(getResources().getDrawable(R.drawable.imagebuttonbg));
            findViewById(extras.getInt("chosenID")).setBackground(getResources().getDrawable(R.drawable.imagebuttonbg2));
        }

        findViewById(R.id.retry).setOnClickListener(retry);
        findViewById(R.id.next).setOnClickListener(next);
    }

    private final View.OnClickListener retry =  new View.OnClickListener() {
        public void onClick(View v) {
            //Intent intent = new Intent(EasyResult.this, LearnEasy.class);
            //startActivity(intent);
            finish();
        }
    };

    private final View.OnClickListener next =  new View.OnClickListener() {
        public void onClick(View v) {
            Intent intent = new Intent(EasyResult.this, LearnHard.class);
            intent.putExtras(extras);
            startActivity(intent);
        }
    };
}