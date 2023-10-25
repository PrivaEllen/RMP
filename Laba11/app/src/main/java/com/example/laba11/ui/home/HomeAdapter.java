package com.example.laba11.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.laba11.R;

import java.util.Arrays;
import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewHolder> {
    private List<String> hdataSet;

    public HomeAdapter(List<String> hdataSet) {
        this.hdataSet = hdataSet;
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

    public static class HomeViewHolder extends RecyclerView.ViewHolder {
        public TextView hTextView;
        public HomeViewHolder(View itemView) {
            super(itemView);
            hTextView = (TextView)itemView.findViewById(R.id.text_receipt);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Navigation.findNavController(v).navigate(R.id.navigation_dashboard);
                }
            });
        }
    }
}
