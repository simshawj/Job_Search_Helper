package com.jamessimshaw.jobsearchhelper.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.jamessimshaw.jobsearchhelper.R;
import com.jamessimshaw.jobsearchhelper.fragments.IncompleteListFragment;
import com.jamessimshaw.jobsearchhelper.fragments.NewPositionFragment;
import com.jamessimshaw.jobsearchhelper.models.Posting;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        IncompleteListFragment.OnFragmentInteractionListener,
        NewPositionFragment.OnFragmentInteractionListener {

    private static final String LIST_TAG = "list";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if (savedInstanceState != null)
            return;

        Fragment fragment = IncompleteListFragment.newInstance();
        //Make sure the container for the fragments is present
        if (findViewById(R.id.fragment_container_main) != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container_main, fragment, LIST_TAG)
                    .commit();
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void gotoFragment(Fragment fragment) {
        //Make sure the container for the fragments is present
        if (findViewById(R.id.fragment_container_main) != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container_main, fragment)
                    .addToBackStack(null)
                    .commit();
        }
    }

    @Override
    public void onFragmentInteraction() {
        Fragment fragment = NewPositionFragment.newInstance();

        gotoFragment(fragment);
    }

    @Override
    public void onFragmentInteraction(Posting posting) {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(LIST_TAG);
        if (fragment instanceof IncompleteListFragment) {
            IncompleteListFragment listFragment = (IncompleteListFragment) fragment;
            listFragment.add(posting);
        }

        getSupportFragmentManager().popBackStack();

    }
}
