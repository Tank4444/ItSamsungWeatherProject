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

public class CitiesFragment extends Fragment {
    private FragmentCitiesBinding binding;
    private List<CityItem> list;
    private CitiesAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCitiesBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        list = new ArrayList<>();
        adapter = new CitiesAdapter(list, new CitiesAdapter.CitiesAdapterInterface() {
            @Override
            public void onCellClick(int index) {

            }

            @Override
            public void onBottomButtonClick() {
                Navigation.findNavController(binding.getRoot()).navigate(R.id.addCityFragment);
            }
        });
        binding.citiesList.setAdapter(adapter);

        updateList();

        binding.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {
//                        Log.i("ADD_DB","First");
//                        App.getInstance().getDatabase().cityDao().insert(new City("asd",1.2,3.4,"RU"));
//                        Log.i("ADD_DB","SEcond");
//                        App.getInstance().getDatabase().cityDao().insert(new City("dsf",1.2,3.4,"RU"));
//                        Log.i("ADD_DB","Third");
//                        App.getInstance().getDatabase().cityDao().insert(new City("asgdfgfd",1.2,3.4,"RU"));
//                        updateList();
                    }
                });
                t.start();
            }
        });



    }
    private void updateList(){
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
//                list.clear();
//                List<City> cities = App.getInstance().getDatabase().cityDao().getAll();
//                for (City c: cities){
//                    list.add(new CityItem(c.name,1));
//                    getActivity().runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            Log.i("ADD_DB","Update");
//                            adapter.notifyDataSetChanged();
//                        }
//                    });
//                }
            }
        });
        t.start();
    }
}