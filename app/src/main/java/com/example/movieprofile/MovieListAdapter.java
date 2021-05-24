package com.example.movieprofile;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/* Sets the MovieListItems in the ListView (Task #3 - Master view) */
public class MovieListAdapter extends ArrayAdapter<String> {

    private final String[] titles;
    private final Integer[] imageIds;

    public MovieListAdapter(Activity context, String[] titles, Integer[] imageIds) {
        super(context, R.layout.movie_list_item, titles);
        this.titles = titles;
        this.imageIds = imageIds;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        // Implementation of ViewHolder pattern
        MovieListViewHolder movieListViewHolder;

        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            view = inflater.inflate(R.layout.movie_list_item, parent, false);
            movieListViewHolder = new MovieListViewHolder(
                    view.findViewById(R.id.list_movie_poster), view.findViewById(R.id.list_movie_title));
            view.setTag(movieListViewHolder);
        } else {
            movieListViewHolder = (MovieListViewHolder) view.getTag();
        }

        movieListViewHolder.getPoster().setImageResource(imageIds[position]);
        movieListViewHolder.getTitle().setText(titles[position]);
        return view;
    }

    public static class MovieListViewHolder {
        private final ImageView poster;
        private final TextView title;
        public MovieListViewHolder(ImageView poster, TextView title) {
            this.poster = poster;
            this.title = title;
        }
        public ImageView getPoster() { return poster; }
        public TextView getTitle() { return title; }
    }
}
