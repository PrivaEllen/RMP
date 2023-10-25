package com.example.laba11.Room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.laba11.Room.AllReceipts.AllReceiptsDao;
import com.example.laba11.Room.AllReceipts.AllReceiptsEntity;
import com.example.laba11.Room.FavoriteReceipt.FavoriteReceiptDao;
import com.example.laba11.Room.FavoriteReceipt.FavoriteReceiptEntity;
import com.example.laba11.Room.Users.UsersDao;
import com.example.laba11.Room.Users.UsersEntity;
import com.example.laba11.Room.UsersFavoriteReceipt.Users_FavoriteReceiptDao;
import com.example.laba11.Room.UsersFavoriteReceipt.Users_FavoriteReceiptEntity;

@Database(
        version = 1,
        entities = {
                UsersEntity.class,
                AllReceiptsEntity.class,
                FavoriteReceiptEntity.class,
                Users_FavoriteReceiptEntity.class
        }
)
public abstract class RoomDB extends RoomDatabase {
    public abstract UsersDao usersDao();
    public abstract AllReceiptsDao allReceiptsDao();
    public abstract FavoriteReceiptDao favoriteReceiptDao();
    public abstract Users_FavoriteReceiptDao usersFavoriteReceiptDao();

}
