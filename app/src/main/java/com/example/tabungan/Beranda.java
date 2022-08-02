package com.example.tabungan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tabungan.javaclass.CustomCursorAdapter;
import com.example.tabungan.javaclass.DatabaseHelper;
import com.example.tabungan.javaclass.UangModel;

import java.util.ArrayList;

public class Beranda extends AppCompatActivity implements View.OnClickListener {

    ImageView pengaturan, pemasukan, pengeluaran, detail;
    DatabaseHelper databaseHelper;
    TextView uangIn, uangOut;
    private ArrayList<UangModel> cashModelArrayList;
    private CustomCursorAdapter customCursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beranda);
        try {
            this.getSupportActionBar().hide();
        } catch (NullPointerException e) {
            System.out.println(e);
        }

        uangIn = findViewById(R.id.uangMasuk);
        uangOut = findViewById(R.id.uangkeluar);
        pengaturan = findViewById(R.id.pengaturan);
        pemasukan = findViewById(R.id.pemasukan);
        pengeluaran = findViewById(R.id.pengeluaran);
        detail = findViewById(R.id.detail);
        databaseHelper = new DatabaseHelper(this);

        pengaturan.setOnClickListener(this);
        pemasukan.setOnClickListener(this);
        pengeluaran.setOnClickListener(this);
        detail.setOnClickListener(this);

        Integer totalPemasukan = databaseHelper.total("1");
        Integer totalPengeluaran = databaseHelper.total("0");

        uangIn.setText("Rp. "+String.valueOf(totalPemasukan));
        uangOut.setText("Rp. "+String.valueOf(totalPengeluaran));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.pemasukan:
                startActivity(new Intent(Beranda.this, UangMasuk.class));
                break;
            case R.id.pengeluaran:
                startActivity(new Intent(Beranda.this, UangKeluar.class));
                break;
            case R.id.detail:
                startActivity(new Intent(Beranda.this, Detail.class));
                break;
            case R.id.pengaturan:
                startActivity(new Intent(Beranda.this, pengaturan.class));
                break;
        }
    }
}