package com.example.weatherapp;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.weatherapp.databinding.FragmentCityHeaderBinding;

public class CityHeaderFragment extends Fragment implements Observer {

    private FragmentCityHeaderBinding binding;

    private CitySwapFragment citySwapFragment;

    public static CityHeaderFragment create() {
        CityHeaderFragment fragment = new CityHeaderFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup
            container, @Nullable Bundle savedInstanceState) {
        binding = FragmentCityHeaderBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        swapCityListener();
        appSettingsListener();
    }

    @Override
    public void updateCurrentCity() {
        String cityName = MainActivity.city.getName();
        binding.currentCity.setText(cityName);
    }

    private void swapCityListener() {
        binding.swapCity.setOnClickListener((view) -> {
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            initCitySwapFragment();
            ft.replace(R.id.fragment_container_main, citySwapFragment);
            ft.addToBackStack(null);
            ft.commit();
        });
    }

    private void initCitySwapFragment() {
        if (citySwapFragment == null) {
            citySwapFragment = CitySwapFragment.create();
        }
    }

    private void appSettingsListener() {
        binding.appSettings.setOnClickListener((view) -> {
            Intent intent = new Intent(getActivity(), SettingsActivity.class);
            startActivityForResult(intent, Constants.REQ_CODE_SETTINGS);
        });
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
    }
}