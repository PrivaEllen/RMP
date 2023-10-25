package com.example.laba11.Room.UsersFavoriteReceipt;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.laba11.Room.Users.UsersEntity;

import java.util.List;

@Dao
public interface Users_FavoriteReceiptDao {
    @Query("SELECT * FROM RUsers_RFavoriteReceipt")
    List<Users_FavoriteReceiptEntity> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Users_FavoriteReceiptEntity RUsers_RFavoriteReceipt);
}
