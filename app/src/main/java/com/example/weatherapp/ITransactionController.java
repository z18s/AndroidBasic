package com.example.weatherapp;

import android.app.Fragment;

public interface ITransactionController {
    void setDefaultFragments();
    void resetDefaultFragments();
    void removeCurrentTempFragment();

    void startAddFragmentsTransaction(int containerId, Fragment fragment);
    void startReplaceFragmentsTransaction(int containerId, Fragment fragment);
    void startRemoveFragmentsTransaction(Fragment fragment);

    void startPopBackStack();
}