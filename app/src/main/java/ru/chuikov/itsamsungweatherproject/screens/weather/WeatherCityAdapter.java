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

    private DateFormat dateFormat = new SimpleDateFormat("dd.MM", Locale.getDefault());

    public WeatherCityAdapter(List<WeatherCity> cities) {
        this.cities = cities;
    }

    @NonNull
    @Override
    public WeatherCityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        WeatherCityItemBinding binding = WeatherCityItemBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent,false);
        return new WeatherCityViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherCityViewHolder holder, int position) {
            holder.binding.weatherCityItemName.setText(cities.get(position).cityName);
        try {
            holder.binding.weatherCityItemDate.setText(
                    dateFormat.format(dateFormat.parse(cities.get(position).date))
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
    public class WeatherCity{
        public String cityName;
        public String date;
        public List<WeatherCityCurrentTimeAdapter.CurrentWeatherItem> list;
    }


    public class WeatherCityViewHolder extends RecyclerView.ViewHolder{
        public WeatherCityItemBinding binding;
        public WeatherCityViewHolder(WeatherCityItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
