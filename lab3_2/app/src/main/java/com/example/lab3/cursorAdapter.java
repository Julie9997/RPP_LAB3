package com.example.lab3;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class cursorAdapter extends CursorAdapter {
    public cursorAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView id = (TextView) view.findViewById(R.id.id);
        TextView name = (TextView) view.findViewById(R.id.name);
        TextView time = (TextView) view.findViewById(R.id.time);

        String _id = cursor.getString(cursor.getColumnIndexOrThrow("_id"));
        String l_name = cursor.getString(cursor.getColumnIndexOrThrow("last_name"));
        String f_name = cursor.getString(cursor.getColumnIndexOrThrow("first_name"));
        String m_name = cursor.getString(cursor.getColumnIndexOrThrow("middle_name"));
        String _time = cursor.getString(cursor.getColumnIndexOrThrow("time"));

        id.setText("ID: " + _id);
        name.setText("Фамилия: " + l_name + "\n Имя: " + f_name + "\n Отчество: " + m_name);
        time.setText("Время: " + _time);
    }
}