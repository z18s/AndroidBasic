package com.example.weatherapp;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.weatherapp.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.net.MalformedURLException;
import java.util.Observable;
import java.util.Observer;

public class MainActivity extends AppCompatActivity implements ITransactionController, IConnectionController, Observer {

    private ActivityMainBinding binding;
    private AppConnection connection;

    private AppValuesFormatter valuesFormatter = AppValuesFormatter.getInstance();
    private String homeCity = "London,uk";

    private HeaderFragment headerFragment;
    private HomeTempFragment homeTempFragment;
    private TempListFragment tempListFragment;
    private CitySwapFragment citySwapFragment;
    private SettingsFragment settingsFragment;

    private BottomNavigationView.OnNavigationItemSelectedListener OnNavigationItemSelectedListener;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());

        initObserver();
        initConnection(new AppConnection(homeCity));

        initMenu();

        initHeaderFragment();
        initHomeFragments();

        setHeaderFragment();
        setHomeFragments();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void initConnection(AppConnection connection) {
        final Handler handler = new Handler();
        this.connection = connection;

        new Thread(() -> {
            try {
                connection.connect(handler);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void initObserver() {
        valuesFormatter.addObserver(this);
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
        resetHomeFragments();
    }

    private void clickOnMenuSettings() {
        setSettingsFragment();
    }

    private void clickOnMenuAbout() {
        new AboutFragment().show(getSupportFragmentManager(), "About");
    }

    private void initHeaderFragment() {
        headerFragment = HeaderFragment.create();
    }

    private void initHomeFragments() {
        homeTempFragment = HomeTempFragment.create();
        tempListFragment = TempListFragment.create();
    }

    private void initCitySwapFragment() {
        if (citySwapFragment == null) {
            citySwapFragment = CitySwapFragment.create();
        }
    }

    private void initSettingsFragment() {
        if (settingsFragment == null) {
            settingsFragment = SettingsFragment.create();
        }
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
        ft.commit();
    }

    @Override
    public void startRemoveFragmentsTransaction(Fragment fragment) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.remove(fragment);
        ft.commit();
    }

    @Override
    public void setHeaderFragment() {
        startAddFragmentsTransaction(R.id.container_header, headerFragment);
    }

    @Override
    public void setHomeFragments() {
        startAddFragmentsTransaction(R.id.container_main, homeTempFragment);
        startAddFragmentsTransaction(R.id.container_footer, tempListFragment);
    }

    @Override
    public void resetHomeFragments() {
        startReplaceFragmentsTransaction(R.id.container_main, homeTempFragment);
        startReplaceFragmentsTransaction(R.id.container_footer, tempListFragment);
    }

    @Override
    public void setCitySwapFragment() {
        initCitySwapFragment();
        startReplaceFragmentsTransaction(R.id.container_main, citySwapFragment);
        startRemoveFragmentsTransaction(tempListFragment);
    }

    @Override
    public void setSettingsFragment() {
        initSettingsFragment();
        updateHeaderTextForSettings();
        startReplaceFragmentsTransaction(R.id.container_main, settingsFragment);
        startRemoveFragmentsTransaction(tempListFragment);
    }

    private void updateHeaderTextByCity() {
        setHeaderText(valuesFormatter.getWeather().getName());
    }

    private void updateHeaderTextForSettings() {
        setHeaderText(getResources().getString(R.string.title_settings));
    }

    private void setHeaderText(String text) {
        headerFragment.setHeaderText(text);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void update(Observable observable, Object o) {
        updateHeaderTextByCity();

        homeTempFragment.updateData();
        tempListFragment.updateData();
    }
}