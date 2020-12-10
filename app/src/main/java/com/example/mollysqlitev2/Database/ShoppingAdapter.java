package com.example.mollysqlitev2.Database;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mollysqlitev2.R;
import com.example.mollysqlitev2.Database.Models.Shopping;

import java.util.List;

public class ShoppingAdapter extends RecyclerView.Adapter<ShoppingAdapter.NotesViewHolder> {
    private Context context;
    private List<Shopping> notesList;

    public class NotesViewHolder extends RecyclerView.ViewHolder {
        public TextView note;

        public NotesViewHolder(View view) {
            super(view);
            note = view.findViewById(R.id.note);
        }
    }


    public ShoppingAdapter(Context context, List<Shopping> notesList) {
        super();
        this.context = context;
        this.notesList = notesList;
    }

    @Override
    public NotesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row, parent, false);

        return new NotesViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(NotesViewHolder holder, int position) {
        Shopping note = notesList.get(position);

        holder.note.setText(note.getNote());
    }

    @Override
    public int getItemCount() {
        return notesList.size();
    }
}
