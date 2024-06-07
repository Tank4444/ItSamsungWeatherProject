package ru.chuikov.itsamsungweatherproject.screens.cityWeather;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import lombok.Builder;
import ru.chuikov.itsamsungweatherproject.App;
import ru.chuikov.itsamsungweatherproject.R;
import ru.chuikov.itsamsungweatherproject.databinding.FragmentCityWeatherBinding;
import ru.chuikov.itsamsungweatherproject.service.WeatherService;

public class CityWeatherFragment extends Fragment {
    private FragmentCityWeatherBinding binding;
    private WeatherService service;
    private CityDailyWeatherData data;

    public static class CityDailyWeatherData {
        public String name;
        public List<DailyItem> list;

        @Builder
        public static class DailyItem {
            public String date;

            public int weatherCode;

            public double tmax;
            public double tmin;

            public int pp;
        }
    }

    private CityWeatherAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCityWeatherBinding.inflate(inflater, container, false);
        service = App.getInstance().getWeatherService();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        data = new CityDailyWeatherData();
        data.list = new ArrayList<>();
        adapter = new CityWeatherAdapter(data.list);
        binding.cityWeatherList.setAdapter(adapter);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    data = service.getWeatherInCityById(getArguments().getLong("city_id"));
                    adapter = new CityWeatherAdapter(data.list);
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            binding.cityWeatherList.setAdapter(adapter);
                        }
                    });


                } catch (IOException e) {

                }
            }
        }).start();

    }
}