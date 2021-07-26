package com.kurt.cryptoapp.api;

import com.kurt.cryptoapp.model.detail.DetailExample;
import com.kurt.cryptoapp.model.list.ListExample;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface AslindaApi {

    @GET("list.php")
    Call<ListExample> getAllCurrencies();

    @GET("detail.php")
    Call<DetailExample> getCurrency(@Query("cod") String symbol);


}
