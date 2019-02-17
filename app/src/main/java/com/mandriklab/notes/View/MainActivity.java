package com.mandriklab.notes.View;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.mandriklab.notes.Model.Entity.Note;
import com.mandriklab.notes.Model.NoteModel;
import com.mandriklab.notes.NoteAdapter;
import com.mandriklab.notes.Presenter.MainPresenter;
import com.mandriklab.notes.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    FloatingActionButton fab;
    private MainPresenter presenter;
    NoteAdapter noteAdapter;
    ListView lvNotes;
    EditText etSearching;
    public Button btnSortByText,btnSortByDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Init();
    }

    public void Init(){
        lvNotes = (ListView) findViewById(R.id.lvNotes);
        etSearching = (EditText) findViewById(R.id.etSearching);
        btnSortByText = (Button) findViewById(R.id.btnSortByText);
        btnSortByDate = (Button) findViewById(R.id.btnSortByDate);
        fab = findViewById(R.id.fab);

        btnSortByDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.filterDate(); // by text
            }
        });
        btnSortByText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.filterText();
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),NoteActivity.class);
                startActivity(intent);
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
            }
        });
        lvNotes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(MainActivity.this, NoteActivity.class);
                i.putExtra("id", ((Note)parent.getAdapter().getItem(position)).getId());
                startActivity(i);
            }
        });

        etSearching.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                presenter.filter(1,s.toString()); // 1 - by text
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // TODO Auto-generated method stub
            }
            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }
        });


        noteAdapter = new NoteAdapter();
        NoteModel model = new NoteModel(this);
        presenter = new MainPresenter(model);
        presenter.attachView(this);
        presenter.viewIsReady();
    }

    @Override
    protected void onStart(){
        super.onStart();
        presenter.viewIsReady();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    public void showNotes(ArrayList<Note> notes) {
        // сдесь тип загрузиться адаптер
        lvNotes.setAdapter(new NoteAdapter(MainActivity.this, notes));
    }
}
