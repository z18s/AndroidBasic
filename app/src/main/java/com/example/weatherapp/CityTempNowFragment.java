package com.example.weatherapp;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.weatherapp.databinding.FragmentCityTempNowBinding;

public class CityTempNowFragment extends Fragment {

    private FragmentCityTempNowBinding binding;

    private AppCalendar calendar = AppCalendar.getInstance();
    private AppValuesFormatter valuesFormatter;

    public static CityTempNowFragment create() {
        return new CityTempNowFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentCityTempNowBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initFragment(view);

        todayAboutListener();
    }

    private void initFragment(View view) {
        valuesFormatter = new AppValuesFormatter(view.getContext(), calendar);

        initDate();

        initTextView(view, R.id.textTempNow, valuesFormatter.getTempString(15, 25), true);
        initTextView(view, R.id.textTempScale, getResources().getString(R.string.celsius));
        initImageView(view, R.id.iconTempNow, valuesFormatter.getTempDayIconId());
    }

    private void initDate() {
        binding.dateTodayFull.setText(calendar.getDateString(0, "dd/MM/yyyy"));
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

    private void todayAboutListener() {
        binding.buttonTodayAbout.setOnClickListener((view) -> {
            String url = (String) getResources().getText(R.string.today_about_url);
            String date = calendar.getDateString(0, "d MMMM");
            Uri uri = Uri.parse(url + date);

            Intent browser = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(browser);
        });
    }
}