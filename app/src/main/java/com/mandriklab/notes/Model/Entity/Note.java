package com.mandriklab.notes.Model.Entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Note {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "text")
    private String text;

    @ColumnInfo(name = "dateCreating")
    private String dateCreating;

    @ColumnInfo(name = "dateChanging")
    private String dateChanging;

    public int getBackground() {
        return background;
    }

    public void setBackground(int background) {
        this.background = background;
    }

    @ColumnInfo(name = "background")
    private int background;

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

    public String getDateCreating() {
        return dateCreating;
    }

    public void setDateCreating(String dateCreating) {
        this.dateCreating = dateCreating;
    }

    public String getDateChanging() {
        return dateChanging;
    }

    public void setDateChanging(String dateChanging) {
        this.dateChanging = dateChanging;
    }
}
