package ru.chuikov.itsamsungweatherproject;

import android.app.Application;

import androidx.room.Room;

import lombok.Getter;
import ru.chuikov.itsamsungweatherproject.data.AppDatabase;
import ru.chuikov.itsamsungweatherproject.service.WeatherService;

public class App extends Application {
    @Getter
    private static App instance;

    @Getter
    private WeatherService weatherService;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        weatherService = new WeatherService(this);
    }

}
