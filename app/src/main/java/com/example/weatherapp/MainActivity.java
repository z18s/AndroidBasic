package com.example.weatherapp;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.weatherapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements ITransactionController, Observer {

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

        setDefaultFragments();
    }

    private void initFragments() {
        city.setName(getCurrentCityText(), false);

        publisher.subscribe(this);
        city.setPublisher(publisher);

        cityHeaderFragment = CityHeaderFragment.create();
        cityTempTodayFragment = CityTempTodayFragment.create();
        cityTempListFragment = CityTempListFragment.create();
    }

    private String getCurrentCityText() {
        return ((TextView) findViewById(R.id.currentCity)).getText().toString();
    }

    private void updateCurrentCityText() {
        ((TextView) findViewById(R.id.currentCity)).setText(city.getName());
    }

    @Override
    public void startAddFragmentsTransaction(Fragment... fragments) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        for (Fragment fragment : fragments) {
            ft.add(R.id.fragment_container_main, fragment);
        }
        ft.commit();
    }

    @Override
    public void startReplaceFragmentsTransaction(Fragment fragment) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_container_main, fragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void startRemoveFragmentsTransaction(Fragment... fragments) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        for (Fragment fragment : fragments) {
            ft.remove(fragment);
        }
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void startPopBackStack() {
        FragmentManager fm = getSupportFragmentManager();
        fm.popBackStack();
    }

    @Override
    public void setDefaultFragments() {
        startAddFragmentsTransaction(cityTempListFragment);
    }

    @Override
    public void resetDefaultFragments() {
        startReplaceFragmentsTransaction(cityTempListFragment);
    }

    @Override
    public void updateCurrentCity() {
        updateCurrentCityText();
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