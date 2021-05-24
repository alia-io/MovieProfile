package com.example.movieprofile;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

/* Activity for Task #2 (ViewPager) */
public class ViewPagerActivity extends FragmentActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);

        NavigationView navigationView = findViewById(R.id.navigation_view);
        drawerLayout = findViewById(R.id.drawer);
        navigationView.setNavigationItemSelectedListener(this);

        MovieData movieData = new MovieData();
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        ViewPager viewPager = findViewById(R.id.view_pager);

        setViewPagerSize(viewPager); // Set viewPager size based on screen size

        // Set up the ViewPager and TabLayout
        CollectionPagerAdapter collectionPagerAdapter = new CollectionPagerAdapter(getSupportFragmentManager(),
                FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, movieData);
        viewPager.setAdapter(collectionPagerAdapter);
        viewPager.setPageTransformer(true, new DepthPageTransformer());
        tabLayout.setupWithViewPager(viewPager);
    }

    /* Sets the size of the ViewPager MovieDetailFragments according to the device screen size */
    private void setViewPagerSize(ViewPager viewPager) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int screenWidth = displayMetrics.widthPixels;
        int screenHeight = displayMetrics.heightPixels;
        int screenLayoutSize = getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK;
        ViewGroup.LayoutParams layoutParams = viewPager.getLayoutParams();
        if ((screenLayoutSize == Configuration.SCREENLAYOUT_SIZE_LARGE)
                || screenLayoutSize == Configuration.SCREENLAYOUT_SIZE_XLARGE) {
            if (screenWidth < screenHeight) {
                layoutParams.height = (int) (screenHeight * 0.4);
                layoutParams.width = (int) (screenWidth * 0.5);
            } else {
                layoutParams.height = (int) (screenHeight * 0.5);
                layoutParams.width = (int) (screenWidth * 0.4);
            }
        } else {
            layoutParams.width = (int) (screenWidth * 0.9);
            layoutParams.height = (int) (screenHeight * 0.6);
        }
        viewPager.requestLayout();
    }

    @Override
    /* Navigation for Drawer items */
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (onItemSelected(item)) {
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        }
        return false;
    }

    /* Handles navigation to other activities */
    private boolean onItemSelected(@NonNull MenuItem item) {
        final int profileActionId = R.id.profile_action;
        final int movieListActionId = R.id.movie_list_action;

        switch (item.getItemId()) {
            case profileActionId:
                startActivity(new Intent(this, MainActivity.class));
                finish();
                break;
            case movieListActionId:
                startActivity(new Intent(this, MasterDetailFlowActivity.class));
                finish();
                break;
            default: return false;
        }
        return true;
    }

    /* Handles ViewPager transitions between fragments */
    public static class DepthPageTransformer implements ViewPager.PageTransformer {
        private static final float MIN_SCALE = 0.75f;
        @Override
        public void transformPage(@NonNull View page, float position) {
            int pageWidth = page.getWidth();
            float scaleFactor = MIN_SCALE + (1 - MIN_SCALE) * (1 - Math.abs(position));
            if (position < -1) page.setAlpha(0);
            else if (position <= 0) {
                page.setAlpha(1);
                page.setTranslationX(0);
                page.setScaleX(1);
                page.setScaleY(1);
            } else if (position <= 1) {
                page.setAlpha(1 - position);
                page.setTranslationX(pageWidth * -position);
                page.setScaleX(scaleFactor);
                page.setScaleY(scaleFactor);
            } else page.setAlpha(0);
        }
    }
}