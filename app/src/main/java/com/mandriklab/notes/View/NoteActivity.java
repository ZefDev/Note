package com.mandriklab.notes.View;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.mandriklab.notes.Model.Entity.Note;
import com.mandriklab.notes.Model.NoteModel;
import com.mandriklab.notes.Presenter.MainPresenter;
import com.mandriklab.notes.Presenter.NotePresenter;
import com.mandriklab.notes.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class NoteActivity extends Activity {

    Button btnDone;
    TextView tvDateNote;
    EditText etText;
    NotePresenter notePresenter;
    BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        Init();
    }

    public void Init(){
        btnDone = (Button) findViewById(R.id.btnDone);
        tvDateNote = (TextView) findViewById(R.id.tvDateNote);
        etText = (EditText) findViewById(R.id.etText);
        bottomNavigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notePresenter.saveNote();
            }
        });
        bottomNavigation.setOnNavigationItemSelectedListener(
        new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_delete:
                        notePresenter.deleteNote();
                        break;
                }
                return false;
            }
        });
        NoteModel model = new NoteModel(this);
        notePresenter = new NotePresenter(model);
        notePresenter.attachView(this);
        //notePresenter.viewIsReady();
    }

    public void showNote(Note note){
        tvDateNote.setText(note.getDateCreating());
        etText.setText(note.getText());
    }

    public Note getNoteData(){
        Note noteData = new Note();
        noteData.setDateCreating(tvDateNote.getText().toString());
        noteData.setDateChanging(tvDateNote.getText().toString());
        noteData.setText(etText.getText().toString());
        return noteData;
    }
    @Override
    protected void onStart(){
        super.onStart();
        notePresenter.viewIsReady();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //notePresenter.saveNote();
        notePresenter.detachView();
    }

}
