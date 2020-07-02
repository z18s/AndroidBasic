package com.example.weatherapp;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.weatherapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private Publisher publisher = new Publisher();

    static CurrentCity city = new CurrentCity();

    private CityHeaderFragment cityHeaderFragment;
    private CityTempTodayFragment cityTempTodayFragment;
    private CityTempListFragment cityTempListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());

        initFragments();
        startFragmentsTransaction();
    }

    private void initFragments() {
        String defaultCityName = ((TextView) findViewById(R.id.currentCity)).getText().toString();
        city.setName(defaultCityName, false);

        cityHeaderFragment = CityHeaderFragment.create();
        publisher.subscribe(cityHeaderFragment);

        city.setPublisher(publisher);

        cityTempTodayFragment = CityTempTodayFragment.create();
        cityTempListFragment = CityTempListFragment.create();
    }

    private void startFragmentsTransaction() {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        setFragments(ft);
        ft.commit();
    }

    void setFragments(FragmentTransaction ft) {
        ft.add(R.id.fragment_container_main, cityTempTodayFragment);
        ft.add(R.id.fragment_container_main, cityTempListFragment);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onRestoreInstanceState(Bundle saveInstanceState) {
        super.onRestoreInstanceState(saveInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle saveInstanceState) {
        super.onSaveInstanceState(saveInstanceState);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void log(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
        Log.d("INFO", message);
    }
}