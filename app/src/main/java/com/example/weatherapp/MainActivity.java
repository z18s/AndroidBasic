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
    protected void onRestoreInstanceState(Bundle saveInstanceState) {
        super.onRestoreInstanceState(saveInstanceState);

        Toast.makeText(getApplicationContext(), "Activity restored", Toast.LENGTH_SHORT).show();
        Log.d("INFO", "Activity restored");
    }

    @Override
    protected void onResume() {
        super.onResume();

        Toast.makeText(getApplicationContext(), "Activity resumed", Toast.LENGTH_SHORT).show();
        Log.d("INFO", "Activity resumed");
    }

    @Override
    protected void onPause() {
        super.onPause();

        Toast.makeText(getApplicationContext(), "Activity paused", Toast.LENGTH_SHORT).show();
        Log.d("INFO", "Activity paused");
    }

    @Override
    protected void onSaveInstanceState(Bundle saveInstanceState) {
        super.onSaveInstanceState(saveInstanceState);

        Toast.makeText(getApplicationContext(), "Activity saved", Toast.LENGTH_SHORT).show();
        Log.d("INFO", "Activity saved");
    }

    @Override
    protected void onStop() {
        super.onStop();

        Toast.makeText(getApplicationContext(), "Activity stopped", Toast.LENGTH_SHORT).show();
        Log.d("INFO", "Activity stopped");
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        Toast.makeText(getApplicationContext(), "Activity restarted", Toast.LENGTH_SHORT).show();
        Log.d("INFO", "Activity restarted");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Toast.makeText(getApplicationContext(), "Activity destroyed", Toast.LENGTH_SHORT).show();
        Log.d("INFO", "Activity destroyed");
    }
}