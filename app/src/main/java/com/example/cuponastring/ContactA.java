package com.example.cuponastring;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

    public void turnCup(boolean up) {
        if (up) {
            //enlarge contact image
        }
        else {
            //bring contact image to normal size
        }
    }

}
