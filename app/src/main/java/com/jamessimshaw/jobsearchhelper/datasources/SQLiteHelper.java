package com.jamessimshaw.jobsearchhelper.datasources;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class SQLiteHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "job_search_helper.db";
    private static final int DB_VERSION = 1;

    public static final String TABLE_POSTINGS = "postings";
    public static final String COLUMN_COMPANY = "company";
    public static final String COLUMN_POSITION = "position";
    public static final String COLUMN_URL = "url";
    public static final String COLUMN_ADDED = "added";
    public static final String COLUMN_COMPLETE = "completed";

    private static final String POSTING_CREATE = "create table " +
            TABLE_POSTINGS + " (" + BaseColumns._ID +
            " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_COMPANY +
            " TEXT NOT NULL, " + COLUMN_POSITION +
            " TEXT NOT NULL, " + COLUMN_URL +
            " TEXT NOT NULL, " + COLUMN_ADDED +
            " INTEGER NOT NULL, " + COLUMN_COMPLETE +
            " INTEGER)";

    public SQLiteHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(POSTING_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }
}
