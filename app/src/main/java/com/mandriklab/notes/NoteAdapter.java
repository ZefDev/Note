package com.mandriklab.notes;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v7.widget.PopupMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.mandriklab.notes.Model.Entity.Note;

import java.util.ArrayList;

public class NoteAdapter extends BaseAdapter {

    private Context context;
    ArrayList<Note> notes;

    public NoteAdapter(Context context, ArrayList<Note> notes) {
        this.context = context;
        this.notes = notes;
    }

    public NoteAdapter() {

    }

    @Override
    public int getCount() {
        return notes.size();
    }

    @Override
    public Object getItem(int position) {
        return notes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View rowView = LayoutInflater.from(context).inflate(R.layout.list_item_news, parent, false);

        TextView title = (TextView) rowView.findViewById(R.id.tvNodeTitle);
        TextView date = (TextView) rowView.findViewById(R.id.tvDateNote);
        TextView tvColor = (TextView) rowView.findViewById(R.id.tvColor);
        final TextView subtitleTextView = (TextView)  rowView.findViewById(R.id.tvNodeText);

        final Note notes = (Note) getItem(position);

        title.setText(notes.getTitle());
        subtitleTextView.setText(notes.getText());
        date.setText(notes.getDateChanging());
        tvColor.setBackgroundColor(notes.getBackground());

        return rowView;
    }



}
