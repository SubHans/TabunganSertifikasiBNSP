package com.example.tabungan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.tabungan.Beranda;
import com.example.tabungan.R;
import com.example.tabungan.javaclass.DatabaseHelper;
import com.example.tabungan.javaclass.DatePicker;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class UangMasuk extends AppCompatActivity{

    private static final String MY_PREFS_NAME = "MyPreferences";
    TextInputLayout dateInput,nominal,keterangan;
    SimpleDateFormat dateFormat;
    Button simpan;

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            this.getSupportActionBar().hide();
        } catch (NullPointerException e) {
            System.out.println(e);
        }
        setContentView(R.layout.activity_uang_masuk);
        databaseHelper = new DatabaseHelper(this);
        dateInput = findViewById(R.id.tanggal_masuk);
        nominal = findViewById(R.id.nominal_masuk);
        keterangan = findViewById(R.id.keterangan_masuk);
        simpan = findViewById(R.id.buttonSimpan);

        dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.forLanguageTag("INDONESIA"));

        dateInput.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePicker.showDateDialog(UangMasuk.this, dateInput, dateFormat);
            }
        });

        simpan.setOnClickListener(new View.OnClickListener() {
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
        int status = 1;
        String tanggal = dateInput.getEditText().getText().toString();
        String nominalIn = nominal.getEditText().getText().toString();
        String keteranganIn = keterangan.getEditText().getText().toString();

        ContentValues values =  new ContentValues();
        values.put(DatabaseHelper.KEY_USER, user);
        values.put(DatabaseHelper.KEY_STATUS, status);
        values.put(DatabaseHelper.KEY_TANGGAL, tanggal);
        values.put(DatabaseHelper.KEY_NOMINAL, nominalIn);
        values.put(DatabaseHelper.KEY_KETERANGAN, keteranganIn);

        if (tanggal.equals("") || nominalIn.equals("") || keteranganIn.equals("")) {
            Toast.makeText(UangMasuk.this, "Harap isi semua data!", Toast.LENGTH_SHORT).show();
        } else {
            databaseHelper.insertData(values);
            Toast.makeText(UangMasuk.this, "Data berhasil disimpan!", Toast.LENGTH_SHORT).show();
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