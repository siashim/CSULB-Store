package com.example.csulbstore;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.ActivityOptions;
import android.content.Intent;
import android.media.Image;
import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import android.os.Bundle;
import android.os.Handler;

//import java.util.logging.Handler;

public class SplashPageActivity extends ActionBarActivity {

    // Splash screen timer
    private static int SPLASH_TIME_OUT = 4000;

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar actionBar = getActionBar();
        actionBar.hide();

        setContentView(R.layout.activity_splash_page);

        final ImageView iv = (ImageView) findViewById(R.id.imageView2);
        final Animation an = AnimationUtils.loadAnimation(getBaseContext(),R.anim.rotate_animator);
        final Animation an2 = AnimationUtils.loadAnimation(getBaseContext(),R.anim.abc_fade_out);

        iv.startAnimation(an);
        an.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onAnimationEnd(Animation animation) {
                iv.startAnimation(an2);
                finish();

                Bundle translateBundle =
                        ActivityOptions.makeCustomAnimation(SplashPageActivity.this, R.anim.abc_fade_in, R.anim.abc_fade_out).toBundle();

                Intent i = new Intent(SplashPageActivity.this, MainActivity.class);
                startActivity(i,translateBundle);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });



    }
}
