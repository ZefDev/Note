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

    Button btnDone,btnBack;
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
        btnBack = (Button) findViewById(R.id.btnBack);
        tvDateNote = (TextView) findViewById(R.id.tvDateNote);
        etText = (EditText) findViewById(R.id.etText);
        bottomNavigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
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
                    case R.id.action_share:
                        notePresenter.shareNote();
                        break;
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
        etText.setText(note.getTitle()+""+ note.getText());
    }

    public Note getNoteData(){
        Note noteData = new Note();

        String text = etText.getText().toString();
        String title = "";
        if (text.length()>0) {
            if (text.indexOf("\n")>-1){
                title = text.substring(0, text.indexOf("\n"));
            }
            else {
                text +="\n";
                title = text.substring(0, text.indexOf("\n"));
            }
            text = text.substring(text.indexOf("\n"), text.length());
        }
        noteData.setDateCreating(tvDateNote.getText().toString());
        noteData.setDateChanging(tvDateNote.getText().toString());
        noteData.setTitle(title);
        noteData.setText(text);
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
