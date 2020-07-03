package com.example.weatherapp;

import android.app.Fragment;

public interface ITransactionController {
    void startAddFragmentsTransaction(Fragment... fragments);
    void startReplaceFragmentsTransaction(Fragment... fragments);
    void startRemoveFragmentsTransaction(Fragment... fragments);
}