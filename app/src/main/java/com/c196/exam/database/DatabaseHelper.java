package com.c196.exam.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "C196.db";

    public DatabaseHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    public SQLiteDatabase getDb() {
        return this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTermsQuery = "CREATE TABLE '%1$s' ( '%2$s' INTEGER PRIMARY KEY, '%3$s' TEXT, '%4$s' DATETIME, '%5$s' DATETIME)";
        try{
            createTermsQuery = String.format(createTermsQuery,
                                DatabaseContract.TermTable.NAME,
                                DatabaseContract.TermTable._ID, 
                                DatabaseContract.TermTable.TITLE_COLUMN,
                                DatabaseContract.TermTable.START_COLUMN,
                                DatabaseContract.TermTable.END_COLUMN);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        db.execSQL(createTermsQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String deleteTermsQuery = "DROP TABLE IF EXISTS " + DatabaseContract.TermTable.NAME;
        db.execSQL(deleteTermsQuery);
        onCreate(db);
    }
}
