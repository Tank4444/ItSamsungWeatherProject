package ru.chuikov.itsamsungweatherproject.api;

import androidx.annotation.NonNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttp;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import ru.chuikov.itsamsungweatherproject.api.responce.WeatherCityResponce;
import ru.chuikov.itsamsungweatherproject.data.enities.City;

public class ApiClient {
    private final String WEATHER_HOST = "https://api.open-meteo.com/v1/";
    private final String WEATHER_HOST_FORECAST = "forecast?";
    private final String GEOCODING_HOST = "https://geocoding-api.open-meteo.com/v1/";
    private final String GEOCODING_HOST_SEARCH = "search?";


    //https://api.open-meteo.com/v1/forecast
    // ?latitude=53.3606&longitude=83.7636
    // &hourly=temperature_2m,weather_code
    // &timezone=Asia%2FBangkok&forecast_hours=12

    private Request getHourlyWeatherRequest(List<City> cities) {
            HttpUrl.Builder builder = HttpUrl.parse(WEATHER_HOST + WEATHER_HOST_FORECAST).newBuilder();
            builder.addQueryParameter("hourly", "temperature_2m,weather_code");
            builder.addQueryParameter("forecast_hours", "12");
            List<Double> lats = new ArrayList<>();
            List<Double> lons = new ArrayList<>();
            List<String> timeZones = new ArrayList<>();
            for (City city : cities) {
                lats.add(city.lat);
                lons.add(city.lon);
                timeZones.add(city.timezone);
            }
            builder.addQueryParameter("latitude", lats.stream().map(String::valueOf).collect(Collectors.joining(",")));
            builder.addQueryParameter("longitude", lons.stream().map(String::valueOf).collect(Collectors.joining(",")));
            builder.addQueryParameter("timezone", String.join(",", timeZones));
            String url = builder.build().toString();
            return new Request.Builder()
                    .url(url)
                    .build();
    }

    public Response getHourlyWeather(List<City> cities) throws IOException {
        return client.newCall(getHourlyWeatherRequest(cities)).execute();
    }


    public ApiClient() {
        client = new OkHttpClient.Builder()
                .build();
    }

    private OkHttpClient client;

    private Request findCity(String name, String lang) {
        HttpUrl.Builder builder = HttpUrl.parse(GEOCODING_HOST + GEOCODING_HOST_SEARCH).newBuilder();
        builder.addQueryParameter("name", name);
        builder.addQueryParameter("language", lang);
        builder.addQueryParameter("count", String.valueOf(10));
        if (lang == null) lang = "ru";
        return new Request.Builder()
                .url(builder.build())
                .build();
    }

    public Response findCitySync(String name, String lang) throws IOException {
        return client.newCall(findCity(name, lang)).execute();
    }

}
