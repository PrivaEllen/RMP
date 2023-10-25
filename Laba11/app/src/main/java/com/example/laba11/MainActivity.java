package com.example.laba11;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    EditText email;
    EditText password;
    AppCompatButton button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = findViewById(R.id.Email);
        password = findViewById(R.id.Password);
        button = findViewById(R.id.SubmitButton);

        email.setOnFocusChangeListener((v, hasFocus) -> {
            email.setTextColor(Color.parseColor("#000000"));
        });

        password.setOnFocusChangeListener((v, hasFocus) -> {
            password.setTextColor(Color.parseColor("#000000"));
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("email", email.getText().toString());
        outState.putString("password", password.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String restoredEmail = savedInstanceState.getString("email");
        String restoredPassword = savedInstanceState.getString("password");
        email.setText(restoredEmail);
        password.setText(restoredPassword);
    }

    public void onClick(View view){
        String[] emailsArray = getApplicationContext().getResources().getStringArray(R.array.emails);
        String[] passwordsArray = getApplicationContext().getResources().getStringArray(R.array.passwords);

        if (Arrays.asList(emailsArray).contains(email.getText().toString()) &&
                Arrays.asList(passwordsArray).contains(password.getText().toString())) {
            Intent intent = new Intent(this, BottomNavActivity.class);
            startActivity(intent);
        }
        else {
            email.setTextColor(Color.parseColor("#FF0000"));
            password.setTextColor(Color.parseColor("#FF0000"));
        }
    }
}