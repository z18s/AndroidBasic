package com.example.weatherapp;

import android.content.Context;
import android.content.res.TypedArray;

import java.util.Random;

public final class AppValuesFormatter {

    private static AppValuesFormatter instance;

    private AppCalendar calendar = AppCalendar.getInstance();

    static {
        instance = new AppValuesFormatter();
    }

    private AppValuesFormatter() {
    }

    public static AppValuesFormatter getInstance() {
        return instance;
    }

    public String getDateDayOfTheWeek(int i) {
        String result = calendar.getDateString(i, "E");
        result = (result.length() > 2) ? result.substring(0, 2) : result;
        result = result.substring(0, 1).toUpperCase() + result.substring(1);
        return result;
    }

    public String getDateDayAndMonth(int i) {
        return calendar.getDateString(i, "dd/MM");
    }

    public String getTempString(Context context, int min, int max) {
        int temp = getTempValue(min, max);
        String[] tempSigns = context.getResources().getStringArray(R.array.temp_signs);
        String tempSign = tempSigns[0];
        if (temp > 0) {
            tempSign = tempSigns[1];
        }
        return tempSign + temp;
    }

    public int getTempColorId(Context context, String tempString) {
        String currentSign = tempString.substring(0, 1);
        String[] tempSigns = context.getResources().getStringArray(R.array.temp_signs);
        if (currentSign.equals(tempSigns[1])) {
            return context.getResources().getColor(R.color.colorPlus);
        } else if (currentSign.equals(tempSigns[2])) {
            return context.getResources().getColor(R.color.colorMinus);
        } else {
            return context.getResources().getColor(R.color.colorZero);
        }
    }

    public int[] getIdsFromArrayResources(Context context, int arrayId) {
        TypedArray resArray = context.getResources().obtainTypedArray(arrayId);

        int len = resArray.length();
        int[] resIds = new int[len];

        for (int i = 0; i < len; i++) {
            resIds[i] = resArray.getResourceId(i, 0);
        }
        resArray.recycle();
        return resIds;
    }

    // Values filled by Random

    public int getTempValue(int min, int max) {
        return new Random().nextInt(max - min) + min;
    }

    public int getTempDayIconId(Context context) {
        int[] resIds = getIdsFromArrayResources(context, R.array.icon_day);
        return resIds[new Random().nextInt(resIds.length)];
    }

    public int getTempNightIconId(Context context) {
        int[] resIds = getIdsFromArrayResources(context, R.array.icon_night);
        return resIds[new Random().nextInt(resIds.length)];
    }

    public int getWindVelocityValue(int min, int max) {
        return new Random().nextInt(max - min) + min;
    }

    public String getWindDirectionValue(Context context) {
        String[] windDirections = context.getResources().getStringArray(R.array.windDirections);
        return windDirections[new Random().nextInt(8)];
    }
}