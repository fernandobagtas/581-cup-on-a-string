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
import android.view.animation.AnimationUtils;
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
    private String cName = "Contact's Name";
    private String cImage = "cute_piggy";

    private boolean cFlag = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.contact_a_layout, null);
        contactImage = (ImageView) view.findViewById(R.id.contact_image);
        contactName = (TextView) view.findViewById(R.id.contact_name);
        redCup = (ImageView) view.findViewById(R.id.red_cup_down);

        contactName.setText(cName);

        int id = getResources().getIdentifier(cImage, "drawable", "com.example.cuponastring");
        contactImage.setImageResource(id);

        return view;
    }

    public ContactA(String name, String image) {
        super();
        this.cName = name;
        this.cImage = image;
    }

    public void turnCup(boolean up, Animation anim) {
        final float normalSize = (float) 1.0;
        final float largeSize = (float) 1.5;
        //Log.d("tag", "got to turnCup()");

        if (up && !cFlag) {
            cFlag = true;
            //enlarge contact image
            redCup.setVisibility(View.INVISIBLE);
            contactName.setVisibility(View.INVISIBLE);
            contactImage.clearAnimation();
            contactImage.startAnimation(anim);

            //contactImage.setScaleX(largeSize);
            //contactImage.setScaleY(largeSize);
            anim.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    contactImage.setScaleX(largeSize);
                    contactImage.setScaleY(largeSize);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
        }
        else if (!up && cFlag){
            cFlag = false;
            //bring contact image to normal size
            redCup.setVisibility(View.VISIBLE);
            contactName.setVisibility(View.VISIBLE);
            contactImage.clearAnimation();
            contactImage.startAnimation(anim);

            //contactImage.setScaleX(normalSize);
            //contactImage.setScaleY(normalSize);
            anim.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    contactImage.setScaleX(normalSize);
                    contactImage.setScaleY(normalSize);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
        }
    }

}
