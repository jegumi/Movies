package com.jegumi.movies.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.jegumi.movies.R;
import com.jegumi.movies.model.Movie;
import com.jegumi.movies.network.ImageCacheManager;

import java.util.List;

public class ReleasesArrayAdapter extends ArrayAdapter<Movie> {

    public static class ViewHolder {
        private TextView titleTextView;
        private TextView synopsisTextView;
        private TextView ratingTextView;
        private NetworkImageView thumbImageView;
    }

    private Context context;
    private ImageLoader imageLoader;

    public ReleasesArrayAdapter(Context context, List<Movie> objects) {
        super(context, R.layout.movies_item, objects);
        this.context = context;
        this.imageLoader = ImageCacheManager.getInstance().getImageLoader();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Movie movie = getItem(position);
        if (convertView == null) {
            convertView = inflateView();
        }
        final ViewHolder holder = (ViewHolder) convertView.getTag();

        String title = context.getString(R.string.title_year, movie.getTitle(), movie.getYear());
        String rating = String.valueOf(movie.getRatings().getAudience_score());
        String synopsis = movie.getSynopsis();

        holder.titleTextView.setText(title);
        holder.ratingTextView.setText(rating);
        holder.synopsisTextView.setText(synopsis);
        holder.thumbImageView.setImageUrl(movie.getPosters().getOriginal(), imageLoader);

        return convertView;
    }

    private View inflateView() {
        final LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = vi.inflate(R.layout.movies_item, null);

        ViewHolder holder = new ViewHolder();
        holder.titleTextView = (TextView) view.findViewById(R.id.title_text_view);
        holder.ratingTextView = (TextView) view.findViewById(R.id.rating_text_view);
        holder.synopsisTextView = (TextView) view.findViewById(R.id.sinopsis_text_view);
        holder.thumbImageView = (NetworkImageView) view.findViewById(R.id.thumb_image_view);
        view.setTag(holder);

        return view;
    }
}

