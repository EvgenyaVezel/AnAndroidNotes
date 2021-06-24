package com.example.myapplication.domain;

import com.example.myapplication.R;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;

import java.util.List;

public class NotesRepositoryImp implements NotesRepository{
    protected List<Note> result;
    @Override
    public List<Note> getNotes() {
        result = new ArrayList<>();

        result.add(new Note("Заметка 1", Calendar.getInstance().getTime(),  "Соержимое заметки 1..."));
        result.add(new Note("Заметка 2", Calendar.getInstance().getTime(),  "Соержимое заметки 2..."));
        result.add(new Note("Заметка 3", Calendar.getInstance().getTime(),  "Соержимое заметки 3..."));

        return result;
    }

    @Override
    public void addNote(Note note) {
        result.add(note);
    }
    @Override
    public void deleteNote(Note note) {
        result.remove(note);
    }
}
