package com.kurt.cryptoapp.viewmodel;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.kurt.cryptoapp.adapter.CryptoAdapter;
import com.kurt.cryptoapp.api.ApiService;
import com.kurt.cryptoapp.api.AslindaApi;
import com.kurt.cryptoapp.model.list.CryptoModel;
import com.kurt.cryptoapp.model.list.ListExample;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavoritesFragmentViewModel extends ViewModel {
    private static final String TAG = "FavoritesFragmentViewMo";
    List<CryptoModel> allCryptoModelList = new ArrayList<>();
    List<CryptoModel> favoritedCryptoModelList = new ArrayList<>();


    public void fetchCryptoModelsToRecycler(CryptoAdapter cryptoAdapter, List<String> favoriteSymbolList) {
        Log.d(TAG, "fetchCryptoModelsToRecycler: favoriliste:" + favoriteSymbolList.toString());
        AslindaApi aslindaApi = ApiService.getInstanceForApi().create(AslindaApi.class);
        aslindaApi.getAllCurrencies().enqueue(new Callback<ListExample>() {
            @Override
            public void onResponse(Call<ListExample> call, Response<ListExample> response) {
                favoritedCryptoModelList.clear();
                if (!response.isSuccessful() || response.body() == null) {//hata varsa
                    Log.d(TAG, "onResponse: Error: " + response.message());
                } else {//hata yoksa ve gelen data varsa listeye at
                    allCryptoModelList = response.body().getCryptoModelList();
                    for (String cryptoModelCod : favoriteSymbolList) {//favori listesindeki coininin güncel halini bul ve listeye ekle
                        for (CryptoModel cryptoModel : allCryptoModelList) {
                            if (cryptoModelCod.equals(cryptoModel.getCod())) {
                                favoritedCryptoModelList.add(cryptoModel);
                            }
                        }
                    }

                    cryptoAdapter.setData(favoritedCryptoModelList,"favorites");
                }
            }

            @Override
            public void onFailure(Call<ListExample> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getLocalizedMessage());
            }
        });
    }


    private void sortListByName(List<CryptoModel> cryptoModelList) {
        cryptoModelList.sort(new Comparator<CryptoModel>() {
            @Override
            public int compare(CryptoModel t1, CryptoModel t2) {
                if (t1.getCod() == null || t2.getCod() == null) {
                    return 0;
                }
                return t1.getCod().compareTo(t2.getCod());
            }
        });
    }


}
