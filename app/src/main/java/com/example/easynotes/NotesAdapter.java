package com.example.easynotes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NoteViewHolder> {
    private final ArrayList<String> notes;
    private LayoutInflater mInflater;


    public NotesAdapter(Context context, ArrayList<String> notes) {
        mInflater = LayoutInflater.from(context);
        this.notes = notes;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.noteslist_item,
                parent, false);
        return new NoteViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        String mCurrent = notes.get(position);
        holder.noteItemView.setText(mCurrent);
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public class NoteViewHolder extends RecyclerView.ViewHolder {
        public final TextView noteItemView;
        final NotesAdapter mAdapter;

        public NoteViewHolder(View itemView, NotesAdapter adapter) {
            super(itemView);
            noteItemView = itemView.findViewById(R.id.note);
            this.mAdapter = adapter;
        }
    }
}
