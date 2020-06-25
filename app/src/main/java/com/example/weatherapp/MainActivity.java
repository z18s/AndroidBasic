package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weatherapp.databinding.ActivityMainBinding;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());

        fillDate();



        //log("Activity created");
    }

    private void fillDate() {
        Date dateNow = new Date();

        TextView dateTodayFull = findViewById(R.id.dateTodayFull);
        dateTodayFull.setText(new SimpleDateFormat("dd/MM/yyyy").format(dateNow));

        binding.dateToday.setText(new SimpleDateFormat("dd/MM").format(dateNow));
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