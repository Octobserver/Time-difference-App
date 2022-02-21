package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;
import java.util.Random;

public class LearnEasy extends AppCompatActivity {

    private String startTime;
    private String endTime;

    private int diffHr;
    private int diffMn;

    private int corrId;
    private int chosenId;
    private Random randHr = new Random();
    private Random randMn = new Random();

    private final View.OnClickListener setDiff = new View.OnClickListener() {
        @SuppressLint("UseCompatLoadingForDrawables")
        public void onClick(View v) {
            Log.d("LearnEasy", "onClick: 1");
            if (chosenId != -1) {
                (findViewById(chosenId)).setBackgroundColor(getResources().getColor(R.color.pale_mauve));
            }
            chosenId = v.getId();
            v.setBackground(getResources().getDrawable(R.drawable.border));
            Log.d("Correct id", (Integer.valueOf(corrId)).toString());
            Log.d("Chosen id", (Integer.valueOf(chosenId)).toString());
        }
    };

    private final View.OnClickListener checkDiff = new View.OnClickListener() {
        public void onClick(View v) {
            // Make a new intent and start a new LearnEasy activity
            Log.d("LearnEasy", "onClick: 2");
            if (chosenId == -1) {
                Toast toast = Toast.makeText(getApplicationContext(), "No answer checked!", Toast.LENGTH_SHORT);
                toast.show();
            } else {
                Intent intent = new Intent(LearnEasy.this, EasyResult.class);
                Bundle extras = new Bundle();
                extras.putString("startTime", startTime);
                extras.putString("endTime", endTime);
                extras.putInt("correctID", corrId);
                extras.putInt("chosenID", chosenId);
                extras.putInt("diffHr", diffHr);
                extras.putInt("diffMin", diffMn);

                (findViewById(chosenId)).setBackgroundColor(getResources().getColor(R.color.pale_mauve));

                intent.putExtras(extras);
                startActivity(intent);
            }
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_easy);
        setTitle("LearnEasy");

        if (savedInstanceState != null) {

            this.corrId = savedInstanceState.getInt("correctID");
            this.chosenId = savedInstanceState.getInt("chosenID");
            this.diffHr = savedInstanceState.getInt("diffHr");
            this.diffMn = savedInstanceState.getInt("diffMin");
            this.startTime = savedInstanceState.getString("startTime");
            this.endTime= savedInstanceState.getString("endTime");

        } else {

            this.corrId = -1;
            this.chosenId = -1;

            Time start = new Time(randHr.nextInt(24) * 100 + randMn.nextInt(60));
            Time end = new Time(randHr.nextInt(24) * 100 + randMn.nextInt(60));
            Time diff = calcDiff(start, end);
            this.diffHr = diff.getHours();
            this.diffMn = diff.getMins();
            corrId = correctButton(diffHr);

            SharedPreferences myPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            String mode = myPref.getString("ClockMode", "24");

            if (mode.equals("24")) {
                this.startTime = start.military();
                this.endTime = end.military();


            } else {
                this.startTime = start.toString();
                this.endTime = end.toString();
            }

        }


        ((TextView) findViewById(R.id.startHour)).setText(startTime);
        ((TextView) findViewById(R.id.endHour)).setText(endTime);

        (findViewById(R.id.b1Result)).setOnClickListener(setDiff);
        (findViewById(R.id.b2Result)).setOnClickListener(setDiff);
        (findViewById(R.id.b3Result)).setOnClickListener(setDiff);

        (findViewById(R.id.check)).setOnClickListener(checkDiff);

    }

   @Override
    protected void onPause() {
        (findViewById(chosenId)).setBackgroundColor(getResources().getColor(R.color.pale_mauve));
        super.onPause();

    }

    @Override
    protected void onResume() {
        (findViewById(chosenId)).setBackground(getResources().getDrawable(R.drawable.border));
        super.onResume();

    }

    @Override
    protected void onSaveInstanceState(Bundle b) {
        b.putString("startTime", startTime);
        b.putString("endTime", endTime);
        b.putInt("correctID", corrId);
        b.putInt("chosenID", chosenId);
        b.putInt("diffHr", diffHr);
        b.putInt("diffMin", diffMn);
        super.onSaveInstanceState(b);
    }

    private Time calcDiff(Time start, Time end) {
        int hrDiff;
        int mnDiff;
        if ((start.getHours() > end.getHours()) ||
                (start.getHours() == end.getHours() && start.getMins() > end.getMins())) {
            hrDiff = 24 - start.getHours() + end.getHours();
        } else {
            hrDiff = end.getHours() - start.getHours();
        }

        if (start.getMins() > end.getMins()) {
            hrDiff -= 1;
            mnDiff = 60 - start.getMins() + end.getMins();
        }else {
            mnDiff = end.getMins() - start.getMins();
        }

        return new Time(hrDiff * 100 + mnDiff);
    }

    private int correctButton(int hr) {
        if (hr < 8) {
            return R.id.b1Result;
        } else if (hr < 16) {
            return R.id.b2Result;
        } else {
            return R.id.b3Result;
        }
    }
}
