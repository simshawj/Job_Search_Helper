package com.jamessimshaw.jobsearchhelper.datasources;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.jamessimshaw.jobsearchhelper.models.Posting;

/**
 * Created by james on 12/7/15.
 */
public class SQLiteDataSource {
    private SQLiteHelper mSQLiteHelper;

    public SQLiteDataSource(Context context) {
        mSQLiteHelper = new SQLiteHelper(context);
    }

    private SQLiteDatabase open() {
        return mSQLiteHelper.getWritableDatabase();
    }

    private void close(SQLiteDatabase database) {
        database.close();
    }

    private ContentValues createValues(Posting posting) {
        ContentValues values = new ContentValues();
        values.put(SQLiteHelper.COLUMN_COMPANY, posting.getCompany());
        values.put(SQLiteHelper.COLUMN_POSITION, posting.getTitle());
        values.put(SQLiteHelper.COLUMN_URL, posting.getUrl().toString());
        values.put(SQLiteHelper.COLUMN_ADDED, posting.getAdded().getTime());

        return values;
    }

    private void create(Posting posting) {
        SQLiteDatabase database = open();
        database.beginTransaction();

        ContentValues values = createValues(posting);
        database.insert(SQLiteHelper.TABLE_POSTINGS, null, values);

        database.setTransactionSuccessful();
        database.endTransaction();
        close(database);
    }

    public Cursor read() {
        SQLiteDatabase database = open();

        Cursor cursor = database.query(SQLiteHelper.TABLE_POSTINGS,
                null,
                null,
                null,
                null,
                null,
                null,
                null
        );
        return cursor;
    }
}
