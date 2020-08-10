package com.example.weatherapp;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.Log;

import com.example.weatherapp.model.WeatherData;

import java.util.Observable;

public final class AppValuesFormatter extends Observable {

    private static AppValuesFormatter instance;

    private AppCalendar calendar = AppCalendar.getInstance();

    private WeatherData weather;

    static {
        instance = new AppValuesFormatter();
    }

    private AppValuesFormatter() {
    }

    public static AppValuesFormatter getInstance() {
        return instance;
    }

    public WeatherData getWeather() {
        return weather;
    }

    public void setWeather(WeatherData weather) {
        this.weather = weather;

        Log.d("DEBUG_ValuesFormatter", "notifyObservers");

        setChanged();
        notifyObservers(weather);
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

    public String getTempCurrentString(Context context) {
        return getTempString(context, getTempValue());
    }

    public String getTempMinString(Context context) {
        return getTempString(context, getTempMinValue());
    }

    public String getTempMaxString(Context context) {
        return getTempString(context, getTempMaxValue());
    }

    public String getTempString(Context context, double tempKelvin) {
        int temp = (int) (tempKelvin - 273.15f);
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

    public int getTempDayIconId(Context context) {
        int[] resIds = getIdsFromArrayResources(context, R.array.icon_day);
        return resIds[0];
    }

    public int getTempNightIconId(Context context) {
        int[] resIds = getIdsFromArrayResources(context, R.array.icon_night);
        return resIds[0];
    }

    public String getWindVelocityString() {
        return String.format("%.1f", getWindVelocityValue());
    }

    public String getWindDirectionString(Context context) {
        String[] windDirections = context.getResources().getStringArray(R.array.windDirections);
        int zone = Math.round(getWindDirectionDegree() / 45.0f);
        if (zone > 7) {
            zone %= 7;
        }
        return windDirections[zone];
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

    public String getDefaultString() {
        return "";
    }

    // Values filled by WeatherData

    public double getTempValue() {
        return weather.getMain().getTemp();
    }

    public double getTempMinValue() {
        return weather.getMain().getTempMin();
    }

    public double getTempMaxValue() {
        return weather.getMain().getTempMax();
    }

    public double getWindVelocityValue() {
        return weather.getWind().getSpeed();
    }

    public int getWindDirectionDegree() {
        return weather.getWind().getDeg();
    }
}