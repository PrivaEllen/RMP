package com.example.laba11.Room.AllReceipts;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "RAllReceipts")
public class AllReceiptsEntity {
    @PrimaryKey(autoGenerate = true)
    public long id;

    public String name;

    public int calorie;

    public int time;

    public String ingredients;

    public int difficulty;
}
