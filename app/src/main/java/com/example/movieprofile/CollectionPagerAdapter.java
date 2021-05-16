package com.example.movieprofile;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.Map;

public class CollectionPagerAdapter extends FragmentStatePagerAdapter {

    MovieData movieData;

    public CollectionPagerAdapter(@NonNull FragmentManager fm, int behavior, MovieData movieData) {
        super(fm, behavior);
        this.movieData = movieData;
    }

    @NonNull @Override
    public Fragment getItem(int position) {
        Map movie = movieData.getItem(position);
        return MovieDetailFragment.newInstance((int) movie.get("image"), movie.get("name").toString(),
                        movie.get("year").toString(), movie.get("length").toString(),
                        Float.parseFloat(movie.get("rating").toString()),
                        movie.get("director").toString(), movie.get("stars").toString(),
                        movie.get("description").toString(), movie.get("url").toString());
    }

    @Override
    public int getCount() {
        return movieData.getSize();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return movieData.getItem(position).get("name").toString();
    }
}
