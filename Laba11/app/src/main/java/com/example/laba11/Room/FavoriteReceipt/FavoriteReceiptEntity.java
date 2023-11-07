package com.example.laba11.Room.FavoriteReceipt;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "RFavoriteReceipt")
public class FavoriteReceiptEntity {
    @PrimaryKey(autoGenerate = true)
    public long id;

    public String name;

    public int calorie;

    public int time;

    public String ingredients;

    public int difficulty;

    @NonNull
    @Override
    public String toString() {
        return String.format("Calorie: %d\nTime: %d\nName: %s\nIngredients: %s\nDifficulty: %d\n\n", calorie, time, name, ingredients, difficulty);
    }
}
