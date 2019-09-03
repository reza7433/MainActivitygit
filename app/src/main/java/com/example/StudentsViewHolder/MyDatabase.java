package com.example.StudentsViewHolder;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDatabase extends SQLiteOpenHelper {
    private static final String DB_NAME = "CLICKSITE";
    private static String TBL_NAME = "user";
    private static int DB_version = 1;
    private static final String COL_ID = "id";
    private static final String COL_NAME = "name";
    private static final String COL_FAMILY = "family";
    private static final String COL_FIELD = "field";

    private static final String QUERY = " CREATE TABLE IF NOT EXISTS " + TBL_NAME + " ( " +
            COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
            COL_NAME + " TEXT ," +
            COL_FAMILY + " TEXT ," +
            COL_FIELD + " TEXT );";


    public MyDatabase(Context context) {
        super(context, DB_NAME, null, DB_version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {


        } catch (SQLException e) {
            e.printStackTrace();
        }
        db.execSQL(QUERY);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long addData(String name, String family, String field) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_NAME, name);
        contentValues.put(COL_FAMILY, family);
        contentValues.put(COL_FIELD, field);
        long id = sqLiteDatabase.insert(TBL_NAME, null, contentValues);
        return id;
    }

    public Cursor getData() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TBL_NAME, null);
        return cursor;
    }


    public Cursor getSpecialData(long id) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TBL_NAME + " WHERE " + COL_ID + " = ?", new String[]{String.valueOf(id)});
        return cursor;
    }


    public void updateRow(long id, String name) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_NAME, name);
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.update(TBL_NAME, contentValues, COL_FAMILY + " = ?", new String[]{String.valueOf(id)});
    }


    public void delet(long id) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(TBL_NAME, COL_ID + " = ?", new String[]{String.valueOf(id)});

    }


}
