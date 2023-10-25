package com.example.laba11.Room.Users;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "RUsers")
public class UsersEntity {
    @PrimaryKey(autoGenerate = true)
    public long id;

    public String login;

    public String password;
}
