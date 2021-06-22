package com.example.myapplication.ui.list;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.domain.Note;
import com.example.myapplication.domain.NotesRepository;
import com.example.myapplication.domain.NotesRepositoryImp;

import java.util.List;

public class NotesListFragment extends Fragment {

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

        RecyclerView noteList = view.findViewById(R.id.list_notes);
        noteList.setLayoutManager(new LinearLayoutManager(requireContext()));

        List<Note> notes = notesRepository.getNotes();
        NotesAdapter notesAdapter = new NotesAdapter();
        notesAdapter.setData(notes);

        notesAdapter.setListener(note -> {
            if (onNoteClicked != null) {
                onNoteClicked.onNoteClicked(note);
            }
        });

        notesAdapter.setOnToolbarClickListener(new NotesAdapter.OnToolbarClickListener() {
            @Override
            public boolean onToolbarClicked(@NonNull Note note, androidx.appcompat.widget.Toolbar toolbar) {
                toolbar.setOnMenuItemClickListener(new androidx.appcompat.widget.Toolbar.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        int itemId = item.getItemId();
                        if (itemId == R.id.option_edit) {
                            if (onNoteClicked != null) {
                                onNoteClicked.onNoteClicked(note);
                            }
                            return true;
                        } else if (itemId == R.id.option_delete) {
                            Toast.makeText(requireContext(), "deleted note", Toast.LENGTH_SHORT).show();
                            return true;
                        }
                        return false;
                    }
                });
                return false;
            }
        });


        noteList.setAdapter(notesAdapter);

        notesAdapter.notifyDataSetChanged();
    }

    public interface OnNoteClicked {

        void onNoteClicked(Note note);
    }



       /* Toolbar toolbar = view.findViewById(R.id.card_toolbar);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.option_edit) {
                    Toast.makeText(requireContext(), "edited note", Toast.LENGTH_SHORT).show();
                    return true;
                } else if (itemId == R.id.option_delete) {
                    Toast.makeText(requireContext(), "deleted note", Toast.LENGTH_SHORT).show();
                    return true;
                }

                return false;
            }
        });*/

       /* notesAdapter.setListenerLong(note -> {
            PopupMenu popupMenu = new PopupMenu(requireContext(), view);
            popupMenu.inflate(R.menu.menu_list_fragment);
            popupMenu.setOnMenuItemClickListener(item -> {
                int itemId = item.getItemId();
                if (itemId == R.id.option_edit) {
                    onNoteClicked.onNoteClicked(note);
                    return true;
                } else if (itemId == R.id.option_delete) {
                    Toast.makeText(requireContext(), "deleted note", Toast.LENGTH_SHORT).show();
                    return true;
                }

                return false;
            });
            popupMenu.show();
            return false;
        });*/


}
