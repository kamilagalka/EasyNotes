package com.example.easynotes;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;


public class EditNote extends SaveDiscardActivity {
    private static final String LOG_TAG = EditNote.class.getSimpleName();
    String noteId;
    String noteName;
    String noteContent;
    private EditText noteNameEditText;
    private EditText noteContentEditText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        noteNameEditText = (EditText) findViewById(R.id.noteName);
        noteContentEditText = (EditText) findViewById(R.id.noteContent);

        noteId = getIntent().getStringExtra("EXTRA_NOTE_ID");
        noteName = getIntent().getStringExtra("EXTRA_NOTE_NAME");
        noteContent = getIntent().getStringExtra("EXTRA_NOTE_CONTENT");

        noteNameEditText.setText(noteName);
        noteContentEditText.setText(noteContent);
    }



    @Override
    public void saveNote() {
        DatabaseHelper myDB = new DatabaseHelper(EditNote.this);
        noteName = noteNameEditText.getText().toString().trim();
        noteContent = noteContentEditText.getText().toString().trim();
        myDB.updateData(noteId, noteName, noteContent);

//        String editedNoteName = noteNameEditText.getText().toString();
//        String editedNoteContent = noteContentEditText.getText().toString();
        Log.d(LOG_TAG, noteId + "");
        Log.d(LOG_TAG, noteName);
        Log.d(LOG_TAG, noteContent);
        finish();
    }

    @Override
    public void discardNote() {
        Log.d(LOG_TAG, "Discarding edition");
        finish();
    }
}