package com.agenda.dfaragec.prj_agenda.database;

import android.arch.persistence.room.Room;
import android.content.Context;

public class ContactDBCliente {

    private Context context;
    private static ContactDBCliente mInstance;

    private ContactDB contactDB;

    private ContactDBCliente(Context mContext){
        context = mContext;

        contactDB = Room.databaseBuilder(context, ContactDB.class, "contacts").build();

    }

    public static synchronized ContactDBCliente getInstance(Context mContext){
        if(mInstance == null){
            mInstance = new ContactDBCliente(mContext);
        }
        return mInstance;
    }

    public ContactDB getContactDB(){
        return contactDB;
    }

}
