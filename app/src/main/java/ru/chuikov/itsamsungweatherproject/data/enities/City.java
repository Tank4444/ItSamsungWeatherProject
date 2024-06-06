package ru.chuikov.itsamsungweatherproject.data.enities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class City {

    @PrimaryKey(autoGenerate = true)
    public long id;

    public long id_service;

    public String name;
    public double lat;
    public double lon;

    public String country_code;
    public String country;

    public String timezone;


}
