package ru.chuikov.itsamsungweatherproject.screens.cities;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class DeleteDialogFragment extends DialogFragment {
    private RemovableCity removableCity;

    public DeleteDialogFragment(RemovableCity removableCity) {
        this.removableCity = removableCity;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final long id = getArguments().getLong("city_id");
        final String name = getArguments().getString("city_name");
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        return builder
                .setTitle("Dialog")
                .setMessage("Remove "+name+"?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        removableCity.remove(id);
                    }
                })
                .setNegativeButton("No",null)
                .create();
    }
}
