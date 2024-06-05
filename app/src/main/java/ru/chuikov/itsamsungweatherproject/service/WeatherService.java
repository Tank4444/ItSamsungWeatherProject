package ru.chuikov.itsamsungweatherproject.service;

import android.content.Context;
import androidx.room.Room;

import com.squareup.moshi.Moshi;

import okhttp3.Callback;
import ru.chuikov.itsamsungweatherproject.api.ApiClient;
import ru.chuikov.itsamsungweatherproject.data.AppDatabase;

public class WeatherService {
    private AppDatabase database;
    private ApiClient client;
    private Moshi moshi;

    public WeatherService(Context context) {
        database = Room.databaseBuilder(context, AppDatabase.class,"database")
                .fallbackToDestructiveMigration()
                .build();
        moshi = new Moshi.Builder().build();
        client = new ApiClient();
    }
    public Moshi getMoshi(){return moshi;}

    public void searchCity(String name, String lang, Callback callback){
        client.findCityAsync(name, lang, callback);
    }



}
