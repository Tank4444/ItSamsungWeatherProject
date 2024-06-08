package ru.chuikov.itsamsungweatherproject.screens.cityWeather;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;
import static ru.chuikov.itsamsungweatherproject.screens.weather.WeatherCityCurrentTimeAdapter.urlFromCode;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import ru.chuikov.itsamsungweatherproject.R;
import ru.chuikov.itsamsungweatherproject.databinding.CityWeatherListItemBinding;

public class CityWeatherAdapter extends RecyclerView.Adapter<CityWeatherAdapter.CityWeatherViewHolder> {

    private List<CityWeatherFragment.CityDailyWeatherData.DailyItem> items;


    public CityWeatherAdapter(List<CityWeatherFragment.CityDailyWeatherData.DailyItem> items) {
        this.items = items;
    }
    private DateFormat toDataFormat = new SimpleDateFormat("dd.MM");
    private DateFormat isoDataFormat = new SimpleDateFormat("yyyy-MM-dd");
    @NonNull
    @Override
    public CityWeatherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                MATCH_PARENT, WRAP_CONTENT);
        params.setMargins(10,10,10,10);
        CityWeatherListItemBinding binding = CityWeatherListItemBinding.inflate(LayoutInflater.from(parent.getContext()));
        binding.getRoot().setLayoutParams(params);
        return new CityWeatherViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CityWeatherViewHolder holder, int position) {
        holder.binding.cityWeatherListTmax.setText(items.get(position).tmax+"°C");
        holder.binding.cityWeatherListTmin.setText(items.get(position).tmin+"°C");
        holder.binding.cityWeatherListPp.setText(items.get(position).pp+"%");
        try {
            holder.binding.cityWeatherListDate.setText(toDataFormat.format(isoDataFormat.parse(items.get(position).date)));
        } catch (ParseException e) {
        }
        Picasso.get().load(urlFromCode(items.get(position).weatherCode))
                .placeholder(R.drawable.weather_unknown)
                .error(R.drawable.weather_unknown)
                .into(holder.binding.cityWeatherListImage);

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class CityWeatherViewHolder extends RecyclerView.ViewHolder {
        public CityWeatherListItemBinding binding;

        public CityWeatherViewHolder(CityWeatherListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
