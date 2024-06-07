package ru.chuikov.itsamsungweatherproject.screens.weather;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ru.chuikov.itsamsungweatherproject.App;
import ru.chuikov.itsamsungweatherproject.R;
import ru.chuikov.itsamsungweatherproject.data.enities.City;
import ru.chuikov.itsamsungweatherproject.databinding.FragmentWeatherBinding;
import ru.chuikov.itsamsungweatherproject.service.WeatherService;

public class WeatherFragment extends Fragment {
    private List<WeatherCityAdapter.WeatherCity> weatherCities;
    private WeatherService service;
    private FragmentWeatherBinding binding;

    private WeatherCityAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentWeatherBinding.inflate(inflater, container, false);
        service = App.getInstance().getWeatherService();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        weatherCities = new ArrayList<>();
        adapter = new WeatherCityAdapter(weatherCities);
        binding.weatherCityList.setAdapter(adapter);
        updateList();


    }

    void updateList() {
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    weatherCities.clear();
                    weatherCities = service.getWeatherCities();
                    adapter = new WeatherCityAdapter(weatherCities);
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            binding.weatherCityList.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                        }
                    });
                } catch (IOException e) {

                }
            }
        }).start();
    }
}