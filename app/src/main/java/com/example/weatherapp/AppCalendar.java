package com.example.weatherapp;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public final class AppCalendar {

    private static AppCalendar instance;

    static {
        instance = new AppCalendar();
    }

    private AppCalendar() {
        init();
    }

    private final int CALENDAR_LENGTH = 7;
    private Calendar[] dates = new GregorianCalendar[CALENDAR_LENGTH];

    private void init() {
        for (int i = 0; i < CALENDAR_LENGTH; i++) {
            dates[i] = Calendar.getInstance();
            dates[i].add(Calendar.DAY_OF_YEAR, i);
        }
    }

    public Calendar[] getDates() {
        return dates;
    }

    public Calendar getDate(int i) {
        return dates[i];
    }

    public String getDateString(int i, String type) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(type);
        return dateFormat.format(dates[i].getTime());
    }

    public static AppCalendar getInstance() {
        return instance;
    }
}