package ru.chuikov.itsamsungweatherproject.screens.cities;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ru.chuikov.itsamsungweatherproject.R;

class CityItem {
    public String name;
    public int count;

    public CityItem(String name, int count) {
        this.name = name;
        this.count = count;
    }
}
public class CitiesAdapter extends RecyclerView.Adapter<CitiesAdapter.CityViewHolder> {

    private List<CityItem> list;
    private CitiesAdapterInterface listener;


    public CitiesAdapter(List<CityItem> list, CitiesAdapterInterface listener) {
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
    public void onBindViewHolder(@NonNull CityViewHolder holder, @SuppressLint("RecyclerView") int position) {
        if (holder.viewType==VIEW_TYPE_CELL){
            holder.name.setText(list.get(position).name);
            holder.count.setText(list.get(position).count+"");
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onCellClick(position);
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
        public TextView name;
        public TextView count;

        public int viewType;

        public CityViewHolder(@NonNull View itemView, int viewType) {
            super(itemView);
            this.viewType = viewType;
            if (viewType==VIEW_TYPE_CELL)
            {
                name = itemView.findViewById(R.id.city_name_item);
                count = itemView.findViewById(R.id.citie_count);
            }

        }
    }
}
