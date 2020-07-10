package com.example.weatherapp;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class CurrentCity implements Serializable {

    private String name = "";
    private Publisher publisher;

    public CurrentCity() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        setName(name, true);
    }

    public void setName(String name, boolean isUpdate) {
        this.name = name;
        if (isUpdate) {
            updateCurrentCity();
        }
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    @NonNull
    @Override
    public String toString() {
        return getName();
    }

    private void updateCurrentCity() {
        publisher.notifyObservers();
    }
}