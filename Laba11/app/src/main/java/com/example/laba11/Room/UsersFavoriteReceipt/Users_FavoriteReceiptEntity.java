package com.example.laba11.Room.UsersFavoriteReceipt;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.example.laba11.Room.FavoriteReceipt.FavoriteReceiptEntity;
import com.example.laba11.Room.Users.UsersEntity;

@Entity(tableName = "RUsers_RFavoriteReceipt",
        foreignKeys = {
                @ForeignKey(
                        entity = UsersEntity.class,
                        parentColumns = "id",
                        childColumns = "userId"),
                @ForeignKey(
                        entity = FavoriteReceiptEntity.class,
                        parentColumns = "id",
                        childColumns = "favoriteReceiptId"
                )
        })
public class Users_FavoriteReceiptEntity {
    @PrimaryKey(autoGenerate = true)
    public long id;

    public int userId;

    public int favoriteReceiptId;
}
