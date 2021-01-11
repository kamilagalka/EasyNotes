package com.example.easynotes;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class MyNotes extends AppCompatActivity {
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private static LinkedList<HashMap<String, String>> notes = new LinkedList<>();
    private RecyclerView recyclerView;
    private NotesAdapter notesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_notes);

        notes = mockNotes(3);

        recyclerView = findViewById(R.id.recycler_view);
        notesAdapter = new NotesAdapter(this, notes);
        recyclerView.setAdapter(notesAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private LinkedList<HashMap<String, String>> mockNotes(int numOfNotes) {
        LinkedList<HashMap<String, String>> mockedNotes = new LinkedList<>();
        for (int i = 0; i < numOfNotes; i++) {
            HashMap<String, String> note = new HashMap<>();
            note.put("noteName","my note " + i);
            note.put("noteContent", "bla bla bla note "+ i);
            mockedNotes.add(note);
        }
        return mockedNotes;
    }

    public void addNote(View view) {
        int wordListSize = notes.size();
        HashMap<String, String> note = new HashMap<>();
        note.put("noteName","My new note");
        note.put("noteContent", "Content of my new note!");
        notes.addLast(note);
        recyclerView.getAdapter().notifyItemInserted(wordListSize);
        recyclerView.smoothScrollToPosition(wordListSize);
    }
}