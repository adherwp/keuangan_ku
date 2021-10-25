package com.example.aplikasikeuangan;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserItemDao {

    @Insert
    void insert(UserItem userItem);

    @Update
    void update(UserItem userItem);

    @Query("SELECT * FROM user_table")
    LiveData<List<UserItem>> getAllUser();

    @Query("SELECT username FROM user_table")
    String getUsername();

    @Query("SELECT password FROM user_table")
    String getPassword();

    @Query("SELECT id FROM user_table")
    int getId();

}
