package com.example.walterorando.citam.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.walterorando.citam.R;

/**
 * Created by Walter Orando on 3/29/2017.
 */

public class CitamSplashScreen extends Activity {

    private static int splashscreentimer = 3000;

    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_citam_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i =  new Intent(CitamSplashScreen.this, MainActivity.class);
                startActivity(i);

                finish();
            }
        },splashscreentimer);

    }

}
