package com.yogadimas.moviecatalogue.utils;


import com.yogadimas.moviecatalogue.data.source.local.entity.movie.MovieEntity;
import com.yogadimas.moviecatalogue.data.source.local.entity.tvshow.TvShowEntity;
import com.yogadimas.moviecatalogue.data.source.remote.responses.movie.detail.DetailMovieResponse;
import com.yogadimas.moviecatalogue.data.source.remote.responses.tvshow.detail.DetailTvShowResponse;

import java.util.ArrayList;

public class DataDummy {

    public static ArrayList<MovieEntity> generateDummyMovies() {
        ArrayList<MovieEntity> movieEntities = new ArrayList<>();

        movieEntities.add(new MovieEntity(
                337404,
                "Cruella",
                8.4,
                "In 1970s London amidst the punk rock revolution, a young grifter named Estella is determined to make a name for herself with her designs. She befriends a pair of young thieves who appreciate her appetite for mischief, and together they are able to build a life for themselves on the London streets. One day, Estella’s flair for fashion catches the eye of the Baroness von Hellman, a fashion legend who is devastatingly chic and terrifyingly haute. But their relationship sets in motion a course of events and revelations that will cause Estella to embrace her wicked side and become the raucous, fashionable and revenge-bent Cruella.",
                "2021-05-26",
                "https://image.tmdb.org/t/p/w500//wToO8opxkGwKgSfJ1JK8tGvkG6U.jpg",
                null));
        movieEntities.add(new MovieEntity(
                399579,
                "Alita: Battle Angel",
                7.2,
                "When Alita awakens with no memory of who she is in a future world she does not recognize, she is taken in by Ido, a compassionate doctor who realizes that somewhere in this abandoned cyborg shell is the heart and soul of a young woman with an extraordinary past.",
                "2019-01-31",
                "https://image.tmdb.org/t/p/w500/xRWht48C2V8XNfzvPehyClOvDni.jpg",
                null));
        movieEntities.add(new MovieEntity(
                297802,
                "Aquaman",
                6.9,
                "Once home to the most advanced civilization on Earth, Atlantis is now an underwater kingdom ruled by the power-hungry King Orm. With a vast army at his disposal, Orm plans to conquer the remaining oceanic people and then the surface world. Standing in his way is Arthur Curry, Orm's half-human, half-Atlantean brother and true heir to the throne.",
                "2018-07-06",
                "https://image.tmdb.org/t/p/w500/xLPffWMhMj1l50ND3KchMjYoKmE.jpg",
                null));

        return movieEntities;
    }

    public static DetailMovieResponse remoteGenerateDummyDetailMovie() {
        return new DetailMovieResponse(
                337404,
                "Cruella",
                8.4,
                "In 1970s London amidst the punk rock revolution, a young grifter named Estella is determined to make a name for herself with her designs. She befriends a pair of young thieves who appreciate her appetite for mischief, and together they are able to build a life for themselves on the London streets. One day, Estella’s flair for fashion catches the eye of the Baroness von Hellman, a fashion legend who is devastatingly chic and terrifyingly haute. But their relationship sets in motion a course of events and revelations that will cause Estella to embrace her wicked side and become the raucous, fashionable and revenge-bent Cruella.",
                "2021-05-26",
                "https://image.tmdb.org/t/p/w500//wToO8opxkGwKgSfJ1JK8tGvkG6U.jpg");
    }

    public static MovieEntity generateDummyDetailMovie(boolean favorite) {
        MovieEntity detailMovie = new MovieEntity(
                337404,
                "Cruella",
                8.4,
                "In 1970s London amidst the punk rock revolution, a young grifter named Estella is determined to make a name for herself with her designs. She befriends a pair of young thieves who appreciate her appetite for mischief, and together they are able to build a life for themselves on the London streets. One day, Estella’s flair for fashion catches the eye of the Baroness von Hellman, a fashion legend who is devastatingly chic and terrifyingly haute. But their relationship sets in motion a course of events and revelations that will cause Estella to embrace her wicked side and become the raucous, fashionable and revenge-bent Cruella.",
                "2021-05-26",
                "https://image.tmdb.org/t/p/w500//wToO8opxkGwKgSfJ1JK8tGvkG6U.jpg",
                null);
        detailMovie.setFavorite(favorite);
        return detailMovie;
    }

    public static ArrayList<TvShowEntity> generateDummyTvShow() {
        ArrayList<TvShowEntity> tvShowEntities = new ArrayList<>();

        tvShowEntities.add(new TvShowEntity(
                1402,
                "The Walking Dead",
                8.1,
                "Sheriff's deputy Rick Grimes awakens from a coma to find a post-apocalyptic world dominated by flesh-eating zombies. He sets out to find his family and encounters many other survivors along the way.",
                "2010-10-31",
                "https://image.tmdb.org/t/p/w500///w21lgYIi9GeUH5dO8l3B9ARZbCB.jpg",
                null));
        tvShowEntities.add(new TvShowEntity(
                79501, "Doom Patrol",
                7.6,
                "The Doom Patrol’s members each suffered horrible accidents that gave them superhuman abilities — but also left them scarred and disfigured. Traumatized and downtrodden, the team found purpose through The Chief, who brought them together to investigate the weirdest phenomena in existence — and to protect Earth from what they find.",
                "2019-02-15",
                "https://image.tmdb.org/t/p/w500/drlfSCIlMKrEeMPhi8pqY4xGxj.jpg",
                null));
        tvShowEntities.add(new TvShowEntity(
                102903, "Control Z",
                8.3,
                "When a hacker begins releasing students' secrets to the entire high school, the socially isolated but observant Sofía works to uncover his/her identity.",
                "2020-05-22",
                "https://image.tmdb.org/t/p/w500/xeDWABNinefdZLnwQ0vJcbpqSVr.jpg",
                null));

        return tvShowEntities;
    }

    public static DetailTvShowResponse remoteGenerateDummyDetailTvShow() {
        return new DetailTvShowResponse(
                1402,
                "The Walking Dead",
                8.1,
                "Sheriff's deputy Rick Grimes awakens from a coma to find a post-apocalyptic world dominated by flesh-eating zombies. He sets out to find his family and encounters many other survivors along the way.",
                "2010-10-31",
                "https://image.tmdb.org/t/p/w500///w21lgYIi9GeUH5dO8l3B9ARZbCB.jpg");
    }


    public static TvShowEntity generateDummyDetailTvShow(boolean favorite) {
        TvShowEntity detailTvShow = new TvShowEntity(
                1402,
                "The Walking Dead",
                8.1,
                "Sheriff's deputy Rick Grimes awakens from a coma to find a post-apocalyptic world dominated by flesh-eating zombies. He sets out to find his family and encounters many other survivors along the way.",
                "2010-10-31",
                "https://image.tmdb.org/t/p/w500///w21lgYIi9GeUH5dO8l3B9ARZbCB.jpg",
                null);
        detailTvShow.setFavorite(favorite);
        return detailTvShow;
    }
}
