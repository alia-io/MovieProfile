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
        LayoutInflater inflater = context.getLayoutInflater();
        View layout = inflater.inflate(R.layout.movie_list_item, null, true);
        ImageView image = layout.findViewById(R.id.list_movie_poster);
        TextView title = layout.findViewById(R.id.list_movie_title);
        image.setImageResource(imageIds[position]);
        title.setText(titles[position]);
        return layout;
    }
}
