package com.yogadimas.moviecatalogue.ui.favorite;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.tabs.TabLayoutMediator;
import com.yogadimas.moviecatalogue.R;
import com.yogadimas.moviecatalogue.databinding.ActivityFavoriteBinding;


public class FavoriteActivity extends AppCompatActivity {
    private static final int[] TAB_TITLES = new int[]{R.string.movies, R.string.tv_show};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityFavoriteBinding activityFavoriteBinding = ActivityFavoriteBinding.inflate(getLayoutInflater());
        setContentView(activityFavoriteBinding.getRoot());

        SectionsPagerAdapterFavorite sectionsPagerAdapterFavorite = new SectionsPagerAdapterFavorite(this);

        activityFavoriteBinding.viewpagerFavorite.setAdapter(sectionsPagerAdapterFavorite);

        new TabLayoutMediator(activityFavoriteBinding.tabsFavorite, activityFavoriteBinding.viewpagerFavorite,
                (tab, position) -> tab.setText(TAB_TITLES[position])).attach();

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }
}