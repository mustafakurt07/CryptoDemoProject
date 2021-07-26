package com.kurt.cryptoapp.viewmodel;

import android.app.Application;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.kurt.cryptoapp.api.ApiService;
import com.kurt.cryptoapp.api.AslindaApi;
import com.kurt.cryptoapp.model.detail.DetailExample;
import com.kurt.cryptoapp.model.list.CryptoModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivityViewModel extends AndroidViewModel {
    private static final String TAG = "DetailActivityViewModel";
    CryptoModel cryptoModel;

    public DetailActivityViewModel(@NonNull Application application) {
        super(application);
    }

    public void fetchDetails(String cod, TextView textViewDetails) {
        AslindaApi aslindaApi = ApiService.getInstanceForApi().create(AslindaApi.class);
        aslindaApi.getCurrency(cod).enqueue(new Callback<DetailExample>() {
            @Override
            public void onResponse(Call<DetailExample> call, Response<DetailExample> response) {
                if (!response.isSuccessful()) {
                    Log.d(TAG, "onResponse: " + response.message());
                }
                assert response.body() != null;
                if (response.body().getD() != null) {//gelen obje null değilse detayını yazdır
                    //Log.d(TAG, "onResponse: responsenulldegil");@REMOVABLE
                    textViewDetails.setText("Las: " + response.body().getD().get(0).getFields().getLas() +
                            "\nBuy: " + response.body().getD().get(0).getFields().getBuy() +
                            "\nSel: " + response.body().getD().get(0).getFields().getSel() +
                            "\nLow: " + response.body().getD().get(0).getFields().getLow() +
                            "\nHig: " + response.body().getD().get(0).getFields().getHig() +
                            "\nDdi: " + response.body().getD().get(0).getFields().getDdi() +
                            "\nPdd: " + response.body().getD().get(0).getFields().getPdd()
                    );
                } else {
                    textViewDetails.setText("This is null API object");
                }

            }

            @Override
            public void onFailure(Call<DetailExample> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getLocalizedMessage());
            }
        });
    }
}
