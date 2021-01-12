package com.example.easynotes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class AddNote extends AppCompatActivity {

    private static final String LOG_TAG = AddNote.class.getSimpleName();
    String noteId;
    String noteName;
    String noteContent;
    private EditText noteNameEditText;
    private EditText noteContentEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        noteNameEditText = (EditText) findViewById(R.id.addNoteName);
        noteContentEditText = (EditText) findViewById(R.id.addNoteContent);

        noteNameEditText.setText("");
        noteContentEditText.setText("");
    }

    public void addNote(View view) {
        DatabaseHelper myDB = new DatabaseHelper(AddNote.this);
        noteName = noteNameEditText.getText().toString().trim();
        noteContent = noteContentEditText.getText().toString().trim();
        myDB.addNote(noteName, noteContent);

//        String editedNoteName = noteNameEditText.getText().toString();
//        String editedNoteContent = noteContentEditText.getText().toString();
        Log.d(LOG_TAG, noteName);
        Log.d(LOG_TAG, noteContent);
        finish();
    }
}