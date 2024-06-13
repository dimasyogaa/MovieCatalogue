package com.yogadimas.moviecatalogue.ui.favorite.movie;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.yogadimas.moviecatalogue.R;
import com.yogadimas.moviecatalogue.data.source.local.entity.movie.MovieEntity;
import com.yogadimas.moviecatalogue.databinding.FragmentMovieFavoriteBinding;
import com.yogadimas.moviecatalogue.ui.movie.MoviesFragmentCallback;
import com.yogadimas.moviecatalogue.viewmodel.ViewModelFactory;

import org.jetbrains.annotations.NotNull;


public class MovieFavoriteFragment extends Fragment implements MoviesFragmentCallback {

    private FragmentMovieFavoriteBinding movieFavoriteBinding;
    private MoviesFavoriteViewModel moviesViewModel;
    private MoviesFavoriteAdapter moviesAdapter;
    private final ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
        @Override
        public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
            return makeMovementFlags(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        }

        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return true;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            int swipedPosition = viewHolder.getBindingAdapterPosition();
            MovieEntity movieEntity = moviesAdapter.getSwipedData(swipedPosition);
            moviesViewModel.setFavoriteMovie(movieEntity);
            Snackbar snackbar = Snackbar.make(getView(), R.string.message_undo, Snackbar.LENGTH_LONG);
            snackbar.setAction(R.string.message_ok, v -> moviesViewModel.setFavoriteMovie(movieEntity));
            snackbar.show();
        }
    });

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        movieFavoriteBinding = FragmentMovieFavoriteBinding.inflate(inflater, container, false);
        return movieFavoriteBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        itemTouchHelper.attachToRecyclerView(movieFavoriteBinding.rvMovies);
        if (getActivity() != null) {
            ViewModelFactory viewModelFactory = ViewModelFactory.getInstance(getActivity());
            moviesViewModel = new ViewModelProvider(requireActivity(), viewModelFactory).get(MoviesFavoriteViewModel.class);

            moviesAdapter = new MoviesFavoriteAdapter(this);

            moviesViewModel.getFavoriteMovie().observe(getViewLifecycleOwner(), movieEntities -> {
                if (movieEntities != null) {
                    movieFavoriteBinding.progressBar.setVisibility(View.GONE);
                    moviesAdapter.submitList(movieEntities);
                    if (movieEntities.size() == 0) {
                        movieFavoriteBinding.textNotYet.setVisibility(View.VISIBLE);
                    } else {
                        movieFavoriteBinding.textNotYet.setVisibility(View.GONE);
                    }
                }


            });
            movieFavoriteBinding.rvMovies.setLayoutManager(new LinearLayoutManager(getContext()));
            movieFavoriteBinding.rvMovies.setHasFixedSize(true);
            movieFavoriteBinding.rvMovies.setAdapter(moviesAdapter);
        }
    }

    @Override
    public void onShareClick(MovieEntity movieEntity) {
        if (getActivity() != null) {
            String mimeType = "text/plain";
            Intent sharingIntent = new Intent(Intent.ACTION_VIEW);
            sharingIntent.setAction(Intent.ACTION_SEND);
            sharingIntent.putExtra(Intent.EXTRA_TEXT,
                    getActivity().getString(R.string.share_movie_text, movieEntity.getTitle()));
            sharingIntent.setType(mimeType);
            getActivity().startActivity(sharingIntent);


        }
    }
}