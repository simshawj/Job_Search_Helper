package com.jamessimshaw.jobsearchhelper.models;

import android.net.Uri;

import java.util.Date;

/**
 * Created by james on 12/3/15.
 */
public class Posting {
    private String mCompany;
    private String mTitle;
    private Date mAdded;
    private Date mCompleted;
    private Uri mUrl;

    public Posting(String company, String title, Uri url) {
        mCompany = company;
        mTitle = title;
        mUrl = url;
        mAdded = new Date();
    }

    public Posting(String company, String title, Uri url, Date added) {
        mCompany = company;
        mTitle = title;
        mUrl = url;
        mAdded = added;
    }

    public Posting(String company, String title, Uri url, Date added, Date completed) {
        mCompany = company;
        mTitle = title;
        mUrl = url;
        mAdded = added;
        mCompleted = completed;
    }

    public String getCompany() {
        return mCompany;
    }

    public String getTitle() {
        return mTitle;
    }

    public Date getAdded() {
        return mAdded;
    }

    public Date getCompleted() {
        return mCompleted;
    }

    public void setCompleted() {
        mCompleted = new Date();
    }

    public Uri getUrl() {
        return mUrl;
    }
}
