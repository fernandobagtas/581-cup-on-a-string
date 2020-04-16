package com.example.cuponastring;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.contact_a_layout, null);
        contactImage = (ImageView) view.findViewById(R.id.contact_image);
        contactName = (TextView) view.findViewById(R.id.contact_name);
        contactPhone = (TextView) view.findViewById(R.id.contact_phone);

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
        float normalSize = (float) 1.0;
        float largeSize = (float) 1.5;
        //Log.d("tag", "got to turnCup()");
        /*
        if (up) {
            //enlarge contact image
            redCup.setVisibility(View.INVISIBLE);
            Animation scale = new ScaleAnimation(normalSize, largeSize, normalSize, largeSize);
            scale.setDuration(750);
            AnimationSet animSet = new AnimationSet(true);
            animSet.setFillEnabled(true);
            animSet.addAnimation(scale);
            contactName.startAnimation(animSet);
        }
        else {
            //bring contact image to normal size
            redCup.setVisibility(View.VISIBLE);
            Animation scale = new ScaleAnimation(largeSize, normalSize, largeSize, normalSize);
            scale.setDuration(750);
            AnimationSet animSet = new AnimationSet(true);
            animSet.setFillEnabled(true);
            animSet.addAnimation(scale);
            contactName.startAnimation(animSet);
        }*/
    }

}
