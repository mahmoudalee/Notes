package com.dell.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.dell.notes.util.DateUtils;

public class AddNoteActivity extends AppCompatActivity {

    private EditText etText;
    private EditText etDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        etText = findViewById(R.id.edit_text_text);
        etDate = findViewById(R.id.edit_text_date);
        etDate.setText(DateUtils.getCurrentDateTime());

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        setTitle("Add Note");
    }



    private void saveNote() {
        String text= etText.getText().toString();
        String date= etDate.getText().toString();

        if (text.trim().isEmpty() || date.trim().isEmpty()) {
            Toast.makeText(this, "Please insert a text and date", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra("text", text);
        data.putExtra("date", date);

        setResult(RESULT_OK, data);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_note_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_note:
                saveNote();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
