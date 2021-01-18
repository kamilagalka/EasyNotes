package com.example.easynotes;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.HashMap;
import java.util.LinkedList;

public class MyNotes extends AppCompatActivity {
    private static final String LOG_TAG = MyNotes.class.getSimpleName();
    private static LinkedList<HashMap<String, String>> notes = new LinkedList<>();
   DatabaseHelper myDB;
    private RecyclerView recyclerView;
    private NotesAdapter notesAdapter;

    ConstraintLayout constraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_notes);
        myDB = new DatabaseHelper(MyNotes.this);

        constraintLayout = findViewById(R.id.constraint_layout);

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

        enableSwipeToDeleteAndUndo();
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


    private void enableSwipeToDeleteAndUndo() {
        SwipeToDeleteCallback swipeToDeleteCallback = new SwipeToDeleteCallback(this) {
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                final int position = viewHolder.getAdapterPosition();
                final HashMap<String, String> item = notesAdapter.getData().get(position);
                Log.i(LOG_TAG, "removeItem"+position);
                notesAdapter.removeItem(position);


                Snackbar snackbar = Snackbar
                        .make(constraintLayout, "Your note was removed.", Snackbar.LENGTH_LONG);
                snackbar.setAction("UNDO", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.i(LOG_TAG, "restore Item "+position);

                        notesAdapter.restoreItem(item, position);
                        recyclerView.scrollToPosition(position);
                    }
                });

                snackbar.setActionTextColor(Color.WHITE);
                snackbar.setDuration(3000);
                snackbar.show();

            }
        };

        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeToDeleteCallback);
        itemTouchhelper.attachToRecyclerView(recyclerView);
    }
}