package ru.chuikov.itsamsungweatherproject.api.responce;

import java.util.List;

public class Daily {
    @com.squareup.moshi.Json(name = "time")
    private List<String> time;
    @com.squareup.moshi.Json(name = "weather_code")
    private List<Integer> weatherCode;
    @com.squareup.moshi.Json(name = "temperature_2m_max")
    private List<Double> temperature2mMax;
    @com.squareup.moshi.Json(name = "temperature_2m_min")
    private List<Double> temperature2mMin;
    @com.squareup.moshi.Json(name = "precipitation_probability_max")
    private List<Integer> precipitationProbabilityMax;

    public List<String> getTime() {
        return time;
    }

    public void setTime(List<String> time) {
        this.time = time;
    }

    public List<Integer> getWeatherCode() {
        return weatherCode;
    }

    public void setWeatherCode(List<Integer> weatherCode) {
        this.weatherCode = weatherCode;
    }

    public List<Double> getTemperature2mMax() {
        return temperature2mMax;
    }

    public void setTemperature2mMax(List<Double> temperature2mMax) {
        this.temperature2mMax = temperature2mMax;
    }

    public List<Double> getTemperature2mMin() {
        return temperature2mMin;
    }

    public void setTemperature2mMin(List<Double> temperature2mMin) {
        this.temperature2mMin = temperature2mMin;
    }

    public List<Integer> getPrecipitationProbabilityMax() {
        return precipitationProbabilityMax;
    }

    public void setPrecipitationProbabilityMax(List<Integer> precipitationProbabilityMax) {
        this.precipitationProbabilityMax = precipitationProbabilityMax;
    }

}
