package ru.chuikov.itsamsungweatherproject.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CityItemSearch {
    public long id;
    public String name;
    public String countryCode;
    public String country;

    public double lat;
    public double lon;
    public String timezone;

    public Boolean inDB;

}
