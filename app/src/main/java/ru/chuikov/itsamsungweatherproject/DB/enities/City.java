package ru.chuikov.itsamsungweatherproject.DB.enities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class City {
    public City() {
    }

    public City(long id, String name, double lat, double lon, String country_code) {
        this.id = id;
        this.name = name;
        this.lat = lat;
        this.lon = lon;
        this.country_code = country_code;
    }

    public City(String name, double lat, double lon, String country_code) {
        this.name = name;
        this.lat = lat;
        this.lon = lon;
        this.country_code = country_code;
    }

    @PrimaryKey(autoGenerate = true)
    public long id;
    public String name;
    public double lat;
    public double lon;

    public String country_code;


}
