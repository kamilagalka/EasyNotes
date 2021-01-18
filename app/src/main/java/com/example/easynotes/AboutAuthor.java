package com.example.easynotes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class AboutAuthor extends AppCompatActivity {
    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_author);

        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.about_author_fragment, new AuthorBio())
                .commit();
    }

    public void goBack(View view) {
        Log.d(LOG_TAG, "Button 'GO BACK' clicked!");
        finish();
    }

    public void showAuthorBio(View view) {
        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.about_author_fragment, new AuthorBio())
                .commit();
    }

    public void showGetInTouch(View view) {
        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.about_author_fragment, new AuthorGetInTouch())
                .commit();
    }
}