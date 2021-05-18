package com.example.movieprofile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

public class MasterDetailFlowActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private int maxFragmentWidth;
    private int maxFragmentHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master_detail_flow);

        maxFragmentWidth = getIntent().getIntExtra("WIDTH", 300);
        maxFragmentHeight = getIntent().getIntExtra("HEIGHT", 500);

        // Set up Action Bar and Drawer
        Toolbar actionBar = ActivitySetupUtilities.setUpActionBar(this);
        drawerLayout = ActivitySetupUtilities.setUpDrawer(this, this, actionBar);

        if (savedInstanceState == null)
            getSupportFragmentManager().beginTransaction().replace(
                    R.id.list_container, new MovieListFragment()).commit();
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
        Intent intent;

        switch (item.getItemId()) {
            case profileActionId:
                startActivity(new Intent(this, MainActivity.class));
                finish();
                break;
            case movieDetailsActionId:
                intent = new Intent(this, ViewPagerActivity.class);
                intent.putExtra("WIDTH", maxFragmentWidth);
                intent.putExtra("HEIGHT", maxFragmentHeight);
                startActivity(intent);
                finish();
                return false;
            default: return false;
        }
        return true;
    }
}