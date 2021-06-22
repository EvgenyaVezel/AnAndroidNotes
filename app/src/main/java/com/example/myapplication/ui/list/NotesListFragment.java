package com.example.myapplication.ui.list;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.domain.Note;
import com.example.myapplication.domain.NotesRepository;
import com.example.myapplication.domain.NotesRepositoryImp;

import java.util.List;

public class NotesListFragment extends Fragment {

    public interface OnNoteClicked {
        void onNoteClicked(Note note);
    }

    private NotesRepository notesRepository;

    private OnNoteClicked onNoteClicked;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof OnNoteClicked) {
            onNoteClicked = (OnNoteClicked) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        onNoteClicked = null;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        notesRepository = new NotesRepositoryImp();
        return inflater.inflate(R.layout.fragment_notes_list, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LinearLayout noteList = view.findViewById(R.id.list_notes);
        List<Note> notes = notesRepository.getNotes();

        for (Note note : notes) {

            View itemView = LayoutInflater.from(requireContext()).inflate(R.layout.item_note, noteList, false);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onNoteClicked != null) {
                        onNoteClicked.onNoteClicked(note);
                    }
                }
            });

            TextView noteHead = itemView.findViewById(R.id.item_note);
            noteHead.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    PopupMenu popupMenu = new PopupMenu(requireContext(), v);
                    requireActivity().getMenuInflater().inflate(R.menu.menu_list_fragment, popupMenu.getMenu());

                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            if (item.getItemId() == R.id.option_edit) {
                                Toast.makeText(requireContext(), "note edited", Toast.LENGTH_SHORT).show();
                                return true;
                            }
                            if (item.getItemId() == R.id.option_delete) {
                                Toast.makeText(requireContext(), "note deleted", Toast.LENGTH_SHORT).show();
                                return true;
                            }

                            return false;
                        }
                    });
                    popupMenu.show();
                    return false;
                }
            });

            noteHead.setText(note.getHead());
            noteList.addView(itemView);

        }

    }

}
