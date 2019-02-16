package com.mandriklab.notes.Model;

import android.content.ContentValues;
import android.content.Context;
import android.os.AsyncTask;

import com.mandriklab.notes.Model.DAO.NoteDAO;
import com.mandriklab.notes.Model.Entity.Note;

import java.util.ArrayList;
import java.util.List;

public class NoteModel implements NoteDAO {

    private NoteDAO noteDao;

    public NoteModel(Context context) {
        noteDao = AppDatabase.getInstance(context).siteDao();
    }

    public void loadNotes(LoadNotesCallback callback) {
        LoadNotes loadNotes = new LoadNotes(callback);
        loadNotes.execute();
    }

    public void addUser(ContentValues contentValues, CompleteCallback callback) {
        AddNote addNote = new AddNote(callback);
        addNote.execute(contentValues);
    }

    public void deleteNote(CompleteCallback completeCallback) {
        DeleteNote deleteNote = new DeleteNote(completeCallback);
        deleteNote.execute();
    }


    public interface LoadNotesCallback {
        void onLoad(List<Note> notes);
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

    class LoadNotes extends AsyncTask<Void, Void, List<Note>> {

        private final LoadNotesCallback callback;

        LoadNotes(LoadNotesCallback callback) {
            this.callback = callback;
        }

        @Override
        protected List<Note> doInBackground(Void... params) {
            List<Note> notes = new ArrayList<>();
            /*Cursor cursor = dbHelper.getReadableDatabase().query(UserTable.TABLE, null, null, null, null, null, null);
            while (cursor.moveToNext()) {
                User user = new User();
                user.setId(cursor.getLong(cursor.getColumnIndex(UserTable.COLUMN.ID)));
                user.setName(cursor.getString(cursor.getColumnIndex(UserTable.COLUMN.NAME)));
                user.setEmail(cursor.getString(cursor.getColumnIndex(UserTable.COLUMN.EMAIL)));
                users.add(user);
            }
            cursor.close();*/
            //Достаём данные из базы
            return notes;
        }

        @Override
        protected void onPostExecute(List<Note> notes) {
            if (callback != null) {
                callback.onLoad(notes);
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
            ContentValues cvUser = params[0];
            /*dbHelper.getWritableDatabase().insert(UserTable.TABLE, null, cvUser);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
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

    class DeleteNote extends AsyncTask<Void, Void, Void> {

        private final CompleteCallback callback;

        DeleteNote(CompleteCallback callback) {
            this.callback = callback;
        }

        @Override
        protected Void doInBackground(Void... params) {
            /*dbHelper.getWritableDatabase().delete(UserTable.TABLE, null, null);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
            // Нахуй заметку из базы
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
