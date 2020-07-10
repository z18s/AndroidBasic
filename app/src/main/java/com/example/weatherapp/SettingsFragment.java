package com.example.weatherapp;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.weatherapp.databinding.FragmentSettingsBinding;

public class SettingsFragment extends Fragment {

    private FragmentSettingsBinding binding;
    private ITransactionController transactionController;

    private Switch themeSwitch;

    public static SettingsFragment create() {
        return new SettingsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentSettingsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initSettings();
        initListeners();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        transactionController = (ITransactionController) activity;
    }

    private void initSettings() {
        themeSwitch = binding.themeSettings;
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            themeSwitch.setChecked(true);
        }
    }

    private void initListeners() {
        initThemeSwitchListener();
        initConfirmButtonListener();
    }

    private void initThemeSwitchListener() {
        themeSwitch.setOnCheckedChangeListener((compoundButton, isChecked) -> {
            setTheme(isChecked);
        });
    }

    private void setTheme(boolean isDark) {
        transactionController.startRemoveFragmentsTransaction(this);
        if (isDark) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    private void initConfirmButtonListener() {
        binding.settingsConfirmButton.setOnClickListener((view) -> {
            transactionController.resetHomeFragments();
        });
    }
}