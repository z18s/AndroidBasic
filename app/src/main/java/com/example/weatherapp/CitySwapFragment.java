package com.example.weatherapp;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.weatherapp.databinding.FragmentCitySwapBinding;

public class CitySwapFragment extends Fragment {

    private FragmentCitySwapBinding binding;

    private String chosenCity = null;

    public static CitySwapFragment create() {
        CitySwapFragment fragment = new CitySwapFragment();
        return fragment;
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
            MainActivity.city.setName(chosenCity);
            chosenCity = null;
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.remove(this);
            ((MainActivity) getActivity()).setFragments(ft);
            ft.addToBackStack(null);
            ft.commit();
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
    }
}