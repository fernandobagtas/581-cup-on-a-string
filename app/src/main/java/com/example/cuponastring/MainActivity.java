package com.example.cuponastring;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.os.CountDownTimer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    ViewPager pager;
    ViewPagerAdapter viewPagerAdapter;
    ImageView addContact;

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

        pager = findViewById(R.id.pager);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        ContactA contact1 = new ContactA("Contact 1", "forsen_cd");
        ContactA contact2 = new ContactA("Contact 2", "cute_duck");
        ContactA contact3 = new ContactA("Contact 3", "cute_piggy");

        viewPagerAdapter.addContact(contact1);
        viewPagerAdapter.addContact(contact2);
        viewPagerAdapter.addContact(contact3);
        pager.setAdapter(viewPagerAdapter);

        // initialize your android device sensor capabilities
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        tvHeading = (TextView) findViewById(R.id.tvHeading);

        anim1 = AnimationUtils.loadAnimation(this, R.anim.big_anim);
        anim2 = AnimationUtils.loadAnimation(this, R.anim.small_anim);

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

        // get the angle around the z-axis rotated
        float degree = Math.round(event.values[1]);
        //Log.d("YDegrees", String.valueOf(degree));
        tvHeading.setText("Heading: " + Float.toString(degree) + " degrees");

        ContactA currentView = (ContactA) viewPagerAdapter.getItem(pager.getCurrentItem());

        //the position of your phone while you're upright is in the negative degrees (-180 to 180)
        degree = -degree;

        if(degree < (talkDegree + 10) && degree > (talkDegree - 10))
        {
            Log.d("cFlag", "true");
            currentView.turnCup(true, anim1);
            //call method in contact page to enlarge contact's image
            //currentView.turnCup(cFlag, anim1);
        }
        else  if(degree < (endDegree + 10) && degree > 170f){
            //call method to show red cup again
            Log.d("cFlag", "false");
            currentView.turnCup(false, anim2);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        //not in use
    }

    private void addContactPressed() {
        //Log.d("AddContact", "button pressed");
        Intent intent = new Intent(MainActivity.this, AddContact.class);
        startActivityForResult(intent, 0);
    }

    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        // Collect data from the intent and use it
        if (data != null) {
            String name = data.getStringExtra("name");
            String phone = data.getStringExtra("phone");
            ContactA newContact = new ContactA(name, "contact_icon");
            viewPagerAdapter.addContact(newContact);
            viewPagerAdapter.notifyDataSetChanged();
        }
    }

}
