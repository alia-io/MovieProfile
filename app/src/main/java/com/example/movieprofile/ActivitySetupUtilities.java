package com.example.movieprofile;

import android.view.View;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class ActivitySetupUtilities {

    public static Toolbar setUpActionBar(AppCompatActivity activity) {
        Toolbar actionBar = activity.findViewById(R.id.action_bar);
        activity.setSupportActionBar(actionBar);
        return actionBar;
    }

    public static DrawerLayout setUpDrawer(AppCompatActivity activity,
            NavigationView.OnNavigationItemSelectedListener listener, Toolbar actionBar) {
        NavigationView navigationView = activity.findViewById(R.id.navigation_view);
        DrawerLayout drawerLayout = activity.findViewById(R.id.drawer);

        navigationView.setNavigationItemSelectedListener(listener);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(activity,
                drawerLayout, actionBar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        return drawerLayout;
    }

}
