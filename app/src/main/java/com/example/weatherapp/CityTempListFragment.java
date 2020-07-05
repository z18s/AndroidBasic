package com.example.weatherapp;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.weatherapp.databinding.FragmentCityTempListBinding;

public class CityTempListFragment extends Fragment {

    private FragmentCityTempListBinding binding;

    public AppCalendar calendar = AppCalendar.getInstance();

    public static CityTempListFragment create() {
        return new CityTempListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentCityTempListBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fillDate();
    }

    private void fillDate() {
        binding.dateToday.setText(calendar.getDateString(0, "dd/MM"));
    }
}