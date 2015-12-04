package com.jamessimshaw.jobsearchhelper.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jamessimshaw.jobsearchhelper.R;
import com.jamessimshaw.jobsearchhelper.models.Posting;

import java.util.ArrayList;

/**
 * Created by james on 12/4/15.
 */
public class PostingRecViewAdapter extends RecyclerView.Adapter<PostingRecViewAdapter.ViewHolder> {

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
