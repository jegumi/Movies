package com.jegumi.movies.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;

import com.jegumi.movies.R;
import com.jegumi.movies.events.BusProvider;
import com.jegumi.movies.events.OpenMovieEvent;
import com.squareup.otto.Subscribe;

public class ListMoviesActivity extends FragmentActivity {
    private static final String TAG = ListMoviesActivity.class.getName();
    public static final String EXTRA_MOVIE = "com.jegumi.movies.movie";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (isTablet()) {
            setContentView(R.layout.main_layout);
        }
        loadFragment();
    }

    private void loadFragment() {
        int layoutDestination = isTablet() ? R.id.left_layout : android.R.id.content;

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ListMoviesFragment startFragment = ListMoviesFragment.newInstance();
        ft.replace(layoutDestination, startFragment, TAG);
        ft.commit();
    }

    @Override
    protected void onPause() {
        super.onPause();
        BusProvider.getInstance().unregister(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        BusProvider.getInstance().register(this);
    }

    // Public for testing
    public boolean isTablet() {
        return getResources().getBoolean(R.bool.is_tablet);
    }

    @Subscribe
    public void openMovieDetails(OpenMovieEvent event) {
        if (isTablet()) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            MovieFragment startFragment = MovieFragment.newInstance(event.movie);
            ft.replace(R.id.right_layout, startFragment, TAG);
            ft.commit();
        } else {
            Intent intent = new Intent(this, MovieActivity.class);
            intent.putExtra(EXTRA_MOVIE, event.movie);
            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, PreferenceWithHeaders.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}