package com.example.weatherapp;

import android.app.Activity;
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

import com.example.weatherapp.databinding.FragmentHomeTempBinding;

public class HomeTempFragment extends Fragment {

    private FragmentHomeTempBinding binding;
    private ITransactionController transactionController;

    private AppCalendar calendar = AppCalendar.getInstance();
    private AppValuesFormatter valuesFormatter = AppValuesFormatter.getInstance();

    public static HomeTempFragment create() {
        return new HomeTempFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentHomeTempBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initFragment(view);

        initListeners();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        transactionController = (ITransactionController) activity;
    }

    private void initFragment(View view) {
        initDate();

        initTextView(view, R.id.textTempNow, valuesFormatter.getTempString(view.getContext(), 15, 25), true);
        initTextView(view, R.id.textTempScale, getResources().getString(R.string.celsius));
        initImageView(view, R.id.iconTempNow, valuesFormatter.getTempDayIconId(view.getContext()));
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
            textView.setTextColor(valuesFormatter.getTempColorId(view.getContext(), text));
        }
    }

    private void initImageView(View view, int viewId, int imageId) {
        ImageView imageView = view.findViewById(viewId);
        imageView.setImageResource(imageId);
    }

    private void initListeners() {
        todayAboutListener();
        swapCityListener();
    }

    private void todayAboutListener() {
        binding.buttonDateAbout.setOnClickListener((view) -> {
            String url = (String) getResources().getText(R.string.date_about_url);
            String date = calendar.getDateString(0, "d MMMM");
            Uri uri = Uri.parse(url + date);

            Intent browser = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(browser);
        });
    }

    private void swapCityListener() {
        binding.iconSwapCity.setOnClickListener((view) -> {
            transactionController.setCitySwapFragment();
        });
    }
}