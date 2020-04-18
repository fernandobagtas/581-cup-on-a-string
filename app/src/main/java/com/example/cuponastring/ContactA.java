package com.example.cuponastring;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class ContactA extends Fragment {

    private ImageView redCup;
    private ImageView contactImage;
    private TextView contactName;
    private TextView contactPhone;
    private String cName = "Contact's Name";
    private String cImage = "cute_piggy";
    private String cPhone = "4031234567";
    private Chronometer chronometer;
    private final float normalSize = 1.0f;
    private final float mediumSize = 1.3f;
    private final float largeSize = 1.8f;
    private Handler handler;

    private boolean cFlag = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.contact_a_layout, null);
        contactImage = (ImageView) view.findViewById(R.id.contact_image);
        contactName = (TextView) view.findViewById(R.id.contact_name);
        contactPhone = (TextView) view.findViewById(R.id.contact_phone);
        chronometer = (Chronometer) view.findViewById(R.id.chronometer1);
        chronometer.setVisibility(View.INVISIBLE);
        handler = new Handler();

        redCup = (ImageView) view.findViewById(R.id.red_cup_down);

        contactName.setText(cName);
        contactPhone.setText(cPhone);

        int id = getResources().getIdentifier(cImage, "drawable", "com.example.cuponastring");
        contactImage.setImageResource(id);

        return view;
    }

    public ContactA(String name, String image, String phone) {
        super();
        this.cName = name;
        this.cImage = image;
        this.cPhone = phone;
    }

    public void updateInfo(String name, String phone) {
        this.cName = name;
        this.cPhone = phone;
        contactName.setText(name);
        contactPhone.setText(phone);
    }

    public String getcName() {
        return cName;
    }

    public String getcPhone() {
        return cPhone;
    }

    public String getcImage() {
        return cImage;
    }

    public void turnCup(boolean up) {

        if (up) {
            redCup.setVisibility(View.INVISIBLE);
            contactName.setVisibility(View.INVISIBLE);
            contactPhone.setVisibility(View.INVISIBLE);
            Animation scale = new ScaleAnimation(normalSize, largeSize, normalSize, largeSize, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            scale.setDuration(1000);
            scale.setFillAfter(true);
            contactImage.startAnimation(scale);
            chronometer.setVisibility(View.VISIBLE);
            chronometer.setBase(SystemClock.elapsedRealtime());
            chronometer.start();
        }
        else {
            redCup.setVisibility(View.VISIBLE);
            contactName.setVisibility(View.VISIBLE);
            contactPhone.setVisibility(View.VISIBLE);
            Animation scale = new ScaleAnimation(largeSize, normalSize, largeSize, normalSize, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            scale.setDuration(1000);
            scale.setFillAfter(true);
            contactImage.startAnimation(scale);
            chronometer.stop();
            chronometer.setVisibility(View.INVISIBLE);
        }
    }

    public void startSimCall() {

        int delay = 1000;
        for (int i = 0; i < 10; i++) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Animation scale1 = new ScaleAnimation(normalSize, largeSize, normalSize, largeSize, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                    scale1.setDuration(1000);
                    contactImage.startAnimation(scale1);
                    Animation scale2 = new ScaleAnimation(normalSize, mediumSize, normalSize, mediumSize, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                    scale2.setDuration(1000);
                    redCup.startAnimation(scale2);
                }
            }, delay);
            delay += 1000;
        }
    }

    public void stopSimCall() {
        handler.removeCallbacksAndMessages(null);
    }

}
