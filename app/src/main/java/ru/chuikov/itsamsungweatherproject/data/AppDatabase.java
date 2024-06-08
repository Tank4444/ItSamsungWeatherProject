package ru.chuikov.itsamsungweatherproject.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import ru.chuikov.itsamsungweatherproject.data.repository.CityDao;
import ru.chuikov.itsamsungweatherproject.data.enities.City;

@Database(entities = {City.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract CityDao cityDao();
}
