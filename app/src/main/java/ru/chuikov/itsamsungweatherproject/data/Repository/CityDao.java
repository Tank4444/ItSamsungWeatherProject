package ru.chuikov.itsamsungweatherproject.data.Repository;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import ru.chuikov.itsamsungweatherproject.data.enities.City;

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

    @Query("DELETE FROM city WHERE id = :id")
    void deleteById(long id);
}
