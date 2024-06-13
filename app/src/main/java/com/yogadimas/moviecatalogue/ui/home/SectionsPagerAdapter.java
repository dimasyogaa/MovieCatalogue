package com.yogadimas.moviecatalogue.ui.home;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.yogadimas.moviecatalogue.ui.movie.MoviesFragment;
import com.yogadimas.moviecatalogue.ui.tvshow.TvShowFragment;


public class SectionsPagerAdapter extends FragmentStateAdapter {


    public SectionsPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new MoviesFragment();
            case 1:
                return new TvShowFragment();
            default:
                return new Fragment();
        }
    }


    @Override
    public int getItemCount() {
        return 2;
    }
}
