package com.example.easynotes;
import android.content.Intent;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class SplashScreen extends AppCompatActivity {
    private static final String LOG_TAG = SplashScreen.class.getSimpleName();
    private static final int SPLASH_SHOW_TIME = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        ActivityStarter starter = new ActivityStarter();
        starter.start();
    }

    private class ActivityStarter extends Thread {

        @Override
        public void run() {
            try {
                Thread.sleep(SPLASH_SHOW_TIME);
            } catch (Exception e){
                Log.e(LOG_TAG,"Thread.sleep failed");
            }

            Intent intent = new Intent(SplashScreen.this, MainActivity.class);
            SplashScreen.this.startActivity(intent);
            SplashScreen.this.finish();
        }
    }
}