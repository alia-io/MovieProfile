package com.example.movieprofile;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MovieListAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] titles;
    private final Integer[] imageIds;

    public MovieListAdapter(Activity context, String[] titles, Integer[] imageIds) {
        super(context, R.layout.movie_list_item, titles);
        this.context = context;
        this.titles = titles;
        this.imageIds = imageIds;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {

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

        /*LayoutInflater inflater = context.getLayoutInflater();
        View layout = inflater.inflate(R.layout.movie_list_item, parent, false);
        ImageView poster = layout.findViewById(R.id.list_movie_poster);
        TextView title = layout.findViewById(R.id.list_movie_title);
        poster.setImageResource(imageIds[position]);
        title.setText(titles[position]);
        return layout;*/
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
