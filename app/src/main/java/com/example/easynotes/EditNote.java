package com.example.easynotes;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;


public class EditNote extends AppCompatActivity {
    private static final String LOG_TAG = EditNote.class.getSimpleName();

    private EditText noteNameEditText;
    private EditText noteContentEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        noteNameEditText = (EditText) findViewById(R.id.noteName);
        noteContentEditText = (EditText) findViewById(R.id.noteContent);

        String noteName = getIntent().getStringExtra("EXTRA_NOTE_NAME");
        String noteContent = getIntent().getStringExtra("EXTRA_NOTE_CONTENT");

        noteNameEditText.setText(noteName);
        noteContentEditText.setText(noteContent);
    }

    public void editNote(View view) {
        String editedNoteName = noteNameEditText.getText().toString();
        String editedNoteContent = noteContentEditText.getText().toString();
        Log.d(LOG_TAG, editedNoteName);
        Log.d(LOG_TAG, editedNoteContent);

    }
}