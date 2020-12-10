package com.example.mollysqlitev2.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.mollysqlitev2.Database.Models.Shopping;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";

    private static  final String DB_NAME = "shopping.db";
    private static  final String TABLE_NAME = "shopping";



    public DatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(Shopping.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    public long insertNote(String note) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put(Shopping.COLUMN_NOTE, note);

        // insert row
        long id = db.insert(Shopping.TABLE_NAME, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }

    public Shopping getNote(long id) {
        // get readable database as we are not inserting anything
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(Shopping.TABLE_NAME,
                new String[]{Shopping.COLUMN_ID, Shopping.COLUMN_NOTE},
                Shopping.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

//        Log.d(TAG, "cursor.getCount()= " + String.valueOf(cursor.getCount()));
//        if(cursor.getCount() > 0) {
//            cursor.moveToFirst();
//        }

        // prepare note object
        Shopping note = new Shopping(
                cursor.getInt(cursor.getColumnIndex(Shopping.COLUMN_ID)),
                cursor.getString(cursor.getColumnIndex(Shopping.COLUMN_NOTE))
        );

        // close the db connection
        cursor.close();

        return note;
    }

    //code from first video that adds to database
    public boolean addData(String item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Shopping.COLUMN_NOTE, item);

        Log.d(TAG, "addData: Adding " + item + " to " + TABLE_NAME);

        long result = db.insert(TABLE_NAME, null, contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

//    public Cursor getData() {
//        SQLiteDatabase db = this.getReadableDatabase();
//        String query = "SELECT * FROM " + TABLE_NAME;
//        Cursor data = db.rawQuery(query, null);
//        return data;
//    }

    public List<Shopping> getAllNotes() {
        List<Shopping> notes = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + Shopping.TABLE_NAME ;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Shopping note = new Shopping();
                note.setId(cursor.getInt(cursor.getColumnIndex(Shopping.COLUMN_ID)));
                note.setNote(cursor.getString(cursor.getColumnIndex(Shopping.COLUMN_NOTE)));


                notes.add(note);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return notes;
    }
    public int getNotesCount() {
        String countQuery = "SELECT  * FROM " + Shopping.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();


        // return count
        return count;
    }

    public int updateNote(Shopping note) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Shopping.COLUMN_NOTE, note.getNote());

        // updating row
        return db.update(Shopping.TABLE_NAME, values, Shopping.COLUMN_ID + " = ?",
                new String[]{String.valueOf(note.getId())});
    }

    public void deleteNote(Shopping note) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Shopping.TABLE_NAME, Shopping.COLUMN_ID + " = ?",
                new String[]{String.valueOf(note.getId())});
        db.close();
    }

}
