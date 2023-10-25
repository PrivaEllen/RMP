package com.example.laba11;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class JSONStructure {

    public int Calorie;
    public int Time;
    public String Name;

    public String Ingredients;
    public int Difficulty;

    @NonNull
    @Override
    public String toString() {
        return String.format("Calorie: %d\nTime: %d\nName: %s\nIngredients: %s\nDifficulty: %d\n\n", Calorie, Time, Name, Ingredients, Difficulty);
    }

    public List<JSONStructure> getReceipts(List<JSONStructure> receipts) {
        List<JSONStructure> allReceipts = new ArrayList<>();
        for (int i = 0; i < receipts.size(); i++) {
            JSONStructure receiptItem = new JSONStructure();
            receiptItem.Name = receipts.get(i).Name;
            receiptItem.Calorie = receipts.get(i).Calorie;
            receiptItem.Time = receipts.get(i).Time;
            receiptItem.Ingredients = receipts.get(i).Ingredients;
            receiptItem.Difficulty = receipts.get(i).Difficulty;
            allReceipts.add(receiptItem);

        }
        return allReceipts;
    }

    public List<String> getNamesReceipts(List<JSONStructure> receipts) {
        List<String> nameReceipts = new ArrayList<>();
        for (int i = 0; i < receipts.size(); i++) {
            nameReceipts.add(i, receipts.get(i).Name);
        }
        return nameReceipts;
    }
}
