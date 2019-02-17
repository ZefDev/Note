package com.mandriklab.notes.Presenter;

import android.content.ContentValues;
import android.graphics.drawable.Drawable;
import android.os.Build;

import com.mandriklab.notes.Model.Entity.Note;
import com.mandriklab.notes.Model.NoteModel;
import com.mandriklab.notes.R;
import com.mandriklab.notes.View.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class MainPresenter {
    private MainActivity view;
    private NoteModel model;

    public MainPresenter(NoteModel model){
        this.model = model;
    }

    public void attachView(MainActivity mainActivity) {
        view = mainActivity;
    }

    public void detachView() {
        view = null;
    }

    public void viewIsReady() {
        loadNotes();
    }

    public void loadNotes() {
        model.loadNotes(new NoteModel.LoadNotesCallback() {
            @Override
            public void onLoad(List<Note> users) {
                ArrayList<Note> list = new ArrayList<>();
                for(int i=0;i<users.size();i++){
                    list.add(users.get(i));
                }
                view.showNotes(list);
            }
        });
    }

    public void filterDate(){
        Drawable.ConstantState dr = view.btnSortByDate.getBackground().getConstantState();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if(dr.equals(view.getResources().getDrawable(R.drawable.arrow_desc_date).getConstantState())){
                filter(4,"");
                view.btnSortByDate.setBackground(view.getDrawable(R.drawable.arrow_asc_date));
            }
            else {
                filter(5,"");
                view.btnSortByDate.setBackground(view.getDrawable(R.drawable.arrow_desc_date));
            }
        }
    }

    public void filterText(){
        Drawable.ConstantState dr = view.btnSortByText.getBackground().getConstantState();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if(dr.equals(view.getResources().getDrawable(R.drawable.sort_by).getConstantState())){
                filter(3,"");
                view.btnSortByText.setBackground(view.getDrawable(R.drawable.sort_by_desc));
            }
            else {
                filter(2,"");
                view.btnSortByText.setBackground(view.getDrawable(R.drawable.sort_by));
            }
        }

    }


    public void filter(int code,String string){
        ContentValues cv = new ContentValues();
        cv.put("id", code);
        cv.put("string", string);
        model.sortingNode(cv,new NoteModel.SortingNodeCallback() {
            @Override
            public void onLoad(List<Note> notes) {
                ArrayList<Note> list = new ArrayList<>();
                for(int i=0;i<notes.size();i++){
                    list.add(notes.get(i));
                }
                view.showNotes(list);
            }
        });
    }
}
