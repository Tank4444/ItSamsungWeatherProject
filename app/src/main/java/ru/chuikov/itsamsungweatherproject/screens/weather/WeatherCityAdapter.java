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

    private WeatherCityAdapter.onClickListener listener;


    public WeatherCityAdapter(List<WeatherCity> cities,WeatherCityAdapter.onClickListener listener) {
        this.cities = cities;
        this.listener = listener;
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
                cities.get(holder.getAdapterPosition()).cityName+" ("+cities.get(holder.getAdapterPosition()).country+")");
        try {
            holder.binding.weatherCityItemDate.setText(
                    toDataFormat.format(isoDataFormat.parse(cities.get(holder.getAdapterPosition()).date))
            );
        } catch (ParseException e) {

        }
        WeatherCityCurrentTimeAdapter adapter = new WeatherCityCurrentTimeAdapter(cities.get(holder.getAdapterPosition()).list, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(cities.get(holder.getAdapterPosition()).id);
            }
        });

        holder.binding.weatherCityItemCurrentList.setAdapter(adapter);

        holder.binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(cities.get(holder.getAdapterPosition()).id);
            }
        });
        holder.binding.weatherCityItemCurrentList.setOnClickListener(null);


    }

    @Override
    public int getItemCount() {
        return cities.size();
    }

    @Builder
    public static class WeatherCity {
        public long id;
        public String cityName;
        public String country;
        public String date;
        public List<WeatherCityCurrentTimeAdapter.CurrentWeatherItem> list;
    }
    public interface onClickListener{
        void onClick(long position);
    }

    public class WeatherCityViewHolder extends RecyclerView.ViewHolder {
        public WeatherCityItemBinding binding;

        public WeatherCityViewHolder(WeatherCityItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
