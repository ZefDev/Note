package com.mandriklab.notes.Presenter;

import com.mandriklab.notes.Model.Entity.Note;
import com.mandriklab.notes.Model.NoteModel;
import com.mandriklab.notes.View.NoteActivity;

import java.util.List;

public class NotePresenter {

    private NoteActivity view;
    private final NoteModel model;// у меня может сервис какой-то (NoteModel) пока пусть note

    public NotePresenter(NoteModel model){
        this.model = model;
    }

    public void attachView(NoteActivity noteActivity) {
        view = noteActivity;
    }

    public void detachView() {
        view = null;
    }

    public void viewIsReady() {
        loadNote();
    }

    public void loadNote() {
        model.loadNotes(new NoteModel.LoadNotesCallback() {
            @Override
            public void onLoad(List<Note> note) {
                view.showNote(note);
            }
        });
    }
}
