package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Calculate extends AppCompatActivity {

    Integer stHr;
    Integer stMn;
    Integer edHr;
    Integer edMn;
    private final View.OnClickListener calculateDiff =  new View.OnClickListener() {
        public void onClick(View v) {
            //Make a new intent and start a new calculate activity
            Log.d("CalculateDiff", "onClick: 1");
            stHr = Integer.parseInt(((EditText) findViewById(R.id.startHr)).getText().toString());
            stMn = Integer.parseInt(((EditText) findViewById(R.id.startMin)).getText().toString());
            edHr = Integer.parseInt(((EditText) findViewById(R.id.endHr)).getText().toString());
            edMn = Integer.parseInt(((EditText) findViewById(R.id.endMin)).getText().toString());
            Log.d("stHr", stHr.toString());
            Log.d("stMin", stMn.toString());
            Log.d("edHr", edHr.toString());
            Log.d("edMn", edMn.toString());

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

                ((TextView) findViewById(R.id.diffHr)).setText(Integer.toString(hrDiff));
                ((TextView) findViewById(R.id.diffMin)).setText(Integer.toString(mnDiff));
            }

        }

        private boolean isValid(Integer stHr, Integer stMn, Integer edHr, Integer edMn) {
            boolean v = true;

            if (isValidHr(stHr)) {
                Toast toast = Toast.makeText(getApplicationContext(), "Invalid Start Hour!", Toast.LENGTH_SHORT);
                toast.show();
                ((EditText) findViewById(R.id.startHr)).setText(R.string.hint);
                v = false;
            }
            if (isValidMin(stMn)) {
                Toast toast = Toast.makeText(getApplicationContext(), "Invalid Start Minute!", Toast.LENGTH_SHORT);
                toast.show();
                ((EditText) findViewById(R.id.startMin)).setText(R.string.hint);
                v = false;
            }
            if (isValidHr(edHr)) {
                Toast toast = Toast.makeText(getApplicationContext(), "Invalid End hour!", Toast.LENGTH_SHORT);
                toast.show();
                ((EditText) findViewById(R.id.endHr)).setText(R.string.hint);
                v = false;
            }
            if (isValidMin(edMn)) {
                Toast toast = Toast.makeText(getApplicationContext(), "Invalid End Minute!", Toast.LENGTH_SHORT);
                toast.show();
                ((EditText) findViewById(R.id.endMin)).setText(R.string.hint);
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
        stHr = 0;
        stMn = 0;
        edHr = 0;
        edMn = 0;
        ImageButton button = findViewById(R.id.calcDiff);
        button.setOnClickListener(calculateDiff);
    }
}