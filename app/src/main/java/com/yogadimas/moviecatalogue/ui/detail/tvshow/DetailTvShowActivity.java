package com.yogadimas.moviecatalogue.ui.detail.tvshow;

import static com.yogadimas.moviecatalogue.utils.Helper.formatDouble;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.yogadimas.moviecatalogue.BuildConfig;
import com.yogadimas.moviecatalogue.R;
import com.yogadimas.moviecatalogue.data.source.local.entity.tvshow.TvShowEntity;
import com.yogadimas.moviecatalogue.databinding.ActivityDetailTvShowBinding;
import com.yogadimas.moviecatalogue.databinding.ContentDetailTvShowBinding;
import com.yogadimas.moviecatalogue.viewmodel.ViewModelFactory;

public class DetailTvShowActivity extends AppCompatActivity {

    public static final String EXTRA_TV_SHOW = "extra_tv_show";
    private ActivityDetailTvShowBinding activityDetailTvShowBinding;
    private ContentDetailTvShowBinding contentDetailTvShowBinding;
    private DetailTvShowViewModel detailTvShowViewModel;
    private Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityDetailTvShowBinding = ActivityDetailTvShowBinding.inflate(getLayoutInflater());
        setContentView(activityDetailTvShowBinding.getRoot());

        setSupportActionBar(activityDetailTvShowBinding.toolbar);

        contentDetailTvShowBinding = activityDetailTvShowBinding.contentDetailTvShow;

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        ViewModelFactory viewModelFactory = ViewModelFactory.getInstance(this);
        detailTvShowViewModel = new ViewModelProvider(this, viewModelFactory).get(DetailTvShowViewModel.class);

        activityDetailTvShowBinding.progressBar.setVisibility(View.VISIBLE);
        activityDetailTvShowBinding.contentTvShow.setVisibility(View.GONE);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int tvShowId = extras.getInt(EXTRA_TV_SHOW, 0);
            String tvId = Integer.toString(tvShowId);
            if (tvId != null) {
                detailTvShowViewModel.setTvShowId(tvId);
                detailTvShowViewModel.getTvShow.observe(this, tvShowEntity -> {
                    if (tvShowEntity != null) {
                        activityDetailTvShowBinding.progressBar.setVisibility(View.GONE);
                        activityDetailTvShowBinding.contentTvShow.setVisibility(View.VISIBLE);
                        populateTvShow(tvShowEntity);
                    }
                });
            }

        }
    }

    private void populateTvShow(TvShowEntity tvShowEntity) {
        Glide.with(this)
                .load(BuildConfig.BASE_URL_IMAGE + tvShowEntity.getImagePath())
                .transform(new RoundedCorners(20))
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error))
                .into(contentDetailTvShowBinding.imagePoster);
        contentDetailTvShowBinding.textScore.setText(String.valueOf(formatDouble(tvShowEntity.getVoteAverage())));
        contentDetailTvShowBinding.textFirstAirDate.setText(tvShowEntity.getFirstAirDate());
        contentDetailTvShowBinding.textTitle.setText(tvShowEntity.getTitle());
        contentDetailTvShowBinding.textOverview.setText(tvShowEntity.getOverview());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        this.menu = menu;
        detailTvShowViewModel.getTvShow.observe(this, movieEntity -> {
            if (movieEntity != null) {
                activityDetailTvShowBinding.progressBar.setVisibility(View.GONE);
                activityDetailTvShowBinding.contentTvShow.setVisibility(View.VISIBLE);
                boolean state = movieEntity.isFavorite();
                setFavoriteState(state);
            }
        });
        return true;
    }

    private void setFavoriteState(boolean state) {
        if (menu == null) return;
        MenuItem menuItem = menu.findItem(R.id.action_favorite);
        if (state) {
            menuItem.setIcon(ContextCompat.getDrawable(this, R.drawable.ic_favorite_on));
        } else {
            menuItem.setIcon(ContextCompat.getDrawable(this, R.drawable.ic_favorite));

        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_favorite) {
            detailTvShowViewModel.setFavoriteTvShow();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        contentDetailTvShowBinding = null;
        activityDetailTvShowBinding = null;
    }

}