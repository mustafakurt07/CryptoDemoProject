package com.kurt.cryptoapp.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.kurt.cryptoapp.adapter.CryptoAdapter;
import com.kurt.cryptoapp.api.ApiService;
import com.kurt.cryptoapp.api.AslindaApi;
import com.kurt.cryptoapp.model.list.CryptoModel;
import com.kurt.cryptoapp.model.list.ListExample;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragmentViewModel extends AndroidViewModel {
    private static final String TAG = "HomeFragmentViewModel";

    List<CryptoModel> cryptoModelList = new ArrayList<>();

    public HomeFragmentViewModel(@NonNull Application application) {
        super(application);
    }

    public void fetchCryptoModelsToRecycler(CryptoAdapter cryptoAdapter) {
        AslindaApi aslindaApi = ApiService.getInstanceForApi().create(AslindaApi.class);
        aslindaApi.getAllCurrencies().enqueue(new Callback<ListExample>() {
            @Override
            public void onResponse(Call<ListExample> call, Response<ListExample> response) {
                if (!response.isSuccessful()) {
                    Log.d(TAG, "onResponse: " + response.message());
                }
                cryptoModelList = response.body().getCryptoModelList();
                Log.d(TAG, "onResponse: size:" + response.body().getCryptoModelList().size());
                for (CryptoModel cryptoModel : response.body().getCryptoModelList()) {
                    Log.d(TAG, "onResponse: coin:" + cryptoModel.toString());
                }
                
                cryptoAdapter.setData(cryptoModelList,"home");
            }

            @Override
            public void onFailure(Call<ListExample> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getLocalizedMessage());
            }
        });
    }

}
