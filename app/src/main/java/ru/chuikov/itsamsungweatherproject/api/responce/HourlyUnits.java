package ru.chuikov.itsamsungweatherproject.api.responce;

public class HourlyUnits {
    @com.squareup.moshi.Json(name = "time")
    private String time;
    @com.squareup.moshi.Json(name = "temperature_2m")
    private String temperature2m;
    @com.squareup.moshi.Json(name = "weather_code")
    private String weatherCode;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTemperature2m() {
        return temperature2m;
    }

    public void setTemperature2m(String temperature2m) {
        this.temperature2m = temperature2m;
    }

    public String getWeatherCode() {
        return weatherCode;
    }

    public void setWeatherCode(String weatherCode) {
        this.weatherCode = weatherCode;
    }
}