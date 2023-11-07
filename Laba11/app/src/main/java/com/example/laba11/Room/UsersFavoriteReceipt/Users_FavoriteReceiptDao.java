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

    @Query("SELECT id FROM RUsers_RFavoriteReceipt WHERE favoriteReceiptId=:receiptId")
    int getId(int receiptId);

    @Query("SELECT favoriteReceiptId FROM RUsers_RFavoriteReceipt WHERE userId=:user_id")
    List<Integer> getReceiptsId(int user_id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Users_FavoriteReceiptEntity RUsers_RFavoriteReceipt);
}
