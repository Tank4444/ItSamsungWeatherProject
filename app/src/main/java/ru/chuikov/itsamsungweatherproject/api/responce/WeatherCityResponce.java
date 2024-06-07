package ru.chuikov.itsamsungweatherproject.api.responce;

import java.util.List;

public class WeatherCityResponce {
    @com.squareup.moshi.Json(name = "latitude")
    private Double latitude;
    @com.squareup.moshi.Json(name = "longitude")
    private Double longitude;
    @com.squareup.moshi.Json(name = "generationtime_ms")
    private Double generationtimeMs;
    @com.squareup.moshi.Json(name = "utc_offset_seconds")
    private Integer utcOffsetSeconds;
    @com.squareup.moshi.Json(name = "timezone")
    private String timezone;
    @com.squareup.moshi.Json(name = "timezone_abbreviation")
    private String timezoneAbbreviation;
    @com.squareup.moshi.Json(name = "elevation")
    private Double elevation;
    @com.squareup.moshi.Json(name = "hourly_units")
    private HourlyUnits hourlyUnits;
    @com.squareup.moshi.Json(name = "hourly")
    private Hourly hourly;
    @com.squareup.moshi.Json(name = "location_id")
    private Integer locationId;

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getGenerationtimeMs() {
        return generationtimeMs;
    }

    public void setGenerationtimeMs(Double generationtimeMs) {
        this.generationtimeMs = generationtimeMs;
    }

    public Integer getUtcOffsetSeconds() {
        return utcOffsetSeconds;
    }

    public void setUtcOffsetSeconds(Integer utcOffsetSeconds) {
        this.utcOffsetSeconds = utcOffsetSeconds;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getTimezoneAbbreviation() {
        return timezoneAbbreviation;
    }

    public void setTimezoneAbbreviation(String timezoneAbbreviation) {
        this.timezoneAbbreviation = timezoneAbbreviation;
    }

    public Double getElevation() {
        return elevation;
    }

    public void setElevation(Double elevation) {
        this.elevation = elevation;
    }

    public HourlyUnits getHourlyUnits() {
        return hourlyUnits;
    }

    public void setHourlyUnits(HourlyUnits hourlyUnits) {
        this.hourlyUnits = hourlyUnits;
    }

    public Hourly getHourly() {
        return hourly;
    }

    public void setHourly(Hourly hourly) {
        this.hourly = hourly;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

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

}
