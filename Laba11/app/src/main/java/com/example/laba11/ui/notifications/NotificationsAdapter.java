package com.example.laba11.ui.notifications;

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
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.laba11.MainActivity;
import com.example.laba11.R;
import com.example.laba11.Room.AllReceipts.AllReceiptsEntity;
import com.example.laba11.Room.App;
import com.example.laba11.Room.FavoriteReceipt.FavoriteReceiptDao;
import com.example.laba11.Room.FavoriteReceipt.FavoriteReceiptEntity;
import com.example.laba11.Room.RoomDB;
import com.example.laba11.Room.UsersFavoriteReceipt.Users_FavoriteReceiptDao;
import com.example.laba11.Room.UsersFavoriteReceipt.Users_FavoriteReceiptEntity;

import java.util.ArrayList;
import java.util.List;

public class NotificationsAdapter extends RecyclerView.Adapter<NotificationsAdapter.NotificationsViewHolder> {
    private List<String> ndataSet;
    static List<FavoriteReceiptEntity> ndata;

    private Context context;

    public NotificationsAdapter(List<String> ndataSet, List<FavoriteReceiptEntity> ndata, Context context) {
        this.ndataSet = ndataSet;
        this.ndata = ndata;
        this.context = context;
    }

    @NonNull
    @Override
    public NotificationsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.receipt, parent, false);
        return new NotificationsViewHolder(itemView);
    }

    @Override
    public int getItemCount() {
        return this.ndataSet.size();
    }


    @Override
    public void onBindViewHolder(NotificationsViewHolder holder, int position) {
        holder.hTextView.setText(this.ndataSet.get(position));
    }

    public class NotificationsViewHolder extends RecyclerView.ViewHolder {
        public TextView hTextView;
        public NotificationsViewHolder(View itemView) {
            super(itemView);
            hTextView = (TextView)itemView.findViewById(R.id.text_receipt);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TextView textView = (TextView) v.findViewById(R.id.text_receipt);

                    String currentText = textView.getText().toString();

                    FavoriteReceiptEntity currentReceipt = new FavoriteReceiptEntity();
                    for (int i = 0; i < ndata.size(); i++) {
                        if (ndata.get(i).name.compareTo(currentText) == 0) {
                            currentReceipt = ndata.get(i);
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


        }
    }
}
