package com.yogadimas.moviecatalogue.ui.favorite.tvshow;

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
import com.yogadimas.moviecatalogue.data.source.local.entity.tvshow.TvShowEntity;
import com.yogadimas.moviecatalogue.databinding.ItemsTvShowBinding;
import com.yogadimas.moviecatalogue.ui.detail.tvshow.DetailTvShowActivity;
import com.yogadimas.moviecatalogue.ui.tvshow.TvShowFragmentCallback;

public class TvShowFavoriteAdapter extends PagedListAdapter<TvShowEntity, TvShowFavoriteAdapter.TvShowViewHolder> {

    private static final DiffUtil.ItemCallback<TvShowEntity> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<TvShowEntity>() {
                @Override
                public boolean areItemsTheSame(@NonNull TvShowEntity oldItem, @NonNull TvShowEntity newItem) {
                    return oldItem.getTvShowId() == newItem.getTvShowId();
                }

                @SuppressLint("DiffUtilEquals")
                @Override
                public boolean areContentsTheSame(@NonNull TvShowEntity oldItem, @NonNull TvShowEntity newItem) {
                    return oldItem == newItem;
                }
            };
    private final TvShowFragmentCallback tvShowFragmentCallback;


    TvShowFavoriteAdapter(TvShowFragmentCallback tvShowFragmentCallback) {
        super(DIFF_CALLBACK);
        this.tvShowFragmentCallback = tvShowFragmentCallback;
    }

    public TvShowEntity getSwipedData(int swipedPosition) {
        return getItem(swipedPosition);
    }


    @NonNull
    @Override
    public TvShowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemsTvShowBinding itemsTvShowBinding = ItemsTvShowBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new TvShowViewHolder(itemsTvShowBinding);
    }


    @Override
    public void onBindViewHolder(@NonNull TvShowViewHolder holder, int position) {
        TvShowEntity tvShowEntity = getItem(position);
        holder.bind(tvShowEntity);
    }


    public class TvShowViewHolder extends RecyclerView.ViewHolder {

        private final ItemsTvShowBinding itemsTvShowBinding;

        public TvShowViewHolder(ItemsTvShowBinding itemsTvShowBinding) {
            super(itemsTvShowBinding.getRoot());
            this.itemsTvShowBinding = itemsTvShowBinding;
        }

        public void bind(TvShowEntity tvShowEntity) {
            itemsTvShowBinding.tvItemTitle.setText(tvShowEntity.getTitle());
            itemsTvShowBinding.textFirstAirDate.setText(tvShowEntity.getFirstAirDate());
            itemView.setOnClickListener(view -> {
                Intent intent = new Intent(view.getContext(), DetailTvShowActivity.class);
                intent.putExtra(DetailTvShowActivity.EXTRA_TV_SHOW, tvShowEntity.getTvShowId());
                itemView.getContext().startActivity(intent);
            });
            itemsTvShowBinding.imgShare.setOnClickListener(view -> tvShowFragmentCallback.onShareClick(tvShowEntity));

            Glide.with(itemView.getContext())
                    .load(BuildConfig.BASE_URL_IMAGE + tvShowEntity.getImagePath())
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                    .into(itemsTvShowBinding.imgPoster);

        }
    }
}

