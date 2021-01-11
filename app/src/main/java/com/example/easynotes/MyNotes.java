package com.example.easynotes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class MyNotes extends AppCompatActivity {
    private static ArrayList<String> notes = new ArrayList<>();
    private RecyclerView recyclerView;
    private NotesAdapter notesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_notes);

        notes = mockNotes(30);

        recyclerView = findViewById(R.id.recycler_view);
        notesAdapter = new NotesAdapter(this, notes);
        recyclerView.setAdapter(notesAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private ArrayList<String> mockNotes(int numOfNotes) {
        ArrayList<String> mockedNotes = new ArrayList<>();
        for (int i=0;i<numOfNotes;i++){
            mockedNotes.add("Note number " + i);
        }
        return mockedNotes;
    }
}