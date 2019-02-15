package com.mandriklab.notes.Model.Entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.time.OffsetDateTime;

@Entity
public class Note {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "text")
    private String text;

    @ColumnInfo(name = "dateCreating")
    private OffsetDateTime dateCreating;

    @ColumnInfo(name = "dateChanging")
    private OffsetDateTime dateChanging;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public OffsetDateTime getDateCreating() {
        return dateCreating;
    }

    public void setDateCreating(OffsetDateTime dateCreating) {
        this.dateCreating = dateCreating;
    }

    public OffsetDateTime getDateChanging() {
        return dateChanging;
    }

    public void setDateChanging(OffsetDateTime dateChanging) {
        this.dateChanging = dateChanging;
    }
}
