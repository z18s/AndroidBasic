package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Date dateNow = new Date();

        TextView dateTodayFull = findViewById(R.id.dateTodayFull);
        dateTodayFull.setText(new SimpleDateFormat("dd/MM/yyyy").format(dateNow));

        TextView dateToday = findViewById(R.id.dateToday);
        dateToday.setText(new SimpleDateFormat("dd/MM").format(dateNow));

        Toast.makeText(getApplicationContext(), "Activity created", Toast.LENGTH_SHORT).show();
        Log.d("INFO", "Activity created");
    }

    @Override
    protected void onStart() {
        super.onStart();

        Toast.makeText(getApplicationContext(), "Activity started", Toast.LENGTH_SHORT).show();
        Log.d("INFO", "Activity started");
    }

    @Override
    protected void onStop() {
        super.onStop();

        Toast.makeText(getApplicationContext(), "Activity stopped", Toast.LENGTH_SHORT).show();
        Log.d("INFO", "Activity stopped");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Toast.makeText(getApplicationContext(), "Activity destroyed", Toast.LENGTH_SHORT).show();
        Log.d("INFO", "Activity destroyed");
    }

    @Override
    protected void onPause() {
        super.onPause();

        Toast.makeText(getApplicationContext(), "Activity paused", Toast.LENGTH_SHORT).show();
        Log.d("INFO", "Activity paused");
    }

    @Override
    protected void onResume() {
        super.onResume();

        Toast.makeText(getApplicationContext(), "Activity resumed", Toast.LENGTH_SHORT).show();
        Log.d("INFO", "Activity resumed");
    }
}