package com.agenda.dfaragec.prj_agenda.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.agenda.dfaragec.prj_agenda.dao.ContactDao;
import com.agenda.dfaragec.prj_agenda.model.Contact;

@Database(entities = {Contact.class}, version = 1, exportSchema = false)
public abstract class ContactDB extends RoomDatabase {

    public abstract ContactDao contactDao();

    private static ContactDB instance;

    public static synchronized ContactDB getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    ContactDB.class,
                    "contact_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }


}
