package com.example.cuponastring;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class AddContact extends AppCompatActivity {

    EditText nameInput;
    EditText phoneInput;
    Button submitContact;
    String name, phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        nameInput = (EditText) findViewById(R.id.name_input);
        phoneInput = (EditText) findViewById(R.id.phone_input);
        submitContact = (Button) findViewById(R.id.submit_contact);
        submitContact.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finishContact();
            }
        });
    }

    private void finishContact() {
        name = nameInput.getText().toString();
        phone = phoneInput.getText().toString();
        showToast();
    }

    private void showToast() {
        Toast.makeText(this, "Contact Added!", Toast.LENGTH_SHORT).show();
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = getIntent();
                intent.putExtra("name", name);
                intent.putExtra("phone", phone);
                setResult(0, intent);
                finish();
            }
        }, 2000);
    }
}
