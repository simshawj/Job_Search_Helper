package com.jamessimshaw.jobsearchhelper.helpers;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.jamessimshaw.jobsearchhelper.interfaces.RecyclerViewMovementInterface;

/**
 * Created by james on 12/4/15.
 */
public class SimpleItemTouchHelper extends ItemTouchHelper.SimpleCallback {

    RecyclerViewMovementInterface mAdapter;

    public SimpleItemTouchHelper(RecyclerViewMovementInterface adapter) {
        super(ItemTouchHelper.UP | ItemTouchHelper.DOWN,
                ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT);
        mAdapter = adapter;
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        int start = viewHolder.getAdapterPosition();
        int finish = target.getAdapterPosition();
        return mAdapter.move(start, finish);
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        int position = viewHolder.getAdapterPosition();
        if (direction == ItemTouchHelper.LEFT)
            mAdapter.swipeLeft(position);
        else
            mAdapter.swipeRight(position);
    }
}
