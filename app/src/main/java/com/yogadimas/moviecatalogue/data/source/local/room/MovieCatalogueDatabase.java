package com.yogadimas.moviecatalogue.data.source.local.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.yogadimas.moviecatalogue.data.source.local.entity.movie.MovieEntity;
import com.yogadimas.moviecatalogue.data.source.local.entity.tvshow.TvShowEntity;

@Database(entities = {MovieEntity.class, TvShowEntity.class}, version = 1, exportSchema = false)
public abstract class MovieCatalogueDatabase extends RoomDatabase {
    private static volatile MovieCatalogueDatabase INSTANCE;

    public static MovieCatalogueDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (MovieCatalogueDatabase.class) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        MovieCatalogueDatabase.class, "MovieCatalogue.db")
                        .build();
            }
        }
        return INSTANCE;
    }

    public abstract MovieCatalogueDao movieCatalogueDao();
}
