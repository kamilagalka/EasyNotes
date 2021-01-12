package com.example.easynotes;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
    private Activity activity;
    private Context context;


    public NotesAdapter(Activity activity, Context context, LinkedList<HashMap<String, String>> notes) {
        this.activity = activity;
        this.notes = notes;
        this.context = context;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View mItemView = inflater.inflate(R.layout.noteslist_item,
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
        private final Context context;

        public NoteViewHolder(View itemView, NotesAdapter adapter) {
            super(itemView);
            context = itemView.getContext();
            noteItemView = itemView.findViewById(R.id.note);
            this.mAdapter = adapter;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int mPosition = getLayoutPosition();
            HashMap<String, String> element = notes.get(mPosition);
            Intent intent = new Intent(context, EditNote.class);
            intent.putExtra("EXTRA_NOTE_ID", element.get("noteId"));
            intent.putExtra("EXTRA_NOTE_NAME", element.get("noteName"));
            intent.putExtra("EXTRA_NOTE_CONTENT", element.get("noteContent"));
            activity.startActivityForResult(intent, 1);
//            mAdapter.notifyItemChanged(mPosition);
//            newElement.put("noteName", "Clicked!");
//            newElement.put("noteContent", "Content clicked!");
//            notes.set(mPosition, newElement);
//            mAdapter.notifyDataSetChanged();
        }
    }
}
