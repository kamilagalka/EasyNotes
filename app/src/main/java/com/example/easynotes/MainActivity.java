package com.example.easynotes;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void letsGo(View view) {
        Log.d(LOG_TAG, "Button 'LETS GO' clicked!");
    }

    public void goToAboutAuthor(View view) {
        Log.d(LOG_TAG, "Button 'ABOUT AUTHOR' clicked!");
        Intent intent = new Intent(this, AboutAuthor.class);
        startActivity(intent);
    }

    public void goToInfo(View view) {
        Log.d(LOG_TAG, "Button '?' clicked!");
    }
}