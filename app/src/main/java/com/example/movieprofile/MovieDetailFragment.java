package com.example.movieprofile;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class MovieDetailFragment extends Fragment {

    private MainActivity parent;

    public static MovieDetailFragment newInstance(int id, String title, String year, String length,
            float rating, String director, String stars, String description, String url) {
        MovieDetailFragment movieDetailFragment = new MovieDetailFragment();
        Bundle args = new Bundle();
        args.putInt("id", id);
        args.putString("title", title);
        args.putString("year", year);
        args.putString("length", length);
        args.putFloat("rating", rating);
        args.putString("director", director);
        args.putString("stars", stars);
        args.putString("description", description);
        args.putString("url", url);
        movieDetailFragment.setArguments(args);
        return movieDetailFragment;
    }

    public void setParent(MainActivity parent) { this.parent = parent; }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle args = getArguments();
        final View view = inflater.inflate(R.layout.movie_detail_fragment, container, false);

        ImageView poster = view.findViewById(R.id.iv_poster);
        TextView title = view.findViewById(R.id.tv_title);
        RatingBar rating = view.findViewById(R.id.rb_rating);
        TextView year = view.findViewById(R.id.tv_year);
        TextView director = view.findViewById(R.id.tv_director);
        TextView stars = view.findViewById(R.id.tv_stars);
        TextView runtime = view.findViewById(R.id.tv_runtime);
        //TextView link = view.findViewById(R.id.tv_url);
        TextView description = view.findViewById(R.id.tv_description);

        if (parent != null) {
            view.setVisibility(View.INVISIBLE);
        }

        poster.setImageResource(args.getInt("id"));
        title.setText(args.getString("title"));
        rating.setRating(args.getFloat("rating"));
        year.setText(args.getString("year"));
        director.setText(args.getString("director"));
        stars.setText(args.getString("stars"));
        runtime.setText(args.getString("length"));
        //link.setText(args.getString("url"));
        description.setText(args.getString("description"));

        LayerDrawable layerDrawable = (LayerDrawable) rating.getProgressDrawable();
        layerDrawable.getDrawable(0).setColorFilter(Color.parseColor("#000000"), PorterDuff.Mode.SRC_ATOP);
        layerDrawable.getDrawable(2).setColorFilter(Color.parseColor("#FBFF12"), PorterDuff.Mode.SRC_ATOP);

        // Sets the appropriate size of a movie detail fragment when the app starts up
        view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                if (parent != null) {
                    parent.setMaxFragmentWidth(view.getWidth());
                    parent.setMaxFragmentHeight(view.getHeight());
                    parent.setAboutMeFragment();
                    view.setVisibility(View.VISIBLE);
                    parent = null;
                }
            }
        });
        return view;
    }
}
