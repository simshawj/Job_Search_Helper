package com.jamessimshaw.jobsearchhelper.interfaces;

/**
 * Created by james on 12/4/15.
 */
public interface RecyclerViewMovementInterface {
    boolean move(int start, int finish);
    void swipeLeft(int position);
    void swipeRight(int position);
}
