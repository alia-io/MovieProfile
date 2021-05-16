package com.example.movieprofile;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

/* Task 2 */
public class ViewPagerActivity extends FragmentActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);

        int width = getIntent().getIntExtra("WIDTH", 300);
        int height = getIntent().getIntExtra("HEIGHT", 500);

        NavigationView navigationView = findViewById(R.id.navigation_view);
        drawerLayout = findViewById(R.id.drawer);
        navigationView.setNavigationItemSelectedListener(this);

        MovieData movieData = new MovieData();
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        ViewPager viewPager = findViewById(R.id.view_pager);

        // Set viewPager size to maximum movie fragment size
        ViewGroup.LayoutParams layoutParams = viewPager.getLayoutParams();
        layoutParams.width = width;
        layoutParams.height = height;
        viewPager.requestLayout();

        CollectionPagerAdapter collectionPagerAdapter = new CollectionPagerAdapter(getSupportFragmentManager(),
                FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, movieData);
        viewPager.setAdapter(collectionPagerAdapter);
        viewPager.setPageTransformer(true, new DepthPageTransformer());
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
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