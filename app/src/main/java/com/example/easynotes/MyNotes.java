package com.example.easynotes;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class MyNotes extends AppCompatActivity {
    private static final String LOG_TAG = MyNotes.class.getSimpleName();
    private static LinkedList<HashMap<String, String>> notes = new LinkedList<>();
   DatabaseHelper myDB;
    private RecyclerView recyclerView;
    private NotesAdapter notesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_notes);
        myDB = new DatabaseHelper(MyNotes.this);

        Cursor cursor = myDB.readAllData();
        getNotes(cursor);
        if (notes != null) {
            Log.d(LOG_TAG, notes.toString());
        }


//        String noteName = getIntent().getStringExtra("EXTRA_NOTE_NAME");
//        String noteContent = getIntent().getStringExtra("EXTRA_NOTE_CONTENT");

        recyclerView = findViewById(R.id.recycler_view);


        notesAdapter = new NotesAdapter(MyNotes.this, this, notes);
        recyclerView.setAdapter(notesAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        Log.d(LOG_TAG, "in on activity result");
        Log.d(LOG_TAG, requestCode+"");
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            Log.d(LOG_TAG, "recreate");
            recreate();
        }
    }

    private void mockNotes(int numOfNotes) {
        for (int i = 0; i < numOfNotes; i++) {
            HashMap<String, String> note = new HashMap<>();
            note.put("noteName","my note " + i);
            note.put("noteContent", "bla bla bla note bla bla bla note bla bla bla note bla bla bla note bla bla bla note bla bla bla note bla bla bla note bla bla bla note "+ i);
            notes.add(note);
        }
    }

    public void addNote(View view) {
        Intent intent = new Intent(this, AddNote.class);
        startActivityForResult(intent, 1);
    }

    public void getNotes(Cursor cursor){
        Log.d(LOG_TAG, "getting notes");
        notes = new LinkedList<>();
//        mockNotes(2);
        while (cursor.moveToNext()){
            HashMap<String, String> note = new HashMap<String, String>();
            note.put("noteId", String.valueOf(cursor.getInt(0)));
            note.put("noteName", cursor.getString(1));
            note.put("noteContent", cursor.getString(2));

            notes.add(note);
        }
        Log.d(LOG_TAG, "getting notes finished");

    }
}