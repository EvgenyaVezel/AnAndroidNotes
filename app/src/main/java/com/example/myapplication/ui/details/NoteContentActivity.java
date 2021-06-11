package com.example.myapplication.ui.details;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.myapplication.R;
import com.example.myapplication.domain.Note;
import com.example.myapplication.ui.list.NotesListFragment;

public class NoteContentActivity extends AppCompatActivity{

    public static final String ARG_NOTE = "ARG_NOTE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_content);

        if(savedInstanceState == null){

            Note note = getIntent().getParcelableExtra(ARG_NOTE);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, NoteContentFragment.getInstance(note))
                    .commit();
        }
    }


}