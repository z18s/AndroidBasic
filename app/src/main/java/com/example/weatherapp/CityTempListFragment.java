package com.example.weatherapp;

import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.weatherapp.databinding.FragmentCityTempListBinding;

import java.util.Random;

public class CityTempListFragment extends Fragment {

    private FragmentCityTempListBinding binding;

    public AppCalendar calendar = AppCalendar.getInstance();

    private final int LIST_SIZE = 7;

    public static CityTempListFragment create() {
        return new CityTempListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentCityTempListBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initList(view);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void initList(View view) {
        LinearLayout layoutView = (LinearLayout) view;
        LayoutInflater layoutInflater = getLayoutInflater();

        for (int i = 0; i < LIST_SIZE; i++) {
            View item = layoutInflater.inflate(R.layout.city_temp_item, layoutView, false);

            TextView tv1 = item.findViewById(R.id.dateDayOfTheWeek);
            String str1 = getDateDayOfTheWeek(i);
            tv1.setText(str1);

            TextView tv2 = item.findViewById(R.id.dateDayAndMonth);
            String str2 = getDateDayAndMonth(i);
            tv2.setText(str2);

            TextView tv3 = item.findViewById(R.id.textTempDay);
            String str3 = getTempDayString(10, 20);
            tv3.setText(str3);
            tv3.setTextColor(getTempDayColor(str3));

            TextView tv5 = item.findViewById(R.id.textTempNight);
            String str5 = getTempDayString(-5, 5);
            tv5.setText(str5);
            tv5.setTextColor(getTempDayColor(str5));

            TextView tv7 = item.findViewById(R.id.windVelocity);
            String str7 = String.valueOf(getWindVelocityDayValue(1, 4));
            tv7.setText(str7);

            TextView tv8 = item.findViewById(R.id.windDirection);
            String str8 = getWindDirectionDayValue();
            tv8.setText(str8);

            layoutView.addView(item);
        }
    }

    private String getDateDayOfTheWeek(int i) {
        String result = calendar.getDateString(i, "E");
        result = (result.length() > 2) ? result.substring(0, 2) : result;
        result = result.substring(0, 1).toUpperCase() + result.substring(1);
        return result;
    }

    private String getDateDayAndMonth(int i) {
        return calendar.getDateString(i, "dd/MM");
    }

    private String getTempDayString(int min, int max) {
        int temp = getTempDayValue(min, max);
        String[] tempSigns = getResources().getStringArray(R.array.temp_signs);
        String tempSign = tempSigns[0];
        if (temp > 0) {
            tempSign = tempSigns[1];
        }
        return tempSign + temp;
    }

    private int getTempDayColor(String tempString) {
        String currentSign = tempString.substring(0, 1);
        String[] tempSigns = getResources().getStringArray(R.array.temp_signs);
        if (currentSign.equals(tempSigns[1])) {
            return getResources().getColor(R.color.colorPlus);
        } else if (currentSign.equals(tempSigns[2])) {
            return getResources().getColor(R.color.colorMinus);
        } else {
            return getResources().getColor(R.color.colorZero);
        }
    }

    // Values filled by Random

    private int getTempDayValue(int min, int max) {
        return new Random().nextInt(max - min) + min;
    }

    private int getWindVelocityDayValue(int min, int max) {
        return new Random().nextInt(max - min) + min;
    }

    private String getWindDirectionDayValue() {
        String[] windDirections = getResources().getStringArray(R.array.windDirections);
        return windDirections[new Random().nextInt(8)];
    }
}