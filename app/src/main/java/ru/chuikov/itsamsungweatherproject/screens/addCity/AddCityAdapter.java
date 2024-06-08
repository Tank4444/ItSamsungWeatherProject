package ru.chuikov.itsamsungweatherproject.screens.addCity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import ru.chuikov.itsamsungweatherproject.R;
import ru.chuikov.itsamsungweatherproject.databinding.AddCityItemBinding;
import ru.chuikov.itsamsungweatherproject.screens.cities.util.RoundedCornersTransformation;
import ru.chuikov.itsamsungweatherproject.service.dto.CityItemSearch;

public class AddCityAdapter extends RecyclerView.Adapter<AddCityAdapter.AddCityViewHolder> {

    private List<CityItemSearch> list;
    private OnClickListener listener;

    public AddCityAdapter(List<CityItemSearch> list, OnClickListener listener) {
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public AddCityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AddCityItemBinding binding = AddCityItemBinding.inflate(
                LayoutInflater.from(
                        parent.getContext()
                ),
                parent,
                false);
        return new AddCityViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull AddCityViewHolder holder, int position) {
        AddCityItemBinding b = holder.binding;
        CityItemSearch result = list.get(position);
        String url = "https://flagcdn.com/w160/"+result.countryCode.toLowerCase()+".png";
        Picasso.get().load(url).placeholder(R.drawable.placeholder_flag)
                .transform(new RoundedCornersTransformation(40,0, RoundedCornersTransformation.CornerType.LEFT))
                .into(b.addCityItemImage);
        b.addCityItemCountry.setText(result.country);
        b.addCityItemTimezone.setText(result.timezone);

        b.addCityItemName.setText(result.name);
        if (result.inDB)b.addCityItemActionAdd.setImageDrawable(b.getRoot().getContext().getResources().getDrawable(R.drawable.add_saved));
        else b.addCityItemActionAdd.setImageDrawable(b.getRoot().getContext().getResources().getDrawable(R.drawable.add_plus));
        b.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(holder.getAdapterPosition());
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class AddCityViewHolder extends RecyclerView.ViewHolder{
        public AddCityItemBinding binding;
        public AddCityViewHolder(AddCityItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
    public interface OnClickListener{
        void onClick(int position);
    }
}
