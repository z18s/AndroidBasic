package com.example.weatherapp;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.weatherapp.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements ITransactionController, Observer {

    private ActivityMainBinding binding;

    private Publisher publisher = new Publisher();

    static CurrentCity city = new CurrentCity();

    private CityHeaderFragment cityHeaderFragment;
    private CityTempNowFragment cityTempNowFragment;
    private CityTempListFragment cityTempListFragment;

    private BottomNavigationView.OnNavigationItemSelectedListener OnNavigationItemSelectedListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());

        initMenu();
        initFragments();

        setMainHeader();
        setDefaultFragments();
    }

    private void initMenu() {
        initMenuListener();

        BottomNavigationView bottomMenu = findViewById(R.id.navigation_view);
        bottomMenu.setOnNavigationItemSelectedListener(OnNavigationItemSelectedListener);
    }

    private void initMenuListener() {
        OnNavigationItemSelectedListener = (item) -> {
            switch (item.getItemId()) {
                case R.id.menu_home:
                    clickOnMenuHome();
                    return true;
                case R.id.menu_swap_city:
                    clickOnMenuSwapCity();
                    return true;
                case R.id.menu_settings:
                    clickOnMenuSettings();
                    return true;
                case R.id.menu_about:
                    clickOnMenuAbout();
                    return true;
            }
            return false;
        };
    }

    private void clickOnMenuHome() {
    }

    private void clickOnMenuSwapCity() {
    }

    private void clickOnMenuSettings() {
    }

    private void clickOnMenuAbout() {
        AboutFragment aboutFragment = new AboutFragment();
        aboutFragment.show(getSupportFragmentManager(), "About");
    }

    private void initFragments() {
        //city.setName(getCurrentCityText(), false);

        publisher.subscribe(this);
        city.setPublisher(publisher);

        cityHeaderFragment = CityHeaderFragment.create();
        cityTempNowFragment = CityTempNowFragment.create();
        cityTempListFragment = CityTempListFragment.create();
    }

//    private String getCurrentCityText() {
//        return ((TextView) findViewById(R.id.currentCity)).getText().toString();
//    }

    private void setCurrentCityText(String name) {
        ((TextView) findViewById(R.id.currentCity)).setText(name);
    }

    @Override
    public void startAddFragmentsTransaction(int containerId, Fragment fragment) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.add(containerId, fragment);
        ft.commit();
    }

    @Override
    public void startReplaceFragmentsTransaction(int containerId, Fragment fragment) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(containerId, fragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void startRemoveFragmentsTransaction(Fragment fragment) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.remove(fragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void startPopBackStack() {
        getSupportFragmentManager().popBackStack();
    }

    private void setMainHeader() {
        startAddFragmentsTransaction(R.id.fragment_container_header, cityHeaderFragment);
    }

    @Override
    public void setDefaultFragments() {
        startAddFragmentsTransaction(R.id.fragment_container_temp_now, cityTempNowFragment);
        startAddFragmentsTransaction(R.id.fragment_container_main, cityTempListFragment);
    }

    @Override
    public void resetDefaultFragments() {
        startReplaceFragmentsTransaction(R.id.fragment_container_temp_now, cityTempNowFragment);
        startReplaceFragmentsTransaction(R.id.fragment_container_main, cityTempListFragment);
    }

    @Override
    public void removeCurrentTempFragment() {
        startRemoveFragmentsTransaction(cityTempNowFragment);
    }

    @Override
    public void updateCurrentCity() {
        setCurrentCityText(city.getName());
    }

    private void log(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
        Log.d("INFO", message);
    }
}