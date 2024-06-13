package com.yogadimas.moviecatalogue.ui.tvshow;

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
import com.yogadimas.moviecatalogue.data.source.local.entity.tvshow.TvShowEntity;
import com.yogadimas.moviecatalogue.databinding.FragmentTvShowBinding;
import com.yogadimas.moviecatalogue.viewmodel.ViewModelFactory;


public class TvShowFragment extends Fragment implements TvShowFragmentCallback {

    private FragmentTvShowBinding fragmentTvShowBinding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentTvShowBinding = FragmentTvShowBinding.inflate(inflater, container, false);
        return fragmentTvShowBinding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getActivity() != null) {
            ViewModelFactory viewModelFactory = ViewModelFactory.getInstance(getActivity());
            TvShowViewModel tvShowViewModel = new ViewModelProvider(requireActivity(), viewModelFactory).get(TvShowViewModel.class);

            TvShowAdapter tvShowAdapter = new TvShowAdapter(this);


            tvShowViewModel.getTvShow().observe(getViewLifecycleOwner(), tvShowEntities -> {
                if (tvShowEntities != null) {
                    switch (tvShowEntities.status) {
                        case LOADING:
                            fragmentTvShowBinding.progressBar.setVisibility(View.VISIBLE);
                            break;
                        case SUCCESS:

                            fragmentTvShowBinding.progressBar.setVisibility(View.GONE);
                            tvShowAdapter.submitList(tvShowEntities.data);
                            break;
                        case ERROR:
                            fragmentTvShowBinding.progressBar.setVisibility(View.GONE);
                            Toast.makeText(getContext(), "Terjadi kesalahan", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            });
            fragmentTvShowBinding.rvTvShow.setLayoutManager(new LinearLayoutManager(getContext()));
            fragmentTvShowBinding.rvTvShow.setHasFixedSize(true);
            fragmentTvShowBinding.rvTvShow.setAdapter(tvShowAdapter);

        }
    }

    @Override
    public void onShareClick(TvShowEntity tvShowEntity) {
        if (getActivity() != null) {
            String mimeType = "text/plain";
            Intent sharingIntent = new Intent(Intent.ACTION_VIEW);
            sharingIntent.setAction(Intent.ACTION_SEND);
            sharingIntent.putExtra(Intent.EXTRA_TEXT,
                    getActivity().getString(R.string.share_tv_show_text, tvShowEntity.getTitle()));
            sharingIntent.setType(mimeType);
            getActivity().startActivity(sharingIntent);
        }
    }
}