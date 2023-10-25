package com.example.laba11.Room.Users;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UsersDao {
    @Query("SELECT * FROM RUsers")
    List<UsersEntity> getAll();

    @Query("SELECT login FROM RUsers")
    List<String> getLogins();

    @Query("SELECT password FROM RUsers")
    List<String> getPasswords();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(UsersEntity RUsers);
}
