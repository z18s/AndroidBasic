package com.example.weatherapp;

import android.app.Activity;
import android.app.Fragment;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherapp.databinding.FragmentCitySwapBinding;

import java.util.Arrays;
import java.util.List;

public class CitySwapFragment extends Fragment {

    private FragmentCitySwapBinding binding;
    private ITransactionController transactionController;
    private IConnectionController connectionController;

    private List<String> citiesList;
    private String chosenCity = null;

    public static CitySwapFragment create() {
        return new CitySwapFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentCitySwapBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initCitiesList(view);
        initListeners();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        transactionController = (ITransactionController) activity;
        connectionController = (IConnectionController) activity;
    }

    private void initCitiesList(View view) {
        citiesList = Arrays.asList(getResources().getStringArray(R.array.cities));
        initListDecorator(view);
        initListAdapter(view);
    }

    private void initListDecorator(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.cities_list_container);
        recyclerView.addItemDecoration(new DividerItemDecoration(view.getContext(), DividerItemDecoration.VERTICAL) {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.top = 10;
                outRect.bottom = 10;
                outRect.left = 25;
            }
        });
    }

    private void initListAdapter(View view) {
        CitiesListAdapter adapter = new CitiesListAdapter();
        binding.citiesListContainer.setLayoutManager(new LinearLayoutManager(view.getContext()));
        binding.citiesListContainer.setAdapter(adapter);

        adapter.setList(citiesList);
        adapter.setOnClickListener((position) -> {
            selectCityByList(view, position);
        });
    }

    private void initListeners() {
        clearDefaultTextListener();
        selectCityButtonListener();
    }

    private void clearDefaultTextListener() {
        binding.textCitySelect.setOnClickListener((view) -> {
            binding.textCitySelect.setText(null);
        });
    }

    private void selectCityButtonListener() {
        binding.buttonSwapCity.setOnClickListener((view) -> {
            chosenCity = binding.textCitySelect.getText().toString();
            returnBack();
        });
    }

    private void selectCityByList(View view, int position) {
        chosenCity = citiesList.get(position);
        returnBack();
    }

    private void returnBack() {
        if (chosenCity != null && !chosenCity.equals("")) {
            connectionController.initConnection(new AppConnection(chosenCity));
            transactionController.resetHomeFragments();
            chosenCity = null;
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
    }
}