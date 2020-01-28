package com.dell.notes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dell.notes.db.Note;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteHolder>{

    private List<Note> notes = new ArrayList<>();

    @NonNull
    @Override
    public NoteAdapter.NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_item,parent,false);
        return new NoteHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteAdapter.NoteHolder holder, int position) {
        Note nNote = notes.get(position);

        holder.txtText.setText(nNote.getText());
        holder.txtDate.setText(nNote.getDate());
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }


    public void setNotes(List<Note> notes) {
        this.notes = notes;
        notifyDataSetChanged();
    }

    public Note getNoteAt(int position) {
        return notes.get(position);
    }

    public class NoteHolder extends RecyclerView.ViewHolder{

        private TextView txtText;
        private TextView txtDate;

        public NoteHolder(@NonNull View itemView) {
            super(itemView);

            txtText = itemView.findViewById(R.id.text_view_text);
            txtDate = itemView.findViewById(R.id.text_view_date);
        }
    }
}
