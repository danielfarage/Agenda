package com.agenda.dfaragec.prj_agenda.viewModel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.persistence.room.Database;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.agenda.dfaragec.prj_agenda.database.ContactDB;
import com.agenda.dfaragec.prj_agenda.database.ContactDBCliente;
import com.agenda.dfaragec.prj_agenda.model.Contact;

import java.util.ArrayList;
import java.util.List;

public class ContactViewModel extends ViewModel {

    public MutableLiveData<String> mensagem = new MutableLiveData<>();
    public MutableLiveData<List<Contact>> listContacts = new MutableLiveData<>();

    public void save(String nome, String tel, Context context) {
        Contact contact = new Contact();
        contact.setName(nome);
        contact.setTelephone(tel);
        saveContactDB(contact, context);
    }


    private void saveContactDB(final Contact contact, final Context context) {

        class SaveContactAsync extends AsyncTask<Void, Void, Void> {
            @Override
            protected Void doInBackground(Void... voids) {

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                mensagem.setValue("Contato adicionado");
            }
        }

        SaveContactAsync saveContact = new SaveContactAsync();
        saveContact.execute();

    }

    public void getAll(final Context context) {

        class getAllAsync extends AsyncTask<Void, Void, ArrayList<Contact>>{
            @Override
            protected ArrayList<Contact> doInBackground(Void... voids) {
                ArrayList<Contact> listaTs =  ContactDBCliente
                        .getInstance(context)
                        .getContactDB()
                        .contactDao().getAll();


                return listaTs;
            }

            @Override
            protected void onPostExecute(ArrayList<Contact> contacts) {
                super.onPostExecute(contacts);
                for (Contact item:contacts) {
                    Log.d("PEGADB", item.getName());
                }
            }
        }
        getAllAsync getAllAsync = new getAllAsync();
        getAllAsync.execute();

    }

}
