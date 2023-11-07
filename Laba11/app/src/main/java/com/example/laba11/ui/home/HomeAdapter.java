package com.example.laba11.ui.home;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.laba11.JSONStructure;
import com.example.laba11.MainActivity;
import com.example.laba11.R;
import com.example.laba11.Room.AllReceipts.AllReceiptsDao;
import com.example.laba11.Room.AllReceipts.AllReceiptsEntity;
import com.example.laba11.Room.App;
import com.example.laba11.Room.FavoriteReceipt.FavoriteReceiptDao;
import com.example.laba11.Room.FavoriteReceipt.FavoriteReceiptEntity;
import com.example.laba11.Room.RoomDB;
import com.example.laba11.Room.UsersFavoriteReceipt.Users_FavoriteReceiptDao;
import com.example.laba11.Room.UsersFavoriteReceipt.Users_FavoriteReceiptEntity;

import java.util.ArrayList;
import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewHolder> {
    private List<String> hdataSet;
    static List<AllReceiptsEntity> hdata;

    private Context context;

    public HomeAdapter(List<String> hdataSet, List<AllReceiptsEntity> hdata, Context context) {
        this.hdataSet = hdataSet;
        this.hdata = hdata;
        this.context = context;
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.receipt, parent, false);
        return new HomeViewHolder(itemView);
    }

    @Override
    public int getItemCount() {
        return this.hdataSet.size();
    }


    @Override
    public void onBindViewHolder(HomeViewHolder holder, int position) {
        holder.hTextView.setText(this.hdataSet.get(position));
    }

    public class HomeViewHolder extends RecyclerView.ViewHolder {
        public TextView hTextView;
        public HomeViewHolder(View itemView) {
            super(itemView);
            hTextView = (TextView)itemView.findViewById(R.id.text_receipt);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TextView textView = (TextView) v.findViewById(R.id.text_receipt);

                    String currentText = textView.getText().toString();

                    AllReceiptsEntity currentReceipt = new AllReceiptsEntity();
                    for (int i = 0; i < hdata.size(); i++) {
                        if (hdata.get(i).name.compareTo(currentText) == 0) {
                            currentReceipt = hdata.get(i);
                        }
                    }

                    Bundle bundle = new Bundle();
                    bundle.putString("name", currentText);
                    bundle.putInt("calorie", currentReceipt.calorie);
                    bundle.putInt("time", currentReceipt.time);
                    bundle.putInt("difficulty", currentReceipt.difficulty);
                    bundle.putString("ingredients", currentReceipt.ingredients);

                    Navigation.findNavController(v).navigate(R.id.navigation_dashboard, bundle);
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    TextView textView = (TextView) v.findViewById(R.id.text_receipt);
                    String currentText = textView.getText().toString();

                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setCancelable(true)
                            .setMessage(currentText)
                            .setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    builder.setCancelable(true);
                                }
                            })
                            .setPositiveButton("In favorite", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    RoomDB db = App.getInstance().getDatabase();
                                    FavoriteReceiptDao favoriteReceiptDao = db.favoriteReceiptDao();
                                    FavoriteReceiptEntity favoriteReceiptEntity = new FavoriteReceiptEntity();

                                    AllReceiptsEntity currentReceipt = new AllReceiptsEntity();
                                    for (int i = 0; i < hdata.size(); i++) {
                                        if (hdata.get(i).name.compareTo(currentText) == 0) {
                                            currentReceipt = hdata.get(i);
                                        }
                                    }

                                    if (favoriteReceiptDao.getId(currentText) == 0) {
                                        favoriteReceiptEntity.name = currentText;
                                        favoriteReceiptEntity.calorie = currentReceipt.calorie;
                                        favoriteReceiptEntity.time = currentReceipt.time;
                                        favoriteReceiptEntity.ingredients = currentReceipt.ingredients;
                                        favoriteReceiptEntity.difficulty = currentReceipt.difficulty;

                                        favoriteReceiptDao.insert(favoriteReceiptEntity);
                                    }

                                    Users_FavoriteReceiptDao usersFavoriteReceiptDao = db.usersFavoriteReceiptDao();

                                    if (usersFavoriteReceiptDao.getId(favoriteReceiptDao.getId(currentText)) == 0) {
                                        Users_FavoriteReceiptEntity usersFavoriteReceiptEntity = new Users_FavoriteReceiptEntity();

                                        usersFavoriteReceiptEntity.userId = MainActivity.currentId;
                                        usersFavoriteReceiptEntity.favoriteReceiptId = favoriteReceiptDao.getId(currentText);
                                        usersFavoriteReceiptDao.insert(usersFavoriteReceiptEntity);
                                    }

                                    List<FavoriteReceiptEntity> allFavoriteReceipts = favoriteReceiptDao.getAll();
                                    List<FavoriteReceiptEntity> userFavoriteReceipts = new ArrayList<>();

                                    List<Integer> receiptsIds = usersFavoriteReceiptDao.getReceiptsId(MainActivity.currentId);

                                    for (int i = 0; i < receiptsIds.size(); i++) {
                                        FavoriteReceiptEntity currentFavoriteReceipt = favoriteReceiptDao
                                                .getReceipt(receiptsIds.get(i));

                                        userFavoriteReceipts.add(i, currentFavoriteReceipt);
                                    }

                                    Log.i("user favorite receipts", userFavoriteReceipts.toString());

                                    Log.i("favorite receipts", allFavoriteReceipts.toString());
                                }
                            });
                    builder.show();
                    return true;
                }
            });
        }
    }
}
