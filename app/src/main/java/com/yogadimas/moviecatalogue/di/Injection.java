package com.yogadimas.moviecatalogue.di;

import android.content.Context;

import com.yogadimas.moviecatalogue.data.source.MovieCatalogueRepository;
import com.yogadimas.moviecatalogue.data.source.local.LocalDataSource;
import com.yogadimas.moviecatalogue.data.source.local.room.MovieCatalogueDatabase;
import com.yogadimas.moviecatalogue.data.source.remote.RemoteDataSource;
import com.yogadimas.moviecatalogue.utils.AppExecutors;

public class Injection {
    public static MovieCatalogueRepository provideRepository(Context context) {

        MovieCatalogueDatabase database = MovieCatalogueDatabase.getInstance(context);
        RemoteDataSource remoteDataSource = RemoteDataSource.getInstance(context);
        LocalDataSource localDataSource = LocalDataSource.getInstance(database.movieCatalogueDao());
        AppExecutors appExecutors = new AppExecutors();
        return MovieCatalogueRepository.getInstance(remoteDataSource, localDataSource, appExecutors);
    }

}
