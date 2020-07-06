package com.example.weatherapp;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.weatherapp.databinding.FragmentCitySwapBinding;

public class CitySwapFragment extends Fragment {

    private FragmentCitySwapBinding binding;
    private ITransactionController transactionController;

    private String chosenCity = null;

    public static CitySwapFragment create() {
        return new CitySwapFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentCitySwapBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initEditTextField();
        clearDefaultTextListener();

        selectCityByButton();
        selectCityByList();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        transactionController = (ITransactionController) activity;
    }

    private void initEditTextField() {
        String cityName = MainActivity.city.getName();
        binding.textCitySelect.setText(cityName);
    }

    private void clearDefaultTextListener() {
        binding.textCitySelect.setOnClickListener((view) -> {
            binding.textCitySelect.setText(null);
        });
    }

    private void selectCityByButton() {
        binding.buttonSwapCity.setOnClickListener((view) -> {
            chosenCity = binding.textCitySelect.getText().toString();
            returnBack();
        });
    }

    private void selectCityByList() {
        binding.citiesList.setOnItemClickListener((adapterView, view, i, l) -> {
            chosenCity = binding.citiesList.getItemAtPosition(i).toString();
            returnBack();
        });
    }

    private void returnBack() {
        if (chosenCity != null && !chosenCity.equals("")) {
            MainActivity.city.setName(chosenCity);
            chosenCity = null;
            transactionController.resetDefaultFragments();
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
    }
}