package com.example.cuponastring;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class AddContact extends AppCompatActivity {

    EditText nameInput;
    EditText phoneInput;
    Button submitContact;
    String name, phone;
    String type;
    String returnMsg = "Contact Added!";
    ImageView cImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        nameInput = (EditText) findViewById(R.id.name_input);
        phoneInput = (EditText) findViewById(R.id.phone_input);
        cImage = (ImageView) findViewById(R.id.current_image);
        submitContact = (Button) findViewById(R.id.submit_contact);
        submitContact.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finishContact();
            }
        });

        Intent intent = getIntent();
        type = intent.getStringExtra("type");

        if (type.equals("add")) {
            submitContact.setText("Add");
        }
        else {
            submitContact.setText("Update");
            returnMsg = "Contact Updated!";
            nameInput.setText(intent.getStringExtra("cName"));
            phoneInput.setText(intent.getStringExtra("cPhone"));
            int id = getResources().getIdentifier(intent.getStringExtra("cImage"), "drawable", "com.example.cuponastring");
            cImage.setImageResource(id);
        }
    }

    private void finishContact() {
        name = nameInput.getText().toString();
        phone = phoneInput.getText().toString();
        showToast();
    }

    private void showToast() {
        Toast.makeText(this, returnMsg, Toast.LENGTH_SHORT).show();
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = getIntent();
                intent.putExtra("name", name);
                intent.putExtra("phone", phone);
                if (type.equals("add")) {
                    setResult(1, intent);
                }
                else {
                    setResult(2, intent);
                }
                finish();
            }
        }, 1800);
    }
}
