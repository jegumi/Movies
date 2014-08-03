package com.jegumi.movies.ui;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.Cache;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.jegumi.movies.R;
import com.jegumi.movies.adapters.ReleasesArrayAdapter;
import com.jegumi.movies.events.BusProvider;
import com.jegumi.movies.events.OpenMovieEvent;
import com.jegumi.movies.model.Movie;
import com.jegumi.movies.model.Movies;
import com.jegumi.movies.network.GsonRequest;
import com.jegumi.movies.network.Utils;
import com.jegumi.movies.network.VolleySingleton;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class ListMoviesFragment extends ListFragment {

    private static final String TAG = ListMoviesFragment.class.getName();
    private static final String API_KEY = "ddgemh3njzwh5n3a2u5874yg";
    private static final String BASE_URL = "http://api.rottentomatoes.com/api/public/v1.0/lists/movies/box_office.json";

    public static ListMoviesFragment newInstance() {
        return new ListMoviesFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.movies_list, null);
        return root;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        downloadReleases();
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Movie movie = (Movie) l.getItemAtPosition(position);
        BusProvider.getInstance().post(new OpenMovieEvent(movie));
    }

    public void downloadReleases()  {
        RequestQueue queue = VolleySingleton.getInstance(getActivity()).getRequestQueue();

        String uri = getMoviesUrl();
        GsonRequest<Movies> jsObjRequest = new GsonRequest<Movies>(
                Request.Method.GET,
                uri,
                Movies.class, null,
                new Response.Listener<Movies>() {
                    @Override
                    public void onResponse(Movies movies) {
                        setListAdapter(new ReleasesArrayAdapter(getActivity(), Arrays.asList(movies.getMovies())));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Log.e(TAG, "Error loading movies", volleyError);
                    }
                }
        );

        jsObjRequest.setTag(TAG);
        if (Utils.isNetworkOnline(getActivity())) {
            queue.add(jsObjRequest);
        } else {
            Cache cache = queue.getCache();
            Cache.Entry entry = cache.get(uri);
            if (entry != null) {
                try {
                    Movies movies = new Gson().fromJson(new String(entry.data, "UTF-8"), Movies.class);
                    setListAdapter(new ReleasesArrayAdapter(getActivity(), Arrays.asList(movies.getMovies())));
                } catch (UnsupportedEncodingException e) {
                    Log.e(TAG, "Error loading cache", e);
                }
            }
        }
    }

    private String getMoviesUrl() {
        return BASE_URL + "?apikey=" + API_KEY;
    }
}
