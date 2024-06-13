package com.yogadimas.moviecatalogue.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.appcompat.app.AppCompatActivity;

import com.yogadimas.moviecatalogue.R;
import com.yogadimas.moviecatalogue.databinding.ActivitySplashScreenBinding;
import com.yogadimas.moviecatalogue.ui.home.MainActivity;

public class SplashScreenActivity extends AppCompatActivity {

    private static final long delay = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivitySplashScreenBinding activitySplashScreenBinding = ActivitySplashScreenBinding.inflate(getLayoutInflater());
        setContentView(activitySplashScreenBinding.getRoot());

        Animation animFade = AnimationUtils.loadAnimation(this, R.anim.anim_fade);
        activitySplashScreenBinding.imageAppLogo.startAnimation(animFade);
        activitySplashScreenBinding.textAppName.startAnimation(animFade);
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            Intent mIntent = new Intent(SplashScreenActivity.this, MainActivity.class);
            startActivity(mIntent);
            finish();
        }, delay);
    }
}