package com.example.sqlite_dolgozat_gyakorlo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "palinka.db";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "palinka";
    private static final String COL_ID = "id";
    private static final String COL_FOZO = "fozo";
    private static final String COL_GYUMOLCS = "gyumolcs";
    private static final String COL_ALKOHOL = "alkohol";

    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE " + TABLE_NAME + " (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_FOZO + " TEXT NOT NULL, " + COL_GYUMOLCS + " TEXT NOT NULL, " + COL_ALKOHOL + " INTEGER NOT NULL" + ");";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(
                "DROP TABLE IF EXISTS " + TABLE_NAME
        );
        onCreate(sqLiteDatabase);
    }

    public boolean rogzites(String fozo, String gyumolcs, int alkohol) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_FOZO, fozo);
        values.put(COL_GYUMOLCS, gyumolcs);
        values.put(COL_ALKOHOL, alkohol);

        long result = database.insert(TABLE_NAME, null, values);

        return result != -1;
    }

    public Cursor adatLekerdezes() {
        SQLiteDatabase database = this.getWritableDatabase();
        return  database.query(TABLE_NAME, new String[] {COL_ID, COL_FOZO, COL_GYUMOLCS, COL_ALKOHOL}, null, null, null, null, null);
    }

    public boolean torles(String id) {
        SQLiteDatabase database = this.getWritableDatabase();
        int result = database.delete(TABLE_NAME, COL_ID + " = ?", new String[] {id});

        return result > 0;
    }
}
