package com.jamessimshaw.jobsearchhelper.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jamessimshaw.jobsearchhelper.R;
import com.jamessimshaw.jobsearchhelper.interfaces.RecyclerViewMovementInterface;
import com.jamessimshaw.jobsearchhelper.models.Posting;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by james on 12/4/15.
 */
public class PostingRecViewAdapter extends RecyclerView.Adapter<PostingRecViewAdapter.ViewHolder>
        implements RecyclerViewMovementInterface{

    ArrayList<Posting> mPostings;

    public PostingRecViewAdapter(ArrayList<Posting> postings) {
        mPostings = postings;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.position_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String dateString = "Added: " + mPostings.get(position).getAdded().toString();
        holder.positionDateAddedTextView.setText(dateString);
        holder.positionCompanyTextView.setText(mPostings.get(position).getCompany());
        holder.positionTitleTextView.setText(mPostings.get(position).getTitle());
        holder.positionUrlTextView.setText(mPostings.get(position).getUrl().toString());
    }

    @Override
    public int getItemCount() {
        return mPostings.size();
    }

    @Override
    public boolean move(int start, int finish) {
        if (start < finish) {
            for (int i = start; i < finish; i++) {
                Collections.swap(mPostings, i, i + 1);
            }
        } else {
            for (int i = start; i > finish; i--) {
                Collections.swap(mPostings, i, i - 1);
            }
        }
        notifyItemMoved(start, finish);
        return true;
    }

    @Override
    public void swipeLeft(int position) {
        mPostings.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public void swipeRight(int position) {
        //TODO: Add in completion features
        mPostings.remove(position);
        notifyItemRemoved(position);
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
