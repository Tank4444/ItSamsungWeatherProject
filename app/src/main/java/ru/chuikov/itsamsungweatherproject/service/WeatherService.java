package ru.chuikov.itsamsungweatherproject.service;

import android.content.Context;

import androidx.room.Room;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.Response;
import ru.chuikov.itsamsungweatherproject.api.ApiClient;
import ru.chuikov.itsamsungweatherproject.api.responce.CityListResponse;
import ru.chuikov.itsamsungweatherproject.api.responce.Daily;
import ru.chuikov.itsamsungweatherproject.api.responce.Hourly;
import ru.chuikov.itsamsungweatherproject.api.responce.WeatherCityResponse;
import ru.chuikov.itsamsungweatherproject.data.AppDatabase;
import ru.chuikov.itsamsungweatherproject.data.enities.City;
import ru.chuikov.itsamsungweatherproject.screens.cityWeather.CityWeatherFragment;
import ru.chuikov.itsamsungweatherproject.screens.weather.WeatherCityAdapter;
import ru.chuikov.itsamsungweatherproject.screens.weather.WeatherCityCurrentTimeAdapter;
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

    public void addCity(City city) {
        database.cityDao().insert(city);
    }

    public List<City> getCities() {
        return database.cityDao().getAll();
    }

    public void deleteCity(long id) {
        database.cityDao().delete(findById(id));
    }


    public City findById(long id) {
        return database.cityDao().getById(id);
    }


    public List<WeatherCityAdapter.WeatherCity> getWeatherCities() throws IOException {
        List<City> cities = getCities();
        Response response = client.getHourlyWeather(cities);
        List<WeatherCityResponse> weatherCityResponses;
        if (cities.size() == 1) {
            JsonAdapter<WeatherCityResponse> responseJsonAdapter = moshi.adapter(WeatherCityResponse.class);
            weatherCityResponses = Arrays.asList(responseJsonAdapter.fromJson(response.body().string()));
        } else {
            Type type = Types.newParameterizedType(List.class, WeatherCityResponse.class);
            JsonAdapter<List<WeatherCityResponse>> responseJsonAdapter = moshi.adapter(type);
            weatherCityResponses = responseJsonAdapter.fromJson(response.body().string());
        }
        List<WeatherCityAdapter.WeatherCity> weatherCities = new ArrayList<>();
        for (int i = 0; i < weatherCityResponses.size(); i++) {
            List<WeatherCityCurrentTimeAdapter.CurrentWeatherItem> list = new ArrayList<>();
            Hourly hourly = weatherCityResponses.get(i).getHourly();
            for (int j = 0; j < hourly.getTime().size(); j++) {
                list.add(WeatherCityCurrentTimeAdapter.CurrentWeatherItem.builder()
                        .time(hourly.getTime().get(j))
                        .weatherCode(hourly.getWeatherCode().get(j))
                        .temp(String.valueOf(hourly.getTemperature2m().get(j)))
                        .build());
            }
            weatherCities.add(WeatherCityAdapter.WeatherCity.builder()
                    .id(cities.get(i).id)
                    .cityName(cities.get(i).name)
                    .country(cities.get(i).country)
                    .date(weatherCityResponses.get(i).getHourly().getTime().get(0))
                    .list(list)
                    .build());
        }


        return weatherCities;

    }


    public CityWeatherFragment.CityDailyWeatherData getWeatherInCityById(long id) throws IOException {
        City city = database.cityDao().getById(id);
        Response response = client.getDailyWeather(city);
        JsonAdapter<WeatherCityResponse> responseJsonAdapter = moshi.adapter(WeatherCityResponse.class);
        WeatherCityResponse weatherCityResponses = responseJsonAdapter.fromJson(response.body().string());


        CityWeatherFragment.CityDailyWeatherData data = new CityWeatherFragment.CityDailyWeatherData();
        data.list = new ArrayList<>();
        data.name = city.name;
        Daily daily = weatherCityResponses.getDaily();
        for (int i = 0; i < daily.getTime().size(); i++){
            CityWeatherFragment.CityDailyWeatherData.DailyItem item = CityWeatherFragment.CityDailyWeatherData.DailyItem.builder()
                    .tmax(daily.getTemperature2mMax().get(i))
                    .tmin(daily.getTemperature2mMin().get(i))
                    .pp(daily.getPrecipitationProbabilityMax().get(i))
                    .date(daily.getTime().get(i))
                    .weatherCode(daily.getWeatherCode().get(i))
                    .build();
            data.list.add(item);
        }


        return data;
    }
}
