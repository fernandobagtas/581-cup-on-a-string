package com.example.cuponastring;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class DialCall extends AppCompatActivity {

    ImageView keyOne;
    ImageView keyTwo;
    ImageView keyThree;
    ImageView keyFour;
    ImageView keyFive;
    ImageView keySix;
    ImageView keySeven;
    ImageView keyEight;
    ImageView keyNine;
    ImageView keyZero;
    ImageView keyBack;
    TextView dialNumber;
    ImageView callButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dial_call);

        keyOne = (ImageView) findViewById(R.id.number1_key);
        keyTwo = (ImageView) findViewById(R.id.number2_key);
        keyThree = (ImageView) findViewById(R.id.number3_key);
        keyFour = (ImageView) findViewById(R.id.number4_key);
        keyFive = (ImageView) findViewById(R.id.number5_key);
        keySix = (ImageView) findViewById(R.id.number6_key);
        keySeven = (ImageView) findViewById(R.id.number7_key);
        keyEight = (ImageView) findViewById(R.id.number8_key);
        keyNine = (ImageView) findViewById(R.id.number9_key);
        keyZero = (ImageView) findViewById(R.id.number0_key);
        keyBack = (ImageView) findViewById(R.id.back_key);
        dialNumber = (TextView) findViewById(R.id.dial_number);
        callButton = (ImageView) findViewById(R.id.call_button2);

        keyOne.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dial("1");
            }
        });
        keyTwo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dial("2");
            }
        });
        keyThree.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dial("3");
            }
        });
        keyFour.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dial("4");
            }
        });
        keyFive.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dial("5");
            }
        });
        keySix.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dial("6");
            }
        });
        keySeven.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dial("7");
            }
        });
        keyEight.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dial("8");
            }
        });
        keyNine.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dial("9");
            }
        });
        keyZero.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dial("0");
            }
        });
        keyBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                undoDial();
            }
        });
        callButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                callButtonPressed();
            }
        });
    }

    private void dial(String num) {
        //Log.d("dialed", (String) dialNumber.getText());
        String currentNum = (String) dialNumber.getText() + num;

        //Log.d("dialed", currentNum);
        dialNumber.setText(currentNum);
        dialNumber.invalidate();
    }

    private void undoDial() {
        String currentNum = (String) dialNumber.getText();
        String newNum = "";
        if (currentNum.length() > 0) newNum = currentNum.substring(0, currentNum.length() - 1);
        dialNumber.setText(newNum);
        dialNumber.invalidate();
    }

    private void callButtonPressed() {
        //animate call
    }
}
