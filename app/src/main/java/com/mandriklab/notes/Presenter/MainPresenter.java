package com.mandriklab.notes.Presenter;

import com.mandriklab.notes.Model.Entity.Note;
import com.mandriklab.notes.Model.NoteModel;
import com.mandriklab.notes.View.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class MainPresenter {
    private MainActivity view;
    private NoteModel model;

    public MainPresenter(NoteModel model){
        this.model = model;
    }

    public void attachView(MainActivity mainActivity) {
        view = mainActivity;
    }

    public void detachView() {
        view = null;
    }

    public void viewIsReady() {
        loadNotes();
    }

    public void loadNotes() {
        model.loadNotes(new NoteModel.LoadNotesCallback() {
            @Override
            public void onLoad(List<Note> users) {
                ArrayList<Note> list = new ArrayList<>();
                for(int i=0;i<users.size();i++){
                    list.add(users.get(i));
                }
                view.showNotes(list);
            }
        });
    }
}
