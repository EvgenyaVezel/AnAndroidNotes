package com.example.myapplication.ui.list;


import android.view.LayoutInflater;

import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.domain.Note;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NoteViewHolder> {

    private final List<Note> notes = new ArrayList<>();
    private OnNoteClickListener listener;
    private OnToolbarClickListener onToolbarClickListener;
    private OnNoteLongClickListener listenerLong;


    public OnNoteClickListener getListener() {
        return listener;
    }

    public void setListener(OnNoteClickListener listener) {
        this.listener = listener;
    }

    public void setData(List<Note> toSet) {
        notes.clear();
        notes.addAll(toSet);
    }


    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);
        return new NoteViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note note = notes.get(position);
        holder.bind(note);
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public void setListenerLong(OnNoteLongClickListener listenerLong) {
        this.listenerLong = listenerLong;
    }

    public void setOnToolbarClickListener(OnToolbarClickListener onToolbarClickListener) {
        this.onToolbarClickListener = onToolbarClickListener;
    }


    public interface OnNoteClickListener {
        void onNoteClickedListener(@NonNull Note note);
    }

    public interface OnNoteLongClickListener {
        boolean onNoteLongClickedListener(@NonNull Note note);
    }

    public interface OnToolbarClickListener {
        boolean onToolbarClicked(@NonNull Note note, Toolbar toolbar);
    }

    class NoteViewHolder extends RecyclerView.ViewHolder {
        private final TextView noteTitle;
        private final TextView noteDate;
        private final Toolbar toolbar;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            noteTitle = itemView.findViewById(R.id.note_title);
            noteDate = itemView.findViewById(R.id.note_date);
            toolbar = itemView.findViewById(R.id.card_toolbar);

            itemView.setOnClickListener(v -> {
                if (getListener() != null) {
                    getListener().onNoteClickedListener(notes.get(getAdapterPosition()));
                }
            });

            itemView.setOnLongClickListener(v -> {
                if (listenerLong != null) {
                    listenerLong.onNoteLongClickedListener(notes.get(getAdapterPosition()));
                    return true;
                }
                return false;
            });

            toolbar.setOnMenuItemClickListener(item -> {
                if (onToolbarClickListener != null) {
                    onToolbarClickListener.onToolbarClicked(notes.get(getAdapterPosition()), toolbar);
                    return true;
                }
                return false;
            });

        }

        public void bind(Note note) {
            noteTitle.setText(note.getHead());
            noteDate.setText(String.valueOf(note.getDate()));
        }

    }
}
