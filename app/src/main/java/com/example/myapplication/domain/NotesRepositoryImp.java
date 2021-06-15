package com.example.myapplication.domain;

import com.example.myapplication.R;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;

import java.util.List;

public class NotesRepositoryImp implements NotesRepository{

    @Override
    public List<Note> getNotes() {
        List<Note> result = new ArrayList<>();

        result.add(new Note("Заметка 1", Calendar.getInstance().getTime(),  "Соержимое заметки 1..."));
        result.add(new Note("Заметка 2", Calendar.getInstance().getTime(),  "Соержимое заметки 2..."));
        result.add(new Note("Заметка 3", Calendar.getInstance().getTime(),  "Соержимое заметки 3..."));

        return result;
    }
}
