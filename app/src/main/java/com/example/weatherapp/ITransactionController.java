package com.example.weatherapp;

import android.app.Fragment;

public interface ITransactionController {
    void setHeaderFragment();
    void setHomeFragments();
    void resetHomeFragments();
    void setCitySwapFragment();
    void setSettingsFragment();

    void startAddFragmentsTransaction(int containerId, Fragment fragment);
    void startReplaceFragmentsTransaction(int containerId, Fragment fragment);
    void startRemoveFragmentsTransaction(Fragment fragment);
}