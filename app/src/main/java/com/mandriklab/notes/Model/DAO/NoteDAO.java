package com.mandriklab.notes.Model.DAO;

import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.mandriklab.notes.Model.Entity.Note;

import java.util.List;

public interface NoteDAO {

    @Query("SELECT * FROM note ORDER BY dateCreating ASC")
    List<Note> sotringDateByASC();

    @Query("SELECT * FROM note ORDER BY dateCreating DESC")
    List<Note> sotringDateByDESC();

    @Query("SELECT * FROM note WHERE id=:id")
    List<Note> findNoteById(final int id);

    @Query("SELECT * FROM note WHERE text LIKE :text")
    Note findByText(String text);

    @Update
    public void updateNote(Note... notes);

    @Insert
    void insertAll(Note... notes);

    @Delete
    void delete(Note site);
}
