package com.example.easynotes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.logging.Logger;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String LOG_TAG = DatabaseHelper.class.getSimpleName();

    // Table Name
    public static final String TABLE_NAME = "NOTES";

    // Table columns
    public static final String _ID = "_id";
    public static final String NOTE_NAME = "noteName";
    public static final String NOTE_CONTENT = "noteContend";

    // Database Information
    static final String DB_NAME = "NOTES.db";

    // database version
    static final int DB_VERSION = 1;

    // Creating table query
    private static final String CREATE_TABLE = "create table " + TABLE_NAME + "(" + _ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NOTE_NAME + " TEXT NOT NULL, " + NOTE_CONTENT + " TEXT);";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    int addNote(String name, String content){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(NOTE_NAME, name);
        cv.put(NOTE_CONTENT, content);
        long result = db.insert(TABLE_NAME,null, cv);
        if(result == -1){
            Log.i(LOG_TAG, "Failed to add note");
        }else {
            Log.i(LOG_TAG, "Note added succesfully!" + name + " " + result);
        }
        return (int) result;
    }

    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    void updateData(String row_id, String name, String content){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(NOTE_NAME, name);
        cv.put(NOTE_CONTENT, content);
        Log.i(LOG_TAG, row_id+"");
        long result = db.update(TABLE_NAME, cv, "_id=?", new String[]{row_id});
        if(result != 1){
            Log.i(LOG_TAG, "Failed to update note, passwd id was "+ row_id);
        }else {
            Log.i(LOG_TAG, "Note updated succesfully! "+ result);
        }

    }

    void deleteOneRow(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "_id=?", new String[]{row_id});
        if(result == 0){
            Log.i(LOG_TAG, "Failed to delete note, id was "+ row_id);
        }else {
            Log.i(LOG_TAG, "Note deleted succesfully!: "+row_id);
        }
    }

    void deleteAllData(){
        Log.i(LOG_TAG, "Deleting all data");
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
        Log.i(LOG_TAG, "Deleting all data finished");

    }
}