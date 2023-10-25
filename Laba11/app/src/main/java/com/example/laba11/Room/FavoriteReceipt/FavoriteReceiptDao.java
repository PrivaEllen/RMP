package com.example.laba11.Room.FavoriteReceipt;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.laba11.Room.AllReceipts.AllReceiptsEntity;

import java.util.List;

@Dao
public interface FavoriteReceiptDao {
    @Query("SELECT * FROM RFavoriteReceipt")
    List<FavoriteReceiptEntity> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(FavoriteReceiptEntity RFavoriteReceipt);
}
