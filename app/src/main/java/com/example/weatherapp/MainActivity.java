package com.example.weatherapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.Toast;

import com.example.weatherapp.databinding.ActivityMainBinding;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    Date dateNow = new Date();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());

        fillDate();

        swapCityListener();
        appSettingsListener();
        todayAboutListener();

        //log("Activity created");
    }

    private void fillDate() {
        binding.dateTodayFull.setText(new SimpleDateFormat("dd/MM/yyyy").format(dateNow));
        binding.dateToday.setText(new SimpleDateFormat("dd/MM").format(dateNow));
    }

    private void swapCityListener() {
        binding.swapCity.setOnClickListener((view) -> {
            Intent intent = new Intent(this, CitySwapActivity.class);
            intent.putExtra(Constants.CURRENT_CITY, R.id.currentCity);
            startActivityForResult(intent, Constants.REQ_CODE_SWAP_CITY);
        });
    }

    private void appSettingsListener() {
        binding.appSettings.setOnClickListener((view) -> {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivityForResult(intent, Constants.REQ_CODE_SETTINGS);
        });
    }

    private void todayAboutListener() {
        binding.buttonTodayAbout.setOnClickListener((view) -> {
            String url = (String) getResources().getText(R.string.today_about_url);
            String date = new SimpleDateFormat("d MMMM").format(dateNow);
            Uri uri = Uri.parse(url + date);

            Intent browser = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(browser);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode != Constants.REQ_CODE_SWAP_CITY) {
            super.onActivityResult(requestCode, resultCode, data);
            return;
        }

        if (resultCode == RESULT_OK) {
            String chosenCity = data.getStringExtra(Constants.CHOSEN_CITY);
            if (chosenCity != null) {
                binding.currentCity.setText(chosenCity);
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        //log("Activity started");
    }

    @Override
    protected void onRestoreInstanceState(Bundle saveInstanceState) {
        super.onRestoreInstanceState(saveInstanceState);

        //log("Activity restored");
    }

    @Override
    protected void onResume() {
        super.onResume();

        //log("Activity resumed");
    }

    @Override
    protected void onPause() {
        super.onPause();

        //log("Activity paused");
    }

    @Override
    protected void onSaveInstanceState(Bundle saveInstanceState) {
        super.onSaveInstanceState(saveInstanceState);

        //log("Activity saved");
    }

    @Override
    protected void onStop() {
        super.onStop();

        //log("Activity stopped");
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        //log("Activity restarted");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //log("Activity destroyed");
    }

    private void log(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
        Log.d("INFO", message);
    }
}