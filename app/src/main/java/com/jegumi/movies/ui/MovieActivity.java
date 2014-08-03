package com.jegumi.movies.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;

import com.jegumi.movies.R;
import com.jegumi.movies.model.Movie;

public class MovieActivity extends FragmentActivity {
    private static final String TAG = MovieActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        Movie movie = (Movie) getIntent().getSerializableExtra(ListMoviesActivity.EXTRA_MOVIE);
        loadFragment(movie);
    }

    private void loadFragment(Movie movie) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        MovieFragment startFragment = MovieFragment.newInstance(movie);
        ft.replace(android.R.id.content, startFragment, TAG);
        ft.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.action_settings:
                Intent intent = new Intent(this, PreferenceWithHeaders.class);
                startActivity(intent);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}