package com.example.cuponastring;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.os.CountDownTimer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    ViewPager pager;
    ViewPagerAdapter viewPagerAdapter;
    ImageView addContact;
    Button editContact;
    ImageView dialCall;
    ImageView callButton;
    ImageView simCall;
    boolean callInProgress = false;
    MediaPlayer mPlayer;

    // record the compass picture angle turned
    private float talkDegree = 90f;
    private float endDegree = 180f;

    // device sensor manager
    private SensorManager mSensorManager;

    private TextView tvHeading;

    Animation anim1;
    Animation anim2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addContact = (ImageView) findViewById(R.id.add_contact);
        addContact.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                addContactPressed();
            }
        });
        editContact = (Button) findViewById(R.id.edit_contact);
        editContact.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                editContactPressed();
            }
        });
        dialCall = (ImageView) findViewById(R.id.dialup);
        dialCall.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dialCallPressed();
            }
        });
        callButton = (ImageView) findViewById(R.id.call_button);
        callButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (callInProgress) {
                    callInProgress = false;
                    callButtonPressed(false);
                }
                else {
                    callButtonPressed(true);
                    callInProgress = true;
                }
            }
        });
        simCall = (ImageView) findViewById(R.id.sim_call);
        simCall.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                simCallPressed();
            }
        });
        mPlayer = MediaPlayer.create(MainActivity.this, R.raw.ringtone);


        pager = findViewById(R.id.pager);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        ContactA contact1 = new ContactA("Contact 1", "forsen_cd", "4031234567");
        ContactA contact2 = new ContactA("Contact 2", "cute_duck", "5871234567");
        ContactA contact3 = new ContactA("Contact 3", "cute_piggy", "5879876543");

        viewPagerAdapter.addContact(contact1);
        viewPagerAdapter.addContact(contact2);
        viewPagerAdapter.addContact(contact3);
        pager.setAdapter(viewPagerAdapter);

        // initialize your android device sensor capabilities
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        //set the screen to fullscreen
        View view = getWindow().getDecorView();
        view.setSystemUiVisibility
                (
                        View.SYSTEM_UI_FLAG_IMMERSIVE
                                // Set the content to appear under the system bars so that the
                                // content doesn't resize when the system bars hide and show.
                                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                // Hide the nav bar and status bar
                                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                                | View.SYSTEM_UI_FLAG_FULLSCREEN
                );

    }

    @Override
    protected void onResume() {
        super.onResume();

        // for the system's orientation sensor registered listeners
        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
                SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    protected void onPause() {
        super.onPause();

        // to stop the listener and save battery
        mSensorManager.unregisterListener(this);
    }

    //Where it determines the position of the device by degrees
    @Override
    public void onSensorChanged(SensorEvent event) {

        // get the angle around the x-axis rotated
        float degree = Math.round(event.values[1]);
        //Log.d("XDegrees", String.valueOf(degree));
        ContactA currentView = (ContactA) viewPagerAdapter.getItem(pager.getCurrentItem());

        //the position of your phone while you're upright is in the negative degrees (-180 to 180)
        degree = -degree;

        if (degree < (talkDegree + 10) && degree > (talkDegree - 10))
        {
            Log.d("cFlag", "true");
            currentView.turnCup(true, anim1);
            //call method in contact page to enlarge contact's image
            callButtonPressed(true);
            callInProgress = true;
        }
        else if (degree < (endDegree + 10) && degree > 170f){
            //call method in contact page to shrink contact's image
            callButtonPressed(false);
            callInProgress = false;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        //not in use
    }

    private void addContactPressed() {
        //Log.d("AddContact", "button pressed");
        Intent intent = new Intent(MainActivity.this, AddContact.class);
        intent.putExtra("type", "add");
        startActivityForResult(intent, 0);
    }

    private void editContactPressed() {
        //Log.d("AddContact", "button pressed");
        ContactA contact = (ContactA) viewPagerAdapter.getItem(pager.getCurrentItem());
        Intent intent = new Intent(MainActivity.this, AddContact.class);
        intent.putExtra("type", "edit");
        intent.putExtra("cName", contact.getcName());
        intent.putExtra("cPhone", contact.getcPhone());
        intent.putExtra("cImage", contact.getcImage());
        startActivityForResult(intent, 0);
    }

    private void callButtonPressed(boolean up) {
        ContactA currentView = (ContactA) viewPagerAdapter.getItem(pager.getCurrentItem());
        if (up) {
            mPlayer.stop();
            currentView.stopSimCall();
            currentView.turnCup(true);
            addContact.setVisibility(View.INVISIBLE);
            dialCall.setVisibility(View.INVISIBLE);
            editContact.setVisibility(View.INVISIBLE);
            callButton.setImageResource(R.drawable.hangup);
        }
        else {
            currentView.turnCup(false);
            addContact.setVisibility(View.VISIBLE);
            dialCall.setVisibility(View.VISIBLE);
            editContact.setVisibility(View.VISIBLE);
            callButton.setImageResource(R.drawable.call);
        }
    }

    private void dialCallPressed() {
        Intent intent = new Intent(MainActivity.this, DialCall.class);
        startActivityForResult(intent, 0);
    }

    private void simCallPressed() {
        mPlayer.start();
        ContactA currentView = (ContactA) viewPagerAdapter.getItem(pager.getCurrentItem());
        currentView.startSimCall();
    }

    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        // Collect data from the intent and use it
        String name = "Contact";
        String phone = "4031239876";

        if ((data != null) && ((resultCode == 1) || (resultCode == 2))) {
            name = data.getStringExtra("name");
            phone = data.getStringExtra("phone");
        }

        if (resultCode == 1) {
            ContactA newContact = new ContactA(name, "contact_icon", phone);
            viewPagerAdapter.addContact(newContact);
            viewPagerAdapter.notifyDataSetChanged();
        }
        else if (resultCode == 2) {
            int currentPosition = pager.getCurrentItem();
            viewPagerAdapter.updateContact(name, phone, currentPosition);
            viewPagerAdapter.notifyDataSetChanged();
        }
    }

}
