package com.yogadimas.moviecatalogue.ui.favorite.tvshow;

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
import com.yogadimas.moviecatalogue.data.source.local.entity.tvshow.TvShowEntity;
import com.yogadimas.moviecatalogue.databinding.FragmentTvShowFavoriteBinding;
import com.yogadimas.moviecatalogue.ui.tvshow.TvShowFragmentCallback;
import com.yogadimas.moviecatalogue.viewmodel.ViewModelFactory;

import org.jetbrains.annotations.NotNull;


public class TvShowFavoriteFragment extends Fragment implements TvShowFragmentCallback {

    private FragmentTvShowFavoriteBinding tvShowFavoriteBinding;

    private TvShowFavoriteViewModel tvShowsViewModel;
    private TvShowFavoriteAdapter tvShowsAdapter;
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
            TvShowEntity tvShowEntity = tvShowsAdapter.getSwipedData(swipedPosition);
            tvShowsViewModel.setFavoriteTvShow(tvShowEntity);
            Snackbar snackbar = Snackbar.make(getView(), R.string.message_undo, Snackbar.LENGTH_LONG);
            snackbar.setAction(R.string.message_ok, v -> tvShowsViewModel.setFavoriteTvShow(tvShowEntity));
            snackbar.show();
        }
    });


    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        tvShowFavoriteBinding = FragmentTvShowFavoriteBinding.inflate(inflater, container, false);
        return tvShowFavoriteBinding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        itemTouchHelper.attachToRecyclerView(tvShowFavoriteBinding.rvTvShow);
        if (getActivity() != null) {
            ViewModelFactory viewModelFactory = ViewModelFactory.getInstance(getActivity());
            tvShowsViewModel = new ViewModelProvider(requireActivity(), viewModelFactory).get(TvShowFavoriteViewModel.class);

            tvShowsAdapter = new TvShowFavoriteAdapter(this);


            tvShowsViewModel.getFavoriteTvShow().observe(getViewLifecycleOwner(), tvShowEntities -> {
                if (tvShowEntities != null) {
                    tvShowFavoriteBinding.progressBar.setVisibility(View.GONE);
                    tvShowsAdapter.submitList(tvShowEntities);
                    if (tvShowEntities.size() == 0) {
                        tvShowFavoriteBinding.textNotYet.setVisibility(View.VISIBLE);
                    } else {
                        tvShowFavoriteBinding.textNotYet.setVisibility(View.GONE);
                    }
                }

            });
            tvShowFavoriteBinding.rvTvShow.setLayoutManager(new LinearLayoutManager(getContext()));
            tvShowFavoriteBinding.rvTvShow.setHasFixedSize(true);
            tvShowFavoriteBinding.rvTvShow.setAdapter(tvShowsAdapter);
        }
    }

    @Override
    public void onShareClick(TvShowEntity tvShowEntity) {
        if (getActivity() != null) {
            String mimeType = "text/plain";
            Intent sharingIntent = new Intent(Intent.ACTION_VIEW);
            sharingIntent.setAction(Intent.ACTION_SEND);
            sharingIntent.putExtra(Intent.EXTRA_TEXT,
                    getActivity().getString(R.string.share_movie_text, tvShowEntity.getTitle()));
            sharingIntent.setType(mimeType);
            getActivity().startActivity(sharingIntent);


        }
    }
}