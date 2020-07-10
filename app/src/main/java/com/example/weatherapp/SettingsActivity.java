package com.example.weatherapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.Switch;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.weatherapp.databinding.ActivitySettingsBinding;

public class SettingsActivity extends Activity {

    private ActivitySettingsBinding binding;

    Switch themeSwitch;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySettingsBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());

        initSettings();
        initListeners();
    }

    private void initSettings() {
        themeSwitch = binding.themeSettings;
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            themeSwitch.setChecked(true);
        }
    }

    private void initListeners() {
        initDarkThemeSwitchListener();
    }

    private void initDarkThemeSwitchListener() {
        themeSwitch.setOnCheckedChangeListener((compoundButton, isChecked) -> setTheme(isChecked));
    }

    private void setTheme(boolean isDark) {
        if (isDark) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
        finish();
        startActivity(new Intent(SettingsActivity.this, MainActivity.class));
    }
}