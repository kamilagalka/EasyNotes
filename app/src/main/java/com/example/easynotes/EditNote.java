package com.example.easynotes;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;


public class EditNote extends AppCompatActivity {
    private static final String LOG_TAG = EditNote.class.getSimpleName();
    int noteId;
    String noteName;
    String noteContent;
    private EditText noteNameEditText;
    private EditText noteContentEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        noteNameEditText = (EditText) findViewById(R.id.noteName);
        noteContentEditText = (EditText) findViewById(R.id.noteContent);

        noteId = getIntent().getIntExtra("EXTRA_NOTE_ID", 0);
        noteName = getIntent().getStringExtra("EXTRA_NOTE_NAME");
        noteContent = getIntent().getStringExtra("EXTRA_NOTE_CONTENT");

        noteNameEditText.setText(noteName);
        noteContentEditText.setText(noteContent);
    }

    public void editNote(View view) {
        DatabaseHelper myDB = new DatabaseHelper(EditNote.this);
        noteName = noteNameEditText.getText().toString().trim();
        noteContent = noteContentEditText.getText().toString().trim();
        myDB.updateData(noteId+1, noteName, noteContent);

//        String editedNoteName = noteNameEditText.getText().toString();
//        String editedNoteContent = noteContentEditText.getText().toString();
        Log.d(LOG_TAG, noteId+"");
        Log.d(LOG_TAG, noteName);
        Log.d(LOG_TAG, noteContent);
        finish();
    }

    public void discardEdition(View view) {
        Log.d(LOG_TAG, "Discarding edition");
        finish();
    }
}