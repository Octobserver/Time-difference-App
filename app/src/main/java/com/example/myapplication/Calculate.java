package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

public class Calculate extends AppCompatActivity {

    private String diffHr;
    private String diffMin;
    private final View.OnClickListener calculateDiff =  new View.OnClickListener() {
        public void onClick(View v) {
            //Make a new intent and start a new calculate activity
            Log.d("CalculateDIff", "onClick: 1");
            String stHr = ((EditText) findViewById(R.id.startHr)).getText().toString();
            String stMn = ((EditText) findViewById(R.id.startMin)).getText().toString();
            String edHr = ((EditText) findViewById(R.id.endHr)).getText().toString();
            String edMn = ((EditText) findViewById(R.id.endMin)).getText().toString();
            Log.d("stHr", stHr);
            Log.d("stMin", stMn);
            Log.d("edHr", edHr);
            Log.d("edMn", edMn);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate);
        diffHr = "00";
        diffMin = "00";
        ImageButton button = (ImageButton) findViewById(R.id.calcDiff);
        button.setOnClickListener(calculateDiff);
    }
}