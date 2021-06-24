package com.example.myapplication.domain;

import java.util.List;

public interface NotesRepository {
    List<Note> getNotes();
    void addNote(Note note);
    void deleteNote(Note note);
}
