package com.jegumi.movies.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.jegumi.movies.R;
import com.jegumi.movies.model.Movie;
import com.jegumi.movies.network.ImageCacheManager;

public class MovieFragment extends Fragment {

    private static final String TAG = MovieFragment.class.getName();

    private TextView titleTextView;
    private TextView ratingTextView;
    private TextView synopsisTextView;
    private NetworkImageView thumbImageView;

    public static MovieFragment newInstance(Movie movie) {
        MovieFragment movieFragment = new MovieFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(ListMoviesActivity.EXTRA_MOVIE, movie);
        movieFragment.setArguments(bundle);
        return movieFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.movie_detail, null);
        return root;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        titleTextView = (TextView) view.findViewById(R.id.title_text_view);
        ratingTextView = (TextView) view.findViewById(R.id.rating_text_view);
        synopsisTextView = (TextView) view.findViewById(R.id.sinopsis_text_view);
        thumbImageView = (NetworkImageView) view.findViewById(R.id.thumb_image_view);

        Bundle arguments = getArguments();
        if (arguments != null) {
            Movie movie = (Movie) arguments.getSerializable(ListMoviesActivity.EXTRA_MOVIE);
            initFields(movie);
        }

    }

    private void initFields(Movie movie) {
        titleTextView.setText(getString(R.string.title_year, movie.getTitle(), movie.getYear()));
        ratingTextView.setText(getString(R.string.ratings, movie.getRatings().getAudience_score(), movie.getRatings().getCritics_score()));
        synopsisTextView.setText(movie.getSynopsis());
        thumbImageView.setImageUrl(movie.getPosters().getOriginal(), ImageCacheManager.getInstance().getImageLoader());
    }
}
