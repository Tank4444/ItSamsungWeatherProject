package ru.chuikov.itsamsungweatherproject.api;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttp;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ApiClient {
    private final String WEATHER_HOST = "https://api.open-meteo.com/v1/";
    private final String WEATHER_HOST_FORECAST = "https://api.open-meteo.com/v1/forecast?";
    private final String GEOCODING_HOST = "https://geocoding-api.open-meteo.com/v1/";
    private final String GEOCODING_HOST_SEARCH = GEOCODING_HOST + "/search?";

    public ApiClient() {
        client = new OkHttpClient.Builder()
                .build();
    }

    private OkHttpClient client;

    private Request findCity(String name, String lang) {
        HttpUrl.Builder builder = HttpUrl.parse(GEOCODING_HOST_SEARCH).newBuilder();
        builder.addQueryParameter("name", name);
        builder.addQueryParameter("count", String.valueOf(10));
        if (lang == null) lang = "ru";
        builder.addQueryParameter("language", lang);
        return new Request.Builder()
                .url(builder.build())
                .build();
    }

    public Response findCitySync(String name, String lang) throws IOException {
        return client.newCall(findCity(name, lang)).execute();
    }

    public void findCityAsync(String name, String lang, Callback call) {
        client.newCall(findCity(name, lang)).enqueue(call);
    }


}
