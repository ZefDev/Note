package com.mandriklab.notes.Model.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.mandriklab.notes.Model.Entity.Note;

import java.util.List;

@Dao
public interface NoteDAO {

    @Query("SELECT * FROM note ORDER BY dateCreating ASC")
    List<Note> sotringDateByASC();

    @Query("SELECT * FROM note ORDER BY dateCreating DESC")
    List<Note> sotringDateByDESC();

    @Query("SELECT * FROM note ORDER BY title ASC")
    List<Note> sotringNoteByTextAsc();

    @Query("SELECT * FROM note ORDER BY title DESC")
    List<Note> sotringNoteByTextDesc();

    @Query("SELECT * FROM note ORDER BY title")
    List<Note> sotringNoteByTitle();

    @Query("SELECT * FROM note WHERE id=:id")
    List<Note> findNoteById(final int id);

    @Query("SELECT * FROM note WHERE text LIKE :text OR title LIKE :text")
    List<Note> findByText(String text);

    @Query("DELETE FROM note WHERE id LIKE :id")
    void deleteById(int id);

    @Update
    public void updateNote(Note... notes);

    @Insert
    void insertAll(Note... notes);

    @Delete
    void delete(Note note);

    @Query("SELECT * FROM note WHERE background=:color")
    List<Note> sotringByColor(int color);
}
