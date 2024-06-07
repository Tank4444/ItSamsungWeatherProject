package ru.chuikov.itsamsungweatherproject.screens.weather;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import lombok.Builder;
import ru.chuikov.itsamsungweatherproject.R;
import ru.chuikov.itsamsungweatherproject.databinding.WeatherCityItemCurrentListItemBinding;

public class WeatherCityCurrentTimeAdapter extends RecyclerView.Adapter<WeatherCityCurrentTimeAdapter.WeatherCityCurrentTimeViewHolder> {
    private DateFormat dateFormat = new SimpleDateFormat("HH.mm", Locale.getDefault());
    @Builder
    public class CurrentWeatherItem {
        public String temp;
        public int weatherCode;
        public String time;
    }

    private List<CurrentWeatherItem> list;

    public WeatherCityCurrentTimeAdapter(List<CurrentWeatherItem> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public WeatherCityCurrentTimeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        WeatherCityItemCurrentListItemBinding binding = WeatherCityItemCurrentListItemBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        );
        return new WeatherCityCurrentTimeViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherCityCurrentTimeViewHolder holder, int position) {
        holder.binding.weatherCityItemCurrentListTime
                .setText(list.get(position).temp + " °C");
        try {
            holder.binding.weatherCityItemCurrentListTime.setText(dateFormat.format(dateFormat.parse(list.get(position).time)));
        } catch (ParseException e) {
        }
        Picasso.get().load(urlFromCode(list.get(position).weatherCode))
                .placeholder(R.drawable.weather_unknown)
                .error(R.drawable.weather_unknown)
                .into(holder.binding.weatherCityItemCurrentListIcon);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class WeatherCityCurrentTimeViewHolder extends RecyclerView.ViewHolder {
        public WeatherCityItemCurrentListItemBinding binding;

        public WeatherCityCurrentTimeViewHolder(@NonNull WeatherCityItemCurrentListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    private String urlFromCode(int code) {
        String res = "";
        switch (code) {
            case 0:
            case 1:
                res = "http://openweathermap.org/img/wn/01d@2x.png";
                break;
            case 2:
                res = "http://openweathermap.org/img/wn/02d@2x.png";
                break;
            case 3:
                res = "http://openweathermap.org/img/wn/03d@2x.png";
                break;
            case 45:
            case 48:
                res = "http://openweathermap.org/img/wn/50d@2x.png";
                break;
            case 51:
            case 53:
            case 55:
            case 56:
            case 57:
            case 80:
            case 81:
            case 82:
                res = "http://openweathermap.org/img/wn/09d@2x.png";
                break;
            case 61:
            case 63:
            case 65:
            case 66:
            case 67:
                res = "http://openweathermap.org/img/wn/10d@2x.png";
                break;
            case 71:
            case 73:
            case 75:
            case 77:
            case 85:
            case 86:
                res = "http://openweathermap.org/img/wn/13d@2x.png";
                break;
            case 95:
            case 96:
            case 99:
                res = "http://openweathermap.org/img/wn/11d@2x.png";
                break;
        }
        return res;
    }
}
