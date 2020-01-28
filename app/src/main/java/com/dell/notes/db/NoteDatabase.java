package com.dell.notes.db;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.dell.notes.util.DateUtils;


@Database(entities = {Note.class},version = 1,exportSchema = false)
public abstract class NoteDatabase extends RoomDatabase {

    private static NoteDatabase instance;
    public abstract NoteDao getDaoInstance();

    public static synchronized NoteDatabase getDatabase(Context context){
        if (instance == null) {
            instance = Room
                    .databaseBuilder(context, NoteDatabase.class, "note.db")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private NoteDao noteDao;

        private PopulateDbAsyncTask(NoteDatabase db) {
            noteDao = db.getDaoInstance();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.addNote(new Note("Title 1", DateUtils.getCurrentDateTime()));
            noteDao.addNote(new Note("Title 2", DateUtils.getCurrentDateTime()));
            noteDao.addNote(new Note("Title 3", DateUtils.getCurrentDateTime()));
            return null;
        }
    }

}
