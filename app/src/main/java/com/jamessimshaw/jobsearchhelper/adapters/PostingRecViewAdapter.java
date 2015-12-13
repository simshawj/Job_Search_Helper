package com.jamessimshaw.jobsearchhelper.adapters;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jamessimshaw.jobsearchhelper.R;
import com.jamessimshaw.jobsearchhelper.datasources.SQLiteDataSource;
import com.jamessimshaw.jobsearchhelper.datasources.SQLiteHelper;
import com.jamessimshaw.jobsearchhelper.interfaces.RecyclerViewMovementInterface;
import com.jamessimshaw.jobsearchhelper.models.Posting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

/**
 * Created by james on 12/4/15.
 */
public class PostingRecViewAdapter extends RecyclerView.Adapter<PostingRecViewAdapter.ViewHolder>
        implements RecyclerViewMovementInterface{

    Cursor mCursor;
    SQLiteDataSource mDataSource;

    public PostingRecViewAdapter(Cursor cursor, Context context) {
        mDataSource = new SQLiteDataSource(context);
        mCursor = cursor;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.position_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        mCursor.moveToPosition(position);
        String dateString = "Added: " + new Date(getLongFromColumnName(mCursor, SQLiteHelper.COLUMN_ADDED)).toString();
        holder.positionDateAddedTextView.setText(dateString);
        holder.positionCompanyTextView.setText(getStringFromColumnName(mCursor, SQLiteHelper.COLUMN_COMPANY));
        holder.positionTitleTextView.setText(getStringFromColumnName(mCursor, SQLiteHelper.COLUMN_POSITION));
        holder.positionUrlTextView.setText(getStringFromColumnName(mCursor, SQLiteHelper.COLUMN_URL));
    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }

    @Override
    public boolean move(int start, int finish) {
        //TODO: Possibly optimize this section
        if (start < finish) {
            for (int i = start; i < finish; i++) {
                mDataSource.swap(i, i + 1);
            }
        } else {
            for (int i = start; i > finish; i--) {
                mDataSource.swap(i, i - 1);
            }
        }
        mCursor = mDataSource.read();
        notifyItemMoved(start, finish);
        return true;
    }

    @Override
    public void swipeLeft(int position) {
        mDataSource.delete(position);
        mCursor = mDataSource.read();
        notifyItemRemoved(position);
    }

    @Override
    public void swipeRight(int position) {
        //TODO: Add in completion features
        mDataSource.delete(position);
        mCursor = mDataSource.read();
        notifyItemRemoved(position);
    }

    private long getLongFromColumnName(Cursor cursor, String columnName) {
        return cursor.getLong(cursor.getColumnIndex(columnName));
    }

    private String getStringFromColumnName(Cursor cursor, String columnName) {
        return cursor.getString(cursor.getColumnIndex(columnName));
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView positionTitleTextView;
        TextView positionCompanyTextView;
        TextView positionUrlTextView;
        TextView positionDateAddedTextView;

        public ViewHolder(View itemView) {
            super(itemView);

            positionCompanyTextView = (TextView) itemView.findViewById(R.id.posting_company);
            positionTitleTextView = (TextView) itemView.findViewById(R.id.posting_title);
            positionUrlTextView = (TextView) itemView.findViewById(R.id.posting_url);
            positionDateAddedTextView = (TextView) itemView.findViewById(R.id.posting_added_date);
        }
    }
}
