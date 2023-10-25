package com.example.laba11.Room.AllReceipts;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.laba11.Room.Users.UsersEntity;

import java.util.List;

@Dao
public interface AllReceiptsDao {
    @Query("SELECT * FROM RAllReceipts")
    List<AllReceiptsEntity> getAll();

    @Query("SELECT name FROM RAllReceipts")
    List<String> getNames();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(AllReceiptsEntity RAllReceipts);
}
