package ru.chuikov.itsamsungweatherproject.DB.Repository;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import ru.chuikov.itsamsungweatherproject.DB.enities.City;

@Dao
public interface CityDao {
    @Query("SELECT * FROM city")
    List<City> getAll();

    @Query("SELECT * FROM city WHERE id = :id")
    City getById(long id);

    @Insert
    void insert(City city);

    @Delete
    void delete(City city);
}
