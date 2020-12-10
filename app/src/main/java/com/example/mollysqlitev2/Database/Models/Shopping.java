package com.example.mollysqlitev2.Database.Models;

public class Shopping {

    public static final String TABLE_NAME = "shopping";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NOTE = "note";

    private int id;
    private String note;

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_NOTE + " TEXT"
                    + ")";

    public Shopping() {
    }

    public Shopping(int id, String note) {
        this.id = id;
        this.note = note;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

}
