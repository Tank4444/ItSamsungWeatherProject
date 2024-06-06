package ru.chuikov.itsamsungweatherproject.service;

import android.content.Context;

import androidx.room.Room;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Callback;
import okhttp3.Response;
import ru.chuikov.itsamsungweatherproject.api.ApiClient;
import ru.chuikov.itsamsungweatherproject.api.responce.CityListResponse;
import ru.chuikov.itsamsungweatherproject.data.AppDatabase;
import ru.chuikov.itsamsungweatherproject.data.enities.City;
import ru.chuikov.itsamsungweatherproject.service.dto.CityItemSearch;

public class WeatherService {
    private AppDatabase database;
    private ApiClient client;
    private Moshi moshi;

    public WeatherService(Context context) {
        database = Room.databaseBuilder(context, AppDatabase.class, "database")
                .fallbackToDestructiveMigration()
                .build();
        moshi = new Moshi.Builder().build();
        client = new ApiClient();
    }

    public Moshi getMoshi() {
        return moshi;
    }

    public List<CityItemSearch> searchCity(String name, String lang) throws IOException {
        Response search = client.findCitySync(name, lang);
        JsonAdapter<CityListResponse> responseJsonAdapter = moshi.adapter(CityListResponse.class);
        CityListResponse resp = responseJsonAdapter.fromJson(search.body().string());
        if (resp.getResults() == null) throw new IOException("Not found");
        List<CityItemSearch> result = new ArrayList<>();
        List<City> cities = database.cityDao().getAll();
        for (CityListResponse.Result res : resp.getResults()) {
            boolean contain = false;
            for (City j : cities)
                if (res.getId() == j.id_service) {
                    contain = true;
                    break;
                }
            result.add(CityItemSearch.builder()
                            .id(res.getId())
                    .name(res.getName())
                    .country(res.getCountry())
                    .countryCode(res.getCountryCode())
                    .lat(res.getLatitude())
                    .lon(res.getLongitude())
                    .timezone(res.getTimezone())
                    .inDB(contain)
                    .build()

            );
        }

        return result;
    }

    public void addCity(City city){
        database.cityDao().insert(city);
    }
    public List<City> getCities(){
        return database.cityDao().getAll();
    }

    public void deleteCity(long id){
        database.cityDao().delete(findById(id));
    }


    public City findById(long id){
        return database.cityDao().getById(id);
    }

}
