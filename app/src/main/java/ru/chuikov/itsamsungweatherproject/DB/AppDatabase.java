package ru.chuikov.itsamsungweatherproject.DB;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import ru.chuikov.itsamsungweatherproject.DB.Repository.CityDao;
import ru.chuikov.itsamsungweatherproject.DB.enities.City;

@Database(entities = {City.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract CityDao cityDao();
}
