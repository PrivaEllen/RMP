package com.example.laba11;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;

import java.util.List;

public class JSONStructure {

    private int Calorie;
    private int Time;
    private String Name;

    private String Ingredients;
    private int Difficulty;

    @NonNull
    @Override
    public String toString() {
        return String.format("Calorie: %d\nTime: %d\nName: %s\nIngredients: %s\nDifficulty: %d\n\n", Calorie, Time, Name, Ingredients, Difficulty);
    }
}
