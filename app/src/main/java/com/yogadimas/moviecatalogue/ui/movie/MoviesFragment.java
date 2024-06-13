package com.yogadimas.moviecatalogue.ui.movie;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.yogadimas.moviecatalogue.R;
import com.yogadimas.moviecatalogue.data.source.local.entity.movie.MovieEntity;
import com.yogadimas.moviecatalogue.databinding.FragmentMoviesBinding;
import com.yogadimas.moviecatalogue.viewmodel.ViewModelFactory;


public class MoviesFragment extends Fragment implements MoviesFragmentCallback {

    private FragmentMoviesBinding fragmentMoviesBinding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentMoviesBinding = FragmentMoviesBinding.inflate(inflater, container, false);
        return fragmentMoviesBinding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getActivity() != null) {
            ViewModelFactory viewModelFactory = ViewModelFactory.getInstance(getActivity());
            MoviesViewModel moviesViewModel = new ViewModelProvider(requireActivity(), viewModelFactory).get(MoviesViewModel.class);

            MoviesAdapter moviesAdapter = new MoviesAdapter(this);

            moviesViewModel.getMovies().observe(getViewLifecycleOwner(), movieEntities -> {
                if (movieEntities != null) {
                    switch (movieEntities.status) {
                        case LOADING:
                            fragmentMoviesBinding.progressBar.setVisibility(View.VISIBLE);
                            break;
                        case SUCCESS:
                            fragmentMoviesBinding.progressBar.setVisibility(View.GONE);
                            moviesAdapter.submitList(movieEntities.data);
                            break;
                        case ERROR:
                            fragmentMoviesBinding.progressBar.setVisibility(View.GONE);
                            Toast.makeText(getContext(), "Terjadi kesalahan", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }

            });
            fragmentMoviesBinding.rvMovies.setLayoutManager(new LinearLayoutManager(getContext()));
            fragmentMoviesBinding.rvMovies.setHasFixedSize(true);
            fragmentMoviesBinding.rvMovies.setAdapter(moviesAdapter);
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