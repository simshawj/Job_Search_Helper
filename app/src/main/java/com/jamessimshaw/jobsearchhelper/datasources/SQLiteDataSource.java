package com.jamessimshaw.jobsearchhelper.datasources;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

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

    public int create(Posting posting) {
        SQLiteDatabase database = open();

        Cursor cursor = database.rawQuery("SELECT MAX(" + SQLiteHelper.COLUMN_PRIORITY + ") AS " +
                                            SQLiteHelper.COLUMN_PRIORITY + " FROM " +
                                            SQLiteHelper.TABLE_POSTINGS, null);
        int priority = 0;
        if (cursor != null) {
            int index = cursor.getColumnIndex(SQLiteHelper.COLUMN_PRIORITY);
            cursor.moveToFirst();
            if (!cursor.isNull(index))
                priority = (int) cursor.getLong(index) + 1;
        }

        database.beginTransaction();

        ContentValues values = new ContentValues();
        values.put(SQLiteHelper.COLUMN_COMPANY, posting.getCompany());
        values.put(SQLiteHelper.COLUMN_POSITION, posting.getTitle());
        values.put(SQLiteHelper.COLUMN_URL, posting.getUrl().toString());
        values.put(SQLiteHelper.COLUMN_ADDED, posting.getAdded().getTime());
        values.put(SQLiteHelper.COLUMN_PRIORITY, priority);
        database.insert(SQLiteHelper.TABLE_POSTINGS, null, values);

        database.setTransactionSuccessful();
        database.endTransaction();
        close(database);
        return priority;
    }

    public Cursor read() {
        SQLiteDatabase database = open();

        Cursor cursor = database.query(SQLiteHelper.TABLE_POSTINGS,
                null,
                null,
                null,
                null,
                null,
                SQLiteHelper.COLUMN_PRIORITY,
                null
        );
        return cursor;
    }

    public void delete(int position) {
        SQLiteDatabase database = open();

        String[] whereArgs = {Integer.toString(position)};
        String query = "UPDATE " + SQLiteHelper.TABLE_POSTINGS + " SET " + SQLiteHelper.COLUMN_PRIORITY +
                " = " + SQLiteHelper.COLUMN_PRIORITY + "-1 WHERE " + SQLiteHelper.COLUMN_PRIORITY +
                " > ?";

        database.delete(SQLiteHelper.TABLE_POSTINGS, SQLiteHelper.COLUMN_PRIORITY + " = ?", whereArgs);
        database.rawQuery(query, whereArgs);

        close(database);
    }
}
