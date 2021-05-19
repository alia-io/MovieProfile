package com.example.movieprofile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import java.util.Map;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private MovieData movieData;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        movieData = new MovieData();

        // Set up Action Bar and Drawer
        Toolbar actionBar = ActivitySetupUtilities.setUpActionBar(this);
        drawerLayout = ActivitySetupUtilities.setUpDrawer(this, this, actionBar);

        setAboutMeFragment();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.task_menu, menu);
        return true;
    }

    @Override
    // Navigation for ActionBar items
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (onItemSelected(item)) return true;
        return super.onOptionsItemSelected(item);
    }

    @Override
    // Navigation for Drawer items
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (onItemSelected(item)) {
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        }
        return false;
    }

    private boolean onItemSelected(@NonNull MenuItem item) {
        final int movieDetailsActionId = R.id.movie_details_action;
        final int movieListActionId = R.id.movie_list_action;

        switch (item.getItemId()) {
            case movieDetailsActionId:
                startActivity(new Intent(this, ViewPagerActivity.class));
                break;
            case movieListActionId:
                startActivity(new Intent(this, MasterDetailFlowActivity.class));
                break;
            default: return false;
        }

        return true;
    }

    // Keeping this here in case I ever need to display a fragment by itself
    private void setMovieFragment(int index, boolean setParent) {
        Map movie = movieData.getItem(index);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        MovieDetailFragment movieDetailFragment = MovieDetailFragment
                .newInstance((int) movie.get("image"), movie.get("name").toString(),
                        movie.get("year").toString(), movie.get("length").toString(),
                        Float.parseFloat(movie.get("rating").toString()),
                        movie.get("director").toString(), movie.get("stars").toString(),
                        movie.get("description").toString(), movie.get("url").toString());
        if (setParent) movieDetailFragment.setParent(this);
        fragmentTransaction.add(R.id.fragment_container, movieDetailFragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
        fragmentTransaction.commit();
    }

    public void setAboutMeFragment() {
        AboutMeFragment aboutMeFragment = new AboutMeFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, aboutMeFragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.commit();
    }
}