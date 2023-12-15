package com.example.biblioteca;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class clienteAPI {
    private final static String URL = "http://192.168.215.103:3000";

    public static Retrofit getClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }

}
