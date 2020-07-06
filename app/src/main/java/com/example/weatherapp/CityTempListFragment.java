package com.example.weatherapp;

import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.weatherapp.databinding.FragmentCityTempListBinding;

public class CityTempListFragment extends Fragment {

    private FragmentCityTempListBinding binding;

    private AppCalendar calendar = AppCalendar.getInstance();
    private AppValuesFormatter valuesFormatter;

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

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void initList(View view) {
        valuesFormatter = new AppValuesFormatter(view.getContext(), calendar);

        LinearLayout layoutView = (LinearLayout) view;
        LayoutInflater layoutInflater = getLayoutInflater();

        for (int i = 0; i < LIST_SIZE; i++) {
            View item = layoutInflater.inflate(R.layout.city_temp_item, layoutView, false);

            initTextView(item, R.id.dateDayOfTheWeek, valuesFormatter.getDateDayOfTheWeek(i));
            initTextView(item, R.id.dateDayAndMonth, valuesFormatter.getDateDayAndMonth(i));
            initTextView(item, R.id.textTempDay, valuesFormatter.getTempString(10, 20), true);
            initImageView(item, R.id.iconTempDay, valuesFormatter.getTempDayIconId());
            initTextView(item, R.id.textTempNight, valuesFormatter.getTempString(-5, 5), true);
            initImageView(item, R.id.iconTempNight, valuesFormatter.getTempNightIconId());
            initTextView(item, R.id.windVelocity, String.valueOf(valuesFormatter.getWindVelocityValue(1, 4)));
            initTextView(item, R.id.windDirection, valuesFormatter.getWindDirectionValue());

            layoutView.addView(item);
        }
    }

    private void initTextView(View view, int viewId, String text) {
        initTextView(view, viewId, text, false);
    }

    private void initTextView(View view, int viewId, String text, boolean isColoured) {
        TextView textView = view.findViewById(viewId);
        textView.setText(text);
        if (isColoured) {
            textView.setTextColor(valuesFormatter.getTempColorId(text));
        }
    }

    private void initImageView(View view, int viewId, int imageId) {
        ImageView imageView = view.findViewById(viewId);
        imageView.setImageResource(imageId);
    }
}