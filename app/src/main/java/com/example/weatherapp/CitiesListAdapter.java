package com.example.weatherapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CitiesListAdapter extends RecyclerView.Adapter<CitiesListAdapter.CitiesListViewHolder> {

    private OnClickListener listener;
    private ArrayList<String> citiesList = new ArrayList<>();

    public void setList(List<String> list) {
        citiesList.clear();
        citiesList.addAll(list);
        notifyDataSetChanged();
    }

    public void setOnClickListener(OnClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public CitiesListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.city_swap_item, parent, false);
        return new CitiesListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CitiesListViewHolder holder, int position) {
        holder.bind(citiesList.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return citiesList.size();
    }

    static class CitiesListViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;

        public CitiesListViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.citiesListItemText);
        }

        public void bind(String item, OnClickListener listener) {
            textView.setText(item);

            textView.setOnClickListener((v) -> listener.onClick(getAdapterPosition()));
        }
    }

    interface OnClickListener {
        void onClick(int position);
    }
}