package com.agenda.dfaragec.prj_agenda.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.agenda.dfaragec.prj_agenda.dao.ContactDao;
import com.agenda.dfaragec.prj_agenda.database.ContactDB;
import com.agenda.dfaragec.prj_agenda.model.Contact;

import java.util.List;

public class ContactRepository {
    private ContactDao contactDao;
    private LiveData<List<Contact>> allContacts;

    public ContactRepository(Application application){
        ContactDB contactDB = ContactDB.getInstance(application);
        contactDao = contactDB.contactDao();
        allContacts = contactDao.getAll();
    }

    public void insert(Contact contact){
        new InsertContactAsyncTask(contactDao).execute(contact);
    }

    public void update(Contact contact){
        new UpdateContactAsyncTask(contactDao).execute(contact);
    }

    public void delete(Contact contact){
        new DeleteContactAsyncTask(contactDao).execute(contact);
    }

    public LiveData<List<Contact>> getAllContacts() {
        return allContacts;
    }

    private static class InsertContactAsyncTask extends AsyncTask<Contact, Void, Void>{

        private ContactDao contactDao;

        private InsertContactAsyncTask(ContactDao contactDao){
            this.contactDao = contactDao;
        }

        @Override
        protected Void doInBackground(Contact... contacts) {
            contactDao.insert(contacts[0]);
            return null;
        }
    }

    private static class UpdateContactAsyncTask extends AsyncTask<Contact, Void, Void>{

        private ContactDao contactDao;

        private UpdateContactAsyncTask(ContactDao contactDao){
            this.contactDao = contactDao;
        }

        @Override
        protected Void doInBackground(Contact... contacts) {
            contactDao.update(contacts[0]);
            return null;
        }
    }

    private static class DeleteContactAsyncTask extends AsyncTask<Contact, Void, Void>{

        private ContactDao contactDao;

        private DeleteContactAsyncTask(ContactDao contactDao){
            this.contactDao = contactDao;
        }

        @Override
        protected Void doInBackground(Contact... contacts) {
            contactDao.delete(contacts[0]);
            return null;
        }
    }
}
