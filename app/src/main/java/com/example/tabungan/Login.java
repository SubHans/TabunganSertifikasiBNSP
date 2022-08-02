package com.example.tabungan;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class Login extends AppCompatActivity {

    private static final String MY_PREFS_NAME = "MyPreferences";
    TextInputLayout username, password;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            this.getSupportActionBar().hide();
        } catch (NullPointerException e) {
            System.out.println(e);
        }
        setContentView(R.layout.activity_login);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernameLog = username.getEditText().getText().toString();
                String passwordLog = password.getEditText().getText().toString();

                SharedPreferences preferences = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
                String prefsUsername = preferences.getString("username", "user");
                String prefsPassword = preferences.getString("password", "user");

                if (usernameLog.equals(prefsUsername) && passwordLog.equals(prefsPassword)) {
                    startActivity(new Intent(Login.this, Beranda.class));
                } else {
                    Toast.makeText(getBaseContext(), "Username atau password salah!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}