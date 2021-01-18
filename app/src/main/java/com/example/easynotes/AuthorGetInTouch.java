package com.example.easynotes;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;


public class AuthorGetInTouch extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String GITHUB_URL = "https://github.com/kamilagalka";
    private static final String LINKEDIN_URL = "https://www.linkedin.com/in/kamilagalka/";

    // TODO: Rename and change types of parameters

    public AuthorGetInTouch() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_author_get_in_touch, container, false);
        Button gh = view.findViewById(R.id.gh);
        gh.setOnClickListener(this::redirectToGH);

        Button linkedin = view.findViewById(R.id.linkedin);
        linkedin.setOnClickListener(this::goToLinkedin);
        return view;
    }

    public void redirectToGH(View view){
        openLink(GITHUB_URL);
    }

    public void goToLinkedin(View view){
        openLink(LINKEDIN_URL);
    }

    private void openLink(String link){
        Uri uri = Uri.parse(link);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
}