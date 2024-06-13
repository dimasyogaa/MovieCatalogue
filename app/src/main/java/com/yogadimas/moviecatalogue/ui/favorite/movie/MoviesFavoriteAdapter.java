package com.yogadimas.moviecatalogue.ui.favorite.movie;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.yogadimas.moviecatalogue.BuildConfig;
import com.yogadimas.moviecatalogue.R;
import com.yogadimas.moviecatalogue.data.source.local.entity.movie.MovieEntity;
import com.yogadimas.moviecatalogue.databinding.ItemsMovieBinding;
import com.yogadimas.moviecatalogue.ui.detail.movie.DetailMovieActivity;
import com.yogadimas.moviecatalogue.ui.movie.MoviesFragmentCallback;

public class MoviesFavoriteAdapter extends PagedListAdapter<MovieEntity, MoviesFavoriteAdapter.MoviesViewHolder> {

    private static final DiffUtil.ItemCallback<MovieEntity> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<MovieEntity>() {
                @Override
                public boolean areItemsTheSame(@NonNull MovieEntity oldItem, @NonNull MovieEntity newItem) {
                    return oldItem.getMovieId() == newItem.getMovieId();
                }

                @SuppressLint("DiffUtilEquals")
                @Override
                public boolean areContentsTheSame(@NonNull MovieEntity oldItem, @NonNull MovieEntity newItem) {
                    return oldItem == newItem;
                }
            };
    private final MoviesFragmentCallback moviesFragmentCallback;


    MoviesFavoriteAdapter(MoviesFragmentCallback moviesFragmentCallback) {
        super(DIFF_CALLBACK);
        this.moviesFragmentCallback = moviesFragmentCallback;
    }

    public MovieEntity getSwipedData(int swipedPosition) {
        return getItem(swipedPosition);
    }


    @NonNull
    @Override
    public MoviesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemsMovieBinding itemsMovieBinding = ItemsMovieBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MoviesViewHolder(itemsMovieBinding);
    }


    @Override
    public void onBindViewHolder(@NonNull MoviesViewHolder holder, int position) {
        MovieEntity movieEntity = getItem(position);
        if (movieEntity != null) {
            holder.bind(movieEntity);
        }
    }


    public class MoviesViewHolder extends RecyclerView.ViewHolder {

        private final ItemsMovieBinding itemsMovieBinding;


        public MoviesViewHolder(ItemsMovieBinding itemsMovieBinding) {
            super(itemsMovieBinding.getRoot());
            this.itemsMovieBinding = itemsMovieBinding;
        }

        void bind(MovieEntity movieEntity) {
            itemsMovieBinding.tvItemTitle.setText(movieEntity.getTitle());
            itemsMovieBinding.textRelease.setText(movieEntity.getRelease());
            itemView.setOnClickListener(view -> {
                Intent intent = new Intent(view.getContext(), DetailMovieActivity.class);
                intent.putExtra(DetailMovieActivity.EXTRA_MOVIE, movieEntity.getMovieId());
                itemView.getContext().startActivity(intent);
            });
            itemsMovieBinding.imgShare.setOnClickListener(view -> moviesFragmentCallback.onShareClick(movieEntity));

            Glide.with(itemView.getContext())
                    .load(BuildConfig.BASE_URL_IMAGE + movieEntity.getImagePath())
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                    .into(itemsMovieBinding.imgPoster);


        }

    }

}


