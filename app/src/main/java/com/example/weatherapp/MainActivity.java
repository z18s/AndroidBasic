package com.example.weatherapp;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.weatherapp.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements ITransactionController, Observer {

    private ActivityMainBinding binding;

    private Publisher publisher = new Publisher();
    static CurrentCity city = new CurrentCity();

    private HeaderFragment headerFragment;
    private HomeTempFragment homeTempFragment;
    private TempListFragment tempListFragment;
    private CitySwapFragment citySwapFragment;
    private SettingsFragment settingsFragment;

    private BottomNavigationView.OnNavigationItemSelectedListener OnNavigationItemSelectedListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());

        initMenu();

        initHeaderFragment();
        initHomeFragments();

        setHeaderFragment();
        setHomeFragments();

        initPublisher();
        initCurrentCity();
    }

    @Override
    protected void onResume() {
        super.onResume();

        updateHeaderTextByCurrentCity();
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

    private void initCurrentCity() {
        //city.setName(getCurrentCityText(), false);
    }

    private void initHeaderFragment() {
        headerFragment = HeaderFragment.create();
    }

    private void initHomeFragments() {
        homeTempFragment = HomeTempFragment.create();
        tempListFragment = TempListFragment.create();
    }

    private void initPublisher() {
        publisher.subscribe(this);
        city.setPublisher(publisher);
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
        updateHeaderTextByCurrentCity();
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

    private void updateHeaderTextByCurrentCity() {
        setHeaderText(city.getName());
    }

    private void updateHeaderTextForSettings() {
        setHeaderText(getResources().getString(R.string.title_settings));
    }

    private void setHeaderText(String text) {
        headerFragment.setHeaderText(text);
    }

    @Override
    public void updateCurrentCity() {
        updateHeaderTextByCurrentCity();
    }

    protected void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}