package com.agenda.dfaragec.prj_agenda.dao;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.agenda.dfaragec.prj_agenda.model.Contact;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface ContactDao {

    @Query("SELECT * FROM contacts")
    LiveData<List<Contact>> getAll();

    @Query("SELECT * FROM contacts WHERE id IN(:userId)")
    List<Contact> getById(int[] userId);

    @Insert
    void insert(Contact contact);

    @Update
    void update(Contact contact);

    @Delete
    void delete(Contact contact);

}
