package com.yogadimas.moviecatalogue.ui.detail.movie;

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
import com.yogadimas.moviecatalogue.data.source.local.entity.movie.MovieEntity;
import com.yogadimas.moviecatalogue.databinding.ActivityDetailMovieBinding;
import com.yogadimas.moviecatalogue.databinding.ContentDetailMovieBinding;
import com.yogadimas.moviecatalogue.viewmodel.ViewModelFactory;


public class DetailMovieActivity extends AppCompatActivity {

    public static final String EXTRA_MOVIE = "extra_movie";
    private ActivityDetailMovieBinding activityDetailMovieBinding;
    private ContentDetailMovieBinding contentDetailMovieBinding;
    private DetailMovieViewModel detailMovieViewModel;
    private Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityDetailMovieBinding = ActivityDetailMovieBinding.inflate(getLayoutInflater());
        setContentView(activityDetailMovieBinding.getRoot());

        contentDetailMovieBinding = activityDetailMovieBinding.contentDetailMovie;

        setSupportActionBar(activityDetailMovieBinding.toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        ViewModelFactory viewModelFactory = ViewModelFactory.getInstance(this);
        detailMovieViewModel = new ViewModelProvider(this, viewModelFactory).get(DetailMovieViewModel.class);

        activityDetailMovieBinding.progressBar.setVisibility(View.VISIBLE);
        activityDetailMovieBinding.contentMovie.setVisibility(View.GONE);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int movieId = extras.getInt(EXTRA_MOVIE, 0);
            String movId = Integer.toString(movieId);
            if (movId != null) {
                detailMovieViewModel.setMovieId(movId);
                detailMovieViewModel.getMovie.observe(this, movieEntity -> {
                    if (movieEntity != null) {
                        activityDetailMovieBinding.progressBar.setVisibility(View.GONE);
                        activityDetailMovieBinding.contentMovie.setVisibility(View.VISIBLE);
                        populateMovie(movieEntity);
                    }
                });
            }
        }


    }


    private void populateMovie(MovieEntity movieEntity) {
        Glide.with(this)
                .load(BuildConfig.BASE_URL_IMAGE + movieEntity.getImagePath())
                .transform(new RoundedCorners(20))
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error))
                .into(contentDetailMovieBinding.imagePoster);
        contentDetailMovieBinding.textScore.setText(String.valueOf(formatDouble(movieEntity.getVoteAverage())));
        contentDetailMovieBinding.textRelease.setText(movieEntity.getRelease());
        contentDetailMovieBinding.textTitle.setText(movieEntity.getTitle());
        contentDetailMovieBinding.textOverview.setText(movieEntity.getOverview());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        this.menu = menu;
        detailMovieViewModel.getMovie.observe(this, movieEntity -> {
            if (movieEntity != null) {
                activityDetailMovieBinding.progressBar.setVisibility(View.GONE);
                activityDetailMovieBinding.contentMovie.setVisibility(View.VISIBLE);
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
            detailMovieViewModel.setFavoriteMovie();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        contentDetailMovieBinding = null;
        activityDetailMovieBinding = null;
    }
}