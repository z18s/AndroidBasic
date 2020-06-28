package com.example.weatherapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.Nullable;

import com.example.weatherapp.databinding.ActivityCitiesBinding;

public class CitySwapActivity extends Activity {

    private ActivityCitiesBinding binding;

    private String currentCity = null;
    private String chosenCity = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCitiesBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());

        currentCity = getIntent().getStringExtra(Constants.CURRENT_CITY);
        binding.textCitySelect.setText(currentCity);

        selectCityByButton();
        selectCityByList();
    }

    private void selectCityByButton() {
        binding.buttonSwapCity.setOnClickListener((view) -> {
            chosenCity = binding.textCitySelect.getText().toString();
            returnToMainActivity();
        });
    }

    private void selectCityByList() {
        binding.citiesList.setOnItemClickListener((adapterView, view, i, l) -> {
            chosenCity = binding.citiesList.getItemAtPosition(i).toString();
            returnToMainActivity();
        });
    }

    private void returnToMainActivity() {
        if (chosenCity != null && !chosenCity.equals("")) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra(Constants.CHOSEN_CITY, chosenCity);
            setResult(RESULT_OK, intent);
            finish();
        }
    }


}