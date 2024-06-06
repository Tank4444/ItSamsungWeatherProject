package ru.chuikov.itsamsungweatherproject.screens.cities;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import ru.chuikov.itsamsungweatherproject.R;
import ru.chuikov.itsamsungweatherproject.service.dto.CityItemSearch;


public class CitiesAdapter extends RecyclerView.Adapter<CitiesAdapter.CityViewHolder> {

    private List<CityItemSearch> list;
    private CitiesAdapterInterface listener;


    public CitiesAdapter(List<CityItemSearch> list, CitiesAdapterInterface listener) {
        this.listener = listener;
        this.list = list;
    }

    @NonNull
    @Override
    public CityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //View v = View.inflate(parent.getContext(), R.layout.cities_item,parent);
        View v;
        if (viewType==VIEW_TYPE_CELL)v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cities_item,parent,false);
        else v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cities_item_button,parent,false);
        return new CityViewHolder(v,viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull CityViewHolder holder,
                                 int position) {
        if (holder.viewType==VIEW_TYPE_CELL){
            CityItemSearch city = list.get(position);
            Picasso.get().load("https://flagcdn.com/w160/"+city
                            .countryCode.toLowerCase()+".png")
                    .into(holder.countryImage);
            holder.nameText.setText(city.name);
            holder.countryText.setText(city.country);
            holder.timezoneText.setText(city.timezone);

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    listener.onCellClick(holder.getAdapterPosition());
                    return true;
                }
            });

        }else {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onBottomButtonClick();
                }
            });
        }
    }

    private final int VIEW_TYPE_FOOTER = 2;
    private final int VIEW_TYPE_CELL = 1;

    @Override
    public int getItemViewType(int position) {
        return (position == list.size()) ? VIEW_TYPE_FOOTER : VIEW_TYPE_CELL;
    }

    @Override
    public int getItemCount() {
        return list.size()+1;
    }

    public interface CitiesAdapterInterface{
        void onCellClick(int index);
        void onBottomButtonClick();
    }

    class CityViewHolder extends RecyclerView.ViewHolder{
        public ImageView countryImage;
        public TextView nameText;

        public  TextView countryText;

        public  TextView timezoneText;

        public int viewType;

        public CityViewHolder(@NonNull View itemView, int viewType) {
            super(itemView);
            this.viewType = viewType;
            if (viewType==VIEW_TYPE_CELL)
            {
                 countryImage = itemView.findViewById(R.id.city_item_image);
                 nameText = itemView.findViewById(R.id.city_item_name);
                 countryText = itemView.findViewById(R.id.city_item_country);
                 timezoneText = itemView.findViewById(R.id.city_item_timezone);
            }

        }
    }
}
