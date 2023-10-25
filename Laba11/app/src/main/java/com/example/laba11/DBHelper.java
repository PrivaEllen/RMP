package com.example.laba11;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(@androidx.annotation.Nullable Context context,
                    @androidx.annotation.Nullable String name, @androidx.annotation.Nullable
                    SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    public DBHelper(@androidx.annotation.Nullable Context context,
                    @androidx.annotation.Nullable String name, @androidx.annotation.Nullable
                    SQLiteDatabase.CursorFactory factory, int version,
                    @androidx.annotation.Nullable DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }
    public DBHelper(@androidx.annotation.Nullable Context context,
                    @androidx.annotation.Nullable String name, int version,
                    @androidx.annotation.NonNull SQLiteDatabase.OpenParams openParams) {
        super(context, name, version, openParams);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table users("
                + "id integer primary key autoincrement,"
                + "login varchar(50),"
                + "password varchar(50)" + ")");

        db.execSQL("create table allReceipts("
                + "id integer primary key autoincrement,"
                + "name varchar(100),"
                + "calorie integer,"
                + "time integer,"
                + "ingredients varchar(300),"
                + "difficulty integer" + ")");

        db.execSQL("create table favoriteReceipts("
                + "id integer primary key autoincrement,"
                + "name varchar(100),"
                + "calorie integer,"
                + "time integer,"
                + "ingredients varchar(300),"
                + "difficulty integer" + ")");

        db.execSQL("create table usersFavoriteReceipts("
                + "id integer primary key autoincrement,"
                + "userId integer,"
                + "favoriteReceiptId integer,"
                + "difficulty integer,"
                + "FOREIGN KEY (userId) REFERENCES favoriteReceipts(id),"
                + "FOREIGN KEY (favoritereceiptId) REFERENCES users(id)"  + ")");

    }
    @Override

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int
            newVersion) {
    }
}
