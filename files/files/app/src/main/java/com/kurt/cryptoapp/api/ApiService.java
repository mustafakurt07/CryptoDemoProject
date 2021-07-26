package com.kurt.cryptoapp.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiService {
    private static Retrofit retrofit = null;
    private static final String API_BASE_URL = "http://www.aslinda.net/coin/";

    public static Retrofit getInstanceForApi() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }


}
