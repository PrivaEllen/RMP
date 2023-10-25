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
import com.example.laba11.Room.AllReceipts.AllReceiptsDao;
import com.example.laba11.Room.AllReceipts.AllReceiptsEntity;
import com.example.laba11.Room.App;
import com.example.laba11.Room.RoomDB;
import com.example.laba11.Room.Users.UsersDao;
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
    private List<AllReceiptsEntity> recyclerData = new ArrayList<>();

    DBHelper dbHelper;

    String[] allReceiptsArray = new String[]{"name", "calorie", "time", "ingredients", "difficulty"};

    List<String> namesReceiptsArray = new ArrayList<>();
    List<JSONStructure> receiptsArray = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        recyclerView = binding.allReceipts;

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

                RoomDB db = App.getInstance().getDatabase();
                AllReceiptsDao allReceiptsDao = db.allReceiptsDao();
                /*AllReceiptsEntity allReceiptsEntity = new AllReceiptsEntity();

                for (int i = 0; i < response.body().size(); i++) {
                    allReceiptsEntity.name = response.body().get(i).Name;
                    allReceiptsEntity.calorie = response.body().get(i).Calorie;
                    allReceiptsEntity.time = response.body().get(i).Time;
                    allReceiptsEntity.ingredients = response.body().get(i).Ingredients;
                    allReceiptsEntity.difficulty = response.body().get(i).Difficulty;
                    allReceiptsDao.insert(allReceiptsEntity);
                }*/

                recyclerDataNames.clear();
                recyclerData.clear();

                recyclerDataNames.addAll(allReceiptsDao.getNames());
                recyclerData.addAll(allReceiptsDao.getAll());

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