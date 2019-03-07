package com.mandriklab.notes.Model;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.mandriklab.notes.Model.DAO.NoteDAO;
import com.mandriklab.notes.Model.Entity.Category;
import com.mandriklab.notes.Model.Entity.Note;

@Database(entities = {Note.class, Category.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase appDatabase = null;

    /**
     * from developers android, made my own singleton
     *
     * @param context
     * @return
     */
    public static AppDatabase getInstance(Context context) {
        if (appDatabase == null) {
            appDatabase = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, "database-name").build();
        }
        return appDatabase;
    }

    public abstract NoteDAO siteDao();
}