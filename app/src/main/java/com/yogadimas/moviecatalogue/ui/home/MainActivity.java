 package com.yogadimas.moviecatalogue.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.tabs.TabLayoutMediator;
import com.yogadimas.moviecatalogue.R;
import com.yogadimas.moviecatalogue.databinding.ActivityMainBinding;
import com.yogadimas.moviecatalogue.ui.favorite.FavoriteActivity;

public class MainActivity extends AppCompatActivity {
    private static final int[] TAB_TITLES = new int[]{R.string.movies, R.string.tv_show};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this);

        activityMainBinding.viewpager.setAdapter(sectionsPagerAdapter);

        new TabLayoutMediator(activityMainBinding.tabs, activityMainBinding.viewpager,
                (tab, position) -> tab.setText(TAB_TITLES[position])).attach();

        if (getSupportActionBar() != null) {
            getSupportActionBar().setElevation(0);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.list_favorite) {
            Intent goToFavoriteActivity = new Intent(MainActivity.this, FavoriteActivity.class);
            startActivity(goToFavoriteActivity);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}