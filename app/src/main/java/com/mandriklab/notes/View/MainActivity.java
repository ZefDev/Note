package com.mandriklab.notes.View;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton.LayoutParams;

import com.mandriklab.notes.Model.Entity.Note;
import com.mandriklab.notes.Model.NoteModel;
import com.mandriklab.notes.NoteAdapter;
import com.mandriklab.notes.Presenter.MainPresenter;
import com.mandriklab.notes.R;

import java.util.ArrayList;
import java.util.List;

import petrov.kristiyan.colorpicker.ColorPicker;

public class MainActivity extends Activity {

    FloatingActionButton fab;
    private MainPresenter presenter;
    NoteAdapter noteAdapter;
    ListView lvNotes;
    EditText etSearching;
    public Button btnSortByText,btnSortByDate,btnSortByColor;
    private AlarmManager am;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Init();
        final ImageView itemIcon = new ImageView(this);
        itemIcon.setImageDrawable(ContextCompat.getDrawable(this,  R.drawable.plus));

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),NoteActivity.class);
                startActivity(intent);
            }
        });

        /*LayoutParams params=new LayoutParams(125,125);
        final FloatingActionButton actionButton = new FloatingActionButton.Builder(this)
                .setContentView(itemIcon)
                //.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.plus))
                .build();

        SubActionButton.Builder itemBuilder = new SubActionButton.Builder(this);
// repeat many times:
        ImageView itemIcon1 = new ImageView(this);
        itemIcon1.setImageDrawable(ContextCompat.getDrawable(this,  R.drawable.text));
        SubActionButton button1 = itemBuilder.setContentView(itemIcon1).build();

        ImageView itemIcon2 = new ImageView(this);
        itemIcon2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.voice));
        SubActionButton button2 = itemBuilder.setContentView(itemIcon2).build();

        ImageView itemIcon3 = new ImageView(this);
        itemIcon3.setImageDrawable(ContextCompat.getDrawable(this,  R.drawable.camera));
        SubActionButton button3 = itemBuilder.setContentView(itemIcon3).build();

        button1.setLayoutParams(params);
        button2.setLayoutParams(params);
        button3.setLayoutParams(params);

        final FloatingActionMenu actionMenu = new FloatingActionMenu.Builder(this)
                .addSubActionView(button1)
                .addSubActionView(button2)
                .addSubActionView(button3)
                .attachTo(actionButton)
                .build();
        actionMenu.setStateChangeListener(new FloatingActionMenu.MenuStateChangeListener() {
            @Override
            public void onMenuOpened(FloatingActionMenu floatingActionMenu) {
                itemIcon.setRotation(0);
                PropertyValuesHolder prv = PropertyValuesHolder.ofFloat(View.ROTATION,45);
                ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(itemIcon,prv);
                animator.start();
            }

            @Override
            public void onMenuClosed(FloatingActionMenu floatingActionMenu) {
                itemIcon.setRotation(45);
                PropertyValuesHolder prv = PropertyValuesHolder.ofFloat(View.ROTATION,0);
                ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(itemIcon,prv);
                animator.start();
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),NoteActivity.class);
                startActivity(intent);
                actionMenu.close(true);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),NoteActivity.class);
                startActivity(intent);
                actionMenu.close(true);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),NoteActivity.class);
                startActivity(intent);
                actionMenu.close(true);
            }
        });*/
    }

    public void Init(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);


            window.setStatusBarColor(getResources().getColor(R.color.colorPrimary));
        }

        lvNotes = (ListView) findViewById(R.id.lvNotes);
        etSearching = (EditText) findViewById(R.id.etSearching);
        btnSortByText = (Button) findViewById(R.id.btnSortByText);
        btnSortByDate = (Button) findViewById(R.id.btnSortByDate);
        btnSortByColor = (Button) findViewById(R.id.btnSortByColor);
        //fab = findViewById(R.id.fab);

        btnSortByDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.filterDate(); // by text
            }
        });

        btnSortByColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initColorPicker();
                 // by color
            }
        });

        btnSortByText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.filterText();
            }
        });
        /*fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),NoteActivity.class);
                startActivity(intent);
            }
        });*/
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

    public void initColorPicker(){
        final ColorPicker colorPicker = new ColorPicker(MainActivity.this);

        colorPicker.setColumns(5);
        colorPicker.setRoundColorButton(true);

        colorPicker.setOnChooseColorListener(new ColorPicker.OnChooseColorListener() {
            @Override
            public void onChooseColor(int position,int color) {
                // filtr by color;
                if(position!=-1) {
                    presenter.filterColor(color);
                }
            }

            @Override
            public void onCancel(){
                presenter.filter(5,"");
                colorPicker.dismissDialog();
            }
        });
        colorPicker.show();
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
