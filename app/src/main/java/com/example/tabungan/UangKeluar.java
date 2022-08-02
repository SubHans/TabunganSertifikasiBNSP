package com.example.tabungan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.tabungan.javaclass.DatabaseHelper;
import com.example.tabungan.javaclass.DatePicker;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class UangKeluar extends AppCompatActivity{
    private static final String MY_PREFS_NAME = "MyPreferences";
    TextInputLayout dateOut, nominalOut, keteranganOut;
    SimpleDateFormat dateFormat;
    Button buttonSimpan;

    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uang_keluar);
        try {
            this.getSupportActionBar().hide();
        } catch (NullPointerException e) {
            System.out.println(e);
        }
        databaseHelper = new DatabaseHelper(this);
        dateOut = findViewById(R.id.tanggal_keluar);
        nominalOut = findViewById(R.id.nominal_keluar);
        keteranganOut = findViewById(R.id.keterangan_keluar);
        buttonSimpan = findViewById(R.id.buttonSimpan);
        dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.forLanguageTag("INDONESIA"));

        dateOut.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePicker.showDateDialog(UangKeluar.this, dateOut, dateFormat);
            }
        });

        buttonSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveSQLite();
            }
        });
    }

    private void saveSQLite() {
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String prefUsername = prefs.getString("username", "No name defined");

        String user = prefUsername;
        int status = 0;
        String tanggalOut = dateOut.getEditText().getText().toString();
        String nominal = nominalOut.getEditText().getText().toString();
        String keterangan = keteranganOut.getEditText().getText().toString();

        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.KEY_USER, user);
        values.put(DatabaseHelper.KEY_STATUS, status);
        values.put(DatabaseHelper.KEY_TANGGAL, tanggalOut);
        values.put(DatabaseHelper.KEY_NOMINAL, nominal);
        values.put(DatabaseHelper.KEY_KETERANGAN, keterangan);

        if (tanggalOut.equals(" ")||nominal.equals(" ")||keterangan.equals(" ")) {
            Toast.makeText(UangKeluar.this, "Harap isi semua data!", Toast.LENGTH_SHORT).show();
        }else  {
            databaseHelper.insertData(values);
            Toast.makeText(UangKeluar.this, "Berhasil disimpan!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, Beranda.class);
            startActivity(intent);
            finish();
        }
    }


    public void handleBack(View view) {
        Intent intent = new Intent(this, Beranda.class);
        startActivity(intent);
    }
}