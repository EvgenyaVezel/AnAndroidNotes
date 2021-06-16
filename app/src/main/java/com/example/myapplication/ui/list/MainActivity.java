package com.example.myapplication.ui.list;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.domain.Note;
import com.example.myapplication.ui.details.NoteContentActivity;
import com.example.myapplication.ui.details.NoteContentFragment;

public class MainActivity extends AppCompatActivity implements NotesListFragment.OnNoteClicked {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public void onNoteClicked(Note note) {
        boolean isLandscape = getResources().getBoolean(R.bool.isLandscape);
        if (isLandscape) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.notes_content_fragment, NoteContentFragment.getInstance(note))
                    .commit();
        }
        else {

            Intent intent = new Intent(this, NoteContentActivity.class);
            intent.putExtra(NoteContentActivity.ARG_NOTE, note);
            startActivity(intent);
        }
    }
}