package com.example.weatherapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.ListView;

import androidx.annotation.Nullable;

import com.example.weatherapp.databinding.ActivityCitiesBinding;

public class CitySwapActivity extends Activity {

    ActivityCitiesBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCitiesBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());

        ListView citiesList = findViewById(R.id.citiesList);
    }
}