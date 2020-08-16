package com.example.weatherapp;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.example.weatherapp.model.WeatherData;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.stream.Collectors;

import javax.net.ssl.HttpsURLConnection;

public class AppConnection {

    private AppValuesFormatter valuesFormatter = AppValuesFormatter.getInstance();

    private final String KEY = "8a4f2513a83690c3e3741076c4029169";
    private String cityName;
    private String url;

    public AppConnection(String cityName) {
        this.cityName = cityName;
        initUrl();
    }

    @SuppressLint("DefaultLocale")
    private void initUrl() {
        url = String.format("https://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s", cityName, KEY);

        Log.d("DEBUG_AppConnection", "initUrl - " + url);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void connect(Handler handler) throws MalformedURLException {

        final URL uri = new URL(url);
        HttpsURLConnection urlConnection = null;

        try {
            urlConnection = (HttpsURLConnection) uri.openConnection();

            Log.d("DEBUG_AppConnection", "uri.openConnection");

            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10000);

            Log.d("DEBUG_AppConnection", "connect - Ok");

            BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String result = getLines(in);

            Log.d("DEBUG_AppConnection", "result - " + result);

            final WeatherData weather = getWeatherDataFromJson(result);

            handler.post(() -> {
                valuesFormatter.setWeather(weather);
            });

        } catch (Exception e) {
            e.printStackTrace();
            Log.d("DEBUG_AppConnection", "exception - " + e.getMessage());
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
                Log.d("DEBUG_AppConnection", "urlConnection.disconnect");
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private String getLines(BufferedReader in) {
        return in.lines().collect(Collectors.joining("\n"));
    }

    private WeatherData getWeatherDataFromJson(String str) {
        return new Gson().fromJson(str, WeatherData.class);
    }
}