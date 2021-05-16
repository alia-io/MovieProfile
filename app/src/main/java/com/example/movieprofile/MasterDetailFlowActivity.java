package com.example.movieprofile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MasterDetailFlowActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private boolean twoPane;
    private MovieData movieData;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master_detail_flow);
        movieData = new MovieData();
        twoPane = false;

        Toolbar actionBar = findViewById(R.id.action_bar);
        setSupportActionBar(actionBar);

        NavigationView navigationView = findViewById(R.id.navigation_view);
        drawerLayout = findViewById(R.id.drawer);

        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,
                drawerLayout, actionBar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        if (findViewById(R.id.detail_container) != null) twoPane = true;
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
        final int profileActionId = R.id.profile_action;
        final int movieDetailsActionId = R.id.movie_details_action;
        final int movieListActionId = R.id.movie_list_action;

        switch (item.getItemId()) {
            case profileActionId:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case movieDetailsActionId:
                Toast.makeText(getApplicationContext(), "Movie Details Action", Toast.LENGTH_SHORT).show();

                return false;
            case movieListActionId:
                Toast.makeText(getApplicationContext(), "Movie List Action", Toast.LENGTH_SHORT).show();
                break;
            default: return false;
        }
        return true;
    }
}