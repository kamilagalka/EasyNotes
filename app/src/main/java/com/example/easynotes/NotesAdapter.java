package com.example.easynotes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;
import java.util.LinkedList;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NoteViewHolder> {
    private final LinkedList<HashMap<String, String>> notes;
    private LayoutInflater mInflater;


    public NotesAdapter(Context context, LinkedList<HashMap<String, String>> notes) {
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
        String mCurrent = notes.get(position).get("noteName");
        if (mCurrent.length() > 18){
            mCurrent = mCurrent.substring(0, 15) + "...";
        }
        holder.noteItemView.setText(mCurrent);
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public class NoteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final TextView noteItemView;
        final NotesAdapter mAdapter;

        public NoteViewHolder(View itemView, NotesAdapter adapter) {
            super(itemView);
            noteItemView = itemView.findViewById(R.id.note);
            this.mAdapter = adapter;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int mPosition = getLayoutPosition();
            HashMap<String, String> element = notes.get(mPosition);
            HashMap<String, String> newElement = new HashMap<>();
            newElement.put("noteName", "Clicked!");
            newElement.put("noteContent", "Content clicked!");
            notes.set(mPosition, newElement);
            mAdapter.notifyDataSetChanged();
        }
    }
}
