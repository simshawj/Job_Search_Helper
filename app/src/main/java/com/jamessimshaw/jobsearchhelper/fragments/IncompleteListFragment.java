package com.jamessimshaw.jobsearchhelper.fragments;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jamessimshaw.jobsearchhelper.R;
import com.jamessimshaw.jobsearchhelper.adapters.PostingRecViewAdapter;
import com.jamessimshaw.jobsearchhelper.datasources.SQLiteDataSource;
import com.jamessimshaw.jobsearchhelper.helpers.SimpleItemTouchHelper;
import com.jamessimshaw.jobsearchhelper.models.Posting;

import java.util.ArrayList;

/**
 * Created by james on 12/3/15.
 */
public class IncompleteListFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private SQLiteDataSource mDataSource;
    PostingRecViewAdapter mAdapter;

    public static IncompleteListFragment newInstance() {
        IncompleteListFragment fragment = new IncompleteListFragment();
        return fragment;
    }

    public IncompleteListFragment() {
        // Required constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lists_with_fab, container, false);

        FloatingActionButton floatingActionButton = (FloatingActionButton) view.findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null)
                    mListener.onFragmentInteraction();
            }
        });

        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Pending");

        RecyclerView postingListRecView = (RecyclerView) view.findViewById(R.id.list_fragment_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mDataSource = new SQLiteDataSource(getContext());
        Cursor cursor = mDataSource.read();
        mAdapter = new PostingRecViewAdapter(cursor, getContext());
        postingListRecView.setLayoutManager(layoutManager);
        postingListRecView.setItemAnimator(new DefaultItemAnimator());
        postingListRecView.setAdapter(mAdapter);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new SimpleItemTouchHelper(mAdapter));
        itemTouchHelper.attachToRecyclerView(postingListRecView);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (OnFragmentInteractionListener) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void add(Posting posting) {
        int position = mDataSource.create(posting);
        mAdapter.notifyItemInserted(position);
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction();
    }
}
