package com.yogadimas.moviecatalogue.ui.home;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.yogadimas.moviecatalogue.R;
import com.yogadimas.moviecatalogue.data.source.local.entity.movie.MovieEntity;
import com.yogadimas.moviecatalogue.data.source.local.entity.tvshow.TvShowEntity;
import com.yogadimas.moviecatalogue.utils.DataDummy;
import com.yogadimas.moviecatalogue.utils.EspressoIdlingResource;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class MainActivityTest {

    private final ArrayList<MovieEntity> dummyMovie = DataDummy.generateDummyMovies();
    private final ArrayList<TvShowEntity> dummyTvShow = DataDummy.generateDummyTvShow();

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule = new ActivityScenarioRule<>(MainActivity.class);

    @Before
    public void setup() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource());
    }

    @After
    public void tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource());
    }

    @Test
    public void loadMovies() {
        int moviesSize = dummyMovie.size() + 16;
        onView(withId(R.id.rv_movies)).check(matches(isDisplayed()));
        onView(withId(R.id.rv_movies)).perform(RecyclerViewActions.scrollToPosition(moviesSize));
    }

    @Test
    public void detailMoviesThenFavoriteFeature() {
        onView(withId(R.id.rv_movies)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onViewCheckDetailMovie();
        addFavoriteMovies();
        removeFavoriteMovies();
    }


    private void addFavoriteMovies() {
        onView(withId(R.id.action_favorite)).perform(click());
        onView(isRoot()).perform(ViewActions.pressBack());
    }

    private void removeFavoriteMovies() {
        onView(withId(R.id.list_favorite)).perform(click());
        onView(withId(R.id.rv_movies)).check(matches(isDisplayed()));
        onView(withId(R.id.rv_movies)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onViewCheckDetailMovie();
        onView(withId(R.id.action_favorite)).perform(click());
        onView(isRoot()).perform(ViewActions.pressBack());
        AtomicInteger itemCount = new AtomicInteger();
        activityRule.getScenario().onActivity(activity -> {
            RecyclerView recyclerView = activity.findViewById(R.id.rv_movies);
            itemCount.set(recyclerView.getAdapter().getItemCount());
        });
        if (itemCount.get() == 0) {
            onView(withId(R.id.text_not_yet)).check(matches(isDisplayed()));
            onView(withId(R.id.text_not_yet)).check(matches(withText(R.string.not_yet_favorite)));
        }
    }

    private void onViewCheckDetailMovie() {
        onView(withId(R.id.text_score)).check(matches(isDisplayed()));
        onView(withId(R.id.text_score)).check(matches(withText(String.valueOf(dummyMovie.get(0).getVoteAverage()))));
        onView(withId(R.id.text_release)).check(matches(isDisplayed()));
        onView(withId(R.id.text_release)).check(matches(withText(dummyMovie.get(0).getRelease())));
        onView(withId(R.id.text_title)).check(matches(isDisplayed()));
        onView(withId(R.id.text_title)).check(matches(withText(dummyMovie.get(0).getTitle())));
        onView(withId(R.id.text_overview)).check(matches(isDisplayed()));
        onView(withId(R.id.text_overview)).check(matches(withText(dummyMovie.get(0).getOverview())));
    }

    @Test
    public void loadTvShow() {
        int tvShowSize = dummyMovie.size() + 16;
        onView(withText("Tv Show")).perform(click());
        onView(withId(R.id.rv_tv_show)).check(matches(isDisplayed()));
        onView(withId(R.id.rv_tv_show)).perform(RecyclerViewActions.scrollToPosition(tvShowSize));
    }

    @Test
    public void detailTvShowThenFavoriteFeature() {
        onView(withText("Tv Show")).perform(click());
        onView(withId(R.id.rv_tv_show)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onViewCheckDetailTvShow();
        addFavoriteTvShow();
        removeFavoriteTvShow();
    }

    private void addFavoriteTvShow() {
        onView(withId(R.id.action_favorite)).perform(click());
        onView(isRoot()).perform(ViewActions.pressBack());
    }


    private void removeFavoriteTvShow() {
        onView(withId(R.id.list_favorite)).perform(click());
        onView(withText("Tv Show")).perform(click());
        onView(withId(R.id.rv_tv_show)).check(matches(isDisplayed()));
        onView(withId(R.id.rv_tv_show)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onViewCheckDetailTvShow();
        onView(withId(R.id.action_favorite)).perform(click());
        onView(isRoot()).perform(ViewActions.pressBack());
        AtomicInteger itemCount = new AtomicInteger();
        activityRule.getScenario().onActivity(activity -> {
            RecyclerView recyclerView = activity.findViewById(R.id.rv_tv_show);
            itemCount.set(recyclerView.getAdapter().getItemCount());
        });
        if (itemCount.get() == 0) {
            onView(withId(R.id.text_not_yet)).check(matches(isDisplayed()));
            onView(withId(R.id.text_not_yet)).check(matches(withText(R.string.not_yet_favorite)));
        }
    }

    private void onViewCheckDetailTvShow() {
        onView(withId(R.id.text_score)).check(matches(isDisplayed()));
        onView(withId(R.id.text_score)).check(matches(withText(String.valueOf(dummyTvShow.get(0).getVoteAverage()))));
        onView(withId(R.id.text_first_air_date)).check(matches(isDisplayed()));
        onView(withId(R.id.text_first_air_date)).check(matches(withText(dummyTvShow.get(0).getFirstAirDate())));
        onView(withId(R.id.text_title)).check(matches(isDisplayed()));
        onView(withId(R.id.text_title)).check(matches(withText(dummyTvShow.get(0).getTitle())));
        onView(withId(R.id.text_overview)).check(matches(isDisplayed()));
        onView(withId(R.id.text_overview)).check(matches(withText(dummyTvShow.get(0).getOverview())));
    }
}