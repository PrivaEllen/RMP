package com.example.laba11.ui.notifications;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.laba11.JSONStructure;
import com.example.laba11.MainActivity;
import com.example.laba11.RetrofitInterface;
import com.example.laba11.Room.AllReceipts.AllReceiptsDao;
import com.example.laba11.Room.AllReceipts.AllReceiptsEntity;
import com.example.laba11.Room.App;
import com.example.laba11.Room.FavoriteReceipt.FavoriteReceiptDao;
import com.example.laba11.Room.FavoriteReceipt.FavoriteReceiptEntity;
import com.example.laba11.Room.RoomDB;
import com.example.laba11.Room.UsersFavoriteReceipt.Users_FavoriteReceiptDao;
import com.example.laba11.databinding.FragmentNotificationsBinding;
import com.example.laba11.ui.home.HomeAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NotificationsFragment extends Fragment {

    private FragmentNotificationsBinding binding;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter nAdapter;

    private List<String> recyclerDataNames = new ArrayList<>();
    private List<FavoriteReceiptEntity> recyclerData = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        NotificationsViewModel notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        recyclerView = binding.favoriteReceipts;

        nAdapter = new NotificationsAdapter(recyclerDataNames, recyclerData, getContext());

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setAdapter(nAdapter);

        RoomDB db = App.getInstance().getDatabase();
        FavoriteReceiptDao favoriteReceiptDao = db.favoriteReceiptDao();
        Users_FavoriteReceiptDao usersFavoriteReceiptDao = db.usersFavoriteReceiptDao();

        List<FavoriteReceiptEntity> userFavoriteReceipts = new ArrayList<>();
        List<String> userFavoriteReceiptsNames = new ArrayList<>();

        List<Integer> receiptsIds = usersFavoriteReceiptDao.getReceiptsId(MainActivity.currentId);

        for (int i = 0; i < receiptsIds.size(); i++) {
            FavoriteReceiptEntity currentFavoriteReceipt = favoriteReceiptDao
                    .getReceipt(receiptsIds.get(i));

            userFavoriteReceipts.add(i, currentFavoriteReceipt);
            userFavoriteReceiptsNames.add(i, currentFavoriteReceipt.name);
        }

        recyclerDataNames.clear();
        recyclerData.clear();

        recyclerDataNames.addAll(userFavoriteReceiptsNames);
        recyclerData.addAll(userFavoriteReceipts);

        nAdapter.notifyDataSetChanged();

        return root;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}