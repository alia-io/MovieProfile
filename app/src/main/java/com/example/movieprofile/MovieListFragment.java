package com.example.movieprofile;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import java.util.Map;

public class MovieListFragment extends Fragment {

    private MasterDetailFlowActivity activity;
    private MovieData movieData;
    private Integer[] imageIds;
    private String[] titles;
    private boolean twoPane;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            activity = (MasterDetailFlowActivity) context;
        } catch (ClassCastException ex) {
            throw new ClassCastException(context.toString() + " must be MasterDetailFlowActivity.");
        }

        movieData = new MovieData();
        imageIds = new Integer[movieData.getSize()];
        titles = new String[movieData.getSize()];
        twoPane = false;

        for (int i = 0; i < movieData.getSize(); i++) {
            Map movie = movieData.getItem(i);
            imageIds[i] = Integer.parseInt(movie.get("image").toString());
            titles[i] = movie.get("name").toString();
        }

        if (activity.findViewById(R.id.detail_container) != null) twoPane = true;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.movie_list_fragment, container, false);
        MovieListAdapter adapter = new MovieListAdapter(activity, titles, imageIds);
        ListView listView = view.findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener((parent, view1, position, id) -> {
            Map movie = movieData.getItem(position);
            MovieDetailFragment movieDetailFragment = MovieDetailFragment.newInstance(
                    (int) movie.get("image"), movie.get("name").toString(), movie.get("year").toString(),
                    movie.get("length").toString(), Float.parseFloat(movie.get("rating").toString()),
                    movie.get("director").toString(), movie.get("stars").toString(),
                    movie.get("description").toString(), movie.get("url").toString());
            if (twoPane) {
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.detail_container,
                        movieDetailFragment).addToBackStack(null).commit();
            } else {
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.list_container,
                        movieDetailFragment).addToBackStack(null).commit();
            }
            activity.setMovieFragmentOpen(true);
        });
        return view;
    }
}
