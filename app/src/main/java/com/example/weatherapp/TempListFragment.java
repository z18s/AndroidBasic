package com.example.weatherapp;

import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.weatherapp.databinding.FragmentTempListBinding;

public class TempListFragment extends Fragment {

    private FragmentTempListBinding binding;

    private AppValuesFormatter valuesFormatter = AppValuesFormatter.getInstance();

    private final int LIST_SIZE = 1;

    public static TempListFragment create() {
        return new TempListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentTempListBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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


    @RequiresApi(api = Build.VERSION_CODES.O)
    public void updateData() {
        LinearLayout layoutView = (LinearLayout) this.getView();
        LayoutInflater layoutInflater = getLayoutInflater();

        for (int i = 0; i < LIST_SIZE; i++) {
            View item = layoutInflater.inflate(R.layout.temp_list_item, layoutView, false);

            initTextView(item, R.id.dateDayOfTheWeek, valuesFormatter.getDateDayOfTheWeek(i));
            initTextView(item, R.id.dateDayAndMonth, valuesFormatter.getDateDayAndMonth(i));
            initTextView(item, R.id.textTempDay, valuesFormatter.getTempMaxString(this.getContext()), true);
            initImageView(item, R.id.iconTempDay, valuesFormatter.getTempDayIconId(this.getContext()));
            initTextView(item, R.id.textTempNight, valuesFormatter.getTempMinString(this.getContext()), true);
            initImageView(item, R.id.iconTempNight, valuesFormatter.getTempNightIconId(this.getContext()));
            initTextView(item, R.id.windVelocity, valuesFormatter.getWindVelocityString());
            initTextView(item, R.id.windDirection, valuesFormatter.getWindDirectionString(this.getContext()));

            layoutView.addView(item);
        }

        Log.d("DEBUG_TempListFragment", "updateData");
    }
}