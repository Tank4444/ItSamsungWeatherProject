package ru.chuikov.itsamsungweatherproject.screens.addCity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import ru.chuikov.itsamsungweatherproject.App;
import ru.chuikov.itsamsungweatherproject.api.responce.CityListResponse;
import ru.chuikov.itsamsungweatherproject.databinding.FragmentAddCityBinding;
import ru.chuikov.itsamsungweatherproject.service.WeatherService;
import ru.chuikov.itsamsungweatherproject.service.dto.CityItemSearch;

public class AddCityFragment extends Fragment {

    private FragmentAddCityBinding binding;

    private WeatherService service;
    private List<CityItemSearch> list;
    private AddCityAdapter adapter;

    private Thread searchThread = new Thread();

    private AddCityAdapter.OnClickListener onClickListener = new AddCityAdapter.OnClickListener() {
        @Override
        public void onClick(int position) {
        //TODO
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAddCityBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        service = App.getInstance().getWeatherService();
        list = new ArrayList<>();

        adapter = new AddCityAdapter(list, onClickListener);
        binding.addCityList.setAdapter(adapter);
        binding.addCityTextEdit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    if (!searchThread.isAlive()) {
                        runSearchThread(v.getText().toString().trim(), Locale.getDefault().getLanguage());
                    } else {
                        Toast.makeText(getContext(), "Request already send", Toast.LENGTH_SHORT).show();
                    }

                    return true;
                }
                return false;
            }
        });


    }

    private void runSearchThread(String name, String local) {
        searchThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    List<CityItemSearch> searches = service.searchCity(name,local);
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            list.clear();
                            list = searches;
                            adapter = new AddCityAdapter(list, onClickListener);
                            binding.addCityList.setAdapter(adapter);
                        }
                    });
                } catch (IOException e) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            sendToast(e.getLocalizedMessage());
                            list.clear();
                            adapter.notifyDataSetChanged();
                        }
                    });

                }



            }
        });
        searchThread.start();
    }

    private void sendToast(String msg) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getContext(), msg, Toast.LENGTH_LONG).show();
            }
        });
    }


}