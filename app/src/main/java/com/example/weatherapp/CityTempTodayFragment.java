package com.example.weatherapp;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.weatherapp.databinding.FragmentCityTempTodayBinding;

public class CityTempTodayFragment extends Fragment {

    private FragmentCityTempTodayBinding binding;

    public AppCalendar calendar = AppCalendar.getInstance();

    public static CityTempTodayFragment create() {
        return new CityTempTodayFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentCityTempTodayBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fillDate();

        todayAboutListener();
    }

    private void fillDate() {
        binding.dateTodayFull.setText(calendar.getDateString(0, "dd/MM/yyyy"));
    }

    private void todayAboutListener() {
        binding.buttonTodayAbout.setOnClickListener((view) -> {
            String url = (String) getResources().getText(R.string.today_about_url);
            String date = calendar.getDateString(0, "d MMMM");
            Uri uri = Uri.parse(url + date);

            Intent browser = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(browser);
        });
    }
}