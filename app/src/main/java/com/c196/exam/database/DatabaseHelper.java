package com.c196.exam.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import com.c196.exam.entities.Term;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "C196.db";

    public DatabaseHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTermsQuery = "CREATE TABLE '%1$s' ( '%2$s' INTEGER PRIMARY KEY, '%3$s' TEXT, '%4$s' VARCHAR, '%5$s' VARCHAR)";
        try{
            createTermsQuery = String.format(createTermsQuery,
                                TermTable.NAME,
                                TermTable._ID,
                                TermTable.TITLE_COLUMN,
                                TermTable.START_COLUMN,
                                TermTable.END_COLUMN);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        db.execSQL(createTermsQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String deleteTermsQuery = "DROP TABLE IF EXISTS " + TermTable.NAME;
        db.execSQL(deleteTermsQuery);
        onCreate(db);
    }

    public ArrayList<Term> getTerms() {
        ArrayList<Term> termList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TermTable.NAME + ";", null);

        if(c.moveToFirst()){
            int index = 0;
            do {

                Term t = new Term(c.getInt(0), c.getString(1), c.getString(2), c.getString(3));
                termList.add(t);
                index++;
            } while(c.move(index));
        }
        c.close();
        return termList;
    }

    public boolean addTerm(Term t) {
        boolean success = false;

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(TermTable.TITLE_COLUMN, t.getTitle());
        cv.put(TermTable.START_COLUMN, t.getStart());
        cv.put(TermTable.END_COLUMN, t.getEnd());

        long result = db.insert(TermTable.NAME, null, cv);

        if(result > 0){
            success = true;
        }
        return success;
    }

    private static class TermTable implements BaseColumns {
        public static final String NAME = "Terms";
        public static final String TITLE_COLUMN = "title";
        public static final String START_COLUMN = "start";
        public static final String END_COLUMN = "end";
    }
}
