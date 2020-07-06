package com.example.weatherapp;

import android.app.Fragment;

public interface ITransactionController {
    void setDefaultFragments();
    void resetDefaultFragments();

    void startAddFragmentsTransaction(Fragment fragment);
    void startRemoveFragmentsTransaction(Fragment fragment);
    void startReplaceFragmentsTransaction(Fragment fragment);

    void startPopBackStack();
}