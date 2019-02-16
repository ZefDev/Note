package com.mandriklab.notes.Model;

import android.content.ContentValues;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.mandriklab.notes.Model.DAO.NoteDAO;
import com.mandriklab.notes.Model.Entity.Note;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class NoteModel implements NoteDAO {

    private NoteDAO noteDao;

    public NoteModel(Context context) {
        noteDao = AppDatabase.getInstance(context).siteDao();
    }

    public void loadNotes(LoadNotesCallback callback) {
        LoadNotes loadNotes = new LoadNotes(callback);
        loadNotes.execute();
    }

    public void addNote(ContentValues contentValues, CompleteCallback callback) {
        AddNote addNote = new AddNote(callback);
        addNote.execute(contentValues);
    }

    public void LoadNoteById(int id, LoadNoteByIdCallback callback){
        LoadNoteById loadNoteById = new LoadNoteById(callback);
        loadNoteById.execute(id);
    }

    public void deleteNote(int id,CompleteCallback completeCallback) {
        DeleteNote deleteNote = new DeleteNote(completeCallback);
        deleteNote.execute(id);
    }
    public void updateNote(ContentValues contentValues,CompleteCallback completeCallback) {
        UpdateNote updateNote = new UpdateNote(completeCallback);
        updateNote.execute(contentValues);
    }

    public interface LoadNotesCallback {
        void onLoad(List<Note> notes);
    }

    public interface LoadNoteByIdCallback{
        void onLoad(Note note);
    }

    public interface CompleteCallback {
        void onComplete();
    }

    @Override
    public List<Note> sotringDateByASC() {
        return noteDao.sotringDateByASC();
    }

    @Override
    public List<Note> sotringDateByDESC() {
        return noteDao.sotringDateByDESC();
    }

    @Override
    public List<Note> findNoteById(int id) {
        return noteDao.findNoteById(id);
    }

    @Override
    public Note findByText(String text) {
        return noteDao.findByText(text);
    }

    @Override
    public void updateNote(Note... notes) {
        noteDao.updateNote(notes);
    }

    @Override
    public void insertAll(Note... notes) {
        noteDao.insertAll(notes);
    }

    @Override
    public void delete(Note note) {
        noteDao.delete(note);
    }

    @Override
    public void deleteById(int id) {
        noteDao.deleteById(id);
    }

    class LoadNotes extends AsyncTask<Void, Void, List<Note>> {

        private final LoadNotesCallback callback;

        LoadNotes(LoadNotesCallback callback) {
            this.callback = callback;
        }


        @Override
        protected List<Note> doInBackground(Void... params) {
            Date d = new Date();
            //SimpleDateFormat ft = new SimpleDateFormat ("E yyyy.MM.dd 'at' hh:mm:ss a zzz");
            //SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM hh:mm", Locale.ENGLISH);

            List<Note> notes = noteDao.sotringDateByDESC();
            return notes;
        }

        @Override
        protected void onPostExecute(List<Note> notes) {
            if (callback != null) {
                callback.onLoad(notes);
            }
        }
    }

    class LoadNoteById extends AsyncTask<Integer, Void, Note> {

        private final LoadNoteByIdCallback callback;

        LoadNoteById(LoadNoteByIdCallback callback) {
            this.callback = callback;
        }


        @Override
        protected Note doInBackground(Integer... params) {
            return noteDao.findNoteById(params[0]).get(0);
        }

        @Override
        protected void onPostExecute(Note note) {
            if (callback != null) {
                callback.onLoad(note);
            }
        }
    }

    class AddNote extends AsyncTask<ContentValues, Void, Void> {

        private final CompleteCallback callback;

        AddNote(CompleteCallback callback) {
            this.callback = callback;
        }

        @Override
        protected Void doInBackground(ContentValues... params) {
            ContentValues noteCV = params[0];
            Note n = new Note();
            n.setText(noteCV.get("text").toString());
            n.setDateCreating(noteCV.get("time").toString());
            n.setDateChanging(noteCV.get("time").toString());
            noteDao.insertAll(n);
            // Хуярим в базу новую замметку
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (callback != null) {
                callback.onComplete();
            }
        }
    }

    class UpdateNote extends AsyncTask<ContentValues, Void, Void> {

        private final CompleteCallback callback;

        UpdateNote(CompleteCallback callback) {
            this.callback = callback;
        }

        @Override
        protected Void doInBackground(ContentValues... params) {
            ContentValues noteCV = params[0];
            Note n = new Note();
            n.setId(Integer.parseInt(noteCV.get("id").toString()));
            n.setText(noteCV.get("text").toString());
            n.setDateCreating(noteCV.get("time").toString());
            n.setDateChanging(noteCV.get("time").toString());
            noteDao.updateNote(n);
            // Хуярим в базу новую замметку
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (callback != null) {
                callback.onComplete();
            }
        }
    }

    class DeleteNote extends AsyncTask<Integer, Void, Void> {

        private final CompleteCallback callback;

        DeleteNote(CompleteCallback callback) {
            this.callback = callback;
        }

        @Override
        protected Void doInBackground(Integer... params) {
            noteDao.deleteById(params[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (callback != null) {
                callback.onComplete();
            }
        }
    }
}
