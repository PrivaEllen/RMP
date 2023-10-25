package com.example.laba11.ui.home;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.laba11.DBHelper;
import com.example.laba11.JSONStructure;
import com.example.laba11.R;
import com.example.laba11.RetrofitInterface;
import com.example.laba11.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter hAdapter;

    private List<String> recyclerDataNames = new ArrayList<>();
    private List<JSONStructure> recyclerData = new ArrayList<>();

    DBHelper dbHelper;

    String[] allReceiptsArray = new String[]{"name", "calorie", "time", "ingredients", "difficulty"};

    List<String> namesReceiptsArray = new ArrayList<>();
    List<JSONStructure> receiptsArray = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        recyclerView = binding.allReceipts;

        dbHelper = new DBHelper(this.getContext(), "mybd", null, 1);
        //SQLiteDatabase dbWriter = dbHelper.getWritableDatabase();
        SQLiteDatabase dbReader = dbHelper.getReadableDatabase();

        //String[] hStrings = getResources().getStringArray(R.array.receipts_data);
        //List<String> receiptsData = Arrays.asList(hStrings);

        hAdapter = new HomeAdapter(recyclerDataNames, recyclerData);

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setAdapter(hAdapter);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://raw.githubusercontent.com/Lpirskaya/JsonLab/master/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);

        Call<List<JSONStructure>> fetchReceipsRequest = retrofitInterface.fetchRecieps();

        fetchReceipsRequest.enqueue(new Callback<List<JSONStructure>>() {
            @Override
            public void onResponse(Call<List<JSONStructure>> call, Response<List<JSONStructure>> response) {
                Log.i("Response", Arrays.toString(response.body().toArray()));

                //List<JSONStructure> receiptsItems = new JSONStructure().getReceipts(response.body());
                //List<String> receiptsNamesItems = new JSONStructure().getNamesReceipts(response.body());

                /*for (int i = 0; i < receiptsItems.size(); i++) {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("name", receiptsItems.get(i).Name);
                    contentValues.put("calorie", receiptsItems.get((i)).Calorie);
                    contentValues.put("time", receiptsItems.get((i)).Time);
                    contentValues.put("ingredients", receiptsItems.get((i)).Ingredients);
                    contentValues.put("difficulty", receiptsItems.get((i)).Difficulty);
                    dbWriter.insert("allReceipts", null, contentValues);
                }*/

                Cursor cursor = dbReader.query(
                        "allReceipts",
                        allReceiptsArray,
                        null,
                        null,
                        null,
                        null,
                        null
                );

                cursor.moveToFirst();

                int nameIndex = cursor.getColumnIndex("name");
                int calorieIndex = cursor.getColumnIndex("calorie");
                int timeIndex = cursor.getColumnIndex("time");
                int ingredientsIndex = cursor.getColumnIndex("ingredients");
                int difficultyIndex = cursor.getColumnIndex("difficulty");

                do{
                    String name = cursor.getString(nameIndex);
                    int calorie = cursor.getInt(calorieIndex);
                    int time = cursor.getInt(timeIndex);
                    String ingredients = cursor.getString(ingredientsIndex);
                    int difficulty = cursor.getInt(difficultyIndex);

                    JSONStructure jsonStructure = new JSONStructure();
                    jsonStructure.Name = name;
                    jsonStructure.Calorie = calorie;
                    jsonStructure.Time = time;
                    jsonStructure.Ingredients = ingredients;
                    jsonStructure.Difficulty = difficulty;

                    namesReceiptsArray.add(name);
                    receiptsArray.add(jsonStructure);
                }while (cursor.moveToNext());


                recyclerDataNames.clear();
                recyclerData.clear();

                recyclerDataNames.addAll(namesReceiptsArray);
                recyclerData.addAll(receiptsArray);

                hAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<List<JSONStructure>> call, Throwable t) {
                Log.i("Error", t.getMessage());
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}