package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class Calculate extends AppCompatActivity {

    //Start time and end time represented by integers
    private Integer stHr = 8;
    private Integer stMn = 0;
    private Integer edHr = 20;
    private Integer edMn = 0;

    //Format for displaying time
    private static final DecimalFormat two0s = new DecimalFormat("00");

    private final View.OnClickListener calculateDiff =  new View.OnClickListener() {
        public void onClick(View v) {
            //Make a new intent and start a new calculate activity
            Log.d("CalculateDiff", "onClick: 1");
            try {
                stHr = Integer.parseInt(((EditText) findViewById(R.id.startHr)).getText().toString());
                stMn = Integer.parseInt(((EditText) findViewById(R.id.startMin)).getText().toString());
                edHr = Integer.parseInt(((EditText) findViewById(R.id.endHr)).getText().toString());
                edMn = Integer.parseInt(((EditText) findViewById(R.id.endMin)).getText().toString());
            } catch (NumberFormatException nfe) {
                Log.d("CalculateDiff", "handle default");
            }

            if (isValid(stHr, stMn, edHr, edMn)) {
                int hrDiff;
                int mnDiff;
                if ((stHr > edHr) || (stHr.equals(edHr) && stMn > edMn)) {
                    hrDiff = 24 - stHr + edHr;
                }
                else {
                    hrDiff = edHr - stHr;
                }

                if (stMn > edMn) {
                    hrDiff -= 1;
                    mnDiff = 60 - stMn + edMn;
                }else {
                    mnDiff = edMn - stMn;
                }

                ((TextView) findViewById(R.id.diffHr)).setText(two0s.format(hrDiff));
                ((TextView) findViewById(R.id.diffMin)).setText(two0s.format(mnDiff));
            }

        }

        private boolean isValid(Integer stHr, Integer stMn, Integer edHr, Integer edMn) {
            boolean v = true;

            if (isValidHr(stHr)) {
                Toast toast = Toast.makeText(getApplicationContext(), "Invalid Start Hour!", Toast.LENGTH_SHORT);
                toast.show();
                ((EditText) findViewById(R.id.startHr)).setText(R.string.defaultStr);
                v = false;
            }
            if (isValidMin(stMn)) {
                Toast toast = Toast.makeText(getApplicationContext(), "Invalid Start Minute!", Toast.LENGTH_SHORT);
                toast.show();
                ((EditText) findViewById(R.id.startMin)).setText(R.string.defaultStr);
                v = false;
            }
            if (isValidHr(edHr)) {
                Toast toast = Toast.makeText(getApplicationContext(), "Invalid End Hour!", Toast.LENGTH_SHORT);
                toast.show();
                ((EditText) findViewById(R.id.endHr)).setText(R.string.defaultStr);
                v = false;
            }
            if (isValidMin(edMn)) {
                Toast toast = Toast.makeText(getApplicationContext(), "Invalid End Minute!", Toast.LENGTH_SHORT);
                toast.show();
                ((EditText) findViewById(R.id.endMin)).setText(R.string.defaultStr);
                v = false;
            }

            return v;
        }

        private boolean isValidHr(Integer hr) {
            return (0 > hr || hr >= 24);
        }

        private boolean isValidMin(Integer min) { return (0 > min || min >= 60); }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate);
        setTitle("Calculate");

        ImageButton button = findViewById(R.id.calcDiff);
        button.setOnClickListener(calculateDiff);
    }
}