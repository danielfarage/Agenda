package com.agenda.dfaragec.prj_agenda.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.agenda.dfaragec.prj_agenda.dao.ContactDao;
import com.agenda.dfaragec.prj_agenda.model.Contact;

@Database(entities = {Contact.class}, version = 1)
public abstract class ContactDB extends RoomDatabase {

    public abstract ContactDao contactDao();

}
