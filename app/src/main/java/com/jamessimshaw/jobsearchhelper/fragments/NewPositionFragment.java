package com.jamessimshaw.jobsearchhelper.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.jamessimshaw.jobsearchhelper.R;
import com.jamessimshaw.jobsearchhelper.models.Posting;

public class NewPositionFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    EditText mCompanyEditText;
    EditText mTitleEditText;
    EditText mURLEditText;
    Button mSubmitButton;

    public NewPositionFragment() {
        // Required empty public constructor
    }

    public static NewPositionFragment newInstance() {
        NewPositionFragment fragment = new NewPositionFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_position, container, false);

        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("New Posting");

        mCompanyEditText = (EditText) view.findViewById(R.id.company_editText);
        mTitleEditText = (EditText) view.findViewById(R.id.title_editText);
        mURLEditText = (EditText) view.findViewById(R.id.url_editText);
        mSubmitButton = (Button) view.findViewById(R.id.submit_button);
        mSubmitButton.setOnClickListener(mClickListener);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String company = mCompanyEditText.getText().toString();
            String title = mTitleEditText.getText().toString();
            Uri url = Uri.parse(mURLEditText.getText().toString());
            Posting posting = new Posting(company, title, url);

            mListener.onFragmentInteraction(posting);
        }
    };

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
        void onFragmentInteraction(Posting posting);
    }
}
