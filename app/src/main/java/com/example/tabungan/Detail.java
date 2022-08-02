package com.example.tabungan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.tabungan.javaclass.CustomCursorAdapter;
import com.example.tabungan.javaclass.DatabaseHelper;
import com.example.tabungan.javaclass.UangModel;

import java.util.ArrayList;

public class Detail extends AppCompatActivity{
    DatabaseHelper databaseHelper;
    private ArrayList<UangModel> cashModelArrayList;
    private CustomCursorAdapter customCursor;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        cashModelArrayList = new ArrayList<>();
        databaseHelper = new DatabaseHelper(Detail.this);

        cashModelArrayList  = databaseHelper.readCash();

        customCursor = new CustomCursorAdapter(cashModelArrayList, Detail.this);
        recyclerView = findViewById(R.id.idRVUang);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Detail.this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(customCursor);
    }
    public void handleBack(View view) {
        Intent intent = new Intent(this, Beranda.class);
        startActivity(intent);
    }
}