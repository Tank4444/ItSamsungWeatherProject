package ru.chuikov.itsamsungweatherproject.screens.weather;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import lombok.Builder;
import ru.chuikov.itsamsungweatherproject.databinding.WeatherCityItemBinding;

public class WeatherCityAdapter extends RecyclerView.Adapter<WeatherCityAdapter.WeatherCityViewHolder> {
    private List<WeatherCity> cities;

    private DateFormat toDataFormat = new SimpleDateFormat("dd.MM");
    private DateFormat isoDataFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");


    public WeatherCityAdapter(List<WeatherCity> cities) {
        this.cities = cities;
    }

    @NonNull
    @Override
    public WeatherCityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        WeatherCityItemBinding binding = WeatherCityItemBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new WeatherCityViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherCityViewHolder holder, int position) {
        holder.binding.weatherCityItemName.setText(
                cities.get(position).cityName+" ("+cities.get(position).country+")");
        try {
            holder.binding.weatherCityItemDate.setText(
                    toDataFormat.format(isoDataFormat.parse(cities.get(position).date))
            );
        } catch (ParseException e) {

        }
        WeatherCityCurrentTimeAdapter adapter = new WeatherCityCurrentTimeAdapter(cities.get(position).list);

        holder.binding.weatherCityItemCurrentList.setAdapter(adapter);

    }

    @Override
    public int getItemCount() {
        return cities.size();
    }

    @Builder
    public static class WeatherCity {
        public String cityName;
        public String country;
        public String date;
        public List<WeatherCityCurrentTimeAdapter.CurrentWeatherItem> list;
    }


    public class WeatherCityViewHolder extends RecyclerView.ViewHolder {
        public WeatherCityItemBinding binding;

        public WeatherCityViewHolder(WeatherCityItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
