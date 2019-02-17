package com.mandriklab.notes.Presenter;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.mandriklab.notes.Model.Entity.Note;
import com.mandriklab.notes.Model.NoteModel;
import com.mandriklab.notes.View.NoteActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
        final Bundle extras = view.getIntent().getExtras();
        int id;
        if (extras != null) {
            id = extras.getInt("id");
            model.LoadNoteById(id,new NoteModel.LoadNoteByIdCallback() {
                @Override
                public void onLoad(Note note) {
                    view.showNote(note);//Load exist note
                }
            });
        }
        else {
            Note n = new Note();
            Date d = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM hh:mm", Locale.ENGLISH);
            n.setDateCreating(dateFormat.format(d));
            n.setTitle("");
            n.setText("");
            view.showNote(n); // Create new
        }

    }


    public void saveNote(){
        final Bundle extras = view.getIntent().getExtras();
        if (extras != null) {
            updateNote(extras.getInt("id"));// update note if we have id in intent
        }
        else {
            addNote(); // create new if we dont have id in intent
        }
    }

    public void addNote() {
        Note note = view.getNoteData();

        ContentValues cv = new ContentValues(4);
        cv.put("text", note.getText());
        cv.put("title", note.getTitle());
        cv.put("time", note.getDateCreating());
        cv.put("background", note.getBackground());
        model.addNote(cv, new NoteModel.CompleteCallback() {
            @Override
            public void onComplete() {
                view.finish();
            }
        });
    }

    public void updateNote(int id){
        Note note = view.getNoteData();
        ContentValues cv = new ContentValues(5);
        cv.put("title", note.getTitle());
        cv.put("text", note.getText());
        cv.put("time", note.getDateCreating());
        cv.put("background", note.getBackground());
        cv.put("id", id);
        model.updateNote(cv, new NoteModel.CompleteCallback() {
            @Override
            public void onComplete() {
                view.finish();
            }
        });

    }

    public void deleteNote(){
        final Bundle extras = view.getIntent().getExtras();
        int id;
        if (extras != null) {
            id = extras.getInt("id");
            model.deleteNote(id, new NoteModel.CompleteCallback() {
                @Override
                public void onComplete() {
                    view.finish();
                }
            });
        }
        else {
            view.finish();
        }
    }

    public void shareNote(){
        Note note = view.getNoteData();
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, note.getTitle()+" "+ note.getText());
        view.startActivity(sharingIntent);
    }
}
