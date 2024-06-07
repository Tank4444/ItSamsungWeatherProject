package ru.chuikov.itsamsungweatherproject.api.responce;

import java.util.List;

public class Hourly {
    @com.squareup.moshi.Json(name = "time")
    private List<String> time;
    @com.squareup.moshi.Json(name = "temperature_2m")
    private List<Double> temperature2m;
    @com.squareup.moshi.Json(name = "weather_code")
    private List<Integer> weatherCode;

    public List<String> getTime() {
        return time;
    }

    public void setTime(List<String> time) {
        this.time = time;
    }

    public List<Double> getTemperature2m() {
        return temperature2m;
    }

    public void setTemperature2m(List<Double> temperature2m) {
        this.temperature2m = temperature2m;
    }

    public List<Integer> getWeatherCode() {
        return weatherCode;
    }

    public void setWeatherCode(List<Integer> weatherCode) {
        this.weatherCode = weatherCode;
    }
}
