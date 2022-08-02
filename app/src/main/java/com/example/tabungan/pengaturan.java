package com.example.tabungan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class pengaturan extends AppCompatActivity {
    private static final String MY_PREFS_NAME = "MyPreferences";

    TextInputLayout oldPassword, newPassword;
    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengaturan);
        setTitle("Pengaturan");
        newPassword = findViewById(R.id.newPassword);
        oldPassword = findViewById(R.id.oldPassword);
        save = findViewById(R.id.buttonSimpan);

        SharedPreferences preferences = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        final String password = preferences.getString("password", "user");
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (oldPassword.getEditText().getText().toString().equals(password)) {
                    SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                    editor.putString("username", "user");
                    editor.putString("password", newPassword.getEditText().getText().toString());
                    editor.apply();
                    Toast.makeText(getBaseContext(), "Password berhasil diubah!", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(getBaseContext(), "Password tidak sama!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void handleBack(View view) {
        Intent intent = new Intent(this, Beranda.class);
        startActivity(intent);
    }
}