package ru.chuikov.itsamsungweatherproject;

import android.app.Application;

import androidx.room.Room;

import ru.chuikov.itsamsungweatherproject.data.AppDatabase;
import ru.chuikov.itsamsungweatherproject.service.WeatherService;

public class App extends Application {
    public static App instance;

    private WeatherService weatherService;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        weatherService = new WeatherService(this);
    }

    public static App getInstance() {
        return instance;
    }

    public WeatherService getWeatherService(){ return weatherService;}

}
