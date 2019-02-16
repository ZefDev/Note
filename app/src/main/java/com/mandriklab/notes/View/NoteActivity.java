package com.mandriklab.notes.View;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.mandriklab.notes.Model.Entity.Note;
import com.mandriklab.notes.Presenter.NotePresenter;
import com.mandriklab.notes.R;

import java.util.List;

public class NoteActivity extends Activity {

    Button btnDone;

    NotePresenter notePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        Init();

    }

    public void Init(){
        btnDone = findViewById(R.id.btnDone);
    }

    public void showNote(List<Note> note){
        note.get(0).getText();
        note.get(0).getDateCreating();
    }



}
