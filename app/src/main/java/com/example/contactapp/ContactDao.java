package com.example.contactapp;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface ContactDao {

    @Query("SELECT * FROM Contact")
    List<Contact> getAll();

    @Insert
    void insertAll(Contact... contacts);

    @Delete
    void delete(Contact contact);

    @Query("DELETE FROM Contact")
    void deleteAll();

    @Insert
    void insertAll(ArrayList<Contact> listContacts);

    @Query("SELECT COUNT(*) FROM Contact")
    int countContacts();
}
