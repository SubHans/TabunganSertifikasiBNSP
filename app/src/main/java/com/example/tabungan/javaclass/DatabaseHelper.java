package com.example.tabungan.javaclass;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class DatabaseHelper extends SQLiteOpenHelper {
     public static final int DATABASE_VERSION=1;
     public static final String DATABASE_NAME = "MyCashFlow";
     public static final String TABLE_NAME = "CashFlow";
     public static final String KEY_ID = "id";
     public static final String KEY_USER = "User";
     public static final String KEY_STATUS = "Status";
     public static final String KEY_NOMINAL = "Nominal";
     public static final String KEY_TANGGAL = "Tanggal";
     public static final String KEY_KETERANGAN = "Keterangan";
     private SQLiteDatabase db;

     public DatabaseHelper(Context context) {
         super(context, DATABASE_NAME, null, DATABASE_VERSION);
         db = getWritableDatabase();
     }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE "+TABLE_NAME+"("+KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                        +KEY_USER+" TEXT,"+KEY_STATUS+" TEXT,"+KEY_NOMINAL+" TEXT,"+KEY_KETERANGAN
                        +" TEXT," +KEY_TANGGAL+" TEXT)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
    }

//    Get all data
    public Cursor allData() {
         Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_NAME, null);
         return  cursor;
    }

//    get data by ID
    public Cursor getData(long id) {
         Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_NAME+ " WHERE"+KEY_ID+"="+id, null);
         return cursor;
    }

    public Cursor getDataChart(long id) {
        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_NAME+ " WHERE"+KEY_STATUS+"="+id, null);
        return cursor;
    }

//    Insert
    public void insertData(ContentValues values) {
         db.insert(TABLE_NAME, null, values);
    }

//    update
    public void updateData(ContentValues values, long id) {
         db.update(TABLE_NAME, values, KEY_ID+"="+id,null);
    }

//    delete data
    public void deleteData(long id) {
         db.delete(TABLE_NAME,KEY_ID+"="+id,null);
    }

    public ArrayList<HashMap<String, String>> getDetailFlow() {
         SQLiteDatabase db = this.getWritableDatabase();
         ArrayList<HashMap<String, String>> userList = new ArrayList<>();
         String query = "SELECT * FROM "+TABLE_NAME;
         Cursor cursor = db.rawQuery(query, null);
         while (cursor.moveToNext()) {
             HashMap<String, String> user = new HashMap<>();
             user.put("id", cursor.getString(cursor.getColumnIndexOrThrow(KEY_ID)));
             user.put("User", cursor.getString(cursor.getColumnIndexOrThrow(KEY_USER)));
                 user.put("Status", cursor.getString(cursor.getColumnIndexOrThrow(KEY_STATUS)));
             user.put("Nominal", cursor.getString(cursor.getColumnIndexOrThrow(KEY_NOMINAL)));
             user.put("Keterangan", cursor.getString(cursor.getColumnIndexOrThrow(KEY_KETERANGAN)));
             user.put("Tanggal", cursor.getString(cursor.getColumnIndexOrThrow(KEY_TANGGAL)));
             userList.add(user);
         }
         return userList;
    }

    public ArrayList<UangModel> readCash() {
         SQLiteDatabase db = this.getReadableDatabase();

        Cursor cashCursor = db.rawQuery("SELECT * FROM CashFlow ORDER BY id DESC" ,  null);

         ArrayList<UangModel> cashModelArrayList = new ArrayList<>();

         if (cashCursor.moveToFirst()) {
             do {
                 cashModelArrayList.add(new UangModel(cashCursor.getInt(2),
                         cashCursor.getString(3),
                         cashCursor.getString(4),
                         cashCursor.getString(5)));
             }while (cashCursor.moveToNext());
         }

         cashCursor.close();
         return cashModelArrayList;
    }

    public Integer total(String status) {
         int jumlah = 0;
         SQLiteDatabase MyDB = this.getWritableDatabase();
         Cursor cursor = MyDB.rawQuery(
                 "SELECT SUM(Nominal) FROM CashFlow WHERE Status =\""+ status+"\"", null
         );
         if (cursor.moveToFirst()) {
             jumlah = cursor.getInt(0);
         }
         return jumlah;
    }

    public Integer uang(String status) {
        int jumlah = 0;
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery(
                "SELECT * FROM CashFlow WHERE Status =\""+ status+"\"", null
        );
        if (cursor.moveToFirst()) {
            jumlah = cursor.getInt(0);
        }
        return jumlah;
    }
}
