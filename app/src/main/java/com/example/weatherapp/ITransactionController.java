package com.example.weatherapp;

import android.app.Fragment;

public interface ITransactionController {
    void setDefaultFragments();
    void resetDefaultFragments();

    void startAddFragmentsTransaction(Fragment... fragments);
    void startRemoveFragmentsTransaction(Fragment... fragments);
    void startReplaceFragmentsTransaction(Fragment fragment);

    void startPopBackStack();
}