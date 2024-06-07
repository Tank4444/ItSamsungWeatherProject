package ru.chuikov.itsamsungweatherproject.api.responce;

public class DailyUnits {
    @com.squareup.moshi.Json(name = "time")
    private String time;
    @com.squareup.moshi.Json(name = "weather_code")
    private String weatherCode;
    @com.squareup.moshi.Json(name = "temperature_2m_max")
    private String temperature2mMax;
    @com.squareup.moshi.Json(name = "temperature_2m_min")
    private String temperature2mMin;
    @com.squareup.moshi.Json(name = "precipitation_probability_max")
    private String precipitationProbabilityMax;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getWeatherCode() {
        return weatherCode;
    }

    public void setWeatherCode(String weatherCode) {
        this.weatherCode = weatherCode;
    }

    public String getTemperature2mMax() {
        return temperature2mMax;
    }

    public void setTemperature2mMax(String temperature2mMax) {
        this.temperature2mMax = temperature2mMax;
    }

    public String getTemperature2mMin() {
        return temperature2mMin;
    }

    public void setTemperature2mMin(String temperature2mMin) {
        this.temperature2mMin = temperature2mMin;
    }

    public String getPrecipitationProbabilityMax() {
        return precipitationProbabilityMax;
    }

    public void setPrecipitationProbabilityMax(String precipitationProbabilityMax) {
        this.precipitationProbabilityMax = precipitationProbabilityMax;
    }
}
