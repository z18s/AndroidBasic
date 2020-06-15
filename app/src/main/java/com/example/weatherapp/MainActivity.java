package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

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
    }
}