package com.example.movieprofile;

import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class MovieDetailFragment extends Fragment {

    public static MovieDetailFragment newInstance(int id, String title, String year, String length,
            float rating, String director, String stars, String decription, String url) {
        MovieDetailFragment movieDetailFragment = new MovieDetailFragment();
        Bundle args = new Bundle();
        args.putInt("id", id);
        args.putString("title", title);
        args.putString("year", year);
        args.putString("length", length);
        args.putFloat("rating", rating);
        args.putString("director", director);
        args.putString("stars", stars);
        args.putString("description", decription);
        args.putString("url", url);
        movieDetailFragment.setArguments(args);
        return movieDetailFragment;
    }

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

        poster.setImageResource(args.getInt("id"));
        title.setText(args.getString("title"));
        rating.setRating(args.getFloat("rating"));
        year.setText(Html.fromHtml("<b><font size='16' color='#D05353'>Year: </b></font>"
                + "<br><font size='14' color='#FFD6C0'>" + args.getString("year") + "</font>"));
        director.setText(Html.fromHtml("<b><font size='16' color='#D05353'>Director: </b></font>"
                + "<br><font size='14' color='#FFD6C0'>" + args.getString("director") + "</font>"));
        stars.setText(Html.fromHtml("<b><font size='16' color='#D05353'>Stars: </b></font>"
                + "<br><font size='14' color='#FFD6C0'>" + args.getString("stars") + "</font>"));
        runtime.setText(Html.fromHtml("<b><font size='16' color='#D05353'>Runtime: </b></font>"
                + "<br><font size='14' color='#FFD6C0'>" + args.getString("length") + "</font>"));
        /*link.setText(Html.fromHtml("<b><font size='16' color='#D05353'>Link: </b></font>"
                + "<font size='14' color='#FFD6C0'>" + args.getString("url") + "</font>"));*/
        description.setText(args.getString("description"));

        return view;
    }

}
