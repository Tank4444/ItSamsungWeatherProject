package ru.chuikov.itsamsungweatherproject.screens.addCity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Types;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import ru.chuikov.itsamsungweatherproject.App;
import ru.chuikov.itsamsungweatherproject.R;
import ru.chuikov.itsamsungweatherproject.api.responce.CityListResponse;
import ru.chuikov.itsamsungweatherproject.databinding.FragmentAddCityBinding;
import ru.chuikov.itsamsungweatherproject.service.WeatherService;

public class AddCityFragment extends Fragment {

    private FragmentAddCityBinding binding;

    private WeatherService service;
    private List<CityListResponse.Result> list;

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
        binding.addCityTextEdit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    Toast.makeText(getContext(), v.getText(), Toast.LENGTH_LONG).show();
                    service.searchCity(v.getText().toString().trim(),
                            Locale.getDefault().getLanguage(),
                            new Callback() {
                                @Override
                                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                                    Toast.makeText(getContext(), "Error", Toast.LENGTH_LONG).show();
                                }

                                @Override
                                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                    Log.i("HTTP","HTTP is OK with code "+response.code());
                                    if (!response.isSuccessful()){
                                        throw new IOException("Unexpected code " + response);
                                    }else {
                                        JsonAdapter<CityListResponse> responseJsonAdapter = service.getMoshi().adapter(CityListResponse.class);
                                        CityListResponse resp = responseJsonAdapter.fromJson(response.body().string());


                                    }
                                }
                            }
                    );
                    return true;
                }
                return false;
            }
        });


    }
}