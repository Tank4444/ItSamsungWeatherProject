package ru.chuikov.itsamsungweatherproject.screens.addCity;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ru.chuikov.itsamsungweatherproject.api.responce.CityListResponse;

public class AddCityAdapter extends RecyclerView.Adapter<AddCityAdapter.AddCityViewHolder> {

    private List<CityListResponse.Result> list;

    public AddCityAdapter(List<CityListResponse.Result> list, View.OnClickListener listener) {
        this.list = list;
    }

    @NonNull
    @Override
    public AddCityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull AddCityViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class AddCityViewHolder extends RecyclerView.ViewHolder{

        public AddCityViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
