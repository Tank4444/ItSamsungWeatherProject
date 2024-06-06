package ru.chuikov.itsamsungweatherproject.screens.cities;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ru.chuikov.itsamsungweatherproject.App;
import ru.chuikov.itsamsungweatherproject.data.enities.City;
import ru.chuikov.itsamsungweatherproject.R;
import ru.chuikov.itsamsungweatherproject.databinding.FragmentCitiesBinding;
import ru.chuikov.itsamsungweatherproject.service.WeatherService;
import ru.chuikov.itsamsungweatherproject.service.dto.CityItemSearch;

public class CitiesFragment extends Fragment{
    private FragmentCitiesBinding binding;
    private List<CityItemSearch> list;
    private CitiesAdapter adapter;

    private WeatherService service;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCitiesBinding.inflate(inflater,container,false);
        service = App.getInstance().getWeatherService();
        return binding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        list = new ArrayList<>();
        adapter = new CitiesAdapter(list, new CitiesAdapter.CitiesAdapterInterface() {
            @Override
            public void onCellClick(int index) {
                DeleteDialogFragment dialogFragment = new DeleteDialogFragment(new RemovableCity() {
                    @Override
                    public void remove(long id) {
                        Thread thread = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                service.deleteCity(id);
                                updateList();
                            }
                        });
                        thread.start();

                    }
                });
                Bundle bundle = new Bundle();
                bundle.putLong("city_id",list.get(index).id);
                bundle.putString("city_name",list.get(index).name);
                dialogFragment.setArguments(bundle);
                dialogFragment.show(getChildFragmentManager(),"Dialog");
            }

            @Override
            public void onBottomButtonClick() {
                Navigation.findNavController(binding.getRoot()).navigate(R.id.addCityFragment);
            }
        });
        binding.citiesList.setAdapter(adapter);

        updateList();



    }
    private void updateList(){
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                List<City> cities = service.getCities();
                list.clear();
                for (City city:cities) list.add(CityItemSearch.builder()
                                .id(city.id)
                                .name(city.name)
                                .countryCode(city.country_code)
                                .country(city.country)
                                .lat(city.lat)
                                .lon(city.lon)
                                .timezone(city.timezone)
                        .build());
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });

            }
        });
        t.start();
    }

}