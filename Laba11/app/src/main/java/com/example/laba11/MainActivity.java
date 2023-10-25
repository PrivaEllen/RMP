package com.example.laba11;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.laba11.Room.App;
import com.example.laba11.Room.RoomDB;
import com.example.laba11.Room.Users.UsersDao;
import com.example.laba11.Room.Users.UsersEntity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText email;
    EditText password;
    AppCompatButton button;
    DBHelper dbHelper;
    String[] usersArray = new String[]{"login", "password"};
    List<String> emailsArray = new ArrayList<>();
    List<String> passwordsArray = new ArrayList<>();

    List<String> logins = Arrays.asList(
            "1",
            "priva@sfedu.ru",
            "smakarov@sfedu.ru",
            "mgavrilova@sfedu.ru",
            "mfomin@sfedu.ru"
    );

    List<String> passwords = Arrays.asList(
            "1",
            "priva123",
            "makarov456",
            "gavrilova789",
            "fomin123"
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RoomDB db = App.getInstance().getDatabase();
        UsersDao usersDao = db.usersDao();
        /*UsersEntity usersEntity = new UsersEntity();

        for (int i = 0; i < 5; i++) {
            usersEntity.login = logins.get(i);
            usersEntity.password = passwords.get(i);
            usersDao.insert(usersEntity);
        }*/

        emailsArray = usersDao.getLogins();
        passwordsArray = usersDao.getPasswords();

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
        if (emailsArray.contains(email.getText().toString()) &&
                passwordsArray.contains(password.getText().toString())) {
            Intent intent = new Intent(this, BottomNavActivity.class);
            startActivity(intent);
        }
        else {
            email.setTextColor(Color.parseColor("#FF0000"));
            password.setTextColor(Color.parseColor("#FF0000"));
        }
    }
}