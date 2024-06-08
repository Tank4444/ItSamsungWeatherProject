package ru.chuikov.itsamsungweatherproject.api.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherCityResponse {

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

    @com.squareup.moshi.Json(name = "daily_units")
    private DailyUnits dailyUnits;

    @com.squareup.moshi.Json(name = "daily")
    private Daily daily;


}
