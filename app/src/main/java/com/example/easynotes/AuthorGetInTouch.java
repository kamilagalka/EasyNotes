package com.example.easynotes;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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


    public static AuthorGetInTouch newInstance(String param1, String param2) {
        AuthorGetInTouch fragment = new AuthorGetInTouch();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_author_get_in_touch, container, false);
        ImageView gh = view.findViewById(R.id.gh);
        gh.setOnClickListener(this::redirectToGH);

        Button b2 = view.findViewById(R.id.settings_aboutAuthor_linkedin_button);
        b2.setOnClickListener(this::goToLinkedin);
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