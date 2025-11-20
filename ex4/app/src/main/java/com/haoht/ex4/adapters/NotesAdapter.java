package com.haoht.ex4.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.haoht.ex4.MainActivity;
import com.haoht.ex4.R; // Cần import R

import com.haoht.ex4.model.NotesModel;

import java.util.List;

public class NotesAdapter extends BaseAdapter {

    private MainActivity context;
    private int layout;
    private List<NotesModel> notesList;

    public NotesAdapter(Context context, int layout, List<NotesModel> notesList) {
        this.context = (MainActivity) context;
        this.layout = layout;
        this.notesList = notesList;
    }

    // ... (Các hàm get/set nếu cần) ...

    @Override
    public int getCount() {
        return notesList.size();
    }

    @Override
    public Object getItem(int position) {
        return notesList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder {
        TextView textViewNotes;
        ImageView imageViewEdit, imageViewDelete;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, parent, false);

            holder = new ViewHolder();
            holder.textViewNotes = convertView.findViewById(R.id.textViewNotes); // Dùng R.id chuẩn
            holder.imageViewEdit = convertView.findViewById(R.id.imageViewEdit);
            holder.imageViewDelete = convertView.findViewById(R.id.imageViewDelete);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final NotesModel notes = notesList.get(position);

        holder.textViewNotes.setText(notes.getNameNotes());

        // Bắt sự kiện nút CẬP NHẬT
        holder.imageViewEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Cập nhật " + notes.getNameNotes(), Toast.LENGTH_SHORT).show();
                context.DialogCapNhatNotes(notes.getNameNotes(), notes.getIdNotes());
            }
        });

        // Bắt sự kiện nút XÓA
        holder.imageViewDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.DialogDelete(notes.getNameNotes(), notes.getIdNotes());
            }
        });

        return convertView;
    }
}